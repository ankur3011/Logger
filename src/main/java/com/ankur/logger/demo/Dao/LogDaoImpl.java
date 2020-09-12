package com.ankur.logger.demo.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.ankur.logger.demo.Models.Log;
import com.ankur.logger.demo.Models.LogMapper;
import com.ankur.logger.demo.Models.ProductMapper;
import com.ankur.logger.demo.Models.ProductModel;
import com.ankur.logger.demo.Utils.Constants;

@Repository
public class LogDaoImpl implements LogDao {

	NamedParameterJdbcTemplate template;
	private static Map<String , ProductModel> productDetailsCache;

	public LogDaoImpl(NamedParameterJdbcTemplate template) {
		this.template = template;
		productDetailsCache = new HashMap<>();
	}

	@Override
	public ProductModel getProductByKey(String key) {
		if(productDetailsCache.containsKey(key)){
			return productDetailsCache.get(key);
		}
		String sql = "select * from logger.product where key = '" + key + "'";
		ProductModel product = null;
		try {
			product = template.query(sql, new ProductMapper()).get(0);
		}catch(Exception e){
			e.fillInStackTrace();
		}
		productDetailsCache.put(key, product);
		return product;
	}

	@Override
	public void saveLog(Log log, int productId) {
		String sql = "insert into logger.log(type, message, time, product_id) values (:type, :message, :time, :product_id)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue(Constants.TYEP, log.getType().toString())
			.addValue(Constants.MESSAGE, log.getMessage())
			.addValue(Constants.TIME, log.getTime())
			.addValue(Constants.PRODUCT_ID, productId);
		template.update(sql, param, keyHolder);
	}

	@Override
	public List<Log> getLogsByProductId(int pid) {
		String sql = "select * from logger.log where product_id = " + pid;
		List<Log> logs = template.query(sql, new LogMapper());
		return logs;
	}

	@Override
	public List<String> getAllProducts(){
		String sql = "select name from logger.product";
		List<String> list = null;
		try {
			list = template.query(sql, new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet resultSet, int i) throws SQLException {
					return resultSet.getString("name");
				}
			});
		}catch(Exception e){
			e.fillInStackTrace();
		}
		return list;
	}
}

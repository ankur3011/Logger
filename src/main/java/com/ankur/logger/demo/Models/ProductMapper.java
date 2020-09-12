package com.ankur.logger.demo.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ankur.logger.demo.Utils.Constants;

public class ProductMapper implements RowMapper<ProductModel> {

	@Override
	public ProductModel mapRow(ResultSet resultSet, int arg1) throws SQLException {
		ProductModel product = new ProductModel();
		product.setId(resultSet.getInt(Constants.ID));
		product.setName(resultSet.getString(Constants.NAME));
		product.setKey(resultSet.getString(Constants.KEY));
		return product;
	}
}

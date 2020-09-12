package com.ankur.logger.demo.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ankur.logger.demo.Utils.Constants;

public class LogMapper implements RowMapper<Log> {
	@Override
	public Log mapRow(ResultSet resultSet, int i) throws SQLException {
		Log log = new Log();
		log.setType(LogTypeEnum.DEBUG.valueOf(resultSet.getString(Constants.TYEP)));
		log.setMessage(resultSet.getString(Constants.MESSAGE));
		log.setTime(resultSet.getLong(Constants.TIME));
		log.setProductId(resultSet.getInt(Constants.PRODUCT_ID));
		return log;
	}
}

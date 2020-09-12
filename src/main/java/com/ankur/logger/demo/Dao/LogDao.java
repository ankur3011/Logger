package com.ankur.logger.demo.Dao;

import java.util.List;

import com.ankur.logger.demo.Models.Log;
import com.ankur.logger.demo.Models.ProductModel;

public interface LogDao {
	ProductModel getProductByKey(String key);
	void saveLog(Log log, int productId);
	List<Log> getLogsByProductId(int pid);
	List<String> getAllProducts();
}

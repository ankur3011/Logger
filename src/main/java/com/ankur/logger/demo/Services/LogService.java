package com.ankur.logger.demo.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ankur.logger.demo.Dao.LogDaoImpl;
import com.ankur.logger.demo.Models.Log;
import com.ankur.logger.demo.Models.ProductModel;
import com.ankur.logger.demo.Models.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LogService {
	private static final String PRODUCT_NOT_PRESENT = "Product not present with given key";
	private static final String LOGS_ADDED_SUCCESS = "Logs saved successfully";
	private static final String ERROR_WHILE_SAVING_LOG = "Error while saving log";
	private static final int STATUS_SUCCESS = 200;
	private static final int STATUS_INTERNAL_ERROR = 500;

	private LogDaoImpl logDaoImpl;
	private ObjectMapper objectMapper;

	public LogService(LogDaoImpl logDaoImpl, ObjectMapper objectMapper){
		this.logDaoImpl = logDaoImpl;
		this.objectMapper = objectMapper;
	}

	public Response persistLog(Log log) {
		Response response = null;
		ProductModel productModel = logDaoImpl.getProductByKey(log.getProductKey());
		if(productModel == null){
			return new Response(PRODUCT_NOT_PRESENT, STATUS_SUCCESS);
		}
		try {
			logDaoImpl.saveLog(log, productModel.getId());
			response = new Response(LOGS_ADDED_SUCCESS, STATUS_SUCCESS);
		}catch(Exception e){
			e.fillInStackTrace();
			response = new Response(ERROR_WHILE_SAVING_LOG, STATUS_INTERNAL_ERROR);
		}
		return response;
	}

	public Response getLogsByProductId(int pid, String key) throws JsonProcessingException {
		ProductModel productModel = logDaoImpl.getProductByKey(key);
		if(productModel == null){
			return new Response(PRODUCT_NOT_PRESENT, STATUS_SUCCESS);
		}
		List<Log> logs = null;
		try {
			logs = logDaoImpl.getLogsByProductId(pid);
		}catch(Exception e){
			e.fillInStackTrace();
		}
		Response response = new Response(objectMapper.writeValueAsString(logs), STATUS_SUCCESS);
		return response;
	}

	public Response getProductList(String key) throws JsonProcessingException {
		ProductModel productModel = logDaoImpl.getProductByKey(key);
		if(productModel == null){
			return new Response(PRODUCT_NOT_PRESENT, STATUS_SUCCESS);
		}
		List<String> productList = logDaoImpl.getAllProducts();
		return new Response(objectMapper.writeValueAsString(productList), STATUS_SUCCESS);
	}
}

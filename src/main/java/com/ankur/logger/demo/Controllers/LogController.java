package com.ankur.logger.demo.Controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ankur.logger.demo.Models.Log;
import com.ankur.logger.demo.Models.Response;
import com.ankur.logger.demo.Services.LogService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/log")
public class LogController {

	private LogService logService;

	public LogController(LogService logService){
		this.logService = logService;
	}

	@RequestMapping(value = "/push", method = RequestMethod.POST)
	public Response pushLog(@RequestBody Log log){
		Response response = logService.persistLog(log);
		return response;
	}

	@RequestMapping(value = "/get/{pid}", method = RequestMethod.GET)
	public Response getByProduct(@PathVariable int pid, @RequestBody String key) throws JsonProcessingException {
		Response response = logService.getLogsByProductId(pid, key);
		return response;
	}

	@RequestMapping(value = "/list-products", method = RequestMethod.GET)
	public Response searchKeyword(@RequestBody String key) throws JsonProcessingException {
		Response response = logService.getProductList(key);
		return response;
	}
}

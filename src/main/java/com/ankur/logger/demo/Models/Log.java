package com.ankur.logger.demo.Models;

import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonInclude;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Log {
	private LogTypeEnum type;
	private String message;
	private long time;
	private String productKey;
	private int productId;

	public LogTypeEnum getType() {
		return type;
	}

	public void setType(LogTypeEnum type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
}

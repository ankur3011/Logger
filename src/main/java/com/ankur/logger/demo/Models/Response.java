package com.ankur.logger.demo.Models;

import java.time.LocalDateTime;

public class Response {
	private String body;
	private int status;
	private LocalDateTime timestamp;

	public Response(String body, int status) {
		this.body = body;
		this.status = status;
		this.timestamp = LocalDateTime.now();
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}

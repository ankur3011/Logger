package com.ankur.logger.demo.Models;

public class ProductModel {
	private int id;
	private String name;
	private String key;

	public ProductModel(){}

	public ProductModel(int id, String name, String key) {
		this.id = id;
		this.name = name;
		this.key = key;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}

package com.hua.model;

public class User {
	private String name;
	private String password;
	private String signature;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String name, String password, String signature) {
		super();
		this.name = name;
		this.password = password;
		this.signature = signature;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}

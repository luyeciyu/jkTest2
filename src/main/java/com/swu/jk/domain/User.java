package com.swu.jk.domain;

public class User {
	private Integer id;
	private String name;
	private String password;
	
	private String password2;
	
	
	public User() {
	}
	
	
	public User(Integer id, String name, String password, String password2) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.password2 = password2;
	}
	public User(String name, String password, String password2) {
		this.name = name;
		this.password = password;
		this.password2 = password2;
	}
	public User(String name, String password) {
		this.name = name;
		this.password = password;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", password2=" + password2 + "]";
	}
	
	
	
}

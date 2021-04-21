package com.gb.model;

public class Researcher {
	
	private int id;
	private String rname;
	private String project;
	private float price;
	private int phone;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return rname;
	}

	public void setName(String rname) {
		this.rname = rname;
	}

	public String getProjectType() {
		return project;
	}

	public void setProjectType(String project) {
		this.project = project;
	}

	public float getPayment() {
		return price;
	}

	public void setPayment(float price) {
		this.price = price;
	}

	public int getContact() {
		return phone;
	}

	public void setContact(int phone) {
		this.phone = phone;
	}

	
	
	public Researcher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Researcher(int id, String rname, String project, float price, int phone) {
		super();
		this.id = id;
		this.rname = rname;
		this.project = project;
		this.price = price;
		this.phone = phone;
	}

}

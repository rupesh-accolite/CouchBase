package com.example.couchBase;

public class CouchBasePerson {
	private String name;
	private String age;
	private String company;
	private String companyId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public CouchBasePerson(String name, String age, String company, String companyId) {
		super();
		this.name = name;
		this.age = age;
		this.company = company;
		this.companyId = companyId;
	}
	
	
}
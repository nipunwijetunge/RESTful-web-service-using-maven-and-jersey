// Maven based jersey RESTful web service for package delivery service

// @author Nipun Wijetunge
// @version 1.1
// @since 14/07/2021

package com.nipun.abxPackageDeliveryService;

import java.io.Serializable;

public class Employee extends Person implements Serializable{
	private String age;
	private String gender;

	public Employee(String name, String id) {
		super(name, id);
	}

	public Employee(String name, String id, String address, String phone, String age, String gender) {
		super(name, id, address, phone);
		this.age = age;
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}

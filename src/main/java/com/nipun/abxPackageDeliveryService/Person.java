// Maven based jersey RESTful web service for package delivery service

// @author Nipun Wijetunge
// @version 1.1
// @since 14/07/2021

package com.nipun.abxPackageDeliveryService;

import java.io.Serializable;

public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7299835737757570097L;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String idType;
	private String id;
	
	public Person() {
		super();
	}
	
	public Person(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

	public Person(String name, String id, String address, String phone) {
		this.name = name;
		this.id = id;
		this.address = address;
		this.phone = phone;
	}
	
	public Person(String name, String address, String phone, String email, String idType, String id) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.idType = idType;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person{" +
				 "name='" + name + '\'' +
				 ", address='" + address + '\''
				 + ", phone='" + phone + '\'' 
				 + ", email=" + email + ", "
				 + "idType=" +idType + ","
				 + "id=" + id + ","
				 + '}';
	}
}

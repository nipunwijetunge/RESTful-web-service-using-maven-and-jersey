// Maven based jersey RESTful web service for package delivery service

// @author Nipun Wijetunge
// @version 1.1
// @since 14/07/2021

package com.nipun.abxPackageDeliveryService;

import java.io.Serializable;

public class Employee extends Person implements Serializable{

	public Employee(String name, String id) {
		super(name, id);
	}
	
}

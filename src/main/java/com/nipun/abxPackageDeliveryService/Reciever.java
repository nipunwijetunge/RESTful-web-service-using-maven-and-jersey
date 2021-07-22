// Maven based jersey RESTful web service for package delivery service

// @author Nipun Wijetunge
// @version 1.1
// @since 14/07/2021

package com.nipun.abxPackageDeliveryService;

import java.io.Serializable;

public class Reciever extends Person implements Serializable{

	public Reciever(String name, String address, String phone, String email, String idType, String id) {
		super(name, address, phone, email, idType, id);
	}
	
}

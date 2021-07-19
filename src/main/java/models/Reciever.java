package models;

import java.io.Serializable;

public class Reciever extends Person implements Serializable{

	public Reciever(String name, String address, String phone, String email, String idType, String id) {
		super(name, address, phone, email, idType, id);
	}
	
}

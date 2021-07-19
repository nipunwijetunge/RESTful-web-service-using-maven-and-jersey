package models;

import java.io.Serializable;

public class Employee extends Person implements Serializable{

	public Employee(String name, String id) {
		super(name, id);
	}
	
}

// Maven based jersey RESTful web service for package delivery service

// @author Nipun Wijetunge
// @version 1.1
// @since 14/07/2021

package com.nipun.abxPackageDeliveryService;

import java.io.File;
import java.util.Scanner;

public class ConsoleApplication {
	static Registrar registrar = Registrar.getInstance();
	
	final static Scanner USER_INPUT = new Scanner(System.in);
	
	static File packagesFile = new File("src"+File.separator+"main"+File.separator+"java"+File.separator+
			"textFiles"+File.separator+"packagesFile.txt");
	static File employeesFile = new File("src"+File.separator+"main"+File.separator+"java"+File.separator+
			"textFiles"+File.separator+"employeesFile.txt");
	
	public static void main(String[] args) {
		Registrar.loadEmployees(employeesFile);
//		Registrar.loadPackageData(packagesFile);
//		Registrar.loadStoreData(packagesFile);
		System.out.println(registrar.getEmployeesList());
		System.out.println(registrar.getPackagesList());
	
//		while (true) {
//			int input = USER_INPUT.nextInt();
//			if (input == 1){
//				registerPackage();
//			} else if (input == 2){
//				storePackage();
//			} else if (input == 3) {
//				assignPackage();
//			}
//		}
	}
	
	public static void registerPackage() {
		System.out.print("Package type: ");
		String packageType = USER_INPUT.next();
		
		System.out.print("Package weight(KG): ");
		double packageWeight = USER_INPUT.nextDouble();
		
		System.out.print("Bearer name: ");
		String bearerName = USER_INPUT.next();
		System.out.print("Bearer address: ");
		String bearerAddress = USER_INPUT.next();
		System.out.print("Bearer phone No: ");
		String bearerPhone = USER_INPUT.next();
		System.out.print("Bearer email: ");
		String bearerEmail = USER_INPUT.next();
		System.out.print("Bearer ID Type: ");
		String bearerIdType = USER_INPUT.next();
		System.out.print("Bearer ID: ");
		String bearerId = USER_INPUT.next();
		Bearer bearer = new Bearer(bearerName, bearerAddress, bearerPhone, bearerEmail, bearerIdType, bearerId);
		
		System.out.print("Reciever name: ");
		String recieverName = USER_INPUT.next();
		System.out.print("Reciever address: ");
		String recieverAddress = USER_INPUT.next();
		System.out.print("Reciever phone No: ");
		String recieverPhone = USER_INPUT.next();
		System.out.print("Reciever email: ");
		String recieverEmail = USER_INPUT.next();
		System.out.print("Reciever ID Type: ");
		String recieverIdType = USER_INPUT.next();
		System.out.print("Reciever ID: ");
		String recieverId = USER_INPUT.next();
		Person reciever = new Reciever(recieverName, recieverAddress, recieverPhone, recieverEmail, recieverIdType, recieverId);
		
		System.out.print("Delivery Type: ");
		String deliveryType = USER_INPUT.next();
		System.out.print("Delivery Date: ");
		String deliveryDate = USER_INPUT.next();
		
		Package pckg = new Package(packageType, packageWeight, bearer, reciever, deliveryType,deliveryDate);
		registrar.registerPackage(pckg);
		for (Package p : registrar.getPackagesList()) {
			System.out.println(p);
		}
	}

//	public static void storePackage() {
//		System.out.print("Package registration no. : ");
//		String regNo = USER_INPUT.next();
//		System.out.print("store no. : ");
//		int storeId = USER_INPUT.nextInt();
//		System.out.print("cupboard no. : ");
//		int cupboardId = USER_INPUT.nextInt();
//		System.out.println("Stored officer ID: ");
//		String storeOfficer = USER_INPUT.next();
//
//		registrar.storePackage(regNo, storeId, cupboardId, storeOfficer);
//		for (Package p : registrar.getPackagesList()) {
//			System.out.println(p);
//		}
//	}
	
//	public static void assignPackage() {
//		System.out.print("Package Registration no. : ");
//		String regNo = USER_INPUT.next();
//		System.out.print("assigner ID: ");
//		String assignerId = USER_INPUT.next();
//		System.out.print("assignee ID: ");
//		String assigneeId = USER_INPUT.next();
//		
//		registrar.assignPackage(regNo, assignerId, assigneeId);
//		for (Package p : registrar.getPackagesList()) {
//			System.out.println(p);
//		}
//	}
}

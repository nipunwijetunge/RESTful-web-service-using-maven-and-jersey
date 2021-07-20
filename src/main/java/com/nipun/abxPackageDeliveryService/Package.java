package com.nipun.abxPackageDeliveryService;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

public class Package implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4510592690257068564L;
	private int packageId;
	private String packageIdString;
	private String packageRegistrationNo;
	private String dateRegistered;
	
	private String packageType;
	private double packageWeight;
	
	private Person bearer;
	private Person reciever;
	
	private String deliveryType;
	private String deliveryDate;
	
	private Employee storedOfficer;
	private int storeId;
	private int cupboardId;
	private String dateStored;
	
	private Employee assigner;
	private Employee assignee;
	private String assignmentdate;
	
	private String packageFlag;
	
	
	
	static enum Categories{
		EL(0),
		ST(0),
		FOOD(0),
		CLO(0),
		OTHER(0);
		
		int id;
		
		Categories (int id){
			this.id = id;
		}
	}
	
	public Package() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat decimalFormatter = new DecimalFormat("00000");
		for (Categories category : Categories.values()) {
			if (category.toString().equalsIgnoreCase(packageType)) {
				this.packageType = category.toString();
				category.id++;
				this.packageId = category.id;
				packageIdString = decimalFormatter.format(category.id);
				break;
			}
		}
		
		Date today = new Date();
		this.dateRegistered = formatter.format(today);
		
		this.packageFlag = "registered";
		
		packageRegistrationNo = this.dateRegistered.substring(6)+"/"+this.packageType+"/"+packageIdString;
	}

	public Package(String packageType, double packageWeight, Person bearer, Person reciever,
			String deliveryType, String deliveryDate) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat decimalFormatter = new DecimalFormat("00000");
		for (Categories category : Categories.values()) {
			if (category.toString().equalsIgnoreCase(packageType)) {
				this.packageType = packageType;
				category.id++;
				this.packageId = category.id;
				packageIdString = decimalFormatter.format(category.id);
				break;
			}
		}
		//packageId++;
		
		Date today = new Date();
		this.dateRegistered = formatter.format(today);
		
		//this.packageType = packageType;
		this.packageWeight = packageWeight;
		this.bearer = bearer;
		this.reciever = reciever;
		this.deliveryType = deliveryType;
		this.deliveryDate = deliveryDate;
		
		this.packageFlag = "registered";
		
		packageRegistrationNo = this.dateRegistered.substring(6)+"/"+this.packageType+"/"+packageIdString;
	}
	
	
	public String getPackageIdString() {
		return packageIdString;
	}

	public void setPackageIdString(String packageIdString) {
		this.packageIdString = packageIdString;
	}

	public String getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(String dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public double getPackageWeight() {
		return packageWeight;
	}

	public void setPackageWeight(double packageWeight) {
		this.packageWeight = packageWeight;
	}

	public Person getBearer() {
		return bearer;
	}

	public void setBearer(Person bearer) {
		this.bearer = bearer;
	}

	public Person getReciever() {
		return reciever;
	}

	public void setReciever(Person reciever) {
		this.reciever = reciever;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getAssignmentdate() {
		return assignmentdate;
	}

	public void setAssignmentdate(String assignmentdate) {
		this.assignmentdate = assignmentdate;
	}

	public String getPackageFlag() {
		return packageFlag;
	}
	
	public void setPackageFlag(String packageFlag) {
		this.packageFlag = packageFlag;
	}
	
	public String getDateStored() {
		return dateStored;
	}

	public void setDateStored(String dateStored) {
		this.dateStored = dateStored;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public String getPackageRegistrationNo() {
		return packageRegistrationNo;
	}

	public void setPackageRegistrationNo(String packageRegistrationNo) {
		this.packageRegistrationNo = packageRegistrationNo;
	}

	public Person getStoredOfficer() {
		return storedOfficer;
	}

	public void setStoredOfficer(Employee storedOfficer) {
		this.storedOfficer = storedOfficer;
	}

	public Person getAssigner() {
		return assigner;
	}

	public void setAssigner(Employee assigner) {
		this.assigner = assigner;
	}

	public Person getAssignee() {
		return assignee;
	}

	public void setAssignee(Employee assignee) {
		this.assignee = assignee;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getCupboardId() {
		return cupboardId;
	}

	public void setCupboardId(int cupboardId) {
		this.cupboardId = cupboardId;
	}
	
	

	 @Override
	public boolean equals(Object obj) {
		if (this == obj ) return true;
		if ((obj == null) || getClass() != obj.getClass()) return false;
		if (!super.equals(obj)) return false;
		return super.equals(obj);
	}

	@Override public String toString() { return "Package{" +
			 "packageRegistrationNo='" + packageRegistrationNo + '\'' +
			 ", dateRegistered='" + dateRegistered + '\''
			 + ", packageType='" + packageType + '\'' 
			 + ", packageWeight=" + packageWeight + ", "
			 + "storeId=" +storeId + ","
			 + "cupboardId=" + cupboardId + ","
			 + " dateStored='" + dateStored + '\''
			 + ", assignmentdate='" + assignmentdate + '\''
			 + ", packageFlag='" + packageFlag + '\'' + '}'; }
	 
}

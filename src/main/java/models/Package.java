package models;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Package implements Serializable{
	private static int packageId = 0;
	private String packageIdString;
	private String packageRegistrationNo;
	private String dateRegistered;
	
	private String packageType;
	private double packageWeight;
	
	private Person bearer;
	private Person reciever;
	
	private String deliveryType;
	private String deliveryDate;
	
	private Person storedOfficer;
	private int storeId;
	private int cupboardId;
	private String dateStored;
	
	private Person assigner;
	private Person assignee;
	private String assignmentdate;
	
	private String packageFlag;
	
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private DecimalFormat decimalFormatter = new DecimalFormat("00000");
	
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
	
	public Package(String packageType, double packageWeight, Person bearer, Person reciever,
			String deliveryType, String deliveryDate) {
		
		for (Categories category : Categories.values()) {
			if (category.toString().equalsIgnoreCase(packageType)) {
				this.packageType = packageType;
				category.id++;
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

	public static int getPackageId() {
		return packageId;
	}

	public static void setPackageId(int packageId) {
		Package.packageId = packageId;
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

	public void setStoredOfficer(Person storedOfficer) {
		this.storedOfficer = storedOfficer;
	}

	public Person getAssigner() {
		return assigner;
	}

	public void setAssigner(Person assigner) {
		this.assigner = assigner;
	}

	public Person getAssignee() {
		return assignee;
	}

	public void setAssignee(Person assignee) {
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

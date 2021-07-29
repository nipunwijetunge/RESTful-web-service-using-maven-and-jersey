package dbResource;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nipun.abxPackageDeliveryService.Employee;
import com.nipun.abxPackageDeliveryService.Person;

public class PackageDB {
	private int packageId;
	private String packageIdString;
	private String packageRegistrationNo;
	private String dateRegistered;
	
	private String packageType;
	private String packageWeight;
	
	private Person bearer;
	private Person reciever;
	
	private String deliveryType;
	private String deliveryDate;
	
	private Employee storedOfficer;
	private String storeLabel;
	private String cupboardLabel;
	private String dateStored;
	
	private Employee assigner;
	private Employee assignee;
	private String assignmentdate;
	
	private String packageFlag;
	
	public static enum Categories{
		EL(0),
		ST(0),
		FOOD(0),
		CLO(0),
		MED(0),
		OTHER(0);
		
		public int id;
		
		Categories (int id){
			this.id = id;
		}
	}
	
	public PackageDB(String packageType, String packageWeight, Person bearer, Person reciever,
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

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public String getPackageIdString() {
		return packageIdString;
	}

	public void setPackageIdString(String packageIdString) {
		this.packageIdString = packageIdString;
	}

	public String getPackageRegistrationNo() {
		return packageRegistrationNo;
	}

	public void setPackageRegistrationNo(String packageRegistrationNo) {
		this.packageRegistrationNo = packageRegistrationNo;
	}

	public String getDateRegistered() {
		return dateRegistered;
	}

	public void setDateRegistered(String dateRegistered) {
		this.dateRegistered = dateRegistered;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getPackageWeight() {
		return packageWeight;
	}

	public void setPackageWeight(String packageWeight) {
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

	public Employee getStoredOfficer() {
		return storedOfficer;
	}

	public void setStoredOfficer(Employee storedOfficer) {
		this.storedOfficer = storedOfficer;
	}

	public String getStoreId() {
		return storeLabel;
	}

	public void setStoreId(String storeId) {
		this.storeLabel = storeId;
	}

	public String getCupboardId() {
		return cupboardLabel;
	}

	public void setCupboardId(String cupboardId) {
		this.cupboardLabel = cupboardId;
	}

	public String getDateStored() {
		return dateStored;
	}

	public void setDateStored(String dateStored) {
		this.dateStored = dateStored;
	}

	public Employee getAssigner() {
		return assigner;
	}

	public void setAssigner(Employee assigner) {
		this.assigner = assigner;
	}

	public Employee getAssignee() {
		return assignee;
	}

	public void setAssignee(Employee assignee) {
		this.assignee = assignee;
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
}

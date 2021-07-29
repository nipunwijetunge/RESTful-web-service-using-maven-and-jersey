package dbResource;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nipun.abxPackageDeliveryService.Bearer;
import com.nipun.abxPackageDeliveryService.Package;
import com.nipun.abxPackageDeliveryService.Person;
import com.nipun.abxPackageDeliveryService.Registrar;

public class RegistrarDB{
	private static RegistrarDB instance = null;
	Connection con = null;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private DecimalFormat decimalFormatter = new DecimalFormat("00000");
	
	public static RegistrarDB getInstance() {
		if (instance == null) {
			synchronized (Registrar.class) {
				if (instance == null) {
					instance = new RegistrarDB();
				}
			}
		}
		return instance;
	}
	
	// load and establish the connection to database and execute the query
	public ResultSet getData(String query) throws Exception{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url = "jdbc:mysql://localhost:3306/abxpackagedeliveryservice";
		con = DriverManager.getConnection(url, "root", "");
	
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		return rs;
	}
	
	// retrieves packages from the DB
	public JsonArray getPackages() throws Exception {
		String query = "select * from package";
		ResultSet rs = getData(query);
		
		JsonArray jarr = new JsonArray();
		while(rs.next()) {
			int totalColumns = rs.getMetaData().getColumnCount();
			JsonObject jObj = new JsonObject();
			for (int i = 1; i <= totalColumns; i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				String value = rs.getString(columnName);
				jObj.addProperty(columnName, value);
			}
			jarr.add(jObj);
		}
		if (con != null) {
			con.close();
		}
		return jarr;
	}
	
	// retrieves customer ID types from the DB
	public String getCustomerIdTypes() throws Exception {
		String query = "select * from customeridtype";
		ResultSet rs = getData(query);
		
		JsonArray jarr = new JsonArray();
		while(rs.next()) {
			int totalColumns = rs.getMetaData().getColumnCount();
			JsonObject jObj = new JsonObject();
			for (int i = 1; i <= totalColumns; i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				String value = rs.getString(columnName);
				jObj.addProperty(columnName, value);
			}
			jarr.add(jObj);
		}
		if (con != null) {
			con.close();
		}
		return jarr.toString();
	}
	
	// retrieves the package types from the DB
	public String getPackageTypes(String packageType) throws Exception {
		String query = "select packageTypeId from packagetype where packageType='"+packageType+"'";
		ResultSet rs = getData(query);
		
		JsonArray jarr = new JsonArray();
		while(rs.next()) {
			int totalColumns = rs.getMetaData().getColumnCount();
			JsonObject jObj = new JsonObject();
			for (int i = 1; i <= totalColumns; i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				String value = rs.getString(columnName);
				jObj.addProperty(columnName, value);
			}
			jarr.add(jObj);
		}
		if (con != null) {
			con.close();
		}
		return jarr.toString();
	}
	
	// retrieves package weight categories from the DB
	public String getPackageWeightCategories() throws Exception {
		String query = "select * from packageweightcategory";
		ResultSet rs = getData(query);
		
		JsonArray jarr =new JsonArray();
		while(rs.next()) {
			int totalColumns = rs.getMetaData().getColumnCount();
			JsonObject jObj = new JsonObject();
			for (int i = 1; i <= totalColumns; i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				String value = rs.getString(columnName);
				jObj.addProperty(columnName, value);
			}
			jarr.add(jObj);
		}
		if (con != null) {
			con.close();
		}
		return jarr.toString();
	}
	
	// retrieves delivery types from the DB
	public String getDeliveryTypes() throws Exception {
		String query = "select * from deliverytype";
		ResultSet rs = getData(query);
		
		JsonArray jarr = new JsonArray();
		while(rs.next()) {
			int totalColumns = rs.getMetaData().getColumnCount();
			JsonObject jObj = new JsonObject();
			for (int i = 1; i <= totalColumns; i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				String value = rs.getString(columnName);
				jObj.addProperty(columnName, value);
			}
			jarr.add(jObj);
		}
		return jarr.toString();
	}
	
	// retrieves employee data from the DB
	public String loadEmployeeData() throws Exception {
		String query = "select * from employee";
		ResultSet rs = getData(query);
		
		JsonArray jarr = new JsonArray();
		while (rs.next()) {
			int totalColumns = rs.getMetaData().getColumnCount();
			JsonObject jObj = new JsonObject();
			for (int i = 1; i <= totalColumns; i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				String value = rs.getString(columnName);
				jObj.addProperty(columnName, value);
			}
			jarr.add(jObj);
		}
		if (con != null) {
			con.close();
		}
		return jarr.toString();
	}
	

//	public JsonObject getEmployeeData(String query) throws Exception {
//		ResultSet rs = getData(query);
//		JsonArray jarr = new JsonArray();
//		JsonObject jObj = new JsonObject();
//		
//		while (rs.next()) {
//			int totalColumns = rs.getMetaData().getColumnCount();
//			for (int i = 1; i <= totalColumns; i++) {
//				String columnName = rs.getMetaData().getColumnLabel(i);
//				String value = rs.getString(columnName);
//				jObj.addProperty(columnName, value);
//			}
//			//return jObj;
//		}
//		if (con != null) {
//			con.close();
//		}
//		return jObj;
//	}
	
	// retrieves bearer or receiver data from the DB
	public JsonArray getUserData(String query) throws Exception {
		ResultSet rs = getData(query);
		JsonArray jarr = new JsonArray();
		
		while (rs.next()) {
			int totalColumns = rs.getMetaData().getColumnCount();
			JsonObject jObj = new JsonObject();
			for (int i = 1; i <= totalColumns; i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				String value = rs.getString(columnName);
				jObj.addProperty(columnName, value);
			}
			jarr.add(jObj);
		}
		if (con != null) {
			con.close();
		}
		return jarr;
	}
	
	// returns package type id according to selected value to store in DB
	public String setPackageTypeData(String packageType) throws Exception {
		String query = "select packageTypeId from packagetype where packageType='"+packageType+"'";
		ResultSet rs = getData(query);

		while (rs.next()) {
			int totalColumns = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= totalColumns; i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				String value = rs.getString(columnName);
				return value;
			}
		}
		if (con != null) {
			con.close();
		}
		return null;
	}
	
	// returns weight category id to store in package table
	public String setWeightData(String weight) throws Exception {
		String query = "select weightCategoryId from packageweightcategory where weightCategory='"+weight+"'";
		ResultSet rs = getData(query);

		while (rs.next()) {
			int totalColumns = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= totalColumns; i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				String value = rs.getString(columnName);
				return value;
			}
		}
		if (con != null) {
			con.close();
		}
		return null;
	}
	
	// returns respective delivery type id to delivery type
	public String setDeliveryTypeData(String deliveryType) throws Exception {
		String query = "select deliveryTypeId from deliverytype where deliveryType='"+deliveryType+"'";
		ResultSet rs = getData(query);
		
		while (rs.next()) {
			int totalColumns = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= totalColumns; i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				String value = rs.getString(columnName);
				return value;
			}
		}
		if (con != null) {
			con.close();
		}
		return null;
	}
	
	// returns the bearer or receiver details according to the query
	public Person setUserData(Person person, String query) throws Exception {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url = "jdbc:mysql://localhost:3306/abxpackagedeliveryservice";
		con = DriverManager.getConnection(url, "root", "");
		
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, person.getId());
		pstmt.setString(2, person.getName());
		pstmt.setString(3, person.getAddress());
		pstmt.setString(4, person.getPhone());
		pstmt.setString(5, person.getEmail());
		pstmt.setString(6, person.getIdType());
		
		pstmt.executeUpdate();
		
		if (con != null) {
			con.close();
		}
		return person;
	}
	
	// returns package registration number to from m_package_registry table
	public String setPackageRegNoData(String packageRegistrationNo) throws Exception {
		String query = "select packageRegistrationNo from m_package_registry where packageRegistrationNo='"+packageRegistrationNo+"'";
		ResultSet rs = getData(query);
		
		while (rs.next()) {
			int totalColumns = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= totalColumns; i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				String value = rs.getString(columnName);
				return value;
			}
		}
		if (con != null) {
			con.close();
		}
		return null;
	}
	
	// returns respective store room id to cupboard id in cupboard table
	public int setStoreData(int cupboardId) throws Exception {
		String query = "select storeroomID from cupboard where cupboardID="+cupboardId;
		ResultSet rs = getData(query);
		
		while (rs.next()) {
			int totalColumns = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= totalColumns; i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				int value = rs.getInt(columnName);
				return value;
			}
		}
		if (con != null) {
			con.close();
		}
		return 0;
	}
	
	// returns cupboard id from cupboard table if exists
	public int setCupboardData(String cupboardLabel) throws Exception {
		String query = "select cupboardID from cupboard where cupboardLabel='"+cupboardLabel+"'";
		ResultSet rs = getData(query);
		
		while (rs.next()) {
			int totalColumns = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= totalColumns; i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				int value = rs.getInt(columnName);
				return value;
			}
		}
		if (con != null) {
			con.close();
		}
		return 0;
	}
	
	// returns employee id from employee table if exists
	public String setEmployeeData(String employeeId) throws Exception {
		String query = "select employeeID from employee where employeeID='"+employeeId+"'";
		ResultSet rs = getData(query);
		
		while (rs.next()) {
			int totalColumns = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= totalColumns; i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				String value = rs.getString(columnName);
				return value;
			}
		}
		if (con != null) {
			con.close();
		}
		return null;
	}
	
	// updates sequence number of each package type when a new package is registered
	public void updateSeqNo(String packageTypeId) throws Exception {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url = "jdbc:mysql://localhost:3306/abxpackagedeliveryservice";
		con = DriverManager.getConnection(url, "root", "");
		
		String selectQuery = "select seqNo from m_package_sequence where packageTypeId='"+packageTypeId+"'";
		ResultSet rs = getData(selectQuery);
		
		JsonObject jObj = new JsonObject();
		while (rs.next()) {
			int totalColumns = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= totalColumns; i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				int value = rs.getInt(columnName);
				jObj.addProperty(columnName, value);
			}
		}
		
		String updateQuery = "update m_package_sequence set seqNo=? where packageTypeId='"+packageTypeId+"'";
		PreparedStatement pstmt = con.prepareStatement(updateQuery);
		
		pstmt.setInt(1, Integer.parseInt(jObj.get("seqNo").getAsString())+1);
		pstmt.executeUpdate();
		
		if (con != null) {
			con.close();
		}
	}
	
	// generates the package registration number for each newly registered package
	public String generateRegistrationNo(String packageTypeId) throws Exception {
		updateSeqNo(packageTypeId);
		String query = "select YEAR(year) as year, seqNo, packageTypeId from m_package_sequence where packageTypeId='"+packageTypeId+"'";
		ResultSet rs = getData(query);
		
		String packageRegistrationNo = "";
		while(rs.next()) {
			System.out.println("qq");
			int totalColumns = rs.getMetaData().getColumnCount();
			JsonObject jObj = new JsonObject();
			for (int i = 1; i <= totalColumns; i++) {
				String columnName = rs.getMetaData().getColumnLabel(i);
				String value = rs.getString(columnName);
				jObj.addProperty(columnName, value);
			}
			String formattedSeqNo = decimalFormatter.format(jObj.get("seqNo").getAsDouble());
			packageRegistrationNo = jObj.get("year").getAsString()+"/"+jObj.get("packageTypeId").getAsString()+"/"+formattedSeqNo;
		}
		if (con != null) {
			con.close();
		}
		return packageRegistrationNo;
	}
	
	// updates package status in m_package_registry table on package registry, storing and assignment
	public void updatePackageStatus(String status, String packageRegistrationNo) throws Exception {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url = "jdbc:mysql://localhost:3306/abxpackagedeliveryservice";
		con = DriverManager.getConnection(url, "root", "");
		
		String query = "update m_package_registry set packageStatus=? where packageRegistrationNo='"+packageRegistrationNo+"'";
		PreparedStatement pstmt = con.prepareStatement(query);
		
		pstmt.setString(1, status);
		
		pstmt.executeUpdate();
	}
	
	// registers package to the system
	public void registerPackage(PackageDB pkg) throws Exception {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url = "jdbc:mysql://localhost:3306/abxpackagedeliveryservice";
		con = DriverManager.getConnection(url, "root", "");
		
		String query = "insert into m_package_registry (packageRegistrationNo,packageTypeId,packageWeightRangeId,bearerId,recieverId,deliveryTypeId,deliveryDate,packageStatus) values(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		
		String packageRegistrationNo = generateRegistrationNo(setPackageTypeData(pkg.getPackageType()));

		pstmt.setString(1, packageRegistrationNo);
		pstmt.setString(2, setPackageTypeData(pkg.getPackageType()));
		pstmt.setString(3, setWeightData(pkg.getPackageWeight()));
		
		// checks whether bearer is already exists in bearer table 
		boolean bearerExist = false;
		Person bearer;
		for (JsonElement user : getUserData("select * from bearer")) {
			JsonObject obj = user.getAsJsonObject();
			if (obj.get("bearerID").getAsString().equalsIgnoreCase(pkg.getBearer().getId())) {
				System.out.println("bearer contains!");
				bearerExist = true;
				break;
			}
		}
		if (!bearerExist) {
			bearer = setUserData(pkg.getBearer(), "insert into bearer values(?,?,?,?,?,?)");
		} else {
			bearer = pkg.getBearer();
		}
		pstmt.setString(4, bearer.getId());
		//
		
		// checks whether receiver is already exists in receiver table
		boolean recieverExist = false;
		Person reciever;
		for (JsonElement user : getUserData("select * from reciever")) {
			JsonObject obj = user.getAsJsonObject();
			if (obj.get("recieverID").getAsString().equalsIgnoreCase(pkg.getReciever().getId())) {
				System.out.println("reciever contains!");
				recieverExist = true;
				break;
			}
		}
		if (!recieverExist) {
			reciever = setUserData(pkg.getReciever(), "insert into reciever values(?,?,?,?,?,?)");
		} else  {
			reciever = pkg.getReciever();
		}
		pstmt.setString(5, reciever.getId());
		//
		
		pstmt.setString(6, setDeliveryTypeData(pkg.getDeliveryType()));
		pstmt.setNull(7, Types.VARCHAR);
		pstmt.setString(8, "Registered");
		
		pstmt.executeUpdate();
		
		if (con != null) {
			con.close();
		}
	}
	
	// stores package
	public void storePackage(PackageDB pkg) throws Exception {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url = "jdbc:mysql://localhost:3306/abxpackagedeliveryservice";
		con = DriverManager.getConnection(url, "root", "");
		
		String query = "insert into m_package_store (packageRegistrationNo,storeID,cupboardID,storedOfficerID) values(?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		
		String checkRegNoQuery = "select packageRegistrationNo from m_package_store where packageRegistrationNo='"+pkg.getPackageRegistrationNo()+"'";
		String checkPackageInRegistry = "select packageRegistrationNo from m_package_registry where packageRegistrationNo='"+pkg.getPackageRegistrationNo()+"'";
		ResultSet rs = getData(checkRegNoQuery);
		ResultSet rs1 = getData(checkPackageInRegistry);
		
		if (!rs.next()) {
			if (rs1.next()) {
				pstmt.setString(1, setPackageRegNoData(pkg.getPackageRegistrationNo()));
				pstmt.setInt(2, setStoreData(setCupboardData(pkg.getCupboardId())));
				pstmt.setInt(3, setCupboardData(pkg.getCupboardId()));
				pstmt.setString(4, setEmployeeData(pkg.getStoredOfficer().getId()));
				updatePackageStatus("Stored", setPackageRegNoData(pkg.getPackageRegistrationNo()));
				
				pstmt.executeUpdate();
			} else {
				System.out.println("Sorry! Such package has not been registered.");
			}
		} else {
			System.out.println("Already stored!");
		}
		
		if (con != null) {
			con.close();
		}
	}
	
	// assigns package to respective employees
	public void assignPackage(PackageDB pkg) throws Exception {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url = "jdbc:mysql://localhost:3306/abxpackagedeliveryservice";
		con = DriverManager.getConnection(url, "root", "");
		
		String query = "insert into m_package_assignment (packageRegistrationNo,assignerId,assigneeId) values(?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		
		String checkRegNoQuery = "select packageRegistrationNo from m_package_assignment where packageRegistrationNo='"+pkg.getPackageRegistrationNo()+"'";
		String checkRegNoQueryInStore = "select packageRegistrationNo from m_package_store where packageRegistrationNo='"+pkg.getPackageRegistrationNo()+"'";
		ResultSet rs = getData(checkRegNoQuery);
		ResultSet rs1 = getData(checkRegNoQueryInStore);
		
		if (!rs.next()) {
			if (rs1.next()) {
				pstmt.setString(1, setPackageRegNoData(pkg.getPackageRegistrationNo()));
				pstmt.setString(2, setEmployeeData(pkg.getAssigner().getId()));
				pstmt.setString(3, setEmployeeData(pkg.getAssignee().getId()));
				updatePackageStatus("Assigned", setPackageRegNoData(pkg.getPackageRegistrationNo()));
				
				pstmt.executeUpdate();
			} else {
				System.out.println("The package is not found in store.");
			}
		} else {
			System.out.println("Already assigned!");
		}
		
		if (con != null) {
			con.close();
		}
	}
}

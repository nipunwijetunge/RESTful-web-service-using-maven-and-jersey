package dbResource;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nipun.abxPackageDeliveryService.Bearer;
import com.nipun.abxPackageDeliveryService.Package;
import com.nipun.abxPackageDeliveryService.Person;
import com.nipun.abxPackageDeliveryService.Registrar;

import qrUtils.QRCodeUtils;

public class RegistrarDB implements RegistrarManagerDB{
	private static RegistrarDB instance = null;
	private static Connection con = null;
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private static DecimalFormat decimalFormatter = new DecimalFormat("00000");
	
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
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		String url = "jdbc:mysql://localhost:3306/abxpackagedeliveryservice";
		con = DriverManager.getConnection(url, "root", "");
	
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		return rs;
	}
	
	// retrieves packages from the DB
//	public JsonArray getPackages() throws Exception {
//		String query = "select * from package";
//		ResultSet rs = getData(query);
//		
//		JsonArray jarr = new JsonArray();
//		while(rs.next()) {
//			int totalColumns = rs.getMetaData().getColumnCount();
//			JsonObject jObj = new JsonObject();
//			for (int i = 1; i <= totalColumns; i++) {
//				String columnName = rs.getMetaData().getColumnLabel(i);
//				String value = rs.getString(columnName);
//				jObj.addProperty(columnName, value);
//			}
//			jarr.add(jObj);
//		}
//		if (con != null) {
//			con.close();
//		}
//		return jarr;
//	}
	
	// retrieves customer ID types from the DB
	public String getCustomerIdTypes() throws Exception {
		String query = "SELECT * FROM customeridtype";
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
		
		Response response = new Response(Response.SUCCESS, "SUCCESS", "Customer ID type data", jarr);
		return new Gson().toJson(response);
	}
	
	// retrieves the package types from the DB
	public String getPackageTypes() throws Exception {
		String query = "SELECT * FROM packagetype";
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
		
		Response response = new Response(Response.SUCCESS, "SUCCESS", "Pakage type data", jarr);
		return new Gson().toJson(response);
	}
	
	// retrieves package weight categories from the DB
	public String getPackageWeightCategories() throws Exception {
		String query = "SELECT * FROM packageweightcategory";
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

		Response response = new Response(Response.SUCCESS, "SUCCESS", "Package weight data", jarr);
		return new Gson().toJson(response);
	}
	
	// retrieves delivery types from the DB
	public String getDeliveryTypes() throws Exception {
		String query = "SELECT * FROM deliverytype";
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
		
		Response response = new Response(Response.SUCCESS, "SUCCESS", "Package delivery types data", jarr);
		return new Gson().toJson(response);
	}
	
	// retrieves store data from the DB
	public String getStoreData() throws Exception {
		String query = "SELECT * FROM store";
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
		
		Response response = new Response(Response.SUCCESS, "SUCCESS", "Store data", jarr);
		return new Gson().toJson(response);
	}
	
	// sends selected store id to server
//	public String setStoreIdToFilterCupboards(int storeId) {
//		
//	}
	
	// retrieves cupboard data from the DB
	public String getCupboardData(PackageDB pkg) throws Exception {
		String query = "SELECT cupboardID FROM cupboard WHERE storeroomID="+pkg.getStoreId();
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
		
		Response response = new Response(Response.SUCCESS, "SUCCESS", "Cupboard data", jarr);
		return new Gson().toJson(response);
	}
	
	// retrieves employee data from the DB
	public String loadEmployeeData() throws Exception {
		String query = "SELECT * FROM employee";
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
		
		Response response = new Response(Response.SUCCESS, "SUCCESS", "Employee data", jarr);
		return new Gson().toJson(response);
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
	private String setPackageTypeData(String packageType) throws Exception {
		String query = "SELECT packageTypeId FROM packagetype "
				+ "WHERE packageType='"+packageType+"'";
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
	private String setWeightData(String weight) throws Exception {
		String query = "SELECT weightCategoryId FROM packageweightcategory "
				+ "WHERE weightCategory='"+weight+"'";
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
	private String setDeliveryTypeData(String deliveryType) throws Exception {
		String query = "SELECT deliveryTypeId FROM deliverytype "
				+ "WHERE deliveryType='"+deliveryType+"'";
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
	private Person setUserData(Person person, String query) throws Exception {
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
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
	
	// returns respective customerIdTypeId to customerIdType
	private String setCustomerIdTypedata(String customerIdType) throws Exception {
		String query = "SELECT idTypeID FROM customeridtype "
				+ "WHERE idType='"+customerIdType+"'";
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
	
	// returns package registration number to from m_package_registry table
	private String setPackageRegNoData(String packageRegistrationNo) throws Exception {
		String query = "SELECT packageRegistrationNo FROM m_package_registry "
				+ "WHERE packageRegistrationNo='"+packageRegistrationNo+"'";
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
	private int setStoreData(int storeId) throws Exception {
		String query = "SELECT storeID FROM store "
				+ "WHERE storeID="+storeId;
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
	private int setCupboardData(int cupboardId) throws Exception {
		String query = "SELECT cupboardID FROM cupboard "
				+ "WHERE cupboardID="+cupboardId;
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
	private String setEmployeeData(String employeeId) throws Exception {
		String query = "SELECT employeeID FROM employee "
				+ "WHERE employeeID='"+employeeId+"'";
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
	private void updateSeqNo(String packageTypeId) throws Exception {
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		String url = "jdbc:mysql://localhost:3306/abxpackagedeliveryservice";
		con = DriverManager.getConnection(url, "root", "");
		
		String selectQuery = "SELECT seqNo FROM m_package_sequence "
				+ "WHERE packageTypeId='"+packageTypeId+"'";
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
		
		String updateQuery = "UPDATE m_package_sequence SET seqNo=? "
				+ "WHERE packageTypeId='"+packageTypeId+"'";
		PreparedStatement pstmt = con.prepareStatement(updateQuery);
		
		pstmt.setInt(1, Integer.parseInt(jObj.get("seqNo").getAsString())+1);
		pstmt.executeUpdate();
		
		if (con != null) {
			con.close();
		}
	}
	
	// generates the package registration number for each newly registered package
	private String generateRegistrationNo(String packageTypeId) throws Exception {
		updateSeqNo(packageTypeId);
		String query = "SELECT YEAR(year) AS year, seqNo, packageTypeId "
				+ "FROM m_package_sequence "
				+ "WHERE packageTypeId='"+packageTypeId+"'";
		ResultSet rs = getData(query);
		
		String packageRegistrationNo = "";
		while(rs.next()) {
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
	private void updatePackageStatus(String status, String packageRegistrationNo) throws Exception {
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		String url = "jdbc:mysql://localhost:3306/abxpackagedeliveryservice";
		con = DriverManager.getConnection(url, "root", "");
		
		String query = "UPDATE m_package_registry "
				+ "SET packageStatus=? "
				+ "WHERE packageRegistrationNo='"+packageRegistrationNo+"'";
		PreparedStatement pstmt = con.prepareStatement(query);
		
		pstmt.setString(1, status);
		
		pstmt.executeUpdate();
	}
	
	// validates customer id number
	private Response validateCustomerDetails(int customerIdTypeId, String customerId) {
		switch (customerIdTypeId) {
			case 1:
				if (customerId.matches("^[0-9]{9}[x|X|v|V]|[0-9]{12}$")) {
					return new Response(Response.SUCCESS, "SUCCESS", "NIC number is valid");
				} else {
					return new Response(Response.ERROR, "FAILED", "NIC number is invalid");
				}
				
			case 2:
				if (customerId.matches("^[N|OL|D][0-9]{7}$")) {
					return new Response(Response.SUCCESS, "SUCCESS", "Passport number is valid");
				} else {
					return new Response(Response.ERROR, "FAILED", "Passport number is invalid");
				}
				
	//		case 3:
	//			if (customerId.matches())
			default:
				return new Response(Response.ERROR, "FAILED", "Invalid ID type");
		}
	}
	
	// validates phone numbers
	private Response validatePhoneNumber(String phone) {
		if (phone.matches("^[0-9]{10}$")) {
			return new Response(Response.SUCCESS, "SUCCESS", "Phone number is valid");
		} else {
			return new Response(Response.ERROR, "FAILED", "Phone number is invalid");
		}
	}
	
	// validate email
	private Response validateEmail(String email) {
		if (email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
			return new Response(Response.SUCCESS, "SUCCESS", "Email is valid");
		} else {
			return new Response(Response.ERROR, "FAILED", "Email is invalid");
		}
	}
	
	// registers package to the system
	@Override
	public Response registerPackage(PackageDB pkg) throws Exception {
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		String url = "jdbc:mysql://localhost:3306/abxpackagedeliveryservice";
		con = DriverManager.getConnection(url, "root", "");
		
		String query = "INSERT INTO m_package_registry (packageRegistrationNo,packageTypeId,packageWeightRangeId,bearerId,recieverId,deliveryTypeId,deliveryDate,packageStatus) "
				+ "VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		
		String packageRegistrationNo = generateRegistrationNo(setPackageTypeData(pkg.getPackageType()));

		pstmt.setString(1, packageRegistrationNo);
		pstmt.setString(2, setPackageTypeData(pkg.getPackageType()));
		pstmt.setString(3, setWeightData(pkg.getPackageWeight()));
		
		// checks whether bearer is already exists in bearer table 
		
		boolean bearerIdFlag = validateCustomerDetails(Integer.parseInt(pkg.getBearer().getIdType()), pkg.getBearer().getId()).getId() == 0;
		boolean bearerPhoneFlag = validatePhoneNumber(pkg.getBearer().getPhone()).getId() == 0;
		boolean bearerEmailFlag = validateEmail(pkg.getBearer().getEmail()).getId() == 0;
		
		boolean bearerExist = false;
		Person bearer;
		
		if (!bearerIdFlag) {
			return new Response(Response.ERROR, "FAILED", "Bearer ID is invalid");
		}
		
		if (!bearerPhoneFlag) {
			return new Response(Response.ERROR, "FAILED", "Bearer phone is invalid");
		} 
		
		if (!bearerEmailFlag) {
			return new Response(Response.ERROR, "FAILED", "Bearer email is invalid");
		}
		
		if (bearerIdFlag && bearerPhoneFlag && bearerEmailFlag) {
			for (JsonElement user : getUserData("SELECT * FROM bearer")) {
				JsonObject obj = user.getAsJsonObject();
				if (obj.get("bearerID").getAsString().equalsIgnoreCase(pkg.getBearer().getId())) {
					bearerExist = true;
					break;
				}
			}
			if (!bearerExist) {
				bearer = setUserData(pkg.getBearer(), "INSERT INTO bearer "
						+ "VALUES(?,?,?,?,?,?)");
			} else {
				bearer = pkg.getBearer();
				//return new Response(Response.DATA_ALREADY_EXISTS, "DATA EXISTS", "Bearer already exists in database");
			}
			pstmt.setString(4, bearer.getId());
		}
		//
		
		// checks whether receiver is already exists in receiver table
		
		boolean receiverIdFlag = validateCustomerDetails(Integer.parseInt(pkg.getReciever().getIdType()), pkg.getReciever().getId()).getId() == 0;
		boolean receiverPhoneFlag = validatePhoneNumber(pkg.getReciever().getPhone()).getId() == 0;
		boolean receiverEmailFlag = validateEmail(pkg.getReciever().getEmail()).getId() == 0;
		
		boolean recieverExist = false;
		Person reciever;
		
		if (!receiverIdFlag) {
			return new Response(Response.ERROR, "FAILED", "Reciever ID is invalid");
		}
		
		if (!receiverPhoneFlag) {
			return new Response(Response.ERROR, "FAILED", "Reciever phone is invalid");
		}
		
		if (!receiverEmailFlag) {
			return new Response(Response.ERROR, "FAILED", "Reciever email is invalid");
		}
		
		if (receiverIdFlag && receiverPhoneFlag && receiverEmailFlag) {
			for (JsonElement user : getUserData("SELECT * FROM reciever")) {
				JsonObject obj = user.getAsJsonObject();
				if (obj.get("recieverID").getAsString().equalsIgnoreCase(pkg.getReciever().getId())) {
					recieverExist = true;
					break;
				}
			}
			if (!recieverExist) {
				reciever = setUserData(pkg.getReciever(), "INSERT INTO reciever "
						+ "VALUES(?,?,?,?,?,?)");
			} else  {
				reciever = pkg.getReciever();
				//return new Response(Response.DATA_ALREADY_EXISTS, "DATA EXISTS", "Reciever already exists in database");
			}
			pstmt.setString(5, reciever.getId());
		}
		//
		
		pstmt.setString(6, setDeliveryTypeData(pkg.getDeliveryType()));
		pstmt.setNull(7, Types.VARCHAR);
		pstmt.setString(8, "Registered");
		
		pstmt.executeUpdate();
		
		if (con != null) {
			con.close();
		}
		
		//Response response = new Response(Response.SUCCESS, "SUCCESS", "Package was successfully registered", packageRegistrationNo);
		return QRCodeUtils.createCode(packageRegistrationNo);
	}
	
	// stores package
	@Override
	public Response storePackage(PackageDB pkg) throws Exception {
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		String url = "jdbc:mysql://localhost:3306/abxpackagedeliveryservice";
		con = DriverManager.getConnection(url, "root", "");
		
		String query = "INSERT INTO m_package_store (packageRegistrationNo,storeID,cupboardID,storedOfficerID) "
				+ "VALUES(?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		
		String checkRegNoQuery = "SELECT packageRegistrationNo FROM m_package_store "
				+ "WHERE packageRegistrationNo='"+pkg.getPackageRegistrationNo()+"'";
		String checkPackageInRegistry = "SELECT packageRegistrationNo FROM m_package_registry "
				+ "WHERE packageRegistrationNo='"+pkg.getPackageRegistrationNo()+"'";
		//String checkPackageWhetherAssigned = "SELECT packageRegistrationNo FROM m_package_assignment WHERE packageRegistrationNo='"+pkg.getPackageRegistrationNo()+"'";
		ResultSet rs = getData(checkRegNoQuery);
		ResultSet rs1 = getData(checkPackageInRegistry);
		//ResultSet rs3 = getData(checkPackageWhetherAssigned);
		
		if (!rs.next()) {
			if (rs1.next()) {
				pstmt.setString(1, setPackageRegNoData(pkg.getPackageRegistrationNo()));
				pstmt.setInt(2, setStoreData(pkg.getStoreId()));
				pstmt.setInt(3, setCupboardData(pkg.getCupboardId()));
				
				boolean employeeValidation = pkg.getStoredOfficer().getId().matches("^[SDRM][0-9]{3}$");
				if (!employeeValidation) {
					return new Response(Response.ERROR, "FAILED", "Invalid employee ID");
				} else {
					pstmt.setString(4, setEmployeeData(pkg.getStoredOfficer().getId()));
				}
				updatePackageStatus("Stored", setPackageRegNoData(pkg.getPackageRegistrationNo()));
				
				pstmt.executeUpdate();
				
				if (con != null) {
					con.close();
				}
				
				return new Response(Response.SUCCESS, "SUCCESS", "Package successfully stored");
			} else {
				return new Response(Response.NO_DATA_FOUND, "FAILED", "No such package has been registered");
			}
		} else {
			return new Response(Response.DATA_ALREADY_EXISTS, "FAILED", "The package has already been stored");
		}
	}
	
	// assigns package to respective employees
	@Override
	public Response assignPackage(PackageDB pkg) throws Exception {
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		String url = "jdbc:mysql://localhost:3306/abxpackagedeliveryservice";
		con = DriverManager.getConnection(url, "root", "");
		
		String query = "INSERT INTO m_package_assignment (packageRegistrationNo,assignerId,assigneeId) "
				+ "VALUES(?,?,?)";
		String deleteFromStore = "DELETE FROM m_package_store "
				+ "WHERE packageRegistrationNo=?";
		
		PreparedStatement pstmt = con.prepareStatement(query);
		PreparedStatement pstmt1 = con.prepareStatement(deleteFromStore);
		
		String checkRegNoQuery = "SELECT packageRegistrationNo FROM m_package_assignment "
				+ "WHERE packageRegistrationNo='"+pkg.getPackageRegistrationNo()+"'";
		String checkRegNoQueryInStore = "SELECT packageRegistrationNo FROM m_package_store "
				+ "WHERE packageRegistrationNo='"+pkg.getPackageRegistrationNo()+"'";
		ResultSet rs = getData(checkRegNoQuery);
		ResultSet rs1 = getData(checkRegNoQueryInStore);
		
		if (!rs.next()) {
			if (rs1.next()) {
				pstmt.setString(1, setPackageRegNoData(pkg.getPackageRegistrationNo()));
				
				boolean employeeValidation = pkg.getAssigner().getId().matches("^[SDRM][0-9]{3}$");
				if (!employeeValidation) {
					return new Response(Response.ERROR, "FAILED", "Invalid assigner ID");
				} else {
					pstmt.setString(2, setEmployeeData(pkg.getAssigner().getId()));
				}
				
				employeeValidation = pkg.getAssignee().getId().matches("^[SDRM][0-9]{3}$");
				if (!employeeValidation) {
					return new Response(Response.ERROR, "FAILED", "Invalid assignee ID");
				} else {
					pstmt.setString(3, setEmployeeData(pkg.getAssignee().getId()));
				}
				
				updatePackageStatus("Assigned", setPackageRegNoData(pkg.getPackageRegistrationNo()));
				
				pstmt1.setString(1, setPackageRegNoData(pkg.getPackageRegistrationNo()));
				
				pstmt.executeUpdate();
				pstmt1.executeUpdate();
				
				if (con != null) {
					con.close();
				}
				
				return new Response(Response.SUCCESS, "SUCCESS", "Package successfully assigned");
			} else {
				return new Response(Response.NO_DATA_FOUND, "FAILED", "No such package found in store");
			}
		} else {
			return new Response(Response.DATA_ALREADY_EXISTS, "FAILED", "The package has already been assigned");
		}
	}
}

package com.nipun.abxPackageDeliveryService;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import dbResource.PackageDB;
import dbResource.RegistrarDB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/dbService")
public class DBService {
	static RegistrarDB registrar = RegistrarDB.getInstance();
	
	// returns packages from DB
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path("/getPackages")
//	public String getPackages() {
//		System.out.println("getPackages()");
//		try {
//			return registrar.getPackages();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	} 
	
	// returns customer id types
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getCustomerIdTypes")
	public String getCustomerIdTypes() {
		try {
			return registrar.getCustomerIdTypes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// returns package types
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getPackageTypes")
	public String getPackageTypes() {
		try {
			return registrar.getPackageTypes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// returns package weight categories
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getPackageWeightCategories")
	public String getPackageWeightCategories() {
		try {
			return registrar.getPackageWeightCategories();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// returns delivery types
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getDeliveryTypes")
	public String getDeliveryTypes() {
		try {
			return registrar.getDeliveryTypes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// returns employee data
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getEmployeeList")
	public String getEmployeeList() {
		try {
			return registrar.loadEmployeeData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("/setPackageWeight")
//	public String setPackageWeight(String packageWeight) {
//		System.out.println("setPackageWeight()");
//		PackageDB value = new Gson().fromJson(packageWeight, PackageDB.class);
//		try {
//			return registrar.setWeightData(value.getPackageWeight());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
//	@POST
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("/setBearer")
//	public String setBearer(String bearer) {
//		System.out.println("setPackageWeight()");
//		Person value = new Gson().fromJson(bearer, Bearer.class);
//		try {
//			return registrar.setUserData(value);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/registerPackageDB")
	public String registerPackage(String json) throws Exception {
		PackageDB pkg = new Gson().fromJson(json, PackageDB.class);
//		for (JsonElement obj : registrar.getPackages()) {
//			for (PackageDB.Categories cat : PackageDB.Categories.values()) {
//				JsonObject jObj = obj.getAsJsonObject();
//				if (jObj.get("packageType").getAsString().equals(cat.toString()) && jObj.get("packageIdCategoryWise").getAsInt() > cat.id) {
//					cat.id = jObj.get("packageIdCategoryWise").getAsInt();
//				}
//			}
//		}
//		PackageDB p = new PackageDB(pkg.getPackageType(), pkg.getPackageWeight(), pkg.getBearer(), pkg.getReciever(), pkg.getDeliveryType(), pkg.getDeliveryDate());
		try {
			return registrar.registerPackage(pkg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/storePackageDB")
	public String storePackage(String json) {
		PackageDB pkg = new Gson().fromJson(json, PackageDB.class);
		try {
			return registrar.storePackage(pkg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/assignPackageDB")
	public String assignPackage(String json) {
		PackageDB pkg = new Gson().fromJson(json, PackageDB.class);
		try {
			return registrar.assignPackage(pkg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

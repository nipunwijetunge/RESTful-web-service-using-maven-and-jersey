// Maven based jersey RESTful web service for package delivery service

// @author Nipun Wijetunge
// @version 1.1
// @since 14/07/2021

package com.nipun.abxPackageDeliveryService;

import java.util.List;

import org.apache.tomcat.jni.File;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import jakarta.json.Json;
import jakarta.servlet.Registration;
import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/service")
public class Service {
	static Registrar registrar = Registrar.getInstance();
	
	//returns a list of packages in JSON format
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getpackages")
	public List<Package> getPackages() {
		System.out.println("called");
		return registrar.getPackagesList();
	}
	
	//returns individual packages from the packages list according to registration number and package type
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getpackage")
	public Package getPackage(@QueryParam("packageType") String packageType, @QueryParam("id") String id) {
		for (Package pkg : registrar.getPackagesList()) {
			if (pkg.getPackageType().equalsIgnoreCase(packageType) && pkg.getPackageRegistrationNo().equalsIgnoreCase(id)) {
				System.out.println("package");
				return pkg;
			}
		}
		return null;
	}
	
	//creates a new package with entered details and adds it to the packages list
	//Gson is used to map JSON format data to a Package object
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/registerpackage")
	public Package addPackage(String json) {
		System.out.println("registered");
		Package pkg = new Gson().fromJson(json, Package.class);
		if (!registrar.getPackagesList().isEmpty())
			for (Package p : registrar.getPackagesList()) {
				for (Package.Categories cat : Package.Categories.values()) {
					if (p.getPackageType().equals(cat.toString()) && p.getPackageId() > cat.id) {
						cat.id = p.getPackageId();
					}
				}
			}
		Package p = new Package(pkg.getPackageType(), pkg.getPackageWeight(), pkg.getBearer(), pkg.getReciever(), pkg.getDeliveryType(), pkg.getDeliveryDate());
		
		return registrar.registerPackage(p);
	}
	
	//updates an existing package with store details and stores that package in the array
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/storepackage")
	public Package storePackage(String json) {
		System.out.println("stored");
		Package pkg = new Gson().fromJson(json, Package.class);
		System.out.println(pkg);
		return registrar.storePackage(pkg);
	}
	
	//assigns a particular package to a delivery officer when correct details are entered
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/assignpackage")
	public Package assignPackage(String json) {
		System.out.println("assigned");
		Package pkg = new Gson().fromJson(json, Package.class);
		System.out.println(pkg);
		
		return registrar.assignPackage(pkg);
	}
}

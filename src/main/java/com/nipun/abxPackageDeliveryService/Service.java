package com.nipun.abxPackageDeliveryService;

import java.util.List;

import org.apache.tomcat.jni.File;

import com.google.gson.Gson;

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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getpackages")
	public List<Package> getPackages() {
		System.out.println("called");
		return registrar.getPackagesList();
	}
	
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

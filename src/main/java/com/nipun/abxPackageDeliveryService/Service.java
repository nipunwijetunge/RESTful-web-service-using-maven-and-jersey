package com.nipun.abxPackageDeliveryService;

import java.util.List;

import org.apache.tomcat.jni.File;

import jakarta.servlet.Registration;
import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/service")
public class Service {
	Registrar registrar = new Registrar();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getpackages")
	public List<Package> getPackages() {
		System.out.println("called");
		return registrar.getPackagesList();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{packageRegistrationNo}")
	public Package getPackage(@PathParam("packageRegistrationNo") String packageRegistrationNo) {
		for (Package pkg : registrar.getPackagesList()) {
			if (pkg.getPackageRegistrationNo().equals(packageRegistrationNo))
				return pkg;
		}
		return null;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/registerpackage") 
	public Package addPackage(Package pkg) {
		System.out.println("registered");
		return registrar.registerPackage(pkg);
	}
}

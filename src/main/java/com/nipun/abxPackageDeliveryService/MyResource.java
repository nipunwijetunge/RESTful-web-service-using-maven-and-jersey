package com.nipun.abxPackageDeliveryService;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	Registrar registrar = new Registrar();
	
    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Package> getIt() {
    	System.out.println("called");
        return registrar.getPackagesList();
    }
}

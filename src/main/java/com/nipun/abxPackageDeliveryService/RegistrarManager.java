// Maven based jersey RESTful web service for package delivery service

// @author Nipun Wijetunge
// @version 1.1
// @since 14/07/2021

package com.nipun.abxPackageDeliveryService;

public interface RegistrarManager {
	Package registerPackage(Package item);
	Package storePackage(Package item);
	Package assignPackage(Package item);
}

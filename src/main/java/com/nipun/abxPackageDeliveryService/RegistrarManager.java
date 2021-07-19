package com.nipun.abxPackageDeliveryService;

public interface RegistrarManager {
	Package registerPackage(Package item);
	void storePackage(String pacjageRegistrationNo, int storeId, int cupboardId, String storeOfficerId);
	void assignPackage(String pacjageRegistrationNo, String assigner, String assignee);
}

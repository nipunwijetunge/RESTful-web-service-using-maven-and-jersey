package models;

public interface RegistrarManager {
	void registerPackage(Package item);
	void storePackage(String pacjageRegistrationNo, int storeId, int cupboardId, String storeOfficerId);
	void assignPackage(String pacjageRegistrationNo, String assigner, String assignee);
}

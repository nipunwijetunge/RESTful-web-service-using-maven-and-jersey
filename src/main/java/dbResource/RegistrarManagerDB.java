package dbResource;

import com.nipun.abxPackageDeliveryService.Package;

public interface RegistrarManagerDB {
	Response registerPackage(PackageDB item) throws Exception;
	String storePackage(PackageDB item) throws Exception;
	String assignPackage(PackageDB item) throws Exception;
}

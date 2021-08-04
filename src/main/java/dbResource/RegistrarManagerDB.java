package dbResource;

import com.nipun.abxPackageDeliveryService.Package;

public interface RegistrarManagerDB {
	Response registerPackage(PackageDB item) throws Exception;
	Response storePackage(PackageDB item) throws Exception;
	Response assignPackage(PackageDB item) throws Exception;
}

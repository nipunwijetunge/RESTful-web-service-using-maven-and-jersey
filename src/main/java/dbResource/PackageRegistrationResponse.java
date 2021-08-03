package dbResource;

public class PackageRegistrationResponse{
	private String packageRegistrationNoQR;

	public PackageRegistrationResponse(String packageRegistrationNo) {
		this.packageRegistrationNoQR = packageRegistrationNo;
	}

	public String getPackageRegistrationNoQR() {
		return packageRegistrationNoQR;
	}

	public void setPackageRegistrationNoQR(String packageRegistrationNo) {
		this.packageRegistrationNoQR = packageRegistrationNo;
	}
}

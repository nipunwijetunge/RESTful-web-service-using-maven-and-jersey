package dbResource;

public class PackageRegistrationResponse{
	private String packageRegistrationNo;

	public PackageRegistrationResponse(String packageRegistrationNo) {
		this.packageRegistrationNo = packageRegistrationNo;
	}

	public String getPackageRegistrationNo() {
		return packageRegistrationNo;
	}

	public void setPackageRegistrationNo(String packageRegistrationNo) {
		this.packageRegistrationNo = packageRegistrationNo;
	}
}

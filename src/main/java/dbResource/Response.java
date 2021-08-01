package dbResource;

import com.google.gson.JsonArray;

public class Response {
	public static final int SUCCESS = 0;
	public static final int ERROR = -1;
	public static final int PRIMARY_KEY_VIOLATION = -2;
	public static final int DATA_ALREADY_EXISTS = -3;
	public static final int NO_DATA_FOUND = -4;
	
	private int id;
	private String status;
	private String description;
	private PackageRegistrationResponse packageRegistrationNo;
	private JsonArray returnedData;
	
	public Response() {
	}

	public Response(int id, String status, String description) {
		super();
		this.id = id;
		this.status = status;
		this.description = description;
	}
	
	public Response(int id, String status, String description, String packageRegistrationNo) {
		super();
		this.id = id;
		this.status = status;
		this.description = description;
		this.packageRegistrationNo = new PackageRegistrationResponse(packageRegistrationNo);
	}

	public Response(int id, String status, String description, JsonArray jarr) {
		super();
		this.id = id;
		this.status = status;
		this.description = description;
		this.returnedData = jarr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "{\"Response\": {\"id\":" + id + ", \"status\":" + status + ", \"description\":" + description + "}";
	}
}

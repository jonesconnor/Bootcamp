
public class University {
	private String universityName;
	private String location;
	
	public University(String universityName, String location) {
		setUniversityName(universityName);
		setLocation(location);
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}

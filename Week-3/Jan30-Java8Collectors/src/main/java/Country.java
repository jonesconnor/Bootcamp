
public class Country {
	private String countryId;
	private String countryName;
	
	Country(String countryId, String countryName) {
		setCountryId(countryId);
		setCountryName(countryName);
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
}

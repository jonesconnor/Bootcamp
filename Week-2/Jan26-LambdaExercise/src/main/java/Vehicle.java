
public class Vehicle {
	private String type;
	private String company;
	private int price;
	
	Vehicle(String type, String company, int price) {
		setType(type);
		setCompany(company);
		setPrice(price);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}

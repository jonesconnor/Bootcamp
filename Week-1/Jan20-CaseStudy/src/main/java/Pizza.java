
public abstract class Pizza {
	
	private String type;
	private boolean toppings;
	private String name;
	private float timeForPreparation;
	private int costOfPizza;
	private String size;
	
	public Pizza(String type, boolean toppings, String name, float timeForPreparation, int costOfPizza, String size) {
		if (type.equals("Veg") || type.equals("Non-Veg")) {
			this.type = type;
		} else {
			System.out.println("You get non-vegetarian because it means more money for me.");
			this.type = "Non-Veg";
		}
		
		if (size.equals("small") || size.equals("medium")) {
			this.size = size;
		} else {
			System.out.println("I need to make a living so I'm forcing you to order a medium pizza.");
			this.size = "medium";
		}
		this.toppings = toppings;
		this.name = name;
		this.timeForPreparation = timeForPreparation;
		this.costOfPizza = costOfPizza;
		this.size = size;
	}
	
	public abstract int calculateCost();
	
	public String getType() {
		return type;
	}
	
	public String getSize() {
		return size;
	}
	
	public boolean getToppings() {
		return toppings;
	}
}

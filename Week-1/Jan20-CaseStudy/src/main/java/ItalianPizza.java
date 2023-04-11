
public class ItalianPizza extends Pizza{

	public ItalianPizza(String type, boolean toppings, String name, float timeForPreparation, int costOfPizza,
			String size) {
		super(type, toppings, name, timeForPreparation, costOfPizza, size);
	}

	@Override
	public int calculateCost() {
		int total = 0;
		if (getToppings()) {
			total += 30;
		}
		if (getType().equals("Veg")) {
			if (getSize().equals("small")) {
				return total + 200;
			}
			return total + 350;
		} else {
			if (getSize().equals("small")) {
				return total + 270;
			}
			return total + 420;
		}
		
	}
	
	@Override
	public String toString() {
		return "Pizza: " + this.getType() + " " + this.getSize() + "\n" + "Cost: " + this.calculateCost();
	}
	
}

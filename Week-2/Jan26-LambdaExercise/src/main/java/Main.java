
public class Main {
	
	public static void processTax(Vehicle V, iShowTax T) {
		int tax = T.findTax(V.getPrice());
		System.out.println(tax);
	}
	
	public static void main(String[] args) {
		
		Vehicle Car = new Vehicle("Clown Car", "Chevrolet", 125000);
		iShowTax tax = (p) -> {
			if (p > 50000) {
				return (int) (p*0.24);
			} else {
				return (int) ( p*0.1);
			}
		};
		
		processTax(Car, tax);
		
	}

}

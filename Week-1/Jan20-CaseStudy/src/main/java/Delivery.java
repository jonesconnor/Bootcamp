import java.util.Scanner;

public class Delivery {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

        System.out.println("Enter Type of Pizza (Veg/Non-Veg): ");
        String type = sc.nextLine();
        System.out.println("Enter Size of Pizza (small/medium): ");
        String size = sc.nextLine();
        System.out.println("You want toppings (true/false): ");
        boolean toppings = sc.nextBoolean();

        Pizza pizza = new ItalianPizza(type, toppings, "Italian", 30, 0, size);
        System.out.println(pizza);

	}

}

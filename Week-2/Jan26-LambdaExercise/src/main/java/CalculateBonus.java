
public class CalculateBonus {
	
	public static void main(String[] args) {
		
		iCalculate bonusCalculation = (salary) -> salary/10;
		int bonus = (int) bonusCalculation.bonusProcess(1000);
		System.out.println(bonus);
		
	}

}

package abstractfactory;

public class Square implements Shape{
	
	private double sideLength;
	
	public Square(double sideLength) {
		setSideLength(sideLength);
	}
	
	public void setSideLength(double sideLength) {
		this.sideLength = sideLength;
	}
	
	public void draw() {
		double area = sideLength * 2;
		System.out.println("SQUARE AREA: " + area);
	}
}

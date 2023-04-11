package abstractfactory;

public class Rectangle implements Shape{
	
	private double length;
	private double width;
	
	public Rectangle(double length, double width) {
		setLength(length);
		setWidth(width);
	}
	
	public void setLength(double length) {
		this.length = length;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public void draw() {
		double area = length * width;
		System.out.println("RECTANGLE AREA: " + area);
	}
}

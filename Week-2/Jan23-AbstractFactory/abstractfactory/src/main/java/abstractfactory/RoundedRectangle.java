package abstractfactory;
import static java.lang.Math.PI;

public class RoundedRectangle implements Shape{
	
	private double length;
	private double width;
	private double radiusRect;
	
	public RoundedRectangle(double length, double width, double radiusRect) {
		setLength(length);
		setWidth(width);
		setRadiusRect(radiusRect);
	}
	
	public void setLength(double length) {
		this.length = length;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public void setRadiusRect(double radiusRect) {
		this.radiusRect = radiusRect;
	}
	
	public void draw() {
		double area = (length*width) - (4 - PI)*(radiusRect*radiusRect);
		System.out.format("ROUNDED RECTANGLE AREA: %.2f\n", area);
	}
	
}

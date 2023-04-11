package abstractfactory;

import static java.lang.Math.PI;

public class RoundedSquare implements Shape{
	
	private double sideLength;
	private double radiusSquare;
	
	RoundedSquare(double sideLength, double radiusSquare) {
		setSideLength(sideLength);
		setRadiusSquare(radiusSquare);
	}
	
	public void setSideLength(double sideLength) {
		this.sideLength = sideLength;
	}
	
	public void setRadiusSquare(double radiusSquare) {
		this.radiusSquare = radiusSquare;
	}
	
	public void draw() {
		double area = (sideLength*sideLength) - (4 - PI)*(radiusSquare*radiusSquare);
		System.out.format("ROUNDED SQUARE AREA: %.2f\n", area);
	}
	
}

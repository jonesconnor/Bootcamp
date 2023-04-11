package abstractfactory;

public abstract class AbstractFactory{
	protected double length;
	protected double width;
	protected double sideLength;
	protected double radiusRect;
	protected double radiusSquare;
	
	public AbstractFactory(double length, double width, double sideLength) {
		setLength(length);
		setWidth(width);
		setSideLength(sideLength);
	}
	
	public AbstractFactory(double length, double width, double sideLength, double radiusRect, double radiusSquare) {
		setLength(length);
		setWidth(width);
		setSideLength(sideLength);
		setRadiusRect(radiusRect);
		setRadiusSquare(radiusSquare);
	}
	
	public void setLength(double length) {
		this.length = length;
	}
	
	public double getLength() {
		return this.length;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public void setSideLength(double sideLength) {
		this.sideLength = sideLength;
	}
	
	public double getSideLength() {
		return this.sideLength;
	}
	
	public void setRadiusRect(double radiusRect) {
		this.radiusRect = radiusRect;
	}
	
	public double getRadiusRect() {
		return this.radiusRect;
	}
	
	public void setRadiusSquare(double radiusSquare) {
		this.radiusSquare = radiusSquare;
	}
	
	public double getRadiusSquare() {
		return this.radiusSquare;
	}
	
	public abstract Shape getShape(String shape);
	
}

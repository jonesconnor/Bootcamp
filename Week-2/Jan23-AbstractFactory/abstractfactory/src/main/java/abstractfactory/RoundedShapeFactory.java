package abstractfactory;

public class RoundedShapeFactory extends AbstractFactory{
	
	public RoundedShapeFactory(double length, double width, double sideLength, double radiusRect, double radiusSquare) {
		super(length, width, sideLength, radiusRect, radiusSquare);
	}
	
	@Override
	public Shape getShape(String shape) {
		if (shape.equalsIgnoreCase("RECTANGLE")) {
			return new RoundedRectangle(length, width, radiusRect);
		} else if (shape.equalsIgnoreCase("SQUARE")) {
			return new RoundedSquare(sideLength, radiusSquare);
		} else {
			return null;
		}
	}
}

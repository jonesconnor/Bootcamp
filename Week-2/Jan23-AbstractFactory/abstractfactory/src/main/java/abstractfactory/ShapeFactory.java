package abstractfactory;

public class ShapeFactory extends AbstractFactory{
	
	public ShapeFactory(double length, double width, double sideLength) {
		super(length, width, sideLength);
	}

	@Override
	public Shape getShape(String shape) {
		if (shape.equalsIgnoreCase("RECTANGLE")) {
			return new Rectangle(length, width);
		} else if (shape.equalsIgnoreCase("SQUARE")) {
			return new Square(sideLength);
		} else {
			return null;
		}
	}
}

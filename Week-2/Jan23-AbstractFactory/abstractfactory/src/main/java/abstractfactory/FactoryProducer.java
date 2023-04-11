package abstractfactory;

public class FactoryProducer {
	public static AbstractFactory getFactory(AbstractFactory A) {
		if (A instanceof ShapeFactory) {
			return new ShapeFactory(A.getLength(), A.getWidth(), A.getSideLength());
		} else if (A instanceof RoundedShapeFactory) {
			return new RoundedShapeFactory(A.getLength(), A.getWidth(), A.getSideLength(), A.getRadiusRect(), A.getRadiusSquare());
		}
		return null;
	}
}

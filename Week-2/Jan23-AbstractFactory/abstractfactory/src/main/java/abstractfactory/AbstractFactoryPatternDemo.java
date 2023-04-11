package abstractfactory;

public class AbstractFactoryPatternDemo {

	public static void main(String[] args) {
		
		AbstractFactory ShapeFactory = FactoryProducer.getFactory(new ShapeFactory(7, 5, 8));
		AbstractFactory ShapeFactoryRounded = FactoryProducer.getFactory(new RoundedShapeFactory(7, 5, 8, 2.5, 2.5));
		
		Shape shape1 = ShapeFactory.getShape("rectangle");
		shape1.draw();
		
		Shape shape2 = ShapeFactory.getShape("square");
		shape2.draw();
		
		Shape shape3 = ShapeFactoryRounded.getShape("rectangle");
		shape3.draw();
		
		Shape shape4 = ShapeFactoryRounded.getShape("square");
		shape4.draw();
		
	}

}

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Fuel extends Shape {
	public Fuel(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public com.sun.javafx.geom.Shape impl_configShape() {
		return null;
	}

	public Rectangle paint() {
		Rectangle fuel = new Rectangle();
		fuel.setX(x);
		fuel.setY(y);
		fuel.setWidth(size);
		fuel.setHeight(size);
		fuel.setFill(Color.DARKRED);
		return fuel;
	}

	
	
	
	private double value;
	private double size = 20;
	private double x;
	private double y;
}

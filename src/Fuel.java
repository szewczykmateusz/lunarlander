import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Fuel extends AbstractShape {
	public Fuel(double x, double y) {
		this.x = x;
		this.y = y;
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
}

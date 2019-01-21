import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Fuel extends Shape {
	public Fuel(double x, double y, double size) {
		this.x = x;
		this.y = y;
		width = size;
		height = size;
	}

	@Override
	public com.sun.javafx.geom.Shape impl_configShape() {
		return null;
	}

	public Rectangle paint() {
		Rectangle fuel = new Rectangle();
		fuel.setX(x);
		fuel.setY(y);
		fuel.setWidth(width);
		fuel.setHeight(height);
		fuel.setFill(Color.DARKRED);
		return fuel;
	}
	public void setX(float x) {this.x = x;}
	public void setY(float y) {this.y = y;}
	public void setWidth(float width) {this.width = width;}
	
	
	
	private double value;
	private double width;
	private double height;
	private double x;
	private double y;
}

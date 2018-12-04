import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class Coin extends Shape {
	public Coin(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public com.sun.javafx.geom.Shape impl_configShape() {
		return null;
	}

	public Ellipse paint() {
		Ellipse coin = new Ellipse();
		coin.setCenterX(x);
		coin.setCenterY(y);
		coin.setRadiusX(radius);
		coin.setRadiusY(radius);
		coin.setFill(Color.GOLD);
		
		return coin;
	}
	public Ellipse remove() {
		return null;
	}
	
	
	private int radius = 10;
	private int value = 100;
	private Double x;
	private Double y;
}

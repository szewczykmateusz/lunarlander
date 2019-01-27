import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class Coin extends Shape {
	public Coin(double x, double y, Config cfg) {
		this.x = x;
		this.y = y;
		this.cfg = cfg;
		radius = Utils.intFromConfig(cfg, "coinRadius");
		value = Utils.intFromConfig(cfg, "coinValue");
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
	public Double getX() {return x;}
	public void setWasUsed() {wasUsed = true;}
	public boolean getWasUsed() {return wasUsed;}

	private Config cfg;
	private int radius;
	private int value;
	private Double x;
	private Double y;
	private boolean wasUsed = false;
}

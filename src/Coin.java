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
	public Double getX() {return x;}

	private Config cfg = new Config(Player.getActualLevel());
	private int radius = Utils.intFromConfig(cfg, "coinRadius");
	private int value = Utils.intFromConfig(cfg, "coinValue");
	private Double x;
	private Double y;
}

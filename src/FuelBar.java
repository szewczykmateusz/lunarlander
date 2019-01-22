import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;

public class FuelBar extends Shape {
	
	public FuelBar(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public com.sun.javafx.geom.Shape impl_configShape() {
		return null;
	}

	/*
	 * Rysuje obramowanie paska paliw
	 */
	public Rectangle paint() {
		Rectangle fuelBar = new Rectangle();
		fuelBar.setX(x);
		fuelBar.setY(y);
		fuelBar.setWidth(width);
		fuelBar.setHeight(height);
		fuelBar.setArcHeight(arcSize);
		fuelBar.setArcWidth(arcSize);
		fuelBar.setFill(Color.TRANSPARENT);
		fuelBar.setStroke(Color.BLACK);
		return fuelBar;
	}

	/*
	 * Wypelnia pasek paliw w zaleznosci od ilosci paliwa rakiety
	 */
	public Rectangle fillFuel(double rocketFuel) {
		Rectangle fuel = new Rectangle();
		double fuelWidth = width * rocketFuel;
		fuel.setX(x);
		fuel.setY(y);
		fuel.setHeight(height);
		fuel.setWidth(fuelWidth);
		fuel.setArcHeight(arcSize);
		fuel.setArcWidth(arcSize);
		fuel.setFill(Color.GRAY);
		return fuel;
	}

	/*
	Method changing the fuel level
	 */
	public Rectangle updateFuelLevel(Rocket rocket) {
		Double burnVel = rocket.getBurnVel();
		if(width >= burnVel)
			width -= burnVel;
		return fillFuel(rocket.getFuel());
	}
	public double getX() {return x;}
	public double getY() {return y;}
	public void setX (double x) {this.x = x;}
	public void setY (double y) {this.y = y;}
	public void setWidth(double factor) {width *= factor;}
	public void setHeight(double factor) {height *= factor;}
	public int getEdgeDistance() {return edgeDistance;}

	private Config cfg = new Config(Player.getActualLevel());
	private double x;
	private double y;
	private double width = 50;
	private double height = 20;
	private double arcSize = 10;
	private int edgeDistance = (int) Utils.floatFromConfig(cfg, "fuelBarEdgeDistance");
}

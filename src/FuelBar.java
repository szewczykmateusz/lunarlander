import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FuelBar extends AbstractShape {
	
	public FuelBar(double x, double y) {
		this.x = x;
		this.y = y;
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
		fuel.setX(x);
		fuel.setY(y);
		fuel.setHeight(height);
		double fuelWidth = width * rocketFuel;
		fuel.setWidth(fuelWidth);
		fuel.setArcHeight(arcSize);
		fuel.setArcWidth(arcSize);
		fuel.setFill(Color.GRAY);
		return fuel;
	}
	
	
	
	

	private double width = 50;
	private double height = 20;
	private double arcSize = 10;
}

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;

/*
 * Klasa rysujaca rakiete oraz animujaca jej przemieszczenie
 */
public class Rocket extends Shape  {
	public Rocket() {
		// x = 300;
		// y = 50;
		// velocityX = 0;
		// velocityY = 0;
		// height = 20;
		// width = 40;
		// fuel = 1.0;
//		centerX.setValue(x);
//		centerY.setValue(y);
		// READING ALL ATTRIBUTES FROM THE CONFIG FILE
		cfg = new Config();
		x = Double.parseDouble(cfg.getProperty("startX"));
		y = Double.parseDouble(cfg.getProperty("startY"));
		velocityX = Double.parseDouble(cfg.getProperty("velocityX"));
		velocityY = Double.parseDouble(cfg.getProperty("velocityY"));
		height = Integer.parseInt(cfg.getProperty("rocketHeight"));
		width = Integer.parseInt(cfg.getProperty("rocketWidth"));
		fuel = Double.parseDouble(cfg.getProperty("fuel"));
		burningVelocity = Double.parseDouble(cfg.getProperty("burningVelocity"));
	}

	@Override
	public com.sun.javafx.geom.Shape impl_configShape() {
		return null;
	}

	public Circle paint() {
		Circle rocket = new Circle();
		rocket.setCenterX(x);
		rocket.setCenterY(y);
		rocket.setRadius(radius);
		rocket.setFill(Color.HOTPINK);
		return rocket;
		
	}

	/*
	Method assuring that the rocket is going to burn 0.1 of it's fuel per 6 seconds
	 */
	public void burnFuel() {
		fuel -= burningVelocity;
		//System.out.println(fuel);
	}

	public double getFuel() {return fuel;}
	public double getVelX() {return velocityX;}
	public double getVelY() {return velocityY;}
	public double getBurnVel() {return burningVelocity;}
//	public DoubleProperty getCenterX() {return centerX;}
//	public DoubleProperty getCenterY() {return centerY;}
//	public void setCenterX(double x) {centerX.setValue(x);}
//	public void setCenterY(double y) {centerY.setValue(y);}
//	public void setCenterX(DoubleProperty x) {centerX = x;}
//	public void setCenterY(DoubleProperty y) {centerY = y;}

	private double velocityX;
	private double velocityY;
	private int height; //wymiary rakiety
	private int width;
	private double fuel = 1; //ilosc paliwa, przyjmuje wartosci z przedzialu <0,1>
	private Config cfg;
//	private DoubleProperty centerX = new SimpleDoubleProperty(); //centralny punkty pilki
//	private DoubleProperty centerY = new SimpleDoubleProperty();
	private double x;
	private double y;
	private double radius = 5;
	private double burningVelocity;
}

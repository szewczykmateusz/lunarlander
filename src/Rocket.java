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
		x = Double.parseDouble(cfg.getProperty("startX"));
		y = Double.parseDouble(cfg.getProperty("startY"));
		height = Integer.parseInt(cfg.getProperty("rocketHeight"));
		width = Integer.parseInt(cfg.getProperty("rocketWidth"));
		fuel = Double.parseDouble(cfg.getProperty("fuel"));
		burningVelocity = Double.parseDouble(cfg.getProperty("burningVelocity"));
		extendFuelValue = Double.parseDouble(cfg.getProperty("fuelTankValue"));
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
	/*
	Method increases fuel when rocket catch extend fuel tank during game
	 */
	public void addFuel() {
		if((fuel += extendFuelValue) > 1)
			fuel = 1;
		else
			fuel += extendFuelValue;
	}

	public double getFuel() {return fuel;}
	public double getBurnVel() {return burningVelocity;}
	public float getUpVelocity(){return upVelocity;}
	public float getLeftVelocity(){return leftVelocity;}
	public float getRightVelocity(){return rightVelocity;}
	public float getInsUpVelocity(){return insUpVelocity;}
	public float getInsLeftVelocity(){return insLeftVelocity;}
	public float getInsRightVelocity(){return insRightVelocity;}

	public void setInsUpVelocity(float insUpVelocity) {this.insUpVelocity = insUpVelocity;}
	public void setInsLeftVelocity(float insLeftVelocity) {this.insLeftVelocity = insLeftVelocity;}
	public void setInsRightVelocity(float insRightVelocity) {this.insRightVelocity = insRightVelocity;}
//	public DoubleProperty getCenterX() {return centerX;}
//	public DoubleProperty getCenterY() {return centerY;}
//	public void setCenterX(double x) {centerX.setValue(x);}
//	public void setCenterY(double y) {centerY.setValue(y);}
//	public void setCenterX(DoubleProperty x) {centerX = x;}
//	public void setCenterY(DoubleProperty y) {centerY = y;}
	public void accUpVelocity() {insUpVelocity -= acceleration;}
	public void accLeftVelocity() {insLeftVelocity -= acceleration;}
	public void accRightVelocity() {insRightVelocity += acceleration;}

	public void resetUpVelocity() {insUpVelocity = upVelocity;}
	public void resetLeftVelocity() {insLeftVelocity = leftVelocity;}
	public void resetRightVelocity() {insRightVelocity = rightVelocity;}

	private Config cfg = new Config();
	//starting values of velocities
	private final float upVelocity = Utils.floatFromConfig(cfg,"yVelocity");
	private final float leftVelocity = Utils.floatFromConfig(cfg,"xVelocity");
	private final float rightVelocity = -leftVelocity;
	//instant values of velocities
	private float insUpVelocity = upVelocity;
	private float insLeftVelocity = leftVelocity;
	private float insRightVelocity = rightVelocity;
	private float acceleration = Utils.floatFromConfig(cfg, "acceleration");
	private int height; //wymiary rakiety
	private int width;
	private double fuel = 1; //ilosc paliwa, przyjmuje wartosci z przedzialu <0,1>
//	private DoubleProperty centerX = new SimpleDoubleProperty(); //centralny punkty pilki
//	private DoubleProperty centerY = new SimpleDoubleProperty();
	private double x;
	private double y;
	private double radius = 5;
	private double burningVelocity;
	private double extendFuelValue;
}

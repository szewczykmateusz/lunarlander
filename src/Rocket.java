import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

/**
 * Class draws rocket and keeps all properties of rocket
 */
public class Rocket extends Shape  {

	private Config cfg;
	//starting values of velocities
	private float upVelocity;
	private float leftVelocity;
	private float rightVelocity;
	//instant values of velocities
	private float insUpVelocity;
	private float insLeftVelocity;
	private float insRightVelocity;
	private float acceleration;
	private int height; //sizes of rocket
	private int width;
	private double radius;
	private double fuel; //fuel quantity, keeps values from <0,1>
	private double x;
	private double y;
	private double burningVelocity;
	private double extendFuelValue;
	// instant velocity with which rocket is falling down, when any key isn`s pressed
	private float fallVelocity;//starting fall velocity
	private float insFallVelocity; //instant fall velocity
	//	private float fallAcceleration = Utils.floatFromConfig(cfg, "weightAcceleration");
	private float fallAcceleration;
	private boolean isPaused = false;

	public Rocket(Enum difficulty, Config cfg) {
		// READING ALL ATTRIBUTES FROM THE CONFIG FILE
		this.cfg = cfg;
		x = Double.parseDouble(cfg.getProperty("startX"));
		y = Double.parseDouble(cfg.getProperty("startY"));
		height = Integer.parseInt(cfg.getProperty("rocketHeight"));
		width = Integer.parseInt(cfg.getProperty("rocketWidth"));
		fuel = Double.parseDouble(cfg.getProperty("fuel"));
		burningVelocity = Double.parseDouble(cfg.getProperty("burningVelocity"));
		extendFuelValue = Double.parseDouble(cfg.getProperty("fuelTankValue"));
		fallAcceleration = Utils.floatFromConfig(cfg, (difficulty + "weightAcceleration"));
		upVelocity = Utils.floatFromConfig(cfg,"yVelocity");
		leftVelocity = Utils.floatFromConfig(cfg,"xVelocity");
		rightVelocity = -leftVelocity;
		//instant values of velocities
		insUpVelocity = upVelocity;
		insLeftVelocity = leftVelocity;
		insRightVelocity = rightVelocity;
		acceleration = Utils.floatFromConfig(cfg, "acceleration");
		radius = Utils.doubleFromConfig(cfg, "rocketRadius");
		fallVelocity = Utils.floatFromConfig(cfg, "startingFallVelocity");
		insFallVelocity = fallVelocity;
	}

	@Override
	public com.sun.javafx.geom.Shape impl_configShape() {
		return null;
	}

	public Ellipse paint() {
		Ellipse rocket = new Ellipse();
		rocket.setCenterX(x);
		rocket.setCenterY(y);
		rocket.setRadiusX(radius);
		rocket.setRadiusY(radius);
		rocket.setFill(Color.BLACK);
		return rocket;
		
	}
	public double getRadius() {return radius;}

	/*
	Method assuring that the rocket is going to burn 0.1 of it's fuel per 6 seconds
	 */
	public void burnFuel() {
		if(!isPaused)
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
	public float increaseInsFallVelocity() {insFallVelocity += fallAcceleration;
											return  insFallVelocity;}
	public void restartInsFallVelocity() {insFallVelocity = fallVelocity;}

	public double getFuel() {return fuel;}
	public double getBurnVel() {return burningVelocity;}
	public float getUpVelocity(){return upVelocity;}
	public float getLeftVelocity(){return leftVelocity;}
	public float getRightVelocity(){return rightVelocity;}
	public float getInsUpVelocity(){return insUpVelocity;}
	public float getInsLeftVelocity(){return insLeftVelocity;}
	public float getInsRightVelocity(){return insRightVelocity;}
	public float getInsFallVelocity() {return insFallVelocity;}
	public float getFallVelocity() {return fallVelocity;}
	public float getAcceleration() {return  acceleration;}
	public float getFallAcceleration() {return  fallAcceleration;}
	public double getX() {return x;}
	public double getY() {return y;}

	public void setRightVelocity(float rightVelocity) {this.rightVelocity = rightVelocity;}
	public void setLeftVelocity(float leftVelocity) {this.leftVelocity = leftVelocity;}
	public void setAcceleration(float acceleration) {this.acceleration = acceleration;}
	public void setInsFallVelocity(float insFallVelocity) {this.fallVelocity = fallVelocity;}
	public void setFallAcceleration(float acceleration) {this.fallAcceleration = fallAcceleration;}
	public void setInsUpVelocity(float insUpVelocity) {this.insUpVelocity = insUpVelocity;}
	public void setInsLeftVelocity(float insLeftVelocity) {this.insLeftVelocity = insLeftVelocity;}
	public void setInsRightVelocity(float insRightVelocity) {this.insRightVelocity = insRightVelocity;}
	public void setFallVelocity(float fallVelocity) {this.fallVelocity = fallVelocity;
													this.insFallVelocity = fallVelocity;}
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
	public void runFuelBurn() {isPaused = false;}
	public void pauseFuelBurn() {isPaused = true;}
}

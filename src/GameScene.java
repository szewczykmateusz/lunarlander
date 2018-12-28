import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javafx.event.EventHandler;

import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import javafx.scene.Node;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import org.w3c.dom.css.Rect;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.scene.shape.Line;
import javafx.scene.shape.CubicCurve;

import javax.swing.Timer;
/*
Class responsbile for making scene wherein the game takes place
 */

public class GameScene extends Scene {

	public GameScene(Region root) {
		super(root);
		DEFAULT_WIDTH = Constants.DEFAULT_WIDTH;
		DEFAULT_HEIGHT = Constants.DEFAULT_HEIGHT;
//		cfg = new Config();
	}
	
	public Scene initiateGame(int difficulty) {
		/*
		Depending on difficulty argument we set fallVelocity
		 */
		fallVelocity = getFallVelocity(difficulty);

		numberOfMountains = Utils.intFromConfig(cfg, "mountainsCount");
		coinsQuantity = Utils.intFromConfig(cfg, "coinsQuantity");
		rocket = new Rocket();
		coins = new Coin[coinsQuantity];
		for(Integer i = 1; i <= coinsQuantity; i++) {
			coins[i - 1] = new Coin(Utils.intFromConfig(cfg,"coin" + i + "X"), Utils.intFromConfig(cfg,"coin" + i + "Y"));
		}
		fuel = new Fuel(Utils.intFromConfig(cfg,"fuelX"), Utils.intFromConfig(cfg,"fuelY"));
		fuelRectangle = fuel.paint();
		fuelBar = new FuelBar(530, 20);
		Text levelNumber = setLevelNumber();
		Text velXText = setVelXText();
		Text velYText = setVelYText();
		//timeText initialization, setting starting value and text properties
		LevelTimer timer = new LevelTimer();
		timeText = setTimeText(timer);
		
		line = new Line();
		line = setLineProperties();
		mountains = new CubicCurve[numberOfMountains];
		for(int i = 0; i < numberOfMountains; i++) {
			mountains[i] = new CubicCurve();
			mountains[i] = setMountainProperties(i+1);
		}

 //       DoubleProperty mountainsProp = new SimpleDoubleProperty();
		DoubleProperty centerX = new SimpleDoubleProperty();
		DoubleProperty centerY = new SimpleDoubleProperty();

		circle = new Circle(0, 0, 5);
		circle = rocket.paint();

		root = new Group(circle, line);
		for(int i = 0; i <mountains.length; i++)
	    	root.getChildren().add(mountains[i]);
		fuelBarRectangle = fuelBar.fillFuel(rocket.getFuel());
		root.getChildren().addAll(levelNumber,
				fuelRectangle, fuelBar.paint(), fuelBarRectangle,
				velXText, velYText, timeText);
		root.setFocusTraversable(true);

		//adding all the coins to root
		for(Integer i = 0; i < coinsQuantity; i++) {
			root.getChildren().addAll(coins[i].paint());
		}
//		startGame();
		animate(timer, centerX, centerY);
		Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		circle.centerXProperty().bind(centerX);
		circle.centerYProperty().bind(centerY);
		centerX.setValue(300);
		centerY.setValue(100);
//		mountainsProp.setValue(250);
		root.requestFocus();
		return scene;
	}
	/*
	 * Method returning number of actual level (Text)
	 */
	private Text setLevelNumber() {
		Text levelNumber = new Text();

		levelNumber.setText(cfg.getProperty("levelText"));
		//levelNumber.setText("LEVEL 1");
		levelNumber.setX(10);
		levelNumber.setY(390);
		Font font = new Font(20);
		levelNumber.setFont(font);
		
		return levelNumber;
	}
	
	private Text setVelXText() {
		Text velX = new Text();
		StringBuilder builder = new StringBuilder();
		builder.append("PredkoscX:");
		builder.append(Double.toString(rocket.getVelX()));
		velX.setText(builder.toString());
		velX.setX(3);
		velX.setY(10);
		velX.setFill(Color.GREEN);
		Font font = new Font(14);
		velX.setFont(font);
		
		return velX;
			
	}

	private Text setVelYText() {
		Text velY = new Text();
		StringBuilder builder = new StringBuilder();
		builder.append("PredkoscY:");
		builder.append(Double.toString(rocket.getVelY()));
		velY.setText(builder.toString());
		velY.setX(3);
		velY.setY(25);
		velY.setFill(Color.GREEN);
		Font font = new Font(14);
		velY.setFont(font);
		
		return velY;
				
	}

	private Text setTimeText(LevelTimer timer) {
		Text timeText = new Text();
		StringBuilder builder = new StringBuilder();
		Date date = timer.getDate();
		builder.append("Czas: ");
		builder.append(date.getMinutes());
		builder.append(":");
		builder.append(date.getSeconds());
		timeText.setText(builder.toString());
		timeText.setX(530);
		timeText.setY(10);
		Font font = new Font(14);
		timeText.setFont(font);

		return timeText;
	}
	/*
	Method checks if rocket has a collision with mountain, if so animation stops
	 */
	public void checkForMountainCollision() {
	    for(int i = 0; i < mountains.length; i++)
            if (mountains[i].contains(circle.getCenterX(), circle.getCenterY())) {
                System.out.println("Kolizja");
                rocketAnimation.stop();
                circle.setVisible(false);
            }
	    if (line.contains(circle.getCenterX(), circle.getCenterY())) {
	        System.out.println("Kolizja");
	        rocketAnimation.stop();
	        circle.setVisible(false);
	    }
	}
	/*
	Method checks if rocket has a collision with fuel tank,
	if so fuel tank dissapears and rocket fuel increases
	 */

	public void checkForFuelCollision() {
		if(fuelRectangle.contains(circle.getCenterX(), circle.getCenterY())) {
			System.out.println("Paliwo");
//			fuelRectangle.setVisible(false);
			root.getChildren().remove(fuelRectangle);
			rocket.addFuel();
		}
	}

	/*
	Method returning fall velocity of the Rocket (Integer) gets difficulty (Integer) as argument
	 */
	private int getFallVelocity(int difficulty) {
		int fallVelocity = 0;
		switch(difficulty){
			case 1:
				fallVelocity = 1;
			break;

			case 2:
				fallVelocity = 2;
			break;

			case 3:
				fallVelocity = 3;
			break;

		}
		return fallVelocity;
	}
	/*
	Method sets parameters of line
	 */
	private Line setLineProperties() {
		Line line = new Line();
		line.setStartX(Utils.intFromConfig(cfg, "landingZoneStartX"));
		line.setStartY(Utils.intFromConfig(cfg, "landingZoneY"));
		line.setEndX(Utils.intFromConfig(cfg, "landingZoneEndX"));
		line.setEndY(Utils.intFromConfig(cfg, "landingZoneY"));
		line.setStrokeWidth(Utils.intFromConfig(cfg, "landingZoneWidth"));
		return line;
	}
	/*
	Method sets parameters of mountain, get index of current mountain (Int)
	 */
	private CubicCurve setMountainProperties(int index) {
		CubicCurve mountain = new CubicCurve();
		StringBuilder builder = new StringBuilder();
		builder.append("mountain");
		builder.append(index);
		String template = builder.toString();
		mountain.setStartX(Utils.floatFromConfig(cfg, template + "StartX"));
		mountain.setStartY(Utils.floatFromConfig(cfg, template + "StartY"));
		mountain.setEndX(Utils.floatFromConfig(cfg, template + "EndX"));
		mountain.setEndY(Utils.floatFromConfig(cfg, template + "EndY"));
		mountain.setControlX1(Utils.floatFromConfig(cfg, template + "ControlX1")) ;
		mountain.setControlX2(Utils.floatFromConfig(cfg, template + "ControlX2"));
		mountain.setControlY1(Utils.floatFromConfig(cfg, template + "ControlY1"));
		mountain.setControlY2(Utils.floatFromConfig(cfg, template + "ControlY2"));
		return mountain;

	}
	/*
	Method actualizes timeText value every second
	 */
	private void setTimer(LevelTimer timer) {
		DateFormat timeFormat = new SimpleDateFormat("mm:ss");
		timeText.setText(timeFormat.format(timer.getDate()));
	}
	/*
	Method makes animation of game
	 */
	private void animate(LevelTimer timer, DoubleProperty centerX, DoubleProperty centerY) {
		rocketAnimation = new Timeline(
				new KeyFrame(new Duration(10.0), t ->  {
					//every second timeText is actualized
					setTimer(timer);
					//checks for collisions
					checkForMountainCollision();
					checkForFuelCollision();
					// set velocity with which rocket falls down
					centerY.setValue(centerY.getValue() + fallVelocity);
					//make rocket burn some amount of it's fuel per frame
					rocket.burnFuel();
					//update fuel bar width
					actualizeFuelBar(root, fuelBar, rocket);
					//controls
					root.setOnKeyPressed(k -> {
						if(rocket.getFuel() > 0) {
							if (k.getCode() == KeyCode.UP) {
								insUpVelocity -= 0.5;
								centerY.setValue(centerY.getValue() + insUpVelocity);
							}
							else if (k.getCode() == KeyCode.DOWN)
								centerY.setValue(centerY.getValue() + 6);
							else if (k.getCode() == KeyCode.LEFT) {
								insLeftVelocity -= 0.5;
								centerX.setValue(centerX.getValue() + insLeftVelocity);
							}
							else if (k.getCode() == KeyCode.RIGHT) {
								insRightVelocity += 0.5;
								centerX.setValue(centerX.getValue() + insRightVelocity);
							}
							}
					});
					/*
					When user realease key, velocity returns to starting value
					 */
					root.setOnKeyReleased(k -> {
						if(k.getCode() == KeyCode.UP)
							insUpVelocity = upVelocity;
						else if(k.getCode() == KeyCode.LEFT)
							insLeftVelocity = leftVelocity;
						else if(k.getCode() == KeyCode.RIGHT)
							insRightVelocity = rightVelocity;
					});
					System.out.println("Vel X " + (insRightVelocity + insLeftVelocity));
//					System.out.println("Vel Y " + (insUpVelocity - fallVelocity));
				})
		);
		rocketAnimation.setCycleCount(Timeline.INDEFINITE);
		rocketAnimation.playFromStart();

	}
	/*
	Method actualizes filling of FuelBar
	We must to remove old fill to paint new one
	 */
	private void actualizeFuelBar(Group root, FuelBar fuelBar, Rocket rocket) {
		root.getChildren().remove(fuelBarRectangle);
		fuelBarRectangle = fuelBar.updateFuelLevel(rocket);
		root.getChildren().add(fuelBarRectangle);
	}
	/*
	Method starts game when user press SPACE and show information about it
	 */
/*	private void startGame() {
		Text information = new Text("Press SPACE to start");
		information.setX(200);
		information.setY(200);
		information.setFill(Color.RED);
		Font font = new Font(30);
		information.setFont(font);
		root.getChildren().add(information);
		while(true) {
			root.setOnKeyPressed(k -> {
				if (k.getCode() == KeyCode.SPACE) {
					root.getChildren().remove(information);
					return;
				}
			});
		}

	} */





	private float DEFAULT_WIDTH;
	private float DEFAULT_HEIGHT;
	private Coin[] coins;
	private int numberOfMountains;
	private int coinsQuantity;
	private FuelBar fuelBar;
	private Rectangle fuelBarRectangle  = new Rectangle();
	private Rocket rocket;
	private Text timeText; 
	private Config cfg = new Config();
	private Timeline rocketAnimation;
	private Rectangle rect;
	private Circle circle;
	private Line line;
	private CubicCurve[] mountains;
	private Group root;
	private int fallVelocity;
	private Fuel fuel;
	private Rectangle fuelRectangle = new Rectangle();
	//starting values of velocities
	private final float upVelocity = Utils.floatFromConfig(cfg,"yVelocity");
	private final float leftVelocity = Utils.floatFromConfig(cfg,"xVelocity");
	private final float rightVelocity = -leftVelocity;
	//instant values of velocities
	private float insUpVelocity = upVelocity;
	private float insLeftVelocity = leftVelocity;
	private float insRightVelocity = rightVelocity;
}

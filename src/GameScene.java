import javafx.animation.Animation;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.*;
import javafx.scene.text.Font;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javafx.event.EventHandler;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
import javafx.scene.transform.Rotate;

import javax.swing.Timer;
/*
Class responsbile for making scene wherein the game takes place
 */

public class GameScene extends Scene {

	public GameScene(Region root, Stage stage, Scene nextScene, Frame frame) {
		super(root);
		DEFAULT_WIDTH = Constants.DEFAULT_WIDTH;
		DEFAULT_HEIGHT = Constants.DEFAULT_HEIGHT;
		this.stage = stage;
		stage.widthProperty().addListener((obs, oldVal, newVal) -> {
			paintElementsAfterWidthChange(DEFAULT_WIDTH, (float)stage.getWidth());
			DEFAULT_WIDTH = (float)stage.getWidth();

		});
	/*	stage.heightProperty().addListener((obs, oldVal, newVal) -> {
//			paintElements((float)oldVal, (float)newVal);
		}); */
		this.nextScene = nextScene;
//		cfg = new Config();
	}
	
	public Scene initiateGame(Enum difficulty) {
		/*
		Depending on difficulty argument we set fallVelocity
		 */
		rocket = new Rocket(difficulty);
	//	rocket.setFallVelocity(getFallVelocity(difficulty));

		numberOfMountains = Utils.intFromConfig(cfg, "mountainsCount");
		coinsQuantity = Utils.intFromConfig(cfg, "coinsQuantity");
		coins = new Coin[coinsQuantity];
		for(Integer i = 1; i <= coinsQuantity; i++) {
			coins[i - 1] = new Coin(Utils.intFromConfig(cfg,"coin" + i + "X"), Utils.intFromConfig(cfg,"coin" + i + "Y"));
		}
		fuel = new Fuel(Utils.intFromConfig(cfg,"fuelX"), Utils.intFromConfig(cfg,"fuelY"));
		fuelRectangle = fuel.paint();
		fuelBar = new FuelBar(530, 20);
		Text levelNumber = setLevelNumber();
		velXText = setStartVelXText();
		velYText = setStartVelYText();
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
		centerX = new SimpleDoubleProperty();
		centerY = new SimpleDoubleProperty();

		circle = new Ellipse(0, 0, 5, 5);
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
	
	private Text setStartVelXText() {
		Text velX = new Text();
		StringBuilder builder = new StringBuilder();
		builder.append("PredkoscX:");
		builder.append(Double.toString(rocket.getInsRightVelocity() + rocket.getInsLeftVelocity()));
		velX.setText(builder.toString());
		velX.setX(3);
		velX.setY(10);
		velX.setFill(Color.GREEN);
		Font font = new Font(14);
		velX.setFont(font);
		
		return velX;
			
	}

	private Text setStartVelYText() {
		Text velY = new Text();
		StringBuilder builder = new StringBuilder();
		builder.append("PredkoscY:");
		builder.append(Double.toString(rocket.getFallVelocity()));
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
				stage.setScene(nextScene);
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
	Method checks if rocket has a collision with landingZone,
	if velocities of rocket weren`t to fast rocket successfully lands,
	else rocket crashes
	 */
	public void checkForLandingZoneCollision() {
		if (line.contains(circle.getCenterX(), circle.getCenterY())) {
			if((rocket.getInsRightVelocity() + rocket.getInsLeftVelocity()) < maxVelX
			&& (rocket.getInsFallVelocity() < maxVelY)) {
				System.out.println("Ladowanie");
				circle.setVisible(true);
				rocketAnimation.stop();
			}
			else {
				System.out.println("Kolizja");
				circle.setVisible(false);
				rocketAnimation.stop();
			}
			stage.setScene(nextScene);
		}

	}
	/*
	Method checks if rocket had move out of bounds
	 */
	public void checkOutOfBoundsCollision() {
		if((circle.getCenterX() < 0) || (circle.getCenterX() > Constants.DEFAULT_WIDTH)
		|| (circle.getCenterY() < 0) || (circle.getCenterY() > Constants.DEFAULT_HEIGHT)) {
			rocketAnimation.stop();
			stage.setScene(nextScene);
		}
	}
	/*
	Method calls methods responsible for checking for collisions
	 */
	private void checkForCollisions() {
		checkForLandingZoneCollision();
		checkForMountainCollision();
		checkForFuelCollision();
		checkOutOfBoundsCollision();
	}

	/*
	Method returning fall velocity of the Rocket (Integer) gets difficulty (Integer) as argument
	 */
	private float getFallVelocity(int difficulty) {
		float fallVelocity = 0;
		switch(difficulty){
			case 1:
				fallVelocity = 0.5f;
			break;

			case 2:
				fallVelocity = 0.55f;
			break;

			case 3:
				fallVelocity = 0.6f;
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
					//if collision happen animation stops
					checkForCollisions();
					// set velocity with which rocket falls down
					rocket.increaseInsFallVelocity();
					centerY.setValue(centerY.getValue() + rocket.getInsFallVelocity());
					root.getChildren().remove(velXText);
					setVelX();
					root.getChildren().add(velXText);
					setVelY();
					//make rocket burn some amount of it's fuel per frame
					rocket.burnFuel();
					//update fuel bar width
					actualizeFuelBar(root, fuelBar, rocket);
					//controls
					root.setOnKeyPressed(k -> {
						actualizeVelTexts(k.getCode());
						if(rocket.getFuel() > 0) {
							if (k.getCode() == KeyCode.UP) {
								rocket.accUpVelocity();
								centerY.setValue(centerY.getValue() + rocket.getInsUpVelocity());
								rocket.restartInsFallVelocity();
							}
							else if (k.getCode() == KeyCode.DOWN)
								centerY.setValue(centerY.getValue() + 6);
							else if (k.getCode() == KeyCode.LEFT) {
								rocket.accLeftVelocity();
								centerX.setValue(centerX.getValue() + rocket.getInsLeftVelocity());
								rocket.restartInsFallVelocity();
							}
							else if (k.getCode() == KeyCode.RIGHT) {
								rocket.accRightVelocity();
								centerX.setValue(centerX.getValue() + rocket.getInsRightVelocity());
								rocket.restartInsFallVelocity();
							}
							else if(k.getCode() == KeyCode.SPACE) {
								if(rocketAnimation.getStatus() == Animation.Status.STOPPED)
									rocketAnimation.playFromStart();
								else
									rocketAnimation.stop();
							}
							}
					});
					/*
					When user realease key, velocity returns to starting value
					 */
					root.setOnKeyReleased(k -> {
						if(k.getCode() == KeyCode.UP) {
							actualizeVelTexts(k.getCode());
							rocket.resetUpVelocity();
						}
						else if(k.getCode() == KeyCode.LEFT) {
							actualizeVelTexts(k.getCode());
							rocket.resetLeftVelocity();
						}
						else if(k.getCode() == KeyCode.RIGHT) {
							actualizeVelTexts(k.getCode());
							rocket.resetRightVelocity();
						}
					});
//					System.out.println("Vel X " + (rocket.getInsRightVelocity() + rocket.getInsLeftVelocity()));
					System.out.println(rocket.getInsFallVelocity());
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
	/*
	Method sets display of velocity by x axis
	if rocket can land display is green, else is red
	 */
	private void setVelX() {
		// instant value of rocket by x axis
		float xValue = rocket.getInsRightVelocity() + rocket.getInsLeftVelocity();
		velXText = new Text();
		StringBuilder builder = new StringBuilder();
		builder.append("PredkoscX:");
		builder.append(Float.toString(xValue));
		velXText.setText(builder.toString());
		velXText.setX(3);
		velXText.setY(10);
		if(Math.abs(xValue) < maxVelX)
			velXText.setFill(Color.GREEN);
		else
			velXText.setFill(Color.RED);
		Font font = new Font(14);
		velXText.setFont(font);
	}
	/*
	Method actualizes velYText when any button is pressed, or released,
	if rocket can land display is green, else is red
	 */
	private void setVelX(KeyCode k) {
		// instant value of rocket by x axis
		float xValue = rocket.getInsRightVelocity() + rocket.getInsLeftVelocity();
		velXText = new Text();
		StringBuilder builder = new StringBuilder();
		builder.append("PredkoscX:");
		builder.append(Float.toString(xValue));
		velXText.setText(builder.toString());
		velXText.setX(3);
		velXText.setY(10);
		if(Math.abs(xValue) < maxVelX)
			velXText.setFill(Color.GREEN);
		else
			velXText.setFill(Color.RED);
		Font font = new Font(14);
		velXText.setFont(font);
	}

	/*
	Method actualizes velYText when any button is pressed, or released
	if rocket can land display is green, else is red
	 */
	private void setVelY(KeyCode k) {
		// instant value of rocket by y axis
		float yValue = 0;
		velYText = new Text();
		StringBuilder builder = new StringBuilder();
		builder.append("PredkoscY:");
		if(k == KeyCode.UP) {
			yValue = rocket.getInsFallVelocity() + rocket.getInsUpVelocity();
			builder.append(Double.toString(yValue));
		}
		else {
			yValue = rocket.getInsFallVelocity();
			builder.append(Double.toString(yValue));
		}
		velYText.setText(builder.toString());
		velYText.setX(3);
		velYText.setY(25);
		if(yValue < maxVelY)
			velYText.setFill(Color.GREEN);
		else
			velYText.setFill(Color.RED);
		Font font = new Font(14);
		velYText.setFont(font);
	}
	/*
	Method actualizes velYText when any button isn`t pressed,
	if rocket can land display is green, else is red
	 */
	private void setVelY() {
		// instant value of rocket by y axis
		float yValue = rocket.getInsFallVelocity();
		root.getChildren().remove(velYText);
		velYText = new Text();
		StringBuilder builder = new StringBuilder();
		builder.append("PredkoscY:");
		builder.append(Double.toString(Utils.round(yValue)));
		velYText.setText(builder.toString());
		velYText.setX(3);
		velYText.setY(25);
		if(yValue < maxVelY)
			velYText.setFill(Color.GREEN);
		else
			velYText.setFill(Color.RED);
		Font font = new Font(14);
		velYText.setFont(font);
		root.getChildren().add(velYText);
	}
	/*
	Method actualizes velocity Texts, removes old texts, to add new ones
	 */
	private void actualizeVelTexts(KeyCode k) {
		root.getChildren().removeAll(velXText, velYText);
		setVelX();
		setVelY(k);
		setVelY(k);
		root.getChildren().addAll(velXText, velYText);
	}
	/*
	Method paints all elements on screen every change of stage width
	 */
	private void paintElementsAfterWidthChange(float oldValue, float newValue) {
		float difference = newValue - oldValue;
		if(root == null) return;
		if(mountains[0] != null) {
			float startX = (float)stage.getMinWidth();
			float endX = (float) mountains[0].getEndX() + difference;
			float controlX1 = (float) mountains[0].getControlX1() + difference;
			float controlX2 = (float) mountains[0].getControlX2() + difference;
			float startY = (float) mountains[0].getStartY();
			float endY = (float) mountains[0].getEndY();
			float controlY1 = (float) mountains[0].getControlY1();
			float controlY2 = (float) mountains[0].getControlY2();
			root.getChildren().remove(mountains[0]);
			CubicCurve mountain = new CubicCurve();
			mountain.setStartX(startX);
			mountain.setStartY(startY);
			mountain.setEndX(endX);
			mountain.setEndY(endY);
			mountain.setControlX1(controlX1);
			mountain.setControlX2(controlX2);
			mountain.setControlY1(controlY1);
			mountain.setControlY2(controlY2);
			mountains[0] = mountain;
			root.getChildren().add(mountains[0]);
		}
		if(circle != null) {
			double x = circle.getCenterX();
			double y = circle.getCenterY();
			double radiusX = circle.getRadiusX() + difference/10;
			double radiusY = circle.getRadiusY();
			root.getChildren().remove(circle);
			circle = new Ellipse(x, y, radiusX, radiusY);
			centerX.setValue(x);
			centerY.setValue(y);
			circle.centerXProperty().bind(centerX);
			circle.centerYProperty().bind(centerY);
			root.getChildren().add(circle);
		}
	}






	private float DEFAULT_WIDTH;
	private float DEFAULT_HEIGHT;
	private Coin[] coins;
	private int numberOfMountains;
	private int coinsQuantity;
	private FuelBar fuelBar;
	private Rectangle fuelBarRectangle  = new Rectangle();
	private Rocket rocket;
	private Text timeText; 
	private Config cfg = new Config(Player.getActualLevel());
	private Timeline rocketAnimation;
	private Rectangle rect;
	private Ellipse circle;
	private Line line;
	private CubicCurve[] mountains;
	private Group root;
	private Fuel fuel;
	private Rectangle fuelRectangle = new Rectangle();
	private Text velXText;
	private Text velYText;
	// maximum values of velocity by which rocket can successfully land
	private float maxVelX = Utils.floatFromConfig(cfg, "maxVelX");
	private float maxVelY = Utils.floatFromConfig(cfg, "maxVelY");
	private Stage stage;
	private Scene nextScene;
	private DoubleProperty centerX;
	private DoubleProperty centerY;
}

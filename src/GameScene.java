import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.text.Font;
import java.util.*;
import java.util.Timer;
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


public class GameScene extends Scene {

	public GameScene(Region root) {
		super(root);
		DEFAULT_WIDTH = Constants.DEFAULT_WIDTH;
		DEFAULT_HEIGHT = Constants.DEFAULT_HEIGHT;
		cfg = new Config();
	}
	
	public Scene initiateGame() {
		Path path = new Path();
		
	//	numberOfMountains = Integer.parseInt(cfg.getProperty("mountainsCount"));
		coinsQuantity = Utils.intFromConfig(cfg, "coinsQuantity");
	/*	MoveTo moveTo = new MoveTo();
		moveTo.setX(0.0f);
		moveTo.setY(300.0f);
		mountains = new CubicCurveTo[numberOfMountains]; */
		
	/*	for(Integer i = 1; i <= numberOfMountains - 1; i++) {// -1 w forze bo narazie recznie narysujemy trzecia gorke
			mountains[i-1] = new CubicCurveTo();
			mountains[i-1].setControlX1(Float.parseFloat(cfg.getProperty("mountain"+i+"ControlX")));
			mountains[i-1].setControlY1(Float.parseFloat(cfg.getProperty("mountain"+i+"ControlY")));
			mountains[i-1].setX(Float.parseFloat(cfg.getProperty("mountain"+i+"X")));
			mountains[i-1].setY(Float.parseFloat(cfg.getProperty("mountain"+i+"Y")));
		}

		mountains[numberOfMountains - 1] = new CubicCurveTo();

		LineTo landingZone = new LineTo(Integer.parseInt(cfg.getProperty("landingZoneStart")), Integer.parseInt(cfg.getProperty("landingZoneEnd"))); 
		mountains[numberOfMountains - 1].setControlX1(300.0f);
		mountains[numberOfMountains - 1].setControlY1(200.0f);
		mountains[numberOfMountains - 1].setControlX2(500.0f);
		mountains[numberOfMountains - 1].setControlY2(200.0f);
		mountains[numberOfMountains - 1].setX(600.0f);
		mountains[numberOfMountains - 1].setY(300.0f);
		*/
		rocket = new Rocket();
		coins = new Coin[coinsQuantity];


		for(Integer i = 1; i <= coinsQuantity; i++) {
			coins[i-1] = new Coin(Integer.parseInt(cfg.getProperty("coin"+i+"X")), Integer.parseInt(cfg.getProperty("coin"+i+"Y")));
		}
		// Coin firstCoin = new Coin(140, 200);
		// Coin secondCoin = new Coin(500, 180);
		//Fuel fuel = new Fuel(540, 220);
		Fuel fuel = new Fuel(Integer.parseInt(cfg.getProperty("fuelX")), Integer.parseInt(cfg.getProperty("fuelY")));
		FuelBar fuelBar = new FuelBar(530, 20);
		
		Text levelNumber = setLevelNumber();
		Text velXText = setVelXText();
		Text velYText = setVelYText();
		LevelTimer timer = new LevelTimer();
		timeText = setTimeText(timer);
		
		//path.getElements().addAll(moveTo, mountains[0], mountains[1],landingZone, mountains[2]);

//		mountains = new Rectangle(0, 50, 600, 20);
        mountains = new CubicCurve[3];
        mountains[0] = new CubicCurve();
        mountains[1] = new CubicCurve();
        mountains[2] = new CubicCurve();
        line = new Line();
        line.setStartX(300);
        line.setStartY(295);
        line.setEndX(400);
        line.setEndY(295);
        line.setStrokeWidth(10);
        mountains[0].setStartX(0);
        mountains[0].setStartY(300);
        mountains[0].setEndX(150);
        mountains[0].setEndY(300);
        mountains[0].setControlX1(120);
        mountains[0].setControlY1(100);
        mountains[1].setStartX(150);
        mountains[1].setStartY(300);
        mountains[1].setEndX(300);
        mountains[1].setEndY(300);
        mountains[1].setControlX1(280);
        mountains[1].setControlY1(200);
        mountains[2].setStartX(400);
        mountains[2].setStartY(300);
        mountains[2].setEndX(600);
        mountains[2].setEndY(300);
        mountains[2].setControlX1(300);
        mountains[2].setControlY1(200);
        mountains[2].setControlX2(500);
        mountains[2].setControlY2(200);



//		mountains.getTransforms().add(new Rotate(-45,0,0));
		DoubleProperty mountainsProp = new SimpleDoubleProperty();




		
//		Pane root = new Pane(path);

/*		rocketAnimation = new Timeline(
				new KeyFrame(new Duration(10.0), t ->  {
					checkForColision();
					int fallingVelocity = 1;             // predkosc z jaka rakieta opada
					rocket.setCenterY(rocket.getCenterY().getValue() + fallingVelocity);
					root.setOnKeyPressed(k -> {
						if(k.getCode() == KeyCode.UP)
							rocket.setCenterY(rocket.getCenterY().getValue() - 10);
						else if(k.getCode() == KeyCode.DOWN)
							rocket.setCenterY(rocket.getCenterY().getValue() + 6);
						else if(k.getCode() == KeyCode.LEFT)
							rocket.setCenterX(rocket.getCenterX().getValue() - 6);
						else if(k.getCode() == KeyCode.RIGHT)
							rocket.setCenterX(rocket.getCenterX().getValue() + 6);

					});
				})
		); */
//		rect = new Rectangle(200,50,20,20);
		DoubleProperty centerX = new SimpleDoubleProperty();
		DoubleProperty centerY = new SimpleDoubleProperty();

//		rect = rocket.paint();
		circle = new Circle(0, 0, 5);
		circle = rocket.paint();

		root = new Group( circle, line);
		for(int i = 0; i <mountains.length; i++)
	    	root.getChildren().add(mountains[i]);
/*		root.getChildren().addAll(levelNumber,
				fuel.paint(), fuelBar.paint(), fuelBar.fillFuel(rocket.getFuel()),
				velXText, velYText, timeText); */
		root.setFocusTraversable(true);

		rocketAnimation = new Timeline(
				new KeyFrame(new Duration(10.0), t ->  {
					checkForColision();
					int fallingVelocity = 1;             // predkosc z jaka rakieta opada
					centerY.setValue(centerY.getValue() + fallingVelocity);
					root.setOnKeyPressed(k -> {
						if(k.getCode() == KeyCode.UP)
							centerY.setValue(centerY.getValue() - 10);
						else if(k.getCode() == KeyCode.DOWN)
							centerY.setValue(centerY.getValue() + 6);
						else if(k.getCode() == KeyCode.LEFT)
							centerX.setValue(centerX.getValue() - 6);
						else if(k.getCode() == KeyCode.RIGHT)
							centerX.setValue(centerX.getValue() + 6);
					});
				})
		);

		
				//adding all the coins to root
		for(Integer i = 0; i < coinsQuantity; i++) {
			root.getChildren().addAll(coins[i].paint());
		}

		Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		rocketAnimation.setCycleCount(Timeline.INDEFINITE);
//		rocket.translateXProperty().bind(rocket.getCenterX());
//		rocket.translateYProperty().bind(rocket.getCenterY());

//		rect.translateXProperty().bind(centerX);
//		rect.translateYProperty().bind(centerY);
//		rect.translateXProperty().bind(centerX);
//		rect.translateYProperty().bind(centerY);
//		mountains.translateXProperty().bind(mountainsProp);
//		mountains.translateYProperty().bind(mountainsProp);
		circle.centerXProperty().bind(centerX);
		circle.centerYProperty().bind(centerY);
		rocketAnimation.playFromStart();
//		rocket.setCenterY(centerY);
//		rocket.setCenterX(centerX);
		centerX.setValue(300);
		centerY.setValue(100);
		mountainsProp.setValue(250);
		root.requestFocus();
		return scene;
	}
	/*
	 * Ustawia wartosc pola tekstowego wypisujacego poziom w lewym dolnym rogu
	 */
	private Text setLevelNumber() {
		Text levelNumber = new Text();

		//CONFIG TEST
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
	Sprawdza czy rakieta miala kolizje, w tym wypadku zatrzymuje animacje
	 */
	public void checkForColision() {
	/*	if(rocket.paint().intersects(mountains.getBoundsInParent())) {
			rocketAnimation.stop();
			rocket.setVisible(false);
		} */
/*		if(rect.getBoundsInLocal().intersects(mountains.getBoundsInParent())) {
			System.out.println("Kolizja");
			rocketAnimation.stop();
	//		rect.setVisible(false);
		}*/
	/*	if(mountains.contains(rect.getX(), rect.getY())) {
			System.out.println("Kolizja");
			rocketAnimation.stop();
			rect.setVisible(false);
		} */
	/*	if(circle.intersects(mountains.getBoundsInParent())) {
			System.out.println("Kolizja");
			rocketAnimation.stop();
			circle.setVisible(false);
		} */
	/*    if(circle.intersects(line.getBoundsInLocal())) {
            System.out.println("Kolizja");
            rocketAnimation.stop();
            circle.setVisible(false);
        } */
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


 /*       if(circle.intersects(mountain.getBoundsInLocal())) {
            System.out.println("Kolizja");
            rocketAnimation.stop();
            circle.setVisible(false);
        } */

	}


	private float DEFAULT_WIDTH;
	private float DEFAULT_HEIGHT;
//	private CubicCurveTo[] mountains;
	private Coin[] coins;
	private int numberOfMountains;
	private int coinsQuantity;
	private Rocket rocket;
	private Text timeText; 
	private Config cfg;
//	private Rectangle mountains;
	private Timeline rocketAnimation;
	private Rectangle rect;
	private Circle circle;
	private Line line;
	private CubicCurve firstMountain;
	private CubicCurve[] mountains;
	private Group root;

}

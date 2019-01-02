import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import java.util.*;
/*
 * Klasa sluzaca do przelaczania miedzy oknami
 * 
 */

public class Frame extends Application {
	public Frame() {
		scenes = new ArrayList<Scene>();
	}

	/*
	Method Initiating the Stage and Scenes and reading css stylesheet
	 */
	@Override
	public void start(Stage stage) {
		setDifficultyScene = new Scene(new Region());
		leaderboardScene = new Scene(new Region());
		pickNickScene = new Scene(new Region());
		scoreScene = ScoreScene.getScoreScene(stage, this);
		gameScene = new GameScene(new Region(), stage, scoreScene);
		setDifficultyScene = SetDifficulty.getSetDifficultyScene(stage, gameScene);
		pickNickScene = PickNick.getPickNickScene(stage, setDifficultyScene);
		mainMenuScene = MainMenu.getMainMenu(stage, leaderboardScene, pickNickScene);


		//stylesheets setup
		linkStylesheets();

		scenes.add(mainMenuScene);
		scenes.add(gameScene);
		stage.setTitle("Lunar Lander");
		stage.setScene(mainMenuScene);
		stage.show();
	}

	private void linkStylesheets() {
		mainMenuScene.getStylesheets().add("css/style.css");
		setDifficultyScene.getStylesheets().add("css/style.css");
		pickNickScene.getStylesheets().add("css/style.css");
		scoreScene.getStylesheets().add("css/style.css");
	}
	/*
	 * Sluzy do wywolania okna w mainie
	 */
	public void launchFrame(String[] args) {
		launch(args);
	}
	public Scene getMainMenuScene(Stage stage) {
		mainMenuScene = MainMenu.getMainMenu(stage, leaderboardScene, pickNickScene);
		mainMenuScene.getStylesheets().add("css/style.css");
		return mainMenuScene;

	}
	
	private ArrayList<Scene> scenes;
	private GameScene gameScene;
	private Scene mainMenuScene;
	private Scene setDifficultyScene;
	private Scene leaderboardScene;
	private Scene pickNickScene;
	private Scene scoreScene;
}

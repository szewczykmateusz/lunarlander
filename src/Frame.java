import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.*;
/*
 * Klasa sluzaca do przelaczania miedzy oknami
 * 
 */

public class Frame extends Application {

	private GameScene gameScene;
	private Scene mainMenuScene;
	private Scene setDifficultyScene;
	private Scene leaderboardScene;
	private Scene pickNickScene;
	private Scene scoreScene;
	private Stage stage;

	public Frame() {}

	/*
	Method Initiating the Stage and Scenes and reading css stylesheet
	 */
	@Override
	public void start(Stage stage) {
		stage.widthProperty().addListener((obs, oldVal, newVal) -> {
			Constants.setDefaultWidth((float)stage.getWidth() - 15);

		});
		this.stage = stage;
		leaderboardScene = new Scene(new Region());
		scoreScene = ScoreScene.getScoreScene(this);
		gameScene = new GameScene(new Region(), stage, scoreScene, this);
		setDifficultyScene = SetDifficulty.getSetDifficultyScene(this);
		pickNickScene = PickNick.getPickNickScene(this);
		mainMenuScene = MainMenu.getMainMenu(this, stage);


		//stylesheets setup
		linkStylesheets();

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
	 * Launches the window in Main Interface
	 */
	public void launchFrame(String[] args) {
		launch(args);
	}

	public void setMainMenuScene() {
		mainMenuScene = MainMenu.getMainMenu(this, stage);
		stage.setScene(mainMenuScene);
		mainMenuScene.getStylesheets().add("css/style.css");
	}
	public void setPickNickScene() {
		pickNickScene = PickNick.getPickNickScene(this);
		stage.setScene(pickNickScene);
		pickNickScene.getStylesheets().add("css/style.css");
	}
	public void setScoreScene() {
		scoreScene = ScoreScene.getScoreScene(this);
		stage.setScene(scoreScene);
		scoreScene.getStylesheets().add("css/style.css");

	}
	public void setGameScene(Enum difficulty) {
		try {
			Client client = new Client();
			client.getLevel(Player.getActualLevel());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		gameScene = new GameScene(new Region(), stage, scoreScene, this);
		stage.setScene(gameScene.initiateGame(difficulty));
	}
	public void setSetDifficultyScene() {
		setDifficultyScene = SetDifficulty.getSetDifficultyScene(this);
		stage.setScene(setDifficultyScene);
		setDifficultyScene.getStylesheets().add("css/style.css");
	}
	public void setLeaderboardScene() {stage.setScene(leaderboardScene);}
}

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
	private Scene startScene;

	public Frame() {}

	/*
	Method Initiating the Stage and Scenes and reading css stylesheet
	 */
	@Override
	public void start(Stage stage) {
		stage.widthProperty().addListener((obs, oldVal, newVal) -> {
			Constants.setDefaultWidth((float)stage.getWidth() - 15);

		});
		stage.heightProperty().addListener((obs, oldVal, newVal) -> {
			Constants.setDefaultHeight((float)stage.getHeight() - 38);

		});
		this.stage = stage;
		leaderboardScene = LeaderboardScene.getLeaderboardScene(this);
		scoreScene = ScoreScene.getScoreScene(this);
//		gameScene = new GameScene(new Region(), stage, scoreScene, this);
		setDifficultyScene = SetDifficulty.getSetDifficultyScene(this);
		pickNickScene = PickNick.getPickNickScene(this);
		mainMenuScene = MainMenu.getMainMenu(this, stage);
		startScene = StartScene.getStartScene(this);
		if(Constants.ifServerConnection) {
				try {
					Client client = new Client();
					client.getLevel(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}

		//stylesheets setup
		linkStylesheets();

		stage.setTitle("Lunar Lander");
		stage.setScene(startScene);
		stage.show();
	}

	private void linkStylesheets() {
		mainMenuScene.getStylesheets().add("css/style.css");
		setDifficultyScene.getStylesheets().add("css/style.css");
		pickNickScene.getStylesheets().add("css/style.css");
		scoreScene.getStylesheets().add("css/style.css");
		startScene.getStylesheets().add("css/style.css");
	}

	/*
	 * Launches the window in Main Interface
	 */
	public void launchFrame(String[] args) {
		launch(args);
	}

	public void setMainMenuScene() {
		Player.reset();
		Player.resetPlayerScore();
		stage.setWidth(stage.getWidth() + 0.1);
		stage.setHeight(stage.getHeight() + 0.1);
		mainMenuScene = MainMenu.getMainMenu(this, stage);
		stage.setScene(mainMenuScene);
		mainMenuScene.getStylesheets().add("css/style.css");
	}
	public void setPickNickScene() {
		stage.setWidth(stage.getWidth() - 1);
		stage.setHeight(stage.getHeight() - 1);
		pickNickScene = PickNick.getPickNickScene(this);
		stage.setScene(pickNickScene);
		pickNickScene.getStylesheets().add("css/style.css");
	}
	public void setScoreScene() {
		stage.setWidth(stage.getWidth() + 1);
		stage.setHeight(stage.getHeight() + 1);
		scoreScene = ScoreScene.getScoreScene(this);
		stage.setScene(scoreScene);
		scoreScene.getStylesheets().add("css/style.css");

	}
	public void setGameScene(Enum difficulty) {
		if(Constants.ifServerConnection) {
			if (Player.getLastLevelStatus() == 1 || !Player.hasPlayed()) {
				try {
					Client client = new Client();
					client.getLevel(Player.getActualLevel());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//read the config that is already retrieved from the server
		//Config cfg = new Config(Player.getActualLevel());
		gameScene = new GameScene(new Region(), stage, scoreScene, this);
		stage.setWidth(Constants.GAME_SCENE_DEFAULT_WIDTH);
		stage.setHeight(Constants.GAME_SCENE_DEFAULT_HEIGHT);
		stage.setScene(gameScene.initiateGame(difficulty));
	}
	public void setSetDifficultyScene() {
		stage.setWidth(stage.getWidth() - 1);
		stage.setHeight(stage.getHeight() - 1);
		setDifficultyScene = SetDifficulty.getSetDifficultyScene(this);
		stage.setScene(setDifficultyScene);
		setDifficultyScene.getStylesheets().add("css/style.css");
	}
	public void setLeaderboardScene() {
		stage.setWidth(stage.getWidth() - 1);
		stage.setHeight(stage.getHeight() - 1);
		leaderboardScene = LeaderboardScene.getLeaderboardScene(this);
		stage.setScene(leaderboardScene);
		leaderboardScene.getStylesheets().add("css/style.css");
	}

	public Stage getStage() {return stage;}
}

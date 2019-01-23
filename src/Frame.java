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
	public Frame() {}

	/*
	Method Initiating the Stage and Scenes and reading css stylesheet
	 */
	@Override
	public void start(Stage stage) {
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
	 * Sluzy do wywolania okna w mainie
	 */
	public void launchFrame(String[] args) {
		launch(args);
	}

	public void setMainMenuScene() {stage.setScene(mainMenuScene);}
	public void setPickNickScene() {stage.setScene(pickNickScene);}
	public void setScoreScene() {stage.setScene(scoreScene);}
	public void setGameScene(Enum difficulty) {stage.setScene(gameScene.initiateGame(difficulty));}
	public void setSetDifficultyScene() {stage.setScene(setDifficultyScene);}
	public void setLeaderboardScene() {stage.setScene(leaderboardScene);}
	/*
	Methods creates new scenes after latest scene changes
	 */
	public void setPickNickSceneAfterChanges() {
		pickNickScene = PickNick.getPickNickScene(this);
		stage.setScene(pickNickScene);
		pickNickScene.getStylesheets().add("css/style.css");
	}

	private GameScene gameScene;
	private Scene mainMenuScene;
	private Scene setDifficultyScene;
	private Scene leaderboardScene;
	private Scene pickNickScene;
	private Scene scoreScene;
	private Stage stage;
}

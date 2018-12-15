import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Scene;
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
	Method Initiating the Stage and Scenes
	 */
	@Override
	public void start(Stage stage) {
		gameScene = new GameScene(new Region());
		setDifficultyScene = new Scene(new Region());
		leaderboardScene = new Scene(new Region());
		setDifficultyScene = SetDifficulty.getSetDifficultyScene(stage, gameScene);
		mainMenu = MainMenu.getMainMenu(stage, leaderboardScene, setDifficultyScene);
		scenes.add(mainMenu);
		scenes.add(gameScene);
		stage.setTitle("Lunar Lander");
		stage.setScene(mainMenu);
		stage.show();
	}
	/*
	 * Sluzy do wywolania okna w mainie
	 */
	public void launchFrame(String[] args) {
		launch(args);
	}
	
	private ArrayList<Scene> scenes;
	private GameScene gameScene;
	private Scene mainMenu;
	private Scene setDifficultyScene;
	private Scene leaderboardScene;
}

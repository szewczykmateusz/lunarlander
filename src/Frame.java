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

	@Override
	public void start(Stage stage) {
		Region root = new Region();
		gameScene = new GameScene(root);
		mainMenu = MainMenu.getMainMenu();
		scenes.add(mainMenu);
		scenes.add(gameScene);
		Integer gameSceneIndex = Utils.getSceneIndex(scenes, gameScene);
		Integer mainMenuIndex = Utils.getSceneIndex(scenes, mainMenu);
		//mainMenu.setActions(stage);
		stage.setTitle("Lunar Lander");
		//stage.setScene(scenes.get(mainMenuIndex));
		stage.setScene(gameScene.initiateGame());
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

}

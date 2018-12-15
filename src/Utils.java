import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;

/*
Utils Class containing common public methods used throughout the project
 */

public class Utils {

    //Returns the index of scene from the scenes list. Arguments
    //Arguments: List of AbstractScene instances, String a name of the scene
    public static int getSceneIndex(ArrayList<Scene> scenes, Scene sceneName) {
        return scenes.indexOf(sceneName);
    }

    /*
    Class sets up actions for buttons, accepts Stage, Next Scene and Button as arguments
    */
    public static void setUpActions(Button button, Stage stage, Scene nextScene) {
        button.setOnAction(e -> stage.setScene(nextScene));
    }

    /*
    Class sets up action for button starting the game, accepts Stage, Next Scene and Button as arguments
     */
    public static void startGame(Button button, Stage stage, GameScene gameScene) {
        button.setOnAction(e -> stage.setScene(gameScene.initiateGame()));
    }
}

import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;
import java.lang.Math;

/*
Utils Class containing common public methods used throughout the project
 */

public class Utils {

    //Returns the index of scene from the scenes list. Arguments
    //Arguments: List of Scene instances, String a name of the scene
    public static int getSceneIndex(ArrayList<Scene> scenes, Scene sceneName) {
        return scenes.indexOf(sceneName);
    }

    /*
    Method sets up actions for buttons, accepts Stage, Next Scene and Button as arguments
    */
    public static void setUpActions(Button button, Stage stage, Scene nextScene) {
        button.setOnAction(e -> stage.setScene(nextScene));
    }

    /*
    Method sets up action for button starting the game, accepts Stage, Next Scene, Button and difficulty level as arguments
     */
    public static void startGame(Button button, Frame frame, Enum difficulty) {
        button.setOnAction(e -> frame.setGameScene(difficulty));
    }

    /*
    Method returning parsed (to integer) value from config file
     */
    public static Integer intFromConfig(Config cfg, String property) {
        return Integer.parseInt(cfg.getProperty(property));
    }
    /*
    Method returning parsed (to float) value from config file
     */
    public static float floatFromConfig(Config cfg, String property) {
        return Float.parseFloat(cfg.getProperty(property));
    }
    /*
    Method returning parsed (to double) value from config file
     */
    public static Double doubleFromConfig(Config cfg, String property) {
        return Double.parseDouble(cfg.getProperty(property));
    }
    /*
    Method round number with specified precision
     */
    public static float round(float number) {
        double x = Math.pow(10, precision);
        number *= (float)x;
        number /= (float)x;
        return number;
    }

    private final static int precision = 2;
}

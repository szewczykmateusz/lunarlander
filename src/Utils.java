import javafx.scene.control.Button;
import javafx.scene.Scene;
import java.util.*;

public class Utils {

    //Returns the index of scene from the scenes list. Arguments
    //Arguments: List of AbstractScene instances, String a name of the scene
    public static int getSceneIndex(ArrayList<Scene> scenes, Scene sceneName) {
        return scenes.indexOf(sceneName);
    }

}

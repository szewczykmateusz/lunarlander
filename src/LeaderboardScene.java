import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/*
Class creates scene which shows users final score and 10 best results
 */
public class LeaderboardScene extends Scene {
    public  LeaderboardScene(Region root) {super(root);}

    public static Scene getLeaderboardScene(Frame frame) {


        VBox layout = new VBox(10);
        return new Scene(layout, Constants.getDefaultWidth(), Constants.getDefaultHeight());
    }

}

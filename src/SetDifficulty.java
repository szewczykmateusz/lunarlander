import java.util.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
   Main Menu Class - creates Main Menu
*/
public class SetDifficulty extends Scene{
    public SetDifficulty(Region root) {super(root);}
    /*
        Method returns Set Difficulty Scene
    */
    public static Scene getSetDifficultyScene(Frame frame) {
        Label label  = new Label("Set Game Difficulty");
        Button easy = new Button("Easy");
        Button medium = new Button("Medium");
        Button hard = new Button("Hard");

        /*
        Actions:
         */
        Utils.startGame(easy, frame, Difficulty.EASY);
        Utils.startGame(medium , frame, Difficulty.MEDIUM);
        Utils.startGame(hard, frame, Difficulty.HARD);

        /*
        Layout:
         */
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, easy, medium, hard);

        return new Scene(layout, Constants.getDefaultWidth(), Constants.getDefaultHeight());
    }

}

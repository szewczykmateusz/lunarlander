import java.util.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
   Main Menu Class - creates Main Menu
*/
public class SetDifficulty {
    public SetDifficulty() {
    }
    /*
        Method returns Set Difficulty Scene
    */
    public static Scene getSetDifficultyScene(Stage stage, GameScene gameScene) {
        Label label  = new Label("Set Game Difficulty");
        Button easy = new Button("Easy");
        Button medium = new Button("Medium");
        Button hard = new Button("Hard");

        /*
        Actions:
         */
        Utils.startGame(easy, stage, gameScene, 1);
        Utils.startGame(medium , stage, gameScene, 2);
        Utils.startGame(hard, stage, gameScene, 3);

        /*
        Layout:
         */
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, easy, medium, hard);

        return new Scene(layout, 600, 400);
    }

}

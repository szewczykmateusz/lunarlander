import javafx.scene.control.Button;
import java.util.*;
import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
   Main Menu Class - creates Main Menu
*/
public class MainMenu {
    public MainMenu() {
    }
    /*
        Method returns Main Menu Scene
    */
    public static Scene getMainMenu(Stage stage, Scene leaderboardScene, Scene setDifficultyScene) {
        Label label  = new Label("Lunar Lander");
        Button newGame = new Button("New Game");
        Button leaderboard = new Button("Leaderboard");
        Button quit = new Button("Quit");

        /*
        Actions:
         */
        Utils.setUpActions(newGame, stage, setDifficultyScene);
        Utils.setUpActions(leaderboard, stage, leaderboardScene);
        quit.setOnAction(e -> Platform.exit());

        /*
        Layout:
         */
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, newGame, leaderboard, quit);

        return new Scene(layout, 600, 400);
    }

}

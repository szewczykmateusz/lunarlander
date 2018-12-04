import javafx.scene.control.Button;
import java.util.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenu {
    public MainMenu() {
    }

    public static Scene getMainMenu() {
        Label label  = new Label("Lunar Lander");
        Button newGame = new Button("New Game");
        Button leaderboard = new Button("Leaderboard");
        Button quit = new Button("Quit");

        //set action dla kazdego buttona zrob

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, newGame, leaderboard, quit);

        return new Scene(layout, 600, 400);
    }
}

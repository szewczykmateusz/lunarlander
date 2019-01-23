import javafx.scene.control.Button;
import java.util.*;
import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
   Main Menu Class - creates Main Menu
*/
public class MainMenu extends Scene{
    private static Stage stage;

    public MainMenu(Region root) {super(root);}
    /*
        Method returns Main Menu Scene
    */
    public static Scene getMainMenu(Frame frame, Stage aStage) {
        stage = aStage;
        Label label  = new Label("Lunar Lander");
        Button newGame = new Button("New Game");
        Button leaderboard = new Button("Leaderboard");
        Button quit = new Button("Quit");

        /*
        Actions:
         */
        configureNewGameButton(newGame, frame);
        configureLeaderboardButton(leaderboard, frame);
        quit.setOnAction(e -> Platform.exit());

        /*
        Layout:
         */
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, newGame, leaderboard, quit);

        return new Scene(layout, Constants.getDefaultWidth(), Constants.getDefaultHeight());
    }

    private static void configureNewGameButton(Button button, Frame frame) {
        button.setOnAction(e -> {
            if(stage.getWidth() != Constants.getDefaultWidth()) {
                Constants.setDefaultWidth((float)stage.getWidth());
                frame.setPickNickSceneAfterChanges();
            }
            else
                frame.setPickNickScene();
        });
    }

    private static void configureLeaderboardButton(Button button, Frame frame) {
        button.setOnAction(e -> {
            frame.setLeaderboardScene();
        });
    }
}

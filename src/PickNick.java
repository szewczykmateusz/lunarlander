import javafx.scene.control.Button;
import java.util.*;
import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PickNick {
    public PickNick() {}

    public static Scene getPickNickScene(Stage stage, Scene setDifficultyScene) {
        Label label  = new Label("Pick your nick: ");

        //Adding TextField
        TextField inputNick = new TextField ();
        inputNick.setText("Nick");
        inputNick.clear();


        //Adding Next button
        Button nextButton = new Button("Next");

        //Button action
        Utils.setUpActions(nextButton, stage, setDifficultyScene);

        //Layout:
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, inputNick, nextButton);

        return new Scene(layout, 600, 400);
    }
}

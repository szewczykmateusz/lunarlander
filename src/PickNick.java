import javafx.scene.control.Button;
import java.util.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class PickNick extends Scene {
    public PickNick(Region root) {super(root);}

    public static Scene getPickNickScene(Frame frame) {
        Label label = new Label("Pick your nick: ");

        //Adding TextField
        TextField inputNick = new TextField();
        inputNick.clear();


        //Adding Next button
        Button nextButton = new Button("Next");
        Text controls = new Text("CONTROLS:");
        Text moveLeft = new Text("MOVE LEFT - LEFT ARROW");
        Text moveRight = new Text("MOVE RIGHT - RIGHT ARROW");
        Text moveUp = new Text("MOVE UP - UP ARROW");
        Text pause = new Text("PAUSE - SPACE");

        //Layout:
        VBox layout = new VBox(20);

        //Button action
        configureButtonAction(nextButton, frame, inputNick, layout);


        layout.getChildren().addAll(label, inputNick,
                controls, moveLeft, moveRight, moveUp, pause, nextButton);


        return new Scene(layout, Constants.getDefaultWidth(), Constants.getDefaultHeight());
    }

    /*
    Method configuring the next button action, gets input value
     */
    private static void configureButtonAction(Button button, Frame frame, TextField input, VBox layout) {
        button.setOnAction(e -> {
            Player.setName(input.getText());
            frame.setSetDifficultyScene();
        });
        layout.setOnKeyPressed(k -> {
            if (k.getCode() == KeyCode.ENTER) {
                Player.setName(input.getText());
                frame.setSetDifficultyScene();
            }
        });
    }

}
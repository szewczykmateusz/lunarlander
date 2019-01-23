import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
Class creates scene which shows users result after every level
 */
public class ScoreScene extends Scene {
    public ScoreScene(Region root) {super(root);}

    public static Scene getScoreScene(Frame frame) {
        Label label = new Label("Score: ");
        Button advanceButton = new Button("Advance");
        Button quitButton = new Button("Quit");
        configureQuitButtonAction(quitButton, frame);
        HBox layout = new HBox(200);
        layout.getChildren().addAll(advanceButton, quitButton);

        return new Scene(layout, Constants.getDefaultWidth(), Constants.getDefaultHeight());
    }

    private static void configureQuitButtonAction(Button button, Frame frame) {
        button.setOnAction(e -> {
            frame.setMainMenuScene();
        });
    }
}

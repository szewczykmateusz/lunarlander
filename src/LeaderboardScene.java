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
    private static Button backButton = new Button("Back");

    public  LeaderboardScene(Region root) {super(root);}

    public static Scene getLeaderboardScene(Frame frame) {
        configureBackButtonAction(backButton, frame);


        VBox layout = new VBox(10);
        layout.getChildren().add(backButton);
        return new Scene(layout, Constants.getDefaultWidth(), Constants.getDefaultHeight());
    }

    /*
    Method backs to main manu
     */
    private static void configureBackButtonAction(Button button, Frame frame) {
        backButton.setOnAction(e ->
                frame.setMainMenuScene());
    }

}

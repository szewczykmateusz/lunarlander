import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
Class creates scene which shows users result after every level
 */
public class ScoreScene extends Scene {
    public ScoreScene(Region root) {super(root);}

    public static Scene getScoreScene(Frame frame) {
        Button button1, button2;

        Text mainText = new Text();
        mainText.setX(50);
        mainText.setY(390);
        Font font = new Font(20);
        mainText.setFont(font);

        if(Player.hasPlayed() && Player.getLastLevelStatus() == 1) {
            mainText.setText("You successfully landed!");
            button1 = new Button("Next Level");
            button2 = new Button("Main Menu");
        } else {
            mainText.setText("You crashed!");
            button1 = new Button("Retry");
            button2 = new Button("Main Menu");
        }

        Text score = new Text();
        StringBuilder builder = new StringBuilder();
        builder.append("Your Score: ");
        builder.append(Double.toString(Player.getPlayerScore()));
        score.setText(builder.toString());
        score.setX(50);
        score.setY(300);
        score.setFont(font);
        configureQuitButtonAction(button2, frame);
        VBox layout = new VBox(10);
        layout.getChildren().addAll(mainText, score, button1, button2);

        return new Scene(layout, Constants.DEFAULT_WIDTH, Constants.DEFAULT_HEIGHT);
    }

    private static void configureQuitButtonAction(Button button, Frame frame) {
        button.setOnAction(e -> {
            frame.setMainMenuScene();
        });
    }


    /*
        Method starts next level of the game
     */
//    private static void configureNextLevelButton() {
//
//    }
}

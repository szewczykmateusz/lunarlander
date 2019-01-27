import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
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
        Font font = new Font(12);
        Text latestResult = new Text("Your score: " + Player.getPlayerScore());
        latestResult.setFont(font);
        latestResult.setFill(Color.DARKRED);
        latestResult.setUnderline(true);
        Text title = new Text("Best scores:");
        title.setFont(font);
        BestScoresConfig bestScores = Player.getBestScores();

        VBox layout = new VBox(10);
        layout.getChildren().addAll(latestResult, title);
        for(int i = 0; i < bestScores.getResultsNumber(); i++) {
            Text result = new Text(i+1 + ". " +
                    bestScores.getResult(i).getValue() + " "
            + bestScores.getResult(i).getKey());
            result.setFont(font);
            layout.getChildren().add(result);
        }


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

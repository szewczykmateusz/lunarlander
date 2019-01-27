import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
/**
Method asks user if wants server connection
 */
public class StartScene extends  Scene {
    public StartScene(Region root) {super(root);}

    public static Scene getStartScene(Frame frame) {
        Text ask = new Text("Do you want connection with server");
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        yesButton.setOnAction(e ->{
            Constants.ifServerConnection = true;
            frame.setMainMenuScene();
        });
        noButton.setOnAction(e ->{
            Constants.ifServerConnection = false;
            frame.setMainMenuScene();
        });
        VBox layout = new VBox(20);
        layout.getChildren().addAll(ask, yesButton, noButton);
        return new Scene(layout, Constants.getDefaultWidth(), Constants.getDefaultHeight());
    }

}

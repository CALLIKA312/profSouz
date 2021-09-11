import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.StageClass;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        StageClass.setPrStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("views/mainFrame.fxml"));
        primaryStage.setTitle("Профсоюз");
        primaryStage.setScene(new Scene(root, 1100, 620));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package flight6f;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainSearch extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("searchpanel.fxml"));
        primaryStage.setTitle("Flight6F Search Engine");

        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

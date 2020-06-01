package sample;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ChartWindow.fxml"));
        primaryStage.setTitle("ЧАТ");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);
    }
}

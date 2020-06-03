package sample;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Chart extends Application {

    public static String title = "Чат. Пользователь: ";
    public static int titlelong = title.length();
    public static Stage stage;

    public  Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        Chart.stage = stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ChartWindow.fxml"));
        primaryStage.setTitle("ЧАТ");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        primaryStage.show();
    }
// перегруженный метод start()

    public void start(Stage primaryStage, String string) throws Exception {
        Parent root  = FXMLLoader.load(getClass().getResource("ChartWindow.fxml"));
        primaryStage.setTitle(title + string); // передаю логин пока что сюда
        primaryStage.setScene(new Scene(root, 600, 400));
        setStage(primaryStage);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        primaryStage.show();}

    public static void main(String[] args) {
        launch(args);
    }
}

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void init() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/content.fxml"));
        primaryStage.setTitle("Magazyn Database Manager");
        Rectangle2D rect = Screen.getPrimary().getVisualBounds();
        primaryStage.setScene(new Scene(root));
        primaryStage.setX((rect.getWidth() - 100) / 2.0);
        primaryStage.setY(rect.getHeight() - 100 / 2.0);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.requestFocus();

    }

    @Override
    public void stop(){

    }

    public static void main(String[] args) {
            Application.launch(args);
    }

}

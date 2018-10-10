package sample;

import databse.DatabseConnection;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.sql.*;



public class Main extends Application {

    @Override
    public void init() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Warsztat Manager");
        Rectangle2D rect = Screen.getPrimary().getVisualBounds();
        primaryStage.setScene(new Scene(root));
        primaryStage.setX((rect.getWidth() - 100) / 2.0);
        primaryStage.setY(rect.getHeight() - 100 / 2.0);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.requestFocus();
        System.out.println(Screen.getPrimary().getVisualBounds());
        System.out.println(Screen.getPrimary().getDpi());
        System.out.println(primaryStage.hasProperties());


    }

    @Override
    public void stop(){



    }


}

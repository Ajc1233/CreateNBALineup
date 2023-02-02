/**
 * This program is built to allow the user to select a year between 1952 and 2022 and build an NBA lineup based on the
 * players that played in the league that year. You can then assign a certain number of minutes to each player and their
 * season stats will change based on the minutes assigned. Once the lineup has been set, the user can save the lineup to
 * a folder and load them whenever they run the program again.
 *
 */

package com.lineupcreator.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 *
 * @author alecc
 */

public class StartingPage extends Application {


    public static void main(String[] args) {

        launch(args);
    }

    public void start(Stage stage) throws Exception {
        //Creates the opening stage but using the OpeningScene fxml file

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OpeningScene.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
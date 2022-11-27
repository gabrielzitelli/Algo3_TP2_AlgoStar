package edu.fiuba.algo3;

import edu.fiuba.algo3.controladores.BienvenidaControlador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    public static int INITIAL_WIDTH = 1200;
    public static int INITIAL_HEIGHT = 800;

    public static void run(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/bienvenidaVista.fxml"));
        loader.setRoot(new AnchorPane());

        Scene scene = new Scene(loader.load(), INITIAL_WIDTH, INITIAL_HEIGHT);
        stage.setTitle("AlgoStar");

        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/icon.png"))));
        stage.setResizable(false);
        stage.show();
    }

    //Start Viejo que mostraba la version de java fx
    /*@Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();


        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }*/

}
package edu.fiuba.algo3;

import edu.fiuba.algo3.controladores.BienvenidaControlador;
import edu.fiuba.algo3.modelo.AlgoStar.AlgoStar;
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

    public static int INITIAL_WIDTH = 1024;
    public static int INITIAL_HEIGHT = 600;
    public static AlgoStar algoStar = new AlgoStar();

    public static void run(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/bienvenidaVista.fxml"));
        loader.setRoot(new AnchorPane());

        Scene scene = new Scene(loader.load(), INITIAL_WIDTH, INITIAL_HEIGHT);
        stage.setScene(scene);

        stage.setTitle("AlgoStar");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/AS_logo_space.png"))));
        stage.setResizable(false);
        stage.show();
    }
}
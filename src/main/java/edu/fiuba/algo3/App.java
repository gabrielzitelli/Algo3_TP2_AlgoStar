package edu.fiuba.algo3;

import edu.fiuba.algo3.controladores.InicioControlador;
import edu.fiuba.algo3.modelo.AlgoStar.AlgoStar;
import edu.fiuba.algo3.modelo.AlgoStar.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    public static final int INITIAL_WIDTH = 1124;
    public static final int INITIAL_HEIGHT = 600;
    public static final AlgoStar algoStar = new AlgoStar();

    public static void run(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Logger.obtener().toggle(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/inicioVista.fxml"));
        loader.setRoot(new BorderPane());

        Scene scene = new Scene(loader.load(), INITIAL_WIDTH, INITIAL_HEIGHT);
        stage.setScene(scene);
        InicioControlador controlador = loader.getController();

        stage.setTitle("AlgoStar");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/brand/AS_logo_space.png"))));

       //scene.setCursor(new ImageCursor(image));
        stage.show();
        controlador.inicializar();
    }
}
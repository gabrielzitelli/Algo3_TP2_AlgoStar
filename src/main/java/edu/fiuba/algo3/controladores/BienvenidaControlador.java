package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class BienvenidaControlador extends Controlador {
    @FXML
    protected Button comenzarBoton;
    private Scene scene;
    private Stage stage;
    Parent root;

    @FXML
    public void empezarCreacionJugador(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/datosJugadoresVista.fxml"));
        root = loader.load();

        stage = obtenerStageActual(event);
        this.scene = new Scene(root, App.INITIAL_WIDTH, App.INITIAL_HEIGHT);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void pruebaDeMovimiento(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/testMapa.fxml"));
        root = loader.load();

        stage = obtenerStageActual(event);
        this.scene = new Scene(root, App.INITIAL_WIDTH, App.INITIAL_HEIGHT);

        stage.setScene(scene);
        stage.show();
    }
}

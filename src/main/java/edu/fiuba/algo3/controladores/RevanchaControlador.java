package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RevanchaControlador extends Controlador {
    @FXML
    private Button botonRevancha;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
    }

    @FXML
    public void empezarRevancha(ActionEvent event) throws IOException {
        App.algoStar.revancha();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/finDePartidaVista.fxml"));

        Stage stage = obtenerStageActual(event);
        Scene scene = new Scene(loader.load(), App.INITIAL_WIDTH, App.INITIAL_HEIGHT);

        stage.setScene(scene);
    }
}



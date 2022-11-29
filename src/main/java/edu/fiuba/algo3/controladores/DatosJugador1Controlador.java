package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DatosJugador1Controlador extends Controlador{
    private Stage stage;
    private Scene scene;
    @FXML
    private ChoiceBox razaBox;
    ObservableList<String> razaLista = FXCollections.observableArrayList("Protoss", "Zergs");

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
        this.razaBox.setItems(razaLista);
    }

    @FXML
    public void empezarCreacionJugador1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/datosJugador2Vista.fxml"));

        stage = obtenerStageActual(event);
        this.scene = new Scene(loader.load(), App.INITIAL_WIDTH, App.INITIAL_HEIGHT);

        stage.setScene(scene);
    }
}

package edu.fiuba.algo3.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DatosJugador2Controlador extends Controlador {
    @FXML
    private ChoiceBox razaBox2;
    ObservableList<String> razaLista = FXCollections.observableArrayList("Protoss", "Zergs");

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
        this.razaBox2.setItems(razaLista);
    }
}

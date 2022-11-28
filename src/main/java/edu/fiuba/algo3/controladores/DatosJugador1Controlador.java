package edu.fiuba.algo3.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class DatosJugador1Controlador {
    @FXML
    private ChoiceBox razaBox;
    ObservableList<String> razaLista = FXCollections.
            observableArrayList("Protoss", "Zergs");
    @FXML
    public void initialize() {
        this.razaBox.setItems(razaLista);
    }
}

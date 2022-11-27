package edu.fiuba.algo3.controladores;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public abstract class Controlador {

    public Stage obtenerStageActual(ActionEvent event){
        return (Stage)(((Node)(event.getSource())).getScene().getWindow());
    }

    public Stage obtenerStageActual(Node nodo){
        return (Stage)(nodo.getScene().getWindow());
    }
}

package edu.fiuba.algo3.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class Controlador implements Initializable {

    protected boolean opcionesAbiertas = false;
    public abstract void initialize(URL url, ResourceBundle resourceBundle);

    public Stage obtenerStageActual(ActionEvent event){
        return (Stage)(((Node)(event.getSource())).getScene().getWindow());
    }

    public Scene obtenerSceneActual(Node nodo){
        return nodo.getScene();
    }

    public Stage obtenerStageActual(Node nodo){
        return (Stage)(nodo.getScene().getWindow());
    }

    protected void cerrarOpciones() {
        opcionesAbiertas = false;
    }
}

package edu.fiuba.algo3.controladores;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public abstract class Pantalla {
    protected Scene escena;

    public Scene getScene() {
        return escena;
    }

    protected void crearEscena(Stage stage) {
        stage.setTitle("escena default");

        TilePane r = new TilePane();

        Label l = new Label("Escena Default, implementar");

        r.getChildren().add(l);

        escena = new Scene(r, 600, 600);
    }
}

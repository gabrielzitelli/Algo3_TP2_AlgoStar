package edu.fiuba.algo3.TestGui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Escena1 extends Escena{

    public Escena1(Stage stage) {
        crearEscena(stage);
    }
    protected void crearEscena(Stage stage) {
        stage.setTitle("escena1");

        Button b = new Button("adelante");

        TilePane r = new TilePane();

        Label l = new Label("Escena1");

        EventHandler<ActionEvent> evento = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(new Escena2(stage).escena);
            }
        };

        b.setOnAction(evento);

        r.getChildren().add(b);
        r.getChildren().add(l);

        escena = new Scene(r, 200, 200);
    }
}

package edu.fiuba.algo3.TestGui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Escena2 extends Escena{

    public Escena2(Stage stage){
        crearEscena(stage);
    }
    protected void crearEscena(Stage stage) {
        stage.setTitle("escena2");

        Button b = new Button("atras");

        TilePane r = new TilePane();

        Label l = new Label("Escena2");

        EventHandler<ActionEvent> evento = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(new Escena1(stage).escena);
            }
        };

        b.setOnAction(evento);

        r.getChildren().add(b);
        r.getChildren().add(l);

        escena = new Scene(r, 300, 200);
    }
}

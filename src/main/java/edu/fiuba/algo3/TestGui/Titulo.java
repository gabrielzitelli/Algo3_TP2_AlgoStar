package edu.fiuba.algo3.TestGui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Titulo extends Escena{

    public Titulo(Stage stage) {
        crearEscena(stage);
    }

    @Override
    protected void crearEscena(Stage stage){
        stage.setTitle("AlgoStar wtf!");
        //stage.setResizable(false);
        BorderPane panelPrincipal = new BorderPane();
        HBox contenedorTituloSuperior = crearTitulo();
        VBox contenedorCentroPantalla = crearCentro();
        HBox contenedorBotonSalir = crearBarraInferior();

        panelPrincipal.setTop(contenedorTituloSuperior);
        panelPrincipal.setCenter(contenedorCentroPantalla);
        panelPrincipal.setBottom(contenedorBotonSalir);

        escena = new Scene(panelPrincipal, 500, 500);
    }

    private HBox crearBarraInferior() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 5, 10, 5));
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.setStyle("-fx-background-color: #336699");

        Button button = new Button("Salir");
        Font fuente = Font.font("Times New Roman", FontWeight.BOLD, 16);
        button.setFont(fuente);

        hBox.getChildren().add(button);

        return hBox;
    }

    private VBox crearCentro() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setSpacing(30);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #6995c2");

        Circle circle = new Circle(80, Color.rgb(113,113,163));
        Label texto = new Label("AlgoStar");
        Font fuente = Font.font("Times New Roman", FontWeight.BOLD, 24);
        texto.setFont(fuente);
        texto.setTextFill(Color.WHITE);

        StackPane panel = new StackPane();
        panel.getChildren().add(circle);
        panel.getChildren().add(texto);
        panel.setPadding(new Insets(0,10,20,10));

        Button boton = new Button("Empezar");
        boton.setFont(fuente);
        boton.setPadding(new Insets(10,10,10,10));

        vBox.getChildren().add(panel);
        vBox.getChildren().add(boton);

        return vBox;
    }
    private HBox crearTitulo() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 5, 10, 5));
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color: #336699");

        Label texto = new Label("AlgoStar - Pantalla de Titulo Demo");
        Font fuente = Font.font("Times New Roman", FontWeight.BOLD, 16);
        texto.setFont(fuente);
        texto.setTextFill(Color.WHITE);

        hBox.getChildren().add(texto);

        return hBox;
    }
}

package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.App;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RevanchaControlador extends Controlador {
    @FXML
    protected Button botonRevancha;
    @FXML
    protected Text textoJugadorGanador;
    @FXML
    protected Button botonMenuPrincipal;
    @FXML
    protected AnchorPane anchorPaneRevancha;

    @FXML
    protected BorderPane mainBorderPaneMapa;
    @FXML
    protected SubScene subsceneRoot;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle){
    }

    public void incializar(BorderPane mainBorderPaneMapa, SubScene subsceneRoot, String nombreJugadorGanador){
        this.mainBorderPaneMapa = mainBorderPaneMapa;
        this.subsceneRoot = subsceneRoot;
        textoJugadorGanador.setText(nombreJugadorGanador);
    }

    @FXML
    public void empezarRevancha(ActionEvent event) {
        App.algoStar.revancha();

        FadeTransition ocultarPaneRevancha = new FadeTransition(Duration.millis(400), anchorPaneRevancha);
        ocultarPaneRevancha.setToValue(0);
        ocultarPaneRevancha.play();
        ocultarPaneRevancha.setOnFinished(e -> {
            mainBorderPaneMapa.getChildren().remove(anchorPaneRevancha);
            mainBorderPaneMapa.getChildren().remove(subsceneRoot);
        });
    }

    @FXML
    public void cargarMenuPrincipal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/inicioVista.fxml"));
        loader.setRoot(new BorderPane());
        Parent root = loader.load();

        Stage stage = obtenerStageActual(event);
        Scene scene = new Scene(root, App.INITIAL_WIDTH, App.INITIAL_HEIGHT);

        InicioControlador controlador = loader.getController();
        controlador.inicializar(stage);

        stage.setScene(scene);

        App.algoStar.reiniciar();
    }
}



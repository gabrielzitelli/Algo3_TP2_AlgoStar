package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BienvenidaControlador extends Controlador implements Initializable {
    @FXML
    protected Button comenzarBoton;
    @FXML
    protected MediaView videoSpace;
    private Scene scene;
    private Stage stage;
    Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.inicializarVideoFondo();
    }

    private void inicializarVideoFondo(){
        //Prepare el mediaPlayer para setearlo en videoSpace
        String workingDir = System.getProperty("user.dir");
        File video = new File(workingDir, "src/main/resources/vid/space_flight_loop.mp4");
        Media media = new Media(video.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //Empieza a reproducir automaticamente y hace loop constante
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        videoSpace.setMediaPlayer(mediaPlayer);
    }

    @FXML
    public void empezarCreacionJugador1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/datosJugador1Vista.fxml"));
        root = loader.load();

        stage = obtenerStageActual(event);
        this.scene = new Scene(root, App.INITIAL_WIDTH, App.INITIAL_HEIGHT);

        stage.setScene(scene);
    }

    @FXML
    public void pruebaDeMovimiento(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/Mapa.fxml"));
        root = loader.load();
        MapaControlador controlador = (MapaControlador) loader.getController();

        stage = obtenerStageActual(event);
        this.scene = new Scene(root, App.INITIAL_WIDTH, App.INITIAL_HEIGHT);

        this.scene.setOnMouseClicked(controlador.pintarCasilla());
        this.scene.setOnKeyPressed(controlador.pressKey());
        this.scene.setOnKeyReleased(controlador.releaseKey());

        stage.setScene(scene);
    }
}

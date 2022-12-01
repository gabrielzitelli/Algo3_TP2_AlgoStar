package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.App;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BienvenidaControlador extends Controlador {
    @FXML
    protected Button comenzarBoton;
    @FXML
    protected MediaView videoSpace;
    @FXML
    protected ImageView algoStarImage;
    @FXML
    protected Label bienvenidoLabel;
    private Scene scene;
    private Stage stage;
    Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.inicializarVideoFondo();
    }

    private void inicializarVideoFondo(){
        //Prepare el mediaPlayer para setearlo en videoSpace
        Media media = new Media(Objects.requireNonNull(getClass().getResource("/vid/space_flight_loop.mp4")).toExternalForm());
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

        //comenzarBoton.setVisible(false);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), bienvenidoLabel);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        //Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/cursor5.png")));
        //scene.setCursor(new ImageCursor(image));

        stage.setScene(scene);
    }
}

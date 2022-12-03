package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.App;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.PortUnreachableException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InicioControlador extends Controlador {

    @FXML
    protected Button botonComenzar;
    @FXML
    protected MediaView mediaviewVideoFondo;
    @FXML
    protected ImageView imagenAlgostar;
    @FXML
    protected Label labelBienvenida;
    @FXML
    protected SubScene subsceneFormJugador1;
    @FXML
    protected SubScene subsceneFormJugador2;

    private Scene scene;
    private Stage stage;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.inicializarVideoFondo();
    }

    private void inicializarVideoFondo(){
        //Preparo el mediaPlayer para setearlo en videoSpace
        Media media = new Media(Objects.requireNonNull(getClass().getResource("/vid/space_flight_loop.mp4")).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //Empieza a reproducir automaticamente y hace loop constante
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        mediaviewVideoFondo.setMediaPlayer(mediaPlayer);
    }

    @FXML
    public void empezarCreacionJugadores(ActionEvent event) throws IOException {

        //Desaparece el texto de bienvenida
        FadeTransition animacionFadeOutBienvenida = new FadeTransition(Duration.millis(400), labelBienvenida);
        animacionFadeOutBienvenida.setToValue(0);
        animacionFadeOutBienvenida.play();

        //Se mueve la imagen de algostar para arriba
        TranslateTransition animacionMovimientoTitulo = new TranslateTransition(Duration.millis(550), imagenAlgostar);
        animacionMovimientoTitulo.setByY(-130);
        animacionMovimientoTitulo.setAutoReverse(true);
        animacionMovimientoTitulo.play();

        //Desaparece el boton de comenzar y lo desactivo
        FadeTransition animacionFadeOutBotonComenzar = new FadeTransition(Duration.millis(400), botonComenzar);
        animacionFadeOutBotonComenzar.setToValue(0);
        animacionFadeOutBotonComenzar.play();
        botonComenzar.setDisable(true);

        this.realizarCargaJugador1();
    }

    private void mostrarForm(SubScene subsceneForm){
        //Primero se esperan 500ms
        FadeTransition animacionEsperarYMostrarForm = new FadeTransition(Duration.millis(300), subsceneForm);
        animacionEsperarYMostrarForm.setFromValue(0);
        animacionEsperarYMostrarForm.setToValue(0);
        animacionEsperarYMostrarForm.setOnFinished(e -> {

            //Luego se muestra
            FadeTransition animacionMostrarForm = new FadeTransition(Duration.millis(300), subsceneForm);
            animacionMostrarForm.setFromValue(0);
            animacionMostrarForm.setToValue(1);
            animacionMostrarForm.play();

        });

        animacionEsperarYMostrarForm.play();

        subsceneForm.setVisible(true);
    }

    private void realizarCargaJugador1() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vistas/datosJugador1Vista.fxml"));
        root = loader.load();

        //Coloco la vista cargada en subsceneForm
        subsceneFormJugador1.setRoot(root);

        mostrarForm(subsceneFormJugador1);

        DatosJugador1Controlador datosJugador1Controlador = loader.getController();
        datosJugador1Controlador.setInicioControlador(this);
    }

    public void realizarCargaJugador2(Parent root, FXMLLoader loader) {
        //Desaparece el subsceneFormJugador1 y lo desactivo
        FadeTransition animacionFadeOutSubsceneJugador1 = new FadeTransition(Duration.millis(400), subsceneFormJugador1);
        animacionFadeOutSubsceneJugador1.setToValue(0);
        animacionFadeOutSubsceneJugador1.play();
        animacionFadeOutSubsceneJugador1.setOnFinished(e -> {
            subsceneFormJugador1.setDisable(true);
            subsceneFormJugador1.setVisible(false);
        });

        subsceneFormJugador2.setRoot(root);
        mostrarForm(subsceneFormJugador2);

        DatosJugador2Controlador datosJugador2Controlador = loader.getController();
        datosJugador2Controlador.setInicioControlador(this);
        datosJugador2Controlador.ponerFocusEnForm();
    }

    //loader ya está cargado
    public void iniciarJuego(Parent root, FXMLLoader loader) throws InterruptedException {
        //Desaparece el subsceneFormJugador1 y lo desactivo
        FadeTransition animacionFadeOutSubsceneJugador2 = new FadeTransition(Duration.millis(400), subsceneFormJugador2);
        animacionFadeOutSubsceneJugador2.setToValue(0);
        animacionFadeOutSubsceneJugador2.play();
        animacionFadeOutSubsceneJugador2.setOnFinished(e -> {
            subsceneFormJugador2.setDisable(true);
            subsceneFormJugador2.setVisible(false);
        });

        //Oculto el titulo y acelero el mediaplayer
        FadeTransition animacionFadeOutImagenAlgostar = new FadeTransition(Duration.millis(400), imagenAlgostar);
        animacionFadeOutImagenAlgostar.setToValue(0);
        animacionFadeOutImagenAlgostar.play();
        animacionFadeOutImagenAlgostar.setOnFinished(e -> {
            imagenAlgostar.setVisible(false);
            mediaviewVideoFondo.getMediaPlayer().setRate(8.0);

            FadeTransition desaparecerVideo = new FadeTransition(Duration.millis(1000), mediaviewVideoFondo);
            desaparecerVideo.setToValue(0);
            desaparecerVideo.play();

            FadeTransition animacionWait = new FadeTransition(Duration.millis(1000), imagenAlgostar);
            animacionWait.setToValue(0);
            animacionWait.play();
            animacionWait.setOnFinished(event -> {

                //Seteo la nueva escena luego de acelerar
                stage = obtenerStageActual(imagenAlgostar);
                Scene scene = new Scene(root, App.INITIAL_WIDTH, App.INITIAL_HEIGHT);

                MapaControlador controladorMapa = loader.getController();
                scene.setOnMouseClicked(controladorMapa.pintarCasilla());
                scene.setOnKeyPressed(controladorMapa.pressKey());
                scene.setOnKeyReleased(controladorMapa.releaseKey());

                stage.setScene(scene);
            });
        });
    }
}

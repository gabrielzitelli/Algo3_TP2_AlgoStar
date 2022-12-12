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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class InicioControlador extends Controlador {

    private Stage stage;
    private double tamanioHorizontal;
    private double tamanioVertical;
    private double decoratorWidth;
    private double decoratorHeight;
    private boolean enPantallaCompleta = false;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.inicializarVideoFondo();
        this.inicializarSonido();
    }

    public void inicializarSonido() {
        ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();
        sonido.cargarMusica("temaPrincipal.mp3", "temaPrincipal");

        sonido.cargarSonido("atacar.wav", "atacar");
        sonido.cargarSonido("clickBoton.mp3", "boton");
        sonido.cargarSonido("colocarEdificio.mp3", "click");
        sonido.cargarSonido("cancelar.wav", "cancelar");

        sonido.cargarSonido("protoss/Dragon.wav", "dragon");
        sonido.cargarSonido("protoss/scout.wav", "scout");
        sonido.cargarSonido("protoss/zealot.wav", "zealot");

        sonido.cargarSonido("zerg/amoSupremo.wav", "amoSupremo");
        sonido.cargarSonido("zerg/devorador.wav", "devorador");
        sonido.cargarSonido("zerg/guardian.wav", "guardian");
        sonido.cargarSonido("zerg/hidralisco.wav", "hidralisco");
        sonido.cargarSonido("zerg/mutalisco.wav", "mutalisco");
        sonido.cargarSonido("zerg/zangano.wav", "zangano");
        sonido.cargarSonido("zerg/zerling.wav", "zerling");

        sonido.modificarVolumenEfectos(50);


        sonido.reproducirMusica("temaPrincipal");
        sonido.modificarVolumenMusica(50);
    }
    public void inicializar() {
        manejarRedimensionDelStage();
    }

    private void manejarRedimensionDelStage(){
        stage = obtenerStageActual(labelBienvenida);
        Scene scene = obtenerSceneActual(labelBienvenida);
        tamanioHorizontal = stage.getWidth();
        tamanioVertical = stage.getHeight();
        decoratorHeight = tamanioVertical - scene.getHeight();
        decoratorWidth = tamanioHorizontal - scene.getWidth();

        manejarRedimensionDelStageEnAncho();
        manejarRedimensionDelStageEnAlto();
    }

    private void manejarRedimensionDelStageEnAncho(){
        stage.widthProperty().addListener((o, oldValue, newValue) -> {
            if (newValue.intValue() < tamanioHorizontal) {
                stage.setResizable(false);
                stage.setWidth(tamanioHorizontal);
                stage.setResizable(true);

            } else
                mediaviewVideoFondo.setFitWidth(newValue.doubleValue());
        });
    }

    private void manejarRedimensionDelStageEnAlto(){
        stage.heightProperty().addListener((o, oldValue, newValue) -> {
            if (newValue.intValue() < tamanioVertical) {
                stage.setResizable(false);
                stage.setHeight(tamanioVertical);
                stage.setResizable(true);

            } else
                mediaviewVideoFondo.setFitHeight(newValue.doubleValue());
        });
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
        ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();
        sonido.reproducirFX("boton");
        this.prepararUIParaCargarJugadores();
        this.realizarCargaJugador1();
    }

    private void prepararUIParaCargarJugadores(){
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
    }

    @FXML
    public void manejarInput(KeyEvent keyEvent) throws IOException {
        KeyCode tecla = keyEvent.getCode();
        if (Objects.equals(tecla, KeyCode.F11)){
            setPantallaCompleta();
        }
    }
    private void setPantallaCompleta() {
        stage = this.obtenerStageActual(labelBienvenida);
        stage.setFullScreen(enPantallaCompleta = !stage.isFullScreen());
        mediaviewVideoFondo.setFitHeight(stage.getHeight());
        mediaviewVideoFondo.setFitWidth(stage.getWidth());
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
        Parent root = loader.load();

        //Coloco la vista cargada en subsceneForm
        subsceneFormJugador1.setRoot(root);
        mostrarForm(subsceneFormJugador1);

        DatosJugador1Controlador datosJugador1Controlador = loader.getController();
        datosJugador1Controlador.setInicioControlador(this);
    }

    /**
     * @param loader Llega cargado ( se le hizo loader.load() )
     */
    public void realizarCargaJugador2(Parent root, FXMLLoader loader) {
        prepararUIParaCargarJugador2();

        subsceneFormJugador2.setRoot(root);
        mostrarForm(subsceneFormJugador2);

        DatosJugador2Controlador datosJugador2Controlador = loader.getController();
        datosJugador2Controlador.setInicioControlador(this);
        datosJugador2Controlador.ponerFocusEnForm();
    }

    private void prepararUIParaCargarJugador2(){
        //Desaparece el subsceneFormJugador1 y lo desactivo
        FadeTransition animacionFadeOutSubsceneJugador1 = new FadeTransition(Duration.millis(400), subsceneFormJugador1);
        animacionFadeOutSubsceneJugador1.setToValue(0);
        animacionFadeOutSubsceneJugador1.play();
        animacionFadeOutSubsceneJugador1.setOnFinished(e -> {
            subsceneFormJugador1.setDisable(true);
            subsceneFormJugador1.setVisible(false);
        });
    }

    /**
     * @param loader Llega cargado ( se le hizo loader.load() )
     */
    public void prepararInicioDelJuego(Parent root, FXMLLoader loader) {

        hacerDesaparecersubsceneFormJugador2();

        //Oculto la imagenAlgostar
        FadeTransition animacionFadeOutImagenAlgostar = new FadeTransition(Duration.millis(400), imagenAlgostar);
        animacionFadeOutImagenAlgostar.setToValue(0);
        animacionFadeOutImagenAlgostar.play();
        animacionFadeOutImagenAlgostar.setOnFinished(e -> {

            imagenAlgostar.setVisible(false);

            //Acelero el video de fondo
            mediaviewVideoFondo.getMediaPlayer().setRate(8.0);

            //Ejecuto animacion de fundido al negro del video
            FadeTransition desaparecerVideo = new FadeTransition(Duration.millis(1000), mediaviewVideoFondo);
            desaparecerVideo.setToValue(0);
            desaparecerVideo.play();

            //Espero a que termine el fundido al negro y comienzo el juego
            FadeTransition animacionWait = new FadeTransition(Duration.millis(1000), imagenAlgostar);
            animacionWait.setToValue(0);
            animacionWait.play();
            animacionWait.setOnFinished(event -> ejecutarComienzoDeJuego(root, loader) );
        });

        ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();
        sonido.detenerMusica();
    }

    private void hacerDesaparecersubsceneFormJugador2(){
        //Desaparece el subsceneFormJugador2 y lo desactivo
        FadeTransition animacionFadeOutSubsceneJugador2 = new FadeTransition(Duration.millis(400), subsceneFormJugador2);
        animacionFadeOutSubsceneJugador2.setToValue(0);
        animacionFadeOutSubsceneJugador2.play();
        animacionFadeOutSubsceneJugador2.setOnFinished(e -> {
            subsceneFormJugador2.setDisable(true);
            subsceneFormJugador2.setVisible(false);
        });
    }

    private void ejecutarComienzoDeJuego(Parent root, FXMLLoader loader){
        stage = obtenerStageActual(imagenAlgostar);
        Scene scene = new Scene(root, App.INITIAL_WIDTH, App.INITIAL_HEIGHT);

        //Preparo controles del mapa
        MapaControlador controladorMapa = loader.getController();
        scene.setOnMouseClicked(controladorMapa.pintarCasilla());
        scene.setOnKeyPressed(controladorMapa.pressKey());
        scene.setOnKeyReleased(controladorMapa.releaseKey());
        controladorMapa.setFocusOnCanvas();

        //Muestro la scene principal
        stage.setScene(scene);

        //Manejo el resize de la pantalla completa y termino de inicializar el mapa
        if (enPantallaCompleta)
            stage.setFullScreen(true);

        controladorMapa.setTamanioMinimo(tamanioHorizontal, tamanioVertical);
        controladorMapa.setDecorators(decoratorWidth, decoratorHeight);
        controladorMapa.inicializar();
    }
}

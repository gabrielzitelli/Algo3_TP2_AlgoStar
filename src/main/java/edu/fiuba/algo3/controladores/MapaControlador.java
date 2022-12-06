package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controladores.ElementosGui.Camara;
import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.especial.EspecialVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.imperios.ProtossVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.imperios.ZergVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.Vista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.cargas.CargaVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.moho.MohoVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.recursos.RecursoVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.superficie.SuperficieVista;
import edu.fiuba.algo3.modelo.AlgoStar.AlgoStar;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class MapaControlador extends Controlador {

    @FXML
    protected Canvas canvasPrincipal;
    //@FXML
    //protected Text libreLabel;
    @FXML
    protected Text debugCoordenadas;
    @FXML
    protected Button pasarTurnoBoton;
    @FXML
    protected VBox bordeDerecha;
    @FXML
    protected VBox bordeIzquierda;


    /*=====================================================================================
     * Mapa y camara
     * ====================================================================================*/
    private final Mapa mapa = Mapa.obtener();
    private final int tamanioMapa = mapa.obtenerTamanioMapa();

    private Camara camara = new Camara(0,0);

    private double tamanioHorizontal;
    private double tamanioVertical;

    private double decoratorWidth;
    private double decoratorHeight;

    /*=====================================================================================
     * Graficos
     * ====================================================================================*/
    private GraphicsContext graphicsContext;
    private final Tile seleccion = new Tile("marcos/32px/seleccion.png");
    private Coordenada coordenadaSeleccion;
    private final int tileWidth = 32;

    /*=====================================================================================
     * GameLoop
     * ====================================================================================*/
    private final ArrayList<String> input = new ArrayList<>();
    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0;
    private  boolean arrayFilled = false;
    private double frameRate;
    private boolean mostrarFPS = false;

    /*=====================================================================================
     * AlgoStar
     * ====================================================================================*/
    private AlgoStar algoStar = App.algoStar;
    private Zerg imperioZerg;
    private Protoss imperioProtoss;

    /*==========  Borde Derecho   ==========*/
    @FXML
    private Text textoJugadorRaza;
    @FXML
    private Text textTurno;
    @FXML
    private Text textoCantMinerales;
    @FXML
    private Text textoCantGas;
    @FXML
    private Text textoCantPoblacion;
    @FXML
    private Text textoNombreJugador;

    @FXML
    protected Text textoNombreEdificio;
    @FXML
    protected ImageView imageviewEdificio;
    @FXML
    protected Text textoVida;
    @FXML
    protected Text textoEscudo;
    @FXML
    protected Pane paneInfoEdificio;
    @FXML
    protected Pane paneInfoImperio;

    @FXML
    protected Button botonEdificio1;
    @FXML
    protected Button botonEdificio2;
    @FXML
    protected Button botonEdificio3;
    @FXML
    protected Button botonEdificio4;
    @FXML
    protected Button botonEdificio5;
    @FXML
    protected Button botonEdificio6;
    @FXML
    protected Button botonEdificio7;
    @FXML
    protected Button botonEdificio8;
    @FXML
    protected Pane wrapperBotonEdificio1;
    @FXML
    protected Pane wrapperBotonEdificio2;
    @FXML
    protected Pane wrapperBotonEdificio3;
    @FXML
    protected Pane wrapperBotonEdificio4;
    @FXML
    protected Pane wrapperBotonEdificio5;
    @FXML
    protected Pane wrapperBotonEdificio6;
    @FXML
    protected Pane wrapperBotonEdificio7;
    @FXML
    protected Pane wrapperBotonEdificio8;

    protected Button[] arrayBotonesEdificio;
    protected Pane[] arrayWrappersBotonesEdificio;

    @FXML
    protected Button botonConstruirEdificio1;
    @FXML
    protected Button botonConstruirEdificio2;
    @FXML
    protected Button botonConstruirEdificio3;
    @FXML
    protected Button botonConstruirEdificio4;
    @FXML
    protected Button botonConstruirEdificio5;
    @FXML
    protected Pane wrapperBotonConstruirEdificio1;
    @FXML
    protected Pane wrapperBotonConstruirEdificio2;
    @FXML
    protected Pane wrapperBotonConstruirEdificio3;
    @FXML
    protected Pane wrapperBotonConstruirEdificio4;
    @FXML
    protected Pane wrapperBotonConstruirEdificio5;

    protected Button[] arrayBotonesContruirEdificio;
    protected Pane[] arrayWrappersBotonesConstruirEdificio;

    /*==========  Borde Izquierdo   ==========*/
    @FXML
    private ImageView imagenTerrenoSeleccionado;
    @FXML
    private Text textoTerrenoSeleccionado;
    @FXML
    private ImageView imagenRecursoSeleccionado;
    @FXML
    private Text textoRecursoSeleccionado;
    @FXML
    private ImageView imagenCargaSeleccionado;
    @FXML
    private Text textoCargaSeleccionado;
    @FXML
    private ImageView imagenContagioSeleccionado;
    @FXML
    private Text textoContagioSeleccionado;



    /*===================================================================================================
    * Metodos
    * ===================================================================================================*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializarMapa();
        pasarTurnoBoton.setFocusTraversable(false);
        App.algoStar.empezarJuego();

        actualizarColorJugador(algoStar.conseguirStringJugadorActual());
        actualizarInfoBordeDerecho();

        //Agrego efecto al imageview
        DropShadow efectorBordePNGBlanco = new DropShadow( 10, Color.WHITE );
        imageviewEdificio.setEffect(efectorBordePNGBlanco);

        arrayBotonesEdificio = new Button[]{botonEdificio1, botonEdificio2, botonEdificio3, botonEdificio4,
                                            botonEdificio5, botonEdificio6, botonEdificio7, botonEdificio8};

        arrayWrappersBotonesEdificio = new Pane[]{wrapperBotonEdificio1, wrapperBotonEdificio2, wrapperBotonEdificio3, wrapperBotonEdificio4,
                wrapperBotonEdificio5, wrapperBotonEdificio6, wrapperBotonEdificio7, wrapperBotonEdificio8};

        arrayBotonesContruirEdificio = new Button[]{botonConstruirEdificio1, botonConstruirEdificio2, botonConstruirEdificio3,
                botonConstruirEdificio4, botonConstruirEdificio5};

        arrayWrappersBotonesConstruirEdificio = new Pane[]{wrapperBotonConstruirEdificio1, wrapperBotonConstruirEdificio2, wrapperBotonConstruirEdificio3,
                wrapperBotonConstruirEdificio4, wrapperBotonConstruirEdificio5};

        for(Button botonEdificio : arrayBotonesEdificio){
            botonEdificio.setVisible(false);
            botonEdificio.setDisable(true);
        }

        for(Button botonConstruirEdificio : arrayBotonesContruirEdificio){
            botonConstruirEdificio.setVisible(false);
            botonConstruirEdificio.setDisable(true);
        }
        paneInfoImperio.setVisible(false);
    }

    public void inicializar() {
        Stage stage = obtenerStageActual(canvasPrincipal);
        Scene scene = obtenerSceneActual(canvasPrincipal);
        canvasPrincipal.setHeight(scene.getHeight());
        canvasPrincipal.setWidth(scene.getWidth() - bordeDerecha.getWidth() - bordeIzquierda.getWidth());

        int anchoMapa = (int) canvasPrincipal.getWidth();
        int largoMapa = (int) canvasPrincipal.getHeight();

        int bordeHorizontal = ((tileWidth * tamanioMapa) - anchoMapa);
        int bordeVertical = ((tileWidth * tamanioMapa) - largoMapa);
        camara = new Camara(bordeHorizontal, bordeVertical);

        menejarRedimension(stage);
    }

    private void menejarRedimension(Stage stage) {
        //manejar redimension
        stage.widthProperty().addListener((o, oldValue, newValue) -> {
            if (newValue.intValue() < tamanioHorizontal) {
                stage.setResizable(false);
                stage.setWidth(tamanioHorizontal);
                stage.setResizable(true);
            }
            else {
                double resto = 0;
                if (!stage.isFullScreen())
                    resto = decoratorWidth;
                canvasPrincipal.setWidth(stage.getWidth() - bordeDerecha.getWidth() - bordeIzquierda.getWidth() - resto);
                camara.setBordeX((tileWidth * tamanioMapa) - (int)canvasPrincipal.getWidth());

                if (Math.abs(camara.getX()) >= (tileWidth * tamanioMapa) - (int)canvasPrincipal.getWidth()){
                    camara.irHacia((tileWidth * tamanioMapa) - (int)canvasPrincipal.getWidth() , Math.abs(camara.getY()));
                }
            }
        });
        stage.heightProperty().addListener((o, oldValue, newValue) -> {
            if (newValue.intValue() < tamanioVertical) {
                stage.setResizable(false);
                stage.setHeight(tamanioVertical);
                stage.setResizable(true);
            }
            else {
                double resto = 0;
                if (!stage.isFullScreen())
                    resto = decoratorHeight;
                canvasPrincipal.setHeight(stage.getHeight() - resto);
                camara.setBordeY((tileWidth * tamanioMapa) - (int)canvasPrincipal.getHeight());
                if (Math.abs(camara.getY()) >= (tileWidth * tamanioMapa) - (int)canvasPrincipal.getHeight()){
                    camara.irHacia(Math.abs(camara.getX()), (tileWidth * tamanioMapa) - (int)canvasPrincipal.getHeight());
                }
            }
        });
    }

    private void inicializarMapa() {

        graphicsContext = canvasPrincipal.getGraphicsContext2D();

        AnimationTimer gameloop = crearGameLoop();
        gameloop.start();
    }

    private void renderizarMapa() {
        //renderizamos solo lo que se encuentra en pantalla
        int anchoMapa = (int) canvasPrincipal.getWidth() / (tileWidth);
        int largoMapa = (int) (canvasPrincipal.getHeight() / (tileWidth));

        for(int i = 0; i < anchoMapa + 2 ; i++){
            for(int j = 0; j < largoMapa + 2; j++){
                int xInicial = ( Math.abs(camara.getX()) / tileWidth) + i;
                int yInicial = ( Math.abs(camara.getY()) / tileWidth) + j;

                //verificamos que no nos salgamos del mapa
                if (xInicial >= tamanioMapa)
                    xInicial = 99;
                if (yInicial >= tamanioMapa)
                    yInicial =99;

                renderizarCasilla(xInicial, yInicial);
            }
        }

        //Renderizado de seleccion
        renderizarSeleccion();
    }
    private void renderizarSeleccion(){
        if (coordenadaSeleccion != null) {
            int x = coordenadaSeleccion.getCoordenadaX();
            int y = coordenadaSeleccion.getCoordenadaY();
            seleccion.render(graphicsContext, camara.getX() + (x*tileWidth), camara.getY() + (y*tileWidth));
        }
    }

    private void renderizarCasilla(int i, int j) {
        Coordenada coordenada = new Coordenada(i,j);
        Casilla casilla = mapa.obtenerCasilla(coordenada);
        //Coordenadas en pantalla
        int posicionX = camara.getX() + (i*tileWidth);
        int posicionY = camara.getY() + (j*tileWidth);

        renderizarPorCapas(casilla, posicionX, posicionY);
    }

    private void renderizarPorCapas(Casilla casilla, int posicionX, int posicionY) {
        JSONObject casillaJson = ConvertidorJSON.convertirAJSON(casilla);

        //Renderizamos la superficie
        Vista superficieVista = SuperficieVista.obtenerSuperficie(casillaJson.get(ConvertidorJSON.SUPERFICIE));
        superficieVista.render(graphicsContext, posicionX, posicionY);

        //Renderizamos el moho
        Vista mohoVista = MohoVista.obtenerMoho(casillaJson.get(ConvertidorJSON.MOHO));
        mohoVista.render(graphicsContext, posicionX, posicionY);

        //Renderizamos la carga
        Vista cargaVista = CargaVista.obtenerCarga(casillaJson.get(ConvertidorJSON.CARGA));
        cargaVista.render(graphicsContext, posicionX, posicionY);

        //Renderizamos los recursos
        Vista recursoVista = RecursoVista.obtenerRecurso(casillaJson.get(ConvertidorJSON.RECURSO));
        recursoVista.render(graphicsContext, posicionX, posicionY);

        //Renderizamos los edificios y unidades
        Vista ocupableVista = OcupableVista.obtenerOcupable(casillaJson.get(ConvertidorJSON.OCUPABLE));
        ocupableVista.render(graphicsContext, posicionX, posicionY);

        //Renderizamos agregados especiales
        Vista especialVista = EspecialVista.obtenerEspecial(casillaJson.get(ConvertidorJSON.ESTADO));
        especialVista.render(graphicsContext, posicionX, posicionY);
    }

    private void render() {
        graphicsContext.clearRect(0, 0, 1124,600);
        renderizarMapa();
        if (mostrarFPS) {
            graphicsContext.fillText("FPS: " + frameRate, 10, 10);
        }
    }

    private void manejarInput() {
        if (input.contains(("LEFT")))
            camara.irAIzquierda();
        else if(input.contains("RIGHT"))
            camara.irAderecha();
        if(input.contains("UP"))
            camara.subir();
        else if (input.contains("DOWN"))
            camara.bajar();
    }

    private void calcularFps(long currentTime) {
        long oldFrameTime = frameTimes[frameTimeIndex];
        frameTimes[frameTimeIndex] = currentTime;
        frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length;
        if (frameTimeIndex == 0) {
            arrayFilled = true;
        }
        if (arrayFilled) {
            long elapsedNanos = currentTime - oldFrameTime;
            long elapsedNanosPerFrame = elapsedNanos / frameTimes.length;
            frameRate = 1_000_000_000.0 / elapsedNanosPerFrame;
        }
    }

    private AnimationTimer crearGameLoop() {
        return new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                manejarInput();
                render();
                setFocusOnCanvas();

                //FPS
                if (mostrarFPS)
                    calcularFps(currentTime);
            }
        };
    }

    public EventHandler<? super MouseEvent> pintarCasilla() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double posMouseX = mouseEvent.getX() - bordeIzquierda.getWidth();
                double posMouseY = mouseEvent.getY();
                if (canvasPrincipal.contains(posMouseX, posMouseY)) {
                    int posX = (int) (((posMouseX - camara.getX()) / tileWidth));
                    int posY = (int) (((posMouseY - camara.getY()) / tileWidth));
                    if (posX >= tamanioMapa)
                        posX = tamanioMapa - 1;
                    coordenadaSeleccion = new Coordenada(posX, posY);
                    debugCoordenadas.setText("X " + posX + " , Y " + posY);
                    actualizarUI();
                }
            }
        };
    }

    @FXML
    private void actualizarUI(){
        actualizarInfoBordeIzquierdo(coordenadaSeleccion);
        actualizarInfoBordeDerecho();
        actualizarPaneOcupable(coordenadaSeleccion);
    }

    private void actualizarPaneOcupable(Coordenada coordenada){

        for(Button botonEdificio : arrayBotonesEdificio){
            botonEdificio.setVisible(false);
            botonEdificio.setDisable(true);
        }

        for(Button botonConstruirEdificio : arrayBotonesContruirEdificio){
            botonConstruirEdificio.setVisible(false);
            botonConstruirEdificio.setDisable(true);
        }

        Casilla casilla = mapa.obtenerCasilla(coordenada);
        JSONObject casillaJson = ConvertidorJSON.convertirAJSON(casilla);
        String tipoOcupable = OcupableVista.obtenerOcupable(casillaJson.get(ConvertidorJSON.OCUPABLE)).getInfo();

        if(Objects.equals(tipoOcupable, "Libre"))
            actualizarPaneOcupableConImperio();
        else if(!Objects.equals(tipoOcupable, "No hay"))
            actualizarPaneOcupableConOcupable(coordenada, (OcupableVista) OcupableVista.obtenerOcupable(casillaJson.get(ConvertidorJSON.OCUPABLE)));
    }

    public void actualizarPaneOcupableConOcupable(Coordenada coordenada, OcupableVista ocupableVista){
        paneInfoImperio.setVisible(false);
        paneInfoEdificio.setVisible(true);

        textoNombreEdificio.setText(ocupableVista.getInfo());
        ocupableVista.renderAdentroDeImageView(imageviewEdificio);

        String stringOcupable = mapa.obtenerOcupable(coordenada).toString();
        String vidaActual = obtenerAtributoImperio(stringOcupable, "vidaActual");
        String vidaMaxima = obtenerAtributoImperio(stringOcupable, "vidaMaxima");
        textoVida.setText(vidaActual + "/" + vidaMaxima);
        ocupableVista.aplicarTextoEscudo(textoEscudo, stringOcupable);

        String imperioDeJugadorActual = obtenerAtributoJugador( algoStar.conseguirStringJugadorActual() , "imperio");

        ocupableVista.manejarBotones(arrayBotonesEdificio, arrayWrappersBotonesEdificio, coordenada, imperioDeJugadorActual);
    }

    public void actualizarPaneOcupableConImperio(){
        paneInfoEdificio.setVisible(false);
        paneInfoImperio.setVisible(true);

        String imperioDeJugadorActual = obtenerAtributoJugador( algoStar.conseguirStringJugadorActual() , "imperio");

        if(imperioDeJugadorActual.equalsIgnoreCase( "Zerg"))
            (new ZergVista()).manejarBotones(coordenadaSeleccion, arrayBotonesContruirEdificio, arrayWrappersBotonesConstruirEdificio, (Zerg)algoStar.conseguirJugadorActual().conseguirImperio());
        else
            (new ProtossVista()).manejarBotones(coordenadaSeleccion, arrayBotonesContruirEdificio, arrayWrappersBotonesConstruirEdificio, (Protoss) algoStar.conseguirJugadorActual().conseguirImperio());
    }

    public EventHandler<? super KeyEvent> pressKey() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String tecla = keyEvent.getCode().toString();
                if (!input.contains(tecla))
                    input.add(tecla);
            }
        };
    }

    public EventHandler<? super KeyEvent> releaseKey() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String tecla = keyEvent.getCode().toString();
                input.remove(tecla);

                //Para fps
                if (Objects.equals(tecla, KeyCode.TAB.toString()))
                    mostrarFPS = !mostrarFPS;

                //Pantalla completa
                if (Objects.equals(tecla, KeyCode.F11.toString())){
                    setPantallaCompleta();
                } else if (Objects.equals(tecla, KeyCode.ESCAPE.toString()) &&
                        obtenerStageActual(canvasPrincipal).isFullScreen()) {
                    canvasPrincipal.setWidth(788.0);
                    canvasPrincipal.setHeight(600.0);
                }
            }
        };
    }

    @FXML
    public void pasarTurno(ActionEvent event){
        algoStar.terminarTurno();
        actualizarColorJugador(algoStar.conseguirStringJugadorActual());
        actualizarInfoBordeDerecho();

        if(coordenadaSeleccion != null)
            actualizarPaneOcupable(coordenadaSeleccion);
    }

    private  String obtenerAtributoImperio(String stringImperio, String tipoAtributo){
        String[] tokensJugador = stringImperio.split(" ");
        String atributoDeseado = null;

        for (int i = 0; i < tokensJugador.length; i++) {
            if(Objects.equals(tokensJugador[i], tipoAtributo))
                atributoDeseado = new String(tokensJugador[i + 1]);
        }

        return atributoDeseado;
    }

    private String obtenerAtributoJugador(String stringJugador, String tipoAtributo){
        String[] tokensJugador = stringJugador.split(" ");
        String atributoDeseado = null;

        for (int i = 0; i < tokensJugador.length; i++) {
            if(Objects.equals(tokensJugador[i], tipoAtributo))
                atributoDeseado = new String(tokensJugador[i + 1]);
        }

        return atributoDeseado;
    }

    private void actualizarColorJugador(String stringJugadorCompleto){
        int r = 255;
        int g = 255;
        int b = 255;
        int a = 255;
        int tipoDeColorHexadecimal;

        String colorJugadorString = obtenerAtributoJugador(stringJugadorCompleto, "color");
        colorJugadorString = colorJugadorString.replace("0x", "");
        tipoDeColorHexadecimal = colorJugadorString.length();

        if(tipoDeColorHexadecimal == 6){
            r = Integer.valueOf(colorJugadorString.substring(0, 2), 16);
            g = Integer.valueOf(colorJugadorString.substring(2, 4), 16);
            b = Integer.valueOf(colorJugadorString.substring(4, 6), 16);

        } else if (tipoDeColorHexadecimal == 8) {
            r = Integer.valueOf(colorJugadorString.substring(0, 2), 16);
            g = Integer.valueOf(colorJugadorString.substring(2, 4), 16);
            b = Integer.valueOf(colorJugadorString.substring(4, 6), 16);
            a = Integer.valueOf(colorJugadorString.substring(6, 8), 16);
        }

        String nuevoColorBackgroundCSS = String.format("-fx-background-color: rgba(%s, %s, %s, %s)", r, g, b, a);
        bordeDerecha.setStyle(nuevoColorBackgroundCSS);
        bordeIzquierda.setStyle(nuevoColorBackgroundCSS);
    }

    private void actualizarInfoBordeDerecho(){

        String imperioJugador = obtenerAtributoJugador(algoStar.conseguirStringJugadorActual(), "imperio");
        textoJugadorRaza.setText("IMPERIO " + imperioJugador.toUpperCase());

        //Actualizo el nombre del jugador actual
        String nombreJugador = obtenerAtributoJugador(algoStar.conseguirStringJugadorActual(), "nombre");
        textoNombreJugador.setText(nombreJugador);

        //Actualizo los minerales del jugador
        String mineralesImperio = obtenerAtributoImperio(algoStar.conseguirJugadorActual().conseguirImperio().recursosToString(), "mineral");
        textoCantMinerales.setText(mineralesImperio);

        //Actualizo el gas del jugador
        String gasImperio = obtenerAtributoImperio(algoStar.conseguirJugadorActual().conseguirImperio().recursosToString(), "gas");
        textoCantGas.setText(gasImperio);

        //Actualizo la poblacion y suministro del jugador
        String poblacionImperio = obtenerAtributoImperio(algoStar.conseguirJugadorActual().conseguirImperio().recursosToString(), "poblacion");
        String suministroImperio = obtenerAtributoImperio(algoStar.conseguirJugadorActual().conseguirImperio().recursosToString(), "suministro");
        textoCantPoblacion.setText(poblacionImperio + "/" + suministroImperio);

        textTurno.setText("Turnos: " + algoStar.turnoActual());
    }

    private void actualizarInfoBordeIzquierdo(Coordenada coordenada){
        Casilla casilla = mapa.obtenerCasilla(coordenada);
        JSONObject casillaJson = ConvertidorJSON.convertirAJSON(casilla);

        Vista superficieVista = SuperficieVista.obtenerSuperficie(casillaJson.get(ConvertidorJSON.SUPERFICIE));
        superficieVista.renderAdentroDeImageView(imagenTerrenoSeleccionado);
        textoTerrenoSeleccionado.setText(superficieVista.getInfo());

        Vista recursoVista = RecursoVista.obtenerRecurso(casillaJson.get(ConvertidorJSON.RECURSO));
        recursoVista.renderAdentroDeImageView(imagenRecursoSeleccionado);
        textoRecursoSeleccionado.setText(recursoVista.getInfo());

        Vista cargaVista = CargaVista.obtenerCarga(casillaJson.get(ConvertidorJSON.CARGA));
        cargaVista.renderAdentroDeImageView(imagenCargaSeleccionado);
        textoCargaSeleccionado.setText(cargaVista.getInfo());

        Vista mohoVista = MohoVista.obtenerMoho(casillaJson.get(ConvertidorJSON.MOHO));
        mohoVista.renderAdentroDeImageView(imagenContagioSeleccionado);

        textoContagioSeleccionado.setText(mohoVista.getInfo());
    }

    private void setPantallaCompleta() {
        Stage stageActual = this.obtenerStageActual(pasarTurnoBoton);
        stageActual.setFullScreen(!stageActual.isFullScreen());
    }

    public void setFocusOnCanvas() {
        canvasPrincipal.requestFocus();
    }

    public void setTamanioMinimo(double tamanioHorizontal, double tamanioVertical) {
        this.tamanioHorizontal = tamanioHorizontal;
        this.tamanioVertical = tamanioVertical;
    }

    public void setDecorators(double decoratorWidth, double decoratorHeight){
        this.decoratorWidth = decoratorWidth;
        this.decoratorHeight = decoratorHeight;
    }
}

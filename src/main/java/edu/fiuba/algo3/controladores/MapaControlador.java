package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controladores.ElementosGui.Camara;
import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.Vista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.cargas.CargaVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.especial.EspecialVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.imperios.ProtossVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.moho.MohoVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.recursos.RecursoVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.superficie.SuperficieVista;
import edu.fiuba.algo3.modelo.AlgoStar.AlgoStar;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
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
import javafx.scene.input.MouseButton;
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

import static java.lang.Integer.parseInt;

public class MapaControlador extends Controlador {

    @FXML
    protected Canvas canvasPrincipal;
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
    private Coordenada coordenadaMouse;
    private Coordenada coordenadaMover;
    private Coordenada coordenadaAtacar;
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

    private ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();

    /*=====================================================================================
     * AlgoStar
     * ====================================================================================*/
    private final AlgoStar algoStar = App.algoStar;

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
    @FXML
    private Text textoConstruccionImperio;



    /*===================================================================================================
    * Metodos
    * ===================================================================================================*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializarMapa();
        pasarTurnoBoton.setFocusTraversable(false);
        App.algoStar.empezarJuego();

        actualizarColorJugador();
        actualizarInfoBordeDerecho();

        //Agrego efecto al imageview
        DropShadow efectorBordePNGBlanco = new DropShadow( 10, Color.WHITE );
        imageviewEdificio.setEffect(efectorBordePNGBlanco);

        inicializarArraysDeBotonesYPanes();
        deshabilitarBotonesYPanes();

        paneInfoImperio.setVisible(false);

        sonido.cargarMusica("ambientSound.mp3", "ambiente");
    }

    private void inicializarArraysDeBotonesYPanes(){
        arrayBotonesEdificio = new Button[]{botonEdificio1, botonEdificio2, botonEdificio3, botonEdificio4,
                botonEdificio5, botonEdificio6, botonEdificio7, botonEdificio8};

        arrayWrappersBotonesEdificio = new Pane[]{wrapperBotonEdificio1, wrapperBotonEdificio2, wrapperBotonEdificio3, wrapperBotonEdificio4,
                wrapperBotonEdificio5, wrapperBotonEdificio6, wrapperBotonEdificio7, wrapperBotonEdificio8};

        arrayBotonesContruirEdificio = new Button[]{botonConstruirEdificio1, botonConstruirEdificio2, botonConstruirEdificio3,
                botonConstruirEdificio4, botonConstruirEdificio5};

        arrayWrappersBotonesConstruirEdificio = new Pane[]{wrapperBotonConstruirEdificio1, wrapperBotonConstruirEdificio2, wrapperBotonConstruirEdificio3,
                wrapperBotonConstruirEdificio4, wrapperBotonConstruirEdificio5};
    }

    private void deshabilitarBotonesYPanes(){
        for(Button botonEdificio : arrayBotonesEdificio){
            botonEdificio.setVisible(false);
            botonEdificio.setDisable(true);
        }

        for(Button botonConstruirEdificio : arrayBotonesContruirEdificio){
            botonConstruirEdificio.setVisible(false);
            botonConstruirEdificio.setDisable(true);
        }

        for(Pane wrapper : arrayWrappersBotonesEdificio)
            Tooltip.install(wrapper, null);

        for(Pane wrapper : arrayWrappersBotonesConstruirEdificio)
            Tooltip.install(wrapper, null);

        textoConstruccionImperio.setVisible(false);
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

        sonido.reproducirMusica("ambiente");
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

        //renderizado de posicion de Mouse
        renderizarPosicionMouse();

    }

    private void renderizarPosicionMouse() {
        if (coordenadaMouse != null) {
            int x = (coordenadaMouse.getCoordenadaX());
            int y = coordenadaMouse.getCoordenadaY();
            graphicsContext.setStroke(Color.color(1,1,1,0.4));
            graphicsContext.strokeRect(camara.getX() + (x*tileWidth), camara.getY() + (y*tileWidth), tileWidth, tileWidth);
        }
    }

    private void renderizarSeleccion(){
        if (coordenadaSeleccion != null) {
            int x = coordenadaSeleccion.getCoordenadaX();
            int y = coordenadaSeleccion.getCoordenadaY();
            seleccion.render(graphicsContext, camara.getX() + (x*tileWidth), camara.getY() + (y*tileWidth));
        }

        //renderizar Rango de Movimiento
        pintarCasillasMovimiento(coordenadaMover);
        //renderizar Rango de Ataque
        pintarCasillasAtaque(coordenadaAtacar);
    }

    private void pintarCasillasAtaque(Coordenada origen) {
        if (coordenadaAtacar == null)
            return;

        JSONObject unidadJSON = ConvertidorJSON.convertirAJSON(mapa.obtenerOcupable(origen));
        int rango = parseInt((String) unidadJSON.get(ConvertidorJSON.RANGO_ATAQUE));

        if (rango == 0) {
            coordenadaAtacar = null;
            return;
        }
        Color borde = Color.DARKRED;
        Color relleno = Color.color(1,0,0,0.2);
        pintarCasillasEnRango(origen, rango, borde, relleno);

    }

    private void pintarCasillasMovimiento(Coordenada origen) {
        if (coordenadaMover == null)
            return;
        int rango = 5;
        Color borde = Color.GREEN;
        Color relleno = Color.color(0,1,0,0.2);
        pintarCasillasEnRango(origen, rango, borde, relleno);
    }

    private void pintarCasillasEnRango(Coordenada origen, int rango, Color borde, Color relleno) {
        int x = origen.getCoordenadaX();
        int y = origen.getCoordenadaY();

        graphicsContext.setStroke(borde);
        graphicsContext.setFill(relleno);
        for (int i = x - rango; i <= x + rango; i++){
            for (int j = y - rango; j <= y + rango; j++){
                Coordenada coordenada = new Coordenada(i,j);
                if (mapa.distanciaEntreDosCoordenadas(coordenada, origen) <= rango){
                    int posX = ((coordenada.getCoordenadaX() * tileWidth) + camara.getX());
                    int posY = ((coordenada.getCoordenadaY() * tileWidth) + camara.getY());
                    graphicsContext.fillRect(posX,posY,tileWidth,tileWidth);
                    graphicsContext.strokeRect(posX, posY, tileWidth, tileWidth);
                }
            }
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
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();
                    sonido.reproducirFX("click");
                    double posMouseX = mouseEvent.getX() - bordeIzquierda.getWidth();
                    double posMouseY = mouseEvent.getY();
                    if (canvasPrincipal.contains(posMouseX, posMouseY)) {
                        int posX = (int) (((posMouseX - camara.getX()) / tileWidth));
                        int posY = (int) (((posMouseY - camara.getY()) / tileWidth));

                        Coordenada coordenadaClickeada = new Coordenada(posX, posY);
                        //Revisar movimiento
                        moverUnidad(coordenadaClickeada);
                        //Revisar ataque
                        atacarUnidad(coordenadaClickeada);
                        //click normal
                        coordenadaSeleccion = coordenadaClickeada;
                        debugCoordenadas.setText("X " + posX + " , Y " + posY);
                        actualizarUI();
                    }
                }
            }
        };
    }

    private void atacarUnidad(Coordenada coordenadaClickeada) {
        if(coordenadaAtacar == null)
            return;

        Unidad unidadAtacante = (Unidad) mapa.obtenerOcupable(coordenadaAtacar);

        JSONObject unidadJSON = ConvertidorJSON.convertirAJSON(unidadAtacante);

        int distancia = mapa.distanciaEntreDosCoordenadas(coordenadaClickeada, coordenadaAtacar);
        int rango = parseInt((String) unidadJSON.get(ConvertidorJSON.RANGO_ATAQUE));
        if (distancia <= rango) {
            //Puede atacar
            try {
                ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();
                sonido.reproducirFX("atacar");
                mapa.atacar(coordenadaAtacar, coordenadaClickeada);
            } catch (RuntimeException e) {
                //todo agregar feedback
            }
        }

        coordenadaAtacar = null;
    }

    private void moverUnidad(Coordenada coordenadaClickeada) {
        if (coordenadaMover == null)
            return;
        int distancia = mapa.distanciaEntreDosCoordenadas(coordenadaClickeada, coordenadaMover);
        if (distancia <= 5) {
            //Puede moverse
            Unidad unidad = (Unidad) algoStar.conseguirOcupableEn(coordenadaMover);

            try {
                JSONObject unidadJSON = ConvertidorJSON.convertirAJSON(unidad);
                Vista unidadVista = OcupableVista.obtenerOcupable(unidadJSON.get(ConvertidorJSON.OCUPABLE));
                unidad.moverA(coordenadaClickeada);
                ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();
                sonido.reproducirFX(unidadVista.obtenerAudio());
            } catch (RuntimeException error){
                //Todo poner algun sonido o algo para indicar que no se puede
            }
        }
        coordenadaMover = null;
    }

    @FXML
    public void actualizarUI(){
        actualizarInfoBordeIzquierdo(coordenadaSeleccion);
        actualizarInfoBordeDerecho();
        actualizarPaneOcupable(coordenadaSeleccion);
    }

    private void actualizarPaneOcupable(Coordenada coordenada){
        deshabilitarBotonesYPanes();

        Casilla casilla = mapa.obtenerCasilla(coordenada);
        JSONObject casillaJson = ConvertidorJSON.convertirAJSON(casilla);
        String tipoOcupable = OcupableVista.obtenerOcupable(casillaJson.get(ConvertidorJSON.OCUPABLE)).getInfo();

        if(Objects.equals(tipoOcupable, "Libre"))
            actualizarPaneOcupableConImperio();
        else if(!Objects.equals(tipoOcupable, "No hay"))
            actualizarPaneOcupableConOcupable(coordenada);
    }

    public void actualizarPaneOcupableConOcupable(Coordenada coordenada){
        paneInfoImperio.setVisible(false);
        paneInfoEdificio.setVisible(true);

        JSONObject ocupableJSON = ConvertidorJSON.convertirAJSON(mapa.obtenerOcupable(coordenada));
        OcupableVista ocupableView = (OcupableVista) OcupableVista.obtenerOcupable(ocupableJSON.get(ConvertidorJSON.OCUPABLE));


        textoNombreEdificio.setText(ocupableView.getInfo());
        ocupableView.renderAdentroDeImageView(imageviewEdificio);

        textoVida.setText(ocupableJSON.get(ConvertidorJSON.VIDA) + "/" + ocupableJSON.get(ConvertidorJSON.VIDAMAX));
        ocupableView.aplicarTextoEscudo(textoEscudo, ocupableJSON);

        JSONObject imperioJSON = ConvertidorJSON.convertirAJSON(algoStar.conseguirJugadorActual());
        String imperioDeJugadorActual = (String) imperioJSON.get(ConvertidorJSON.IMPERIO);

        ocupableView.manejarBotones(arrayBotonesEdificio, arrayWrappersBotonesEdificio, coordenada, imperioDeJugadorActual, this);
    }

    public void actualizarPaneOcupableConImperio(){
        paneInfoEdificio.setVisible(false);
        paneInfoImperio.setVisible(true);

        JSONObject imperioActual = ConvertidorJSON.convertirAJSON(algoStar.conseguirJugadorActual());
        String imperioDeJugadorActual = (String) imperioActual.get(ConvertidorJSON.IMPERIO);

        if(imperioDeJugadorActual.equalsIgnoreCase( "Protoss")){
            textoConstruccionImperio.setVisible(true);
            (new ProtossVista()).manejarBotones(coordenadaSeleccion, arrayBotonesContruirEdificio, arrayWrappersBotonesConstruirEdificio, (Protoss) algoStar.conseguirJugadorActual().conseguirImperio());
        }
    }

    @FXML
    public void actualizarCoordenadaMouse(MouseEvent mouseEvent) {
        double posMouseX = mouseEvent.getX() - bordeIzquierda.getWidth();
        double posMouseY = mouseEvent.getY();
        int posX = (int) (((posMouseX - camara.getX()) / tileWidth));
        int posY = (int) (((posMouseY - camara.getY()) / tileWidth));

        coordenadaMouse = new Coordenada(posX, posY);
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
        ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();
        sonido.reproducirFX("boton");
        algoStar.terminarTurno();
        actualizarColorJugador();
        actualizarInfoBordeDerecho();

        if(coordenadaSeleccion != null)
            actualizarPaneOcupable(coordenadaSeleccion);

        coordenadaAtacar = null;
        coordenadaMover = null;
    }

    private void actualizarColorJugador(){
        int r = 255;
        int g = 255;
        int b = 255;
        int a = 255;
        int tipoDeColorHexadecimal;

        JSONObject jugadorJSON = ConvertidorJSON.convertirAJSON(algoStar.conseguirJugadorActual());
        String colorJugadorString = (String) jugadorJSON.get(ConvertidorJSON.COLOR);
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

    public void actualizarInfoBordeDerecho(){
        JSONObject imperioActualJSON = ConvertidorJSON.convertirAJSON(algoStar.conseguirJugadorActual());

        textoJugadorRaza.setText(((String) imperioActualJSON.get(ConvertidorJSON.IMPERIO)).toUpperCase());

        //Actualizo el nombre del jugador actual
        textoNombreJugador.setText((String) imperioActualJSON.get(ConvertidorJSON.NOMBRE));

        //Actualizo los minerales del jugador
        textoCantMinerales.setText((String) imperioActualJSON.get(ConvertidorJSON.MINERAL));

        //Actualizo el gas del jugador
        textoCantGas.setText((String) imperioActualJSON.get(ConvertidorJSON.GAS));

        //Actualizo la poblacion y suministro del jugador
        textoCantPoblacion.setText(imperioActualJSON.get(ConvertidorJSON.POBLACION) + "/" +
                                    imperioActualJSON.get(ConvertidorJSON.SUMINISTRO));

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

    public void guardarCasillaMovimiento(Coordenada coordenadaAGuardar) {
        ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();
        sonido.reproducirFX("boton");
        coordenadaAtacar = null;
        coordenadaMover = coordenadaAGuardar;
    }

    public void guardarCasillaAtacar(Coordenada coordenadaAGuardar) {
        ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();
        sonido.reproducirFX("boton");
        coordenadaMover = null;
        coordenadaAtacar = coordenadaAGuardar;
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

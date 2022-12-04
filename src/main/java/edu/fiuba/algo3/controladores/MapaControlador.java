package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controladores.ElementosGui.Camara;
import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.especial.EspecialVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.Vista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.cargas.CargaVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.moho.MohoVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.recursos.RecursoVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.superficie.SuperficieVista;
import edu.fiuba.algo3.modelo.AlgoStar.AlgoStar;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Pilon;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    @FXML
    protected Text terrenoLabel;
    @FXML
    protected Text recursoLabel;
    @FXML
    protected Text cargaLabel;
    @FXML
    protected Text contaminadoLabel;
    @FXML
    protected Text libreLabel;
    @FXML
    protected Text estadoLabel;
    @FXML
    protected Text debugCoordenadas;
    @FXML
    protected Button pasarTurnoBoton;
    @FXML
    protected Button botonTest;
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

    private final Font fuente = Font.loadFont(Objects.requireNonNull(getClass().getResourceAsStream("/fonts/Retro Gaming.ttf")), 10);

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

    /*===================================================================================================
    * Metodos
    * ===================================================================================================*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializarMapa();
        pasarTurnoBoton.setFocusTraversable(false);

        App.algoStar.empezarJuego();

        terrenoLabel.setFont(fuente);
        terrenoLabel.setFill(Color.GREEN);
        recursoLabel.setFont(fuente);
        recursoLabel.setFill(Color.GREEN);
        cargaLabel.setFont(fuente);
        cargaLabel.setFill(Color.GREEN);terrenoLabel.setFont(fuente);
        contaminadoLabel.setFill(Color.GREEN);terrenoLabel.setFont(fuente);
        contaminadoLabel.setFill(Color.GREEN);
        libreLabel.setFont(fuente);
        libreLabel.setFill(Color.GREEN);
        estadoLabel.setFont(fuente);
        estadoLabel.setFill(Color.GREEN);


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

        graphicsContext.fillText(camara.getX() + " x " + camara.getY(), 200,200);
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
                    obtenerInfoCasilla(coordenadaSeleccion);
                }
            }
        };
    }

    private void obtenerInfoCasilla(Coordenada coordenada) {
        Casilla casilla = mapa.obtenerCasilla(coordenada);
        JSONObject casillaJson = ConvertidorJSON.convertirAJSON(casilla);

        Vista superficieVista = SuperficieVista.obtenerSuperficie(casillaJson.get(ConvertidorJSON.SUPERFICIE));
        terrenoLabel.setText("Terreno: " + superficieVista.getInfo());

        Vista recursoVista = RecursoVista.obtenerRecurso(casillaJson.get(ConvertidorJSON.RECURSO));
        recursoLabel.setText("Recurso: " + recursoVista.getInfo());

        Vista cargaVista = CargaVista.obtenerCarga(casillaJson.get(ConvertidorJSON.CARGA));
        cargaLabel.setText("Carga del terreno: " + cargaVista.getInfo());

        Vista mohoVista = MohoVista.obtenerMoho(casillaJson.get(ConvertidorJSON.MOHO));
        contaminadoLabel.setText("Contaminado con: " + mohoVista.getInfo());

        Vista ocupableVista = OcupableVista.obtenerOcupable(casillaJson.get(ConvertidorJSON.OCUPABLE));
        libreLabel.setText("'" + ocupableVista.getInfo() + "'");
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
        //TODO DEBUG SACAR CUANDO SE IMPLEMENTE
        System.out.print("Turno pasado: " + algoStar.turnoActual() + "\n");

        //Lo saqué porque tiraba excepcion
        //((Edificio)( Mapa.obtener().obtenerOcupable(new Coordenada(20,20)))).pasarTurno();
    }

    public void setearEdifYUnid(ActionEvent event){
        Mapa elMapa = Mapa.obtener();
        Criadero c = new Criadero();
        Pilon pilon = new Pilon();
        pilon.asignarSuministro(new Suministro(0));
        elMapa.construirEdificio(c, new Coordenada(20, 20));
        elMapa.construirEdificio(pilon, new Coordenada(4, 7));
        pilon.pasarTurno();
        pilon.pasarTurno();
        pilon.pasarTurno();
        pilon.pasarTurno();
        pilon.pasarTurno();
        c.pasarTurno();
        c.pasarTurno();
        c.pasarTurno();
        c.pasarTurno();
        c.pasarTurno();

        elMapa.colocarUnaUnidad( new Dragon(),new Coordenada(83,58));
    }

    private void setPantallaCompleta() {
        Stage stageActual = this.obtenerStageActual(estadoLabel);
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

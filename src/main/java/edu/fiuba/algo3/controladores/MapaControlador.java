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
import edu.fiuba.algo3.modelo.Excepciones.ErrorCasillaVacia;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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
    protected Text debugCoordenadas;

    /*=====================================================================================
     * Mapa y camara
     * ====================================================================================*/
    private Mapa mapa = Mapa.obtener();
    private int tamanioMapa = mapa.obtenerTamanioMapa();
    private int anchoMapa = 24;
    private int largoMapa = 18;

    private Camara camara;

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
    private ArrayList<String> input = new ArrayList<>();
    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0;
    private  boolean arrayFilled = false;
    private double frameRate;
    private boolean mostrarFPS = false;

    /*===================================================================================================
    * Metodos
    * ===================================================================================================*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializarMapa();

        App.algoStar.empezarJuego();
    }

    private void inicializarMapa() {
        int bordeHorizontal = tileWidth * (tamanioMapa - anchoMapa);
        int bordeVertical = tileWidth * (tamanioMapa - largoMapa);
        camara = new Camara(bordeHorizontal, bordeVertical);
        graphicsContext = canvasPrincipal.getGraphicsContext2D();

        AnimationTimer gameloop = crearGameLoop();
        gameloop.start();
    }
    private void renderizarMapa() {
        //renderizamos solo lo que se encuentra en pantalla
        for(int i = 0; i < anchoMapa + 1 ; i++){
            for(int j = 0; j < largoMapa + 1; j++){
                int xInicial = ( Math.abs(camara.getX()) / tileWidth) + i;
                int yInicial = ( Math.abs(camara.getY()) / tileWidth) + j;

                //verificamos que no nos salgamos del mapa
                if (xInicial == tamanioMapa)
                    xInicial -= 1;
                if (yInicial == tamanioMapa)
                    yInicial -= 1;


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
        //Renderizamos la superficie
        Vista superficieVista = SuperficieVista.obtenerSuperficie(casilla.obtenerSuperficie());
        superficieVista.render(graphicsContext, posicionX, posicionY);

        //Renderizamos la carga
        Vista cargaVista = CargaVista.obtenerCarga(casilla.obtenerCarga());
        cargaVista.render(graphicsContext, posicionX, posicionY);

        //Renderizamos el moho
        Vista mohoVista = MohoVista.obtenerMoho(casilla.obtenerMoho());
        mohoVista.render(graphicsContext, posicionX, posicionY);

        //Renderizamos los recursos
        Vista recursoVista = RecursoVista.obtenerRecurso(casilla.obtenerMaterial());
        recursoVista.render(graphicsContext, posicionX, posicionY);

        //Renderizamos los edificios y unidades
        Vista ocupableVista = OcupableVista.obtenerOcupable(casilla.obtenerOcupable());
        ocupableVista.render(graphicsContext, posicionX, posicionY);

        //Renderizamos agregados especiales
        Vista especialVista = EspecialVista.obtenerEspecial(casilla.obtenerOcupable());
        especialVista.render(graphicsContext, posicionX, posicionY);
    }

    private void render() {
        graphicsContext.clearRect(0, 0, 1024,600);
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

        if (input.contains(KeyCode.P.toString()))
            camara.irHacia(73*tileWidth, 58*tileWidth);
        if (input.contains(KeyCode.O.toString()))
            camara.irHacia(0*tileWidth, 23*tileWidth);
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
                double posMouseX = mouseEvent.getX();
                double posMouseY = mouseEvent.getY();
                if (canvasPrincipal.contains(posMouseX, posMouseY)) {
                    int posX = (int) (((posMouseX - camara.getX()) / tileWidth));
                    int posY = (int) ((((posMouseY - 12) - camara.getY()) / tileWidth));
                    coordenadaSeleccion = new Coordenada(posX, posY);
                    debugCoordenadas.setText("Coordenadas: " + posX + " - " + posY);
                    terrenoLabel.setText(obtenerInfoCasilla(coordenadaSeleccion));
                }
            }
        };
    }

    private String obtenerInfoCasilla(Coordenada coordenada) {
        String informacion;
        Casilla casilla = mapa.obtenerCasilla(coordenada);

        Vista superficieVista = SuperficieVista.obtenerSuperficie(casilla.obtenerSuperficie());
        informacion = "Terreno: " + superficieVista.getInfo() + "\n";

        Vista recursoVista = RecursoVista.obtenerRecurso(casilla.obtenerMaterial());
        informacion += "Recurso: " + recursoVista.getInfo() + "\n";

        Vista cargaVista = CargaVista.obtenerCarga(casilla.obtenerCarga());
        informacion += "Carga del terreno: " + cargaVista.getInfo() + "\n";

        Vista mohoVista = MohoVista.obtenerMoho(casilla.obtenerMoho());
        informacion += "Contaminado con: " + mohoVista.getInfo() + "\n";

        try {
            Vista ocupableVista = OcupableVista.obtenerOcupable(casilla.obtenerOcupable());
            informacion += "'" + ocupableVista.getInfo() + "'";
        } catch (ErrorCasillaVacia casillaVacia){
            //No hacemos nada
        }



        return informacion;
    }
    public EventHandler<? super KeyEvent> pressKey() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String tecla = keyEvent.getCode().toString();
                if (!input.contains(tecla)){
                    input.add(tecla);
                }
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
                if (Objects.equals(tecla, KeyCode.TAB.toString())){
                    mostrarFPS = !mostrarFPS;
                }
            }
        };
    }

}

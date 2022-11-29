package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.controladores.ElementosGui.Camara;
import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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

public class MapaControlador extends Controlador implements Initializable {

    @FXML
    protected Canvas canvasPrincipal;
    @FXML
    protected Text debugPosMouse;
    @FXML
    protected Text debugCoordenadas;

    /*=====================================================================================
     * Mapa y camara
     * ====================================================================================*/
    //debug
    private Mapa mapa = Mapa.obtener();
    private int tamanioMapa = mapa.obtenerTamanioMapa();
    private int anchoMapa = 24;
    private int largoMapa = 18;

    private Camara camara;

    /*=====================================================================================
     * Graficos
     * ====================================================================================*/
    private GraphicsContext graphicsContext;
    private final Tile tierra = new Tile("tierra.png");
    private final Tile seleccion = new Tile("seleccion.png");
    private Coordenada coordenadaSeleccion;
    private final int tileWidth = tierra.tamanio();

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
        for(int i = 0; i < tamanioMapa; i++){
            for(int j = 0; j < tamanioMapa; j++){
                tierra.render(graphicsContext, camara.getX() + (i*tileWidth), camara.getY() + (j*tileWidth));
            }
        }
        if (coordenadaSeleccion != null) {
            int x = coordenadaSeleccion.getCoordenadaX();
            int y = coordenadaSeleccion.getCoordenadaY();
            seleccion.render(graphicsContext, camara.getX() + (x*tileWidth), camara.getY() + (y*tileWidth));
        }
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
                debugPosMouse.setText("Posicion de la camara: " + camara.getX() + " x " + camara.getY());
                manejarInput();
                render();
                calcularFps(currentTime);
            }
        };
    }
    public EventHandler<? super MouseEvent> pintarCasilla() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int posX = (int) (((mouseEvent.getX() - camara.getX()) / tileWidth)) ;
                int posY = (int) (((mouseEvent.getY() - camara.getY()) / tileWidth)) ;
                coordenadaSeleccion = new Coordenada(posX, posY);
                debugCoordenadas.setText("Coordenadas: " + posX + " - " + posY);
            }
        };
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

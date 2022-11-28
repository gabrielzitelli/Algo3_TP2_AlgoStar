package edu.fiuba.algo3.TestGui;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class MapaTest extends Escena {
    /*=====================================================================================
     * Mapa y camara
     * ====================================================================================*/
    private final int tamañoMapa = 50;
    private Camara camara;

    /*=====================================================================================
    * Graficos
    * ====================================================================================*/
    private GraphicsContext graphicsContext;
    private final Tile tierra = new Tile("tierra.png");
    private final Tile seleccion = new Tile("seleccion.png");
    private final Image marco = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/marco-placeHolder.png")));
    private Coordenada coordenadaSeleccion;
    private final int tileWidth = 32;
    private String posMouse = "";

    /*=====================================================================================
     * GameLoop
     * ====================================================================================*/
    private ArrayList<String> input = new ArrayList<>();

    public MapaTest(Stage stage){
        //todo acomodar
        camara = new Camara(((tamañoMapa - 10)*32) - 1024, (tamañoMapa*32) - 600);
        crearEscena(stage);
    }
    private void renderizarMapa() {
        for(int i = 0; i < tamañoMapa; i++){
            for(int j = 0; j < tamañoMapa; j++){
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
        graphicsContext.drawImage(marco, 0,0);
        graphicsContext.fillText(posMouse, 788, 30);
        String debug = "posCamara = " + camara.getX() + " - " + camara.getY();
        graphicsContext.fillText(debug, 788, 50);
    }
    private void manejarInput() {
        if (input.contains(("LEFT"))){
            camara.irAIzquierda();
        }
        else if(input.contains("RIGHT"))
            camara.irAderecha();
        if(input.contains("UP")){
            camara.subir();
        }
        else if (input.contains("DOWN"))
            camara.bajar();
    }
    private AnimationTimer crearGameLoop() {
        return new AnimationTimer() {
            @Override
            public void handle(long l) {
                manejarInput();
                render();
            }
        };
    }

    private EventHandler<? super MouseEvent> pintarCasilla() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int posX = (int) (((mouseEvent.getX() - camara.getX()) / 32)) ;
                int posY = (int) (((mouseEvent.getY() - camara.getY()) / 32)) ;
                posMouse = "Posicion " + posX + " - " + posY;
                coordenadaSeleccion = new Coordenada(posX, posY);
            }
        };
    }
    private EventHandler<? super KeyEvent> pressKey() {
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
    private EventHandler<? super KeyEvent> releaseKey() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String tecla = keyEvent.getCode().toString();
                input.remove(tecla);
            }
        };
    }

    protected void crearEscena(Stage stage) {

        Group root = new Group();
        escena = new Scene(root);

        Canvas canvas = new Canvas(1024,600);
        root.getChildren().add(canvas);

        graphicsContext = canvas.getGraphicsContext2D();

        escena.setOnMouseClicked(pintarCasilla());
        escena.setOnKeyPressed(pressKey());
        escena.setOnKeyReleased(releaseKey());

        AnimationTimer gameloop = crearGameLoop();
        gameloop.start();
    }


}

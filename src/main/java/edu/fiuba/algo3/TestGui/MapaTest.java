package edu.fiuba.algo3.TestGui;

import edu.fiuba.algo3.modelo.Mapa.Mapa;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MapaTest extends Escena {

    private GraphicsContext graphicsContext;
    private Mapa elMapa = Mapa.obtener();
    private Image tileSet = new Image("../Resources/Graphics/TexturaMapa.png");
    private int tileWidth = 32;
    private enum tileName {
        TIERRA, SELECCIONAR
    }

    public MapaTest(Stage stage){
        crearEscena(stage);
    }
    private void  renderizarTile(int tileId, int x, int y){
        PixelReader reader = tileSet.getPixelReader();
        PixelWriter writer = graphicsContext.getPixelWriter();
        int tilePos = tileId * tileWidth;
        for (int i = 0; i < tileWidth; i++){
            for (int j = 0; i < tileWidth; i++){
                Color color = reader.getColor(tilePos + i, tilePos + j);
                if (color.isOpaque()){
                    writer.setColor(x + i, y + j, color);
                }
            }
        }
    }
    private void renderizarMapa() {
        elMapa.reiniciarMapa();
        int cantidad = 18;
        for(int i = 0; i < cantidad; i++){
            for(int j = 0; j < cantidad; j++){
                renderizarTile(tileName.TIERRA.ordinal(), i*tileWidth, j*tileWidth);
            }
        }
    }
    protected void crearEscena(Stage stage) {
        Group root = new Group();
        escena = new Scene(root);

        Canvas canvas = new Canvas(1024,600);
        root.getChildren().add(canvas);

        graphicsContext = canvas.getGraphicsContext2D();
        renderizarMapa();
    }
}

package edu.fiuba.algo3.TestGui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Tile {

    Image textura;
    public Tile(String fileName) {
        textura = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/" + fileName)));
    }

    public void render(GraphicsContext gc, int x, int y) {
        gc.drawImage(textura, x, y);
    }
}

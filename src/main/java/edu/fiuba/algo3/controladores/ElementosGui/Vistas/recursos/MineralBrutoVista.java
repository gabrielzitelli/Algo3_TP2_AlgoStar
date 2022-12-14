package edu.fiuba.algo3.controladores.ElementosGui.Vistas.recursos;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import javafx.scene.image.Image;

import java.util.Objects;

public class MineralBrutoVista extends RecursoVista {

    public MineralBrutoVista() {
        tile = new Tile("recursos/32px/mineral.png");
        identificador = "mineral";
        info = "Mineral";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/recursos/original/mineralRaw.png")));
    }
}

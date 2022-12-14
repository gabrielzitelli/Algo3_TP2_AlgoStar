package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import javafx.scene.image.Image;

import java.util.Objects;

public class ZealotInvisibleVista extends UnidadProtossVista {
    public ZealotInvisibleVista(){
        this.tile = new Tile("unidades_protoss/32px/zealot_invisible.png");
        this.identificador = "zealot_invisible";
        this.archivoDeSonido = "zealot";
        this.info = "Zealot Invisible";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_protoss/original/zealotRawInvisible.png")));
    }
}

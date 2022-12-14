package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import javafx.scene.image.Image;

import java.util.Objects;

public class ScoutVista extends UnidadProtossVista {

    public ScoutVista() {
        this.tile = new Tile("unidades_protoss/32px/scout.png");
        this.identificador = "scout";
        this.info = "Scout";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_protoss/original/scoutRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_protoss/original/creacion/scoutRawCrear.png")));
        this.archivoDeSonido = "scout";
    }
}

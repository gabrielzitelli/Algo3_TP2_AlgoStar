package edu.fiuba.algo3.controladores.ElementosGui.Vistas.recursos;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.modelo.Mapa.GasBruto;
import javafx.scene.image.Image;

import java.util.Objects;

public class GasBrutoVista extends RecursoVista {
    public GasBrutoVista() {
        this.identificador = "gas";
        this.tile = new Tile("recursos/32px/volcan.png");
        this.info = "Gas";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/recursos/original/volcanRaw.png")));
    }
}

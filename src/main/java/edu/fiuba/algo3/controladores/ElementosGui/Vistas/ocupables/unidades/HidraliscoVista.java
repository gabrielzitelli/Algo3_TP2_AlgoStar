package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Hidralisco;
import javafx.scene.image.Image;

import java.util.Objects;

public class HidraliscoVista extends OcupableVista {
    public HidraliscoVista() {
        this.tile = new Tile("unidades_zerg/32px/hidralisco.png");
        this.identificador = "hidralisco";
        this.info = "Hidralisco";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/hidraliscoRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/creacion/hidraliscoRawCrear.png")));
    }
}

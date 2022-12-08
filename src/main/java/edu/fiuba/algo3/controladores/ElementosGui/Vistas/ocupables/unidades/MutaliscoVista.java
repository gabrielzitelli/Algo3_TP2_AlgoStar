package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Mutalisco;
import javafx.scene.image.Image;

import java.util.Objects;

public class MutaliscoVista extends UnidadZergVista {
    public MutaliscoVista() {
        this.tile = new Tile("unidades_zerg/32px/mutalisco.png");
        this.identificador = "mutalisco";
        this.info = "Mutalisco";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/mutaliscoRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/creacion/mutaliscoRawCrear.png")));
    }
}

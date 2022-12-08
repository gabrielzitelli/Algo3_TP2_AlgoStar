package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.AmoSupremo;
import javafx.scene.image.Image;

import java.util.Objects;

public class AmoSupremoVista extends UnidadZergVista {
    public AmoSupremoVista() {
        this.tile = new Tile("unidades_zerg/32px/amo_supremo.png");
        this.identificador = "amo_supremo";
        this.info = "Amo Supremo";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/amo_supremoRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/creacion/amo_supremoRawCrear.png")));
    }
}

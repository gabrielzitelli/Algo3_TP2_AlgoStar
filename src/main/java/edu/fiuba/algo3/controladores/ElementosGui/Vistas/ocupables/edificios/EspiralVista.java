package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Espiral;
import javafx.scene.image.Image;

import java.util.Objects;

public class EspiralVista extends OcupableVista {

    public EspiralVista(){
        this.tile = new Tile("edificios_zerg/32px/espiral.png");
        this.identificador = "espiral";
        this.info = "Espiral";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_zerg/original/espiralRaw.png")));
    }
}

package edu.fiuba.algo3.controladores.ElementosGui.Vistas.moho;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import javafx.scene.image.Image;

import java.util.Objects;

public class ConMohoVista extends MohoVista {

    public ConMohoVista() {
        this.tile = new Tile("superficies/32px/moho_transparente.png");
        this.info = "Moho";
        this.identificador = "moho";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/superficies/32px/mini_terreno_con_moho.png")));
    }
}

package edu.fiuba.algo3.controladores.ElementosGui.Vistas.superficie;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import javafx.scene.image.Image;

import java.util.Objects;


public class SuperficieTerrestreVista extends SuperficieVista {

    public SuperficieTerrestreVista() {
        this.identificador = "terrestre";
        this.tile = new Tile("superficies/32px/terrestre.png");
        this.info = "Terrestre";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/superficies/32px/terrestre.png")));
    }
}

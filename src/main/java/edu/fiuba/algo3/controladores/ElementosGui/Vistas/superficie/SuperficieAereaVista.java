package edu.fiuba.algo3.controladores.ElementosGui.Vistas.superficie;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import javafx.scene.image.Image;

import java.util.Objects;

public class SuperficieAereaVista extends SuperficieVista {
    public SuperficieAereaVista(){
        this.identificador = "aerea";
        this.tile = new Tile("superficies/32px/espacial.png");
        this.info = "Espacial";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/superficies/32px/espacial.png")));
    }
}

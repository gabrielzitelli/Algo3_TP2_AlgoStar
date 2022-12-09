package edu.fiuba.algo3.controladores.ElementosGui.Vistas.cargas;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import javafx.scene.image.Image;

import java.util.Objects;

public class ConCargaVista extends CargaVista {

    public ConCargaVista() {
        this.tile = new Tile("superficies/32px/energia_transparente.png");
        this.identificador = "carga";
        this.info = "Cargado";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/superficies/32px/mini_terreno_cargado.png")));
    }
}

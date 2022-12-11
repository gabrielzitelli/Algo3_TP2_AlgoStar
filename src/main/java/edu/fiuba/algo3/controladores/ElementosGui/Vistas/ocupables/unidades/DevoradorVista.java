package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import javafx.scene.image.Image;

import java.util.Objects;

public class DevoradorVista extends UnidadZergVista {
    public DevoradorVista(){
        this.tile = new Tile("unidades_zerg/32px/devorador.png");
        this.identificador = "devorador";
        this.info = "Devorador";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/devoradorRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/creacion/devoradorRawCrear.png")));
        this.archivoDeSonido = "devorador";
    }
}

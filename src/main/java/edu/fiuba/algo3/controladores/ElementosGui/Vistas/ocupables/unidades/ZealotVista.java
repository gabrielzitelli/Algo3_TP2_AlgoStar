package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import javafx.scene.image.Image;

import java.util.Objects;

public class ZealotVista extends UnidadVista {

    public ZealotVista(){
        this.tile = new Tile("unidades_protoss/32px/zealot.png");
        this.identificador = "zealot";
        this.info = "Zealot";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_protoss/original/zealotRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_protoss/original/creacion/zealotRawCrear.png")));
    }
}

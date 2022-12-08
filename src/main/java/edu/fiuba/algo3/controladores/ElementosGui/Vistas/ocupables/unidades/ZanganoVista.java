package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import javafx.scene.image.Image;

import java.util.Objects;

public class ZanganoVista extends UnidadZergVista {
    public ZanganoVista() {
        this.tile = new Tile("unidades_zerg/32px/zangano.png");
        this.identificador = "zangano";
        this.info = "Zangano";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/zanganoRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/creacion/zanganoRawCrear.png")));
    }
}

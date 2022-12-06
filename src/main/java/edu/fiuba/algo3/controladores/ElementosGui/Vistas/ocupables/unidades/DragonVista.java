package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import javafx.scene.image.Image;

import java.util.Objects;

public class DragonVista extends UnidadVista {

    public DragonVista() {
        this.tile = new Tile("unidades_protoss/32px/dragon.png");
        this.identificador = "dragon";
        this.info = "Dragon";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_protoss/original/dragonRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_protoss/original/creacion/dragonRawCrear.png")));
    }
}

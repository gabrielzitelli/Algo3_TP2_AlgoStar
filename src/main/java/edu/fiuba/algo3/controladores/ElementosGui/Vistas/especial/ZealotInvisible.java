package edu.fiuba.algo3.controladores.ElementosGui.Vistas.especial;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.modelo.Unidades.EstadoUnidad.Invisible;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;

public class ZealotInvisible extends EspecialVista {
    public ZealotInvisible() {
        this.elemento = new Invisible(new Zealot());
        this.tile = new Tile("unidades_protoss/32px/zealot_invisible.png");
        this.info = "Unidad invisible";
    }
}

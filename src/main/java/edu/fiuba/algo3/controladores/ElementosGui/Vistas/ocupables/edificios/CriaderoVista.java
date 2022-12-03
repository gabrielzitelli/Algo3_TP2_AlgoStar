package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import javafx.scene.canvas.GraphicsContext;

public class CriaderoVista extends OcupableVista {

    Tile piso = new Tile("superficies/32px/moho_transparente.png");
    public CriaderoVista(){
        this.tile = new Tile("edificios_zerg/32px/criadero.png");
        this.identificador = "criadero";
        this.info = "Criadero";
    }

    @Override
    public void render(GraphicsContext gc, int x, int y) {
        piso.render(gc, x, y);
        super.render(gc,x,y);
    }
}

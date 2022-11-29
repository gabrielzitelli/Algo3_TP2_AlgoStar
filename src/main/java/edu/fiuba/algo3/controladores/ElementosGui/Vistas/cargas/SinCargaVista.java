package edu.fiuba.algo3.controladores.ElementosGui.Vistas.cargas;

import edu.fiuba.algo3.modelo.Mapa.Casilla.SinCarga;
import javafx.scene.canvas.GraphicsContext;

public class SinCargaVista extends edu.fiuba.algo3.controladores.ElementosGui.Vistas.Vista {

    public SinCargaVista(){
        this.elemento = new SinCarga();
        this.info = "ninguno";
    }

    @Override
    public void render(GraphicsContext gc, int x, int y){
        //No renderiza
    }
}

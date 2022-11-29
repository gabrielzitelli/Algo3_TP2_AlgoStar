package edu.fiuba.algo3.controladores.ElementosGui.Vistas.moho;

import edu.fiuba.algo3.modelo.Mapa.Casilla.SinMoho;
import javafx.scene.canvas.GraphicsContext;

public class SinMohoVista extends MohoVista {
    SinMohoVista() {
        this.elemento = new SinMoho();
        this.info = "ninguno";
    }

    @Override
    public void render(GraphicsContext gc, int x, int y){
        //No renderiza
    }
}


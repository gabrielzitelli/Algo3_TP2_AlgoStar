package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables;

import edu.fiuba.algo3.modelo.Unidades.SinOcupar;
import javafx.scene.canvas.GraphicsContext;

public class SinOcuparVista extends OcupableVista {

    public SinOcuparVista(){
        this.info = "libre";
        this.elemento = new SinOcupar();
    }

    @Override
    public void render(GraphicsContext gc, int x, int y) {
        //No renderiza
    }
}

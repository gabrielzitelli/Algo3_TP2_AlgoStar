package edu.fiuba.algo3.controladores.ElementosGui.Vistas;

import javafx.scene.canvas.GraphicsContext;

public class SinVista extends Vista{
    public SinVista() {
        this.info = "ninguno";
    }

    @Override
    public void render(GraphicsContext gc, int x, int y) {
        //No renderiza
    }
}

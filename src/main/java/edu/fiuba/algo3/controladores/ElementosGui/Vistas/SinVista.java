package edu.fiuba.algo3.controladores.ElementosGui.Vistas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

public class SinVista extends Vista{
    public SinVista() {
        this.info = "No hay";
    }

    @Override
    public void render(GraphicsContext gc, int x, int y) {
        //No renderiza
    }

    @Override
    public void renderAdentroDeImageView(ImageView imageView){
        imageView.setImage(null);
    }
}

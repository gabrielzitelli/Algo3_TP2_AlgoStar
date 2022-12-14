package edu.fiuba.algo3.controladores.ElementosGui.Vistas;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Vista {

    protected String identificador;
    protected Tile tile;
    protected String info;
    protected Image imagenParaDisplay;
    protected String archivoDeSonido;
    private static final Vista sinVista = new SinVista();
    protected final String emojiInformacionUnicode = "ⓘ";
    protected final String emojiAdvertenciaUnidode = "⚠";
    protected final String emojiBulletPoint = "•";

    protected static Vista obtenerVista(String obtenerElemento, ArrayList<Vista> elementosVista) {
        for (Vista vista : elementosVista){

            if (vista.identificador.equals(obtenerElemento)){
                return vista;
            }
        }
        return sinVista;
    }

    protected String obtenerAtributoDeString(String stringFormateado, String tipoAtributo){
        String[] tokensJugador = stringFormateado.split(" ");
        String atributoDeseado = null;

        for (int i = 0; i < tokensJugador.length; i++) {
            if(Objects.equals(tokensJugador[i], tipoAtributo))
                atributoDeseado = tokensJugador[i + 1];
        }

        return atributoDeseado;
    }

   public void renderAdentroDeImageView(ImageView imageView){
        imageView.setImage(imagenParaDisplay);
    }

    public void render(GraphicsContext gc, int x, int y){
        tile.render(gc, x, y);
    }

    public String getInfo() {
        return info;
    }

    public String obtenerAudio() {
        return archivoDeSonido;
    }
}

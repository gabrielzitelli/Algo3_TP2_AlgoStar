package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Espiral;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class EspiralVista extends EdificioVista {

    public EspiralVista(){
        this.tile = new Tile("edificios_zerg/32px/espiral.png");
        this.identificador = "espiral";
        this.info = "Espiral";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_zerg/original/espiralRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_zerg/original/evolucion/espiralRawEvolucion.png")));
        this.edificiosRequisitos = "\n" + emojiInformacionUnicode + " Requiere: Moho, Guarida";
    }

    public void actualizarEfectoSiEstaEnConstruccion(ImageView imageView, Coordenada coordenada){
        Espiral esteEspriral = (Espiral) Mapa.obtener().obtenerOcupable(coordenada);

        if(esteEspriral.toString().contains(" estado en_construccion"))
            aplicarEfectoBlancoYNegroImageView(imageView);
    }


}

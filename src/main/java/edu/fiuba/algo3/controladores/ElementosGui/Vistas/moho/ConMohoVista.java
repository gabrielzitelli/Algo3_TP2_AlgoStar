package edu.fiuba.algo3.controladores.ElementosGui.Vistas.moho;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.superficie.SuperficieTerrestreVista;
import edu.fiuba.algo3.modelo.Mapa.Casilla.ConMoho;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ConMohoVista extends MohoVista {

    public ConMohoVista() {
        this.tile = new Tile("superficies/32px/moho_transparente.png");
        this.info = "Moho";
        this.identificador = "moho";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/superficies/32px/mini_terreno_con_moho.png")));
    }
}

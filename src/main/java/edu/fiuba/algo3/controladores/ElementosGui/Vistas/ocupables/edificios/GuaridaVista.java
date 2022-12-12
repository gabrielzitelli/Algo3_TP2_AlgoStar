package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import javafx.scene.image.Image;

import java.util.Objects;

public class GuaridaVista extends EdificioVista {
    public GuaridaVista() {
        this.tile = new Tile("edificios_zerg/32px/guarida.png");
        this.identificador = "guarida";
        this.info = "Guarida";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_zerg/original/guaridaRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_zerg/original/evolucion/guaridaRawEvolucion.png")));
        this.edificiosRequisitos = "\n" + emojiInformacionUnicode + " Requiere: Moho, Reserva de reproducción";
    }
}

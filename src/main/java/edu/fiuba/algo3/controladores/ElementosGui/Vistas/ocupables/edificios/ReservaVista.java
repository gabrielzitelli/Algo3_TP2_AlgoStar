package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import javafx.scene.image.Image;

import java.util.Objects;

public class ReservaVista extends EdificioVista {

    public ReservaVista() {
        this.tile = new Tile("edificios_zerg/32px/reserva_reproduccion.png");
        this.identificador = "reserva_reproduccion";
        this.info = "Reserva De Reproduccion";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_zerg/original/reserva_reproduccionRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_zerg/original/evolucion/reserva_reproduccionRawEvolucion.png")));
        this.edificiosRequisitos = "\n" + emojiInformacionUnicode + " Requiere: Moho";
    }
}

package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.ReservaDeReproduccion;

public class ReservaVista extends OcupableVista {

    public ReservaVista() {
        this.tile = new Tile("edificios_zerg/32px/reserva_reproduccion.png");
        this.identificador = "reserva_reproduccion";
        this.info = "Reserva De Reproduccion";
    }
}

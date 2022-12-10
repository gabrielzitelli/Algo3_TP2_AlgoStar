package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.ReservaDeReproduccion;

public class FabricaReservaDeReproduccion extends FabricaEdificio {

    @Override
    public Edificio crear() {
        ReservaDeReproduccion reserva = new ReservaDeReproduccion();
        reserva.asignarListaDeUnidades(fabricasDisponibles);
        return reserva;
    }
}

package edu.fiuba.algo3.modelo.Edificios.FabricasEdificios;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.ReservaDeReproduccion;

public class FabricaReservaDeReproduccion extends FabricaEdificio {

    @Override
    public Edificio crear() {
        ReservaDeReproduccion reserva = new ReservaDeReproduccion();
        reserva.asignarListaDeUnidades(fabricasDisponibles);
        return reserva;
    }
}

package edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Superficie;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import java.util.ArrayList;

public abstract class UnidadProtoss implements Unidad {

    protected int turnosDeConstruccion;
    protected Superficie superficieDondeSeMueve;

    public boolean estaConstruida() {
        return (turnosDeConstruccion == 0);
    }

    public void pasarTurno() {
        if (turnosDeConstruccion > 0)
            turnosDeConstruccion--;
    }

    public void verificarSuperficie(Superficie superficie){
        if (!superficie.puedeMoverse(this.superficieDondeSeMueve))
            throw new ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible();
    }

    public boolean esIgualA(Unidad unidad) {
        return this.getClass().equals(unidad.getClass());
    }
}

package edu.fiuba.algo3.modelo.UnidadesProtoss;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Superficie;
import edu.fiuba.algo3.modelo.Unidad;
import java.util.ArrayList;

public abstract class UnidadProtoss implements Unidad {
    int turnosDeConstruccion;
    public boolean estaConstruida() {
        return (turnosDeConstruccion == 0);
    }
    public void pasarTurno() {
        if (turnosDeConstruccion > 0) {
            turnosDeConstruccion--;
        }
    }
    protected ArrayList<Superficie> superficieDondeSeMueve = new ArrayList<>();

    public void verificarSuperficie(Superficie superficie){
        if( this.superficieDondeSeMueve.stream().allMatch( sup -> sup.soyDiferenteA(superficie) ) )
            throw new ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible();
    }

    @Override
    public boolean esIgualA(Unidad unidad) {
        return this.getClass().equals(unidad.getClass());
    }
}

package edu.fiuba.algo3.modelo.UnidadesZerg;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Superficie;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.Unidad;

import java.util.ArrayList;

public abstract class UnidadZerg implements Unidad {
    protected MaterialBruto recursoARecolectar;
    protected Recurso mineralDelImperio;
    protected ArrayList<Superficie> superficieDondeSeMueve = new ArrayList<>();
    int turnosDeConstruccion;
    public boolean estaConstruida() {
        return (turnosDeConstruccion == 0);
    }
    public void pasarTurno() {
        if (turnosDeConstruccion > 0) {
            turnosDeConstruccion--;
        }
    }
    //private int valorVital = x;
    public void interaccionar(Casilla unaCasilla){
    }
    public void setDepositoRecurso( Recurso recursoImperio ){
    }
    public void extraer(){
    }

    public boolean esIgualA(Unidad unidad) {
        return this.getClass().equals(unidad.getClass());
    }

    public void verificarSuperficie(Superficie superficie){
        if( this.superficieDondeSeMueve.stream().allMatch( sup -> sup.soyDiferenteA(superficie) ) )
            throw new ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible();
    }
}

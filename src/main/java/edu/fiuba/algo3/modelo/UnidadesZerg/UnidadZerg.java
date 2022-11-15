package edu.fiuba.algo3.modelo.UnidadesZerg;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.Unidad;

public abstract class UnidadZerg implements Unidad {
    protected MaterialBruto recursoARecolectar;
    protected Recurso mineralDelImperio;
    //private int valorVital = x;
    public void interaccionar(Casilla unaCasilla){
    }
    public void setDepositoRecurso( Recurso recursoImperio ){
    }
    public void extraer(){
    }

    public boolean esIgualA(UnidadZerg unidad) {
        return this.getClass().equals(unidad.getClass());
    }
}

package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

public abstract class Fabrica {

    protected static int poblacionNecesaria;

    public abstract Unidad crearUnidad();

    public boolean esIgualA(Fabrica unaFabrica){
        return this.getClass().equals(unaFabrica.getClass());
    }

    public void estasApta(Suministro unSuministro){
        unSuministro.tenesCapacidad(poblacionNecesaria);
        unSuministro.agregarPoblacion(poblacionNecesaria);
    }

    public static int obtenerPoblacionNecesaria(){
        return poblacionNecesaria;
    }

}

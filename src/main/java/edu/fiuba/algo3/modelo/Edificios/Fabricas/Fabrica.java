package edu.fiuba.algo3.modelo.Edificios.Fabricas;

import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

public abstract class Fabrica {

    protected static int poblacionNecesaria;

    public abstract Unidad crearUnidad();

    public void estasApta(Suministro unSuministro){
        unSuministro.tenesCapacidad(poblacionNecesaria);
        unSuministro.agregarPoblacion(poblacionNecesaria);
    }

    public static int obtenerPoblacionNecesaria(){
        return poblacionNecesaria;
    }

}

package edu.fiuba.algo3.modelo.Edificios.FabricasUnidades;

import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

public abstract class FabricasUnidades {

    protected int poblacionNecesaria;

    public abstract Unidad crearUnidad();

    public void estasApta(Suministro unSuministro){
        unSuministro.tenesCapacidad(poblacionNecesaria);
        unSuministro.agregarPoblacion(poblacionNecesaria);
    }

    public void estasAptaVerificacion(Suministro unSuministro){
        unSuministro.tenesCapacidad(poblacionNecesaria);
    }

    public int obtenerPoblacionNecesariaInstancia(){
        return poblacionNecesaria;
    }
}

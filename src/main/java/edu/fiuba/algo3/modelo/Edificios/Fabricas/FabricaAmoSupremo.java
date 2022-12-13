package edu.fiuba.algo3.modelo.Edificios.Fabricas;

import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.AmoSupremo;

public class FabricaAmoSupremo extends Fabrica {

    //private Suministro poblacionDelImperio;
    private static final int suministroAportado = 5;

    private static final int poblacionQueOcupa = 0;

    public FabricaAmoSupremo(){
        this.poblacionNecesaria = poblacionQueOcupa;
    }

    public static int obtenerPoblacionNecesaria(){
        return poblacionQueOcupa;
    }

    @Override
    public Unidad crearUnidad() {
        return new AmoSupremo();
    }

    @Override
    public void estasApta(Suministro unSuministro){
        super.estasApta(unSuministro);
        unSuministro.aumentarSuministro(suministroAportado);
    }
    public static int obtenerSuministroAportado(){
        return suministroAportado;
    }
}

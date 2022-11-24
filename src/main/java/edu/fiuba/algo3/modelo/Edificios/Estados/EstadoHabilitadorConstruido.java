package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Imperio.Suministro;

import java.util.ArrayList;

public class EstadoHabilitadorConstruido implements EstadoHabilitador {

    private Suministro poblacionImperio = new Suministro(200);

    private int aumentoSuministro; // PUEDE SERVIR PARA DESTRUIR SINO SACARLO

    @Override
    public EstadoHabilitador actualizar(ArrayList<Fabrica> fabricasAHabilitar, FabricasDisponibles fabricasDisponibles) {
        return this;
    }

    @Override
    public void marcarSuministro(Suministro suministroImperio, int cantidadAumentoSuministro){
        poblacionImperio = suministroImperio;
        aumentoSuministro = cantidadAumentoSuministro;
    }

    @Override
    public void modificarSuministro(){

    }

    @Override
     public void estaAptoParaCrearse(Fabrica unaFabrica){
        unaFabrica.estasApta(poblacionImperio);
     }


}

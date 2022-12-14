package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidades;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Imperio.Suministro;

import java.util.ArrayList;

public class EstadoHabilitadorConstruido implements EstadoHabilitador {

    private Suministro poblacionImperio;

    @Override
    public EstadoHabilitador actualizar(ArrayList<FabricasUnidades> fabricasAHabilitar, FabricasDisponibles fabricasDisponibles) {
        return this;
    }

    @Override
    public void marcarSuministro(Suministro suministroImperio, int cantidadAumentoSuministro){
        poblacionImperio = suministroImperio;
    }

    @Override
    public void modificarSuministro(){
    }

    public void disminuirSuministro(int cantidadDisminucionSuministro){
        poblacionImperio.disminuirSuministro(cantidadDisminucionSuministro);
    }

    @Override
     public void estaAptoParaCrearse(FabricasUnidades unaFabricasUnidades){
        unaFabricasUnidades.estasApta(poblacionImperio);
     }

    public void estaAptoParaCrearseVerificacion(FabricasUnidades unaFabricasUnidades){
        unaFabricasUnidades.estasAptaVerificacion(poblacionImperio);
    }

    @Override
    public String getEstado() {
        return " estado contruido";
    }
}

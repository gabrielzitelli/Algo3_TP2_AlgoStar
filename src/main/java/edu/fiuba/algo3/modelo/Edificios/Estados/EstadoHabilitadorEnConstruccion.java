package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.AlgoStar.Logger;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Imperio.Suministro;

import java.util.ArrayList;

public class EstadoHabilitadorEnConstruccion implements EstadoHabilitador {

    private int turnoParaEstarConstruido;
    private Suministro poblacionImperio = new Suministro(0);

    private int aumentoSuministro;

    public EstadoHabilitadorEnConstruccion(int turnoParaEstarConstruido){
        this.turnoParaEstarConstruido = turnoParaEstarConstruido;
    }


    @Override
    public EstadoHabilitador actualizar(ArrayList<Fabrica> fabricasAHabilitar, FabricasDisponibles fabricasDisponibles) {
        turnoParaEstarConstruido--;
        if(turnoParaEstarConstruido == 0) {
            if (fabricasDisponibles != null)
                fabricasDisponibles.aumentar(fabricasAHabilitar);

            modificarSuministro();
            EstadoHabilitador nuevoEstado = new EstadoHabilitadorConstruido();
            nuevoEstado.marcarSuministro(poblacionImperio, 0);
            return nuevoEstado;
        }
        return this;
    }

    @Override
    public void marcarSuministro(Suministro suministroImperio, int cantidadAumentoSuministro){
        poblacionImperio = suministroImperio;
        aumentoSuministro = cantidadAumentoSuministro;
    }

    @Override
    public void modificarSuministro(){
        poblacionImperio.aumentarSuministro( aumentoSuministro );
    }

    public void disminuirSuministro(int cantidadDisminucionSuministro){
    }

    @Override
    public void estaAptoParaCrearse(Fabrica unaFabrica){
    }

    public void estaAptoParaCrearseVerificacion(Fabrica unaFabrica){
    }

    @Override
    public String getEstado() {
        return " estado en_construccion";
    }
}

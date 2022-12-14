package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidades;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.GestorDeCrianza;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosRequisitosDeEstaUnidad;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

public class EstadoCreadorConstruido extends EstadoCreador {

    private final GestorDeCrianza gestorDeCrianza;

    public EstadoCreadorConstruido(FabricasDisponibles fabricasDisponibles, Coordenada coordenadaEdificioCreador) {
        this.fabricasDisponibles = fabricasDisponibles;
        this.gestorDeCrianza = new GestorDeCrianza(coordenadaEdificioCreador);
    }

    @Override
    public EstadoCreador actualizar() {
        gestorDeCrianza.actualizar();
        return this;
    }

    public void comprobarRequisitosMaterialesVerificacion(Ocupable ocupable, Mineral mineralDelImperio, Gas gasDelImperio){
        ArrayList<Recurso> listaDeRequisitos = ocupable.requisitosMateriales();
        Recurso mineralAConsumir = listaDeRequisitos.get(0);
        Recurso gasAconsumir = listaDeRequisitos.get(1);

        if (!mineralDelImperio.tienesMasQue(mineralAConsumir) || !gasDelImperio.tienesMasQue(gasAconsumir))
            throw new ErrorCantidadDeRecursoInsuficiente();
    }

    private void comprobarRequisitosMateriales(Ocupable ocupable, Mineral mineralDelImperio, Gas gasDelImperio){
        ArrayList<Recurso> listaDeRequisitos = ocupable.requisitosMateriales();
        Recurso mineralAConsumir = listaDeRequisitos.get(0);
        Recurso gasAconsumir = listaDeRequisitos.get(1);

        if (mineralDelImperio.tienesMasQue(mineralAConsumir) && gasDelImperio.tienesMasQue(gasAconsumir)){
            mineralDelImperio.consumir(mineralAConsumir.obtenerCantidad());
            gasDelImperio.consumir(gasAconsumir.obtenerCantidad());
        } else {
            throw new ErrorCantidadDeRecursoInsuficiente();
        }
    }

    public void colocarCoordenadaAlGestorDeCrianza(Coordenada coordenadaEdificioCreador){
        gestorDeCrianza.actualizarCoordenada(coordenadaEdificioCreador);
    }

    @Override
    public void crearUnidad(FabricasUnidades unaFabricasUnidades, ArrayList<Unidad> unidades, Mineral mineralDelImperio, Gas gasDelImperio) {
        verificarQueSePuedeFabricar(unaFabricasUnidades);
        Unidad unidad = unaFabricasUnidades.crearUnidad();
        comprobarRequisitosMateriales(unidad, mineralDelImperio, gasDelImperio);
        gestorDeCrianza.agregarUnidad(unidad, unidades, mineralDelImperio);
    }

    public void verificarQueSePuedeFabricar(FabricasUnidades unaFabricasUnidades) {
        if (!this.fabricasDisponibles.verificar(unaFabricasUnidades))
            throw new ErrorNoSeCumplenLosRequisitosDeEstaUnidad();
    }

}

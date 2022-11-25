package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Ataque.Ocupable;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Edificios.GestorDeCrianza;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosRequisitosDeEstaUnidad;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

public class EstadoCreadorConstruido extends EstadoCreador {

    GestorDeCrianza gestorDeCrianza = new GestorDeCrianza();

    public EstadoCreadorConstruido(FabricasDisponibles fabricasDisponibles) {
        this.fabricasDisponibles = fabricasDisponibles;
    }

    @Override
    public EstadoCreador actualizar() {
        gestorDeCrianza.actualizar();
        return this;
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
    @Override
    public void crearUnidad(Fabrica unaFabrica, ArrayList<Unidad> unidades, Mineral mineralDelImperio, Gas gasDelImperio) {
        verificarQueSePuedeFabricar(unaFabrica);
        Unidad unidad = unaFabrica.crearUnidad();
        comprobarRequisitosMateriales(unidad, mineralDelImperio, gasDelImperio);
        gestorDeCrianza.agregarUnidad(unidad, unidades);
    }

    private void verificarQueSePuedeFabricar(Fabrica unaFabrica) {
        if (!this.fabricasDisponibles.verificar(unaFabrica))
            throw new ErrorNoSeCumplenLosRequisitosDeEstaUnidad();
    }

}

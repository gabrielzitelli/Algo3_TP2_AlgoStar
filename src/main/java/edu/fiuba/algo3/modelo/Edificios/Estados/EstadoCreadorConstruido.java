package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Edificios.GestorDeCrianza;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosRequisitosDeEstaUnidad;
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

    @Override
    public void crearUnidad(Fabrica unaFabrica, ArrayList<Unidad> unidades) {
        verificarQueSePuedeFabricar(unaFabrica);
        gestorDeCrianza.agregarUnidad(unaFabrica.crearUnidad(), unidades);
    }

    private void verificarQueSePuedeFabricar(Fabrica unaFabrica) {
        if (!this.fabricasDisponibles.verificar(unaFabrica))
            throw new ErrorNoSeCumplenLosRequisitosDeEstaUnidad();
    }

}
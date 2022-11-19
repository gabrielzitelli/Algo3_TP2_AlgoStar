package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;

import java.util.ArrayList;

// Permite habilitar unidades para que los EstadosCreadores las creen
public interface EstadoHabilitador {

    EstadoHabilitador actualizar(ArrayList<Fabrica> fabricasAHabilitar, ArrayList<Fabrica> listaDeFabricasDisponibles);
}

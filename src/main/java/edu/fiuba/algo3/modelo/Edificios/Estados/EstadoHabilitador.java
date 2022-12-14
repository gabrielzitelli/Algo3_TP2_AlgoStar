package edu.fiuba.algo3.modelo.Edificios.Estados;

import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidades;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Imperio.Suministro;

import java.util.ArrayList;

// Permite habilitar unidades para que los EstadosCreadores las creen
public interface EstadoHabilitador {

    EstadoHabilitador actualizar(ArrayList<FabricasUnidades> fabricasAHabilitar, FabricasDisponibles fabricasDisponibles);

    void marcarSuministro(Suministro suministroImperio, int cantidadAumentoSuministro);
    void modificarSuministro();
    void disminuirSuministro(int cantidadDisminucionSuministro);

    void estaAptoParaCrearse(FabricasUnidades unaFabricasUnidades);
    void estaAptoParaCrearseVerificacion(FabricasUnidades unaFabricasUnidades);

    String getEstado();
}

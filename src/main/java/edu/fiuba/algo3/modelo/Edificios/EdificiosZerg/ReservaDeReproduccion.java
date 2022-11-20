package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitador;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitadorEnConstruccion;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Vida.VidaRegenerativa;

import java.util.ArrayList;

public class ReservaDeReproduccion extends Edificio {

    private EstadoHabilitador estadoHabilitador;
    private int turnoParaEstarConstruido = 12;
    private int valorVital = 1000;

    // Fabricas que el edificio habilita
    private ArrayList<Fabrica> listaFabricasAHabilitar = new ArrayList<Fabrica>();
    private FabricasDisponibles fabricasDisponibles;

    public ReservaDeReproduccion(){
        this.costoMineral = 150;
        this.costoGas = 0;
        this.estadoMoho = new ConMoho();
        this.estadoRecolectable = new NoRecolectable();
        this.vida = new VidaRegenerativa(valorVital);

        estadoHabilitador = new EstadoHabilitadorEnConstruccion(turnoParaEstarConstruido);

        listaFabricasAHabilitar.add(new FabricaZerling());
    }

    public void pasarTurno(){
        estadoHabilitador = estadoHabilitador.actualizar(listaFabricasAHabilitar, fabricasDisponibles);
        vida.pasarTurno();
    }

    public void asignarListaDeUnidades(FabricasDisponibles fabricasDisponibles) {
        this.fabricasDisponibles = fabricasDisponibles;
    }
}

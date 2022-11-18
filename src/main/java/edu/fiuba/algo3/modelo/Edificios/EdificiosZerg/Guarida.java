package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitador;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitadorEnConstruccion;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Vida.VidaRegenerativa;

import java.util.ArrayList;

public class Guarida extends Edificio {

    private EstadoHabilitador estadoHabilitador;
    private int turnoParaEstarConstruido = 12;
    private int valorVital = 1250;

    // Fabricas que el edificio habilita
    private ArrayList<Fabrica> listaFabricasAHabilitar = new ArrayList<Fabrica>();
    private ArrayList<Fabrica> listaDeFabricasDisponibles;

    public Guarida(){
        this.costoMineral = 200;
        this.costoGas = 100;
        this.estadoMoho = new ConMoho();
        this.estadoRecolectable = new NoRecolectable();
        this.vida = new VidaRegenerativa(valorVital);

        estadoHabilitador = new EstadoHabilitadorEnConstruccion(turnoParaEstarConstruido);

        listaFabricasAHabilitar.add(new FabricaHidralisco());
    }

    public static ArrayList<Edificio> requisitos() {
        ArrayList<Edificio> requisitos = new ArrayList<>();
        requisitos.add(new ReservaDeReproduccion());
        return requisitos;
    }

    public void pasarTurno() {
        estadoHabilitador = estadoHabilitador.actualizar(listaFabricasAHabilitar, listaDeFabricasDisponibles);
        vida.pasarTurno();
    }

    public void asignarListaDeUnidades(ArrayList<Fabrica> listaDeFabricasDisponibles) {
        this.listaDeFabricasDisponibles = listaDeFabricasDisponibles;
    }
}

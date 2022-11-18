package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitador;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitadorEnConstruccion;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Vida.VidaRegenerativa;

import java.util.ArrayList;

public class Espiral extends Edificio {

    private EstadoHabilitador estadoHabilitador;
    private int turnoParaEstarConstruido = 10;
    private int valorVital = 1300;

    // Fabricas que el edificio habilita
    private ArrayList<Fabrica> listaFabricasAHabilitar = new ArrayList<Fabrica>();
    private ArrayList<Fabrica> listaDeFabricasDisponibles;

    public Espiral() {
        this.costoGas = 100;
        this.costoMineral = 150;
        this.estadoRecolectable = new NoRecolectable();
        this.estadoMoho = new ConMoho();
        this.vida = new VidaRegenerativa(valorVital);

        estadoHabilitador = new EstadoHabilitadorEnConstruccion(turnoParaEstarConstruido);

        listaFabricasAHabilitar.add(new FabricaMutalisco());
    }

    public static ArrayList<Edificio> requisitos() {
        ArrayList<Edificio> requisitos = new ArrayList<>();
        requisitos.add(new Guarida());
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

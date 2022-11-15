package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.States.EstadoEspiral;
import edu.fiuba.algo3.modelo.States.EstadoEspiralEnConstruccion;
import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;

import java.util.ArrayList;

public class Espiral extends Edificio {

    private EstadoEspiral estado;

    private int turnoParaEstarConstruido = 10;
    private int valorVital = 1300;
    private ArrayList<Fabrica> listaDeFabricasDisponibles;


    public Espiral() {
        this.costoGas = 100;
        this.costoMineral = 150;
        this.estadoRecolectable = new NoRecolectable();
        this.estadoMoho = new ConMoho();
        this.vida = new VidaRegenerativa(valorVital);
        estado = new EstadoEspiralEnConstruccion(turnoParaEstarConstruido);
    }

    public static ArrayList<Edificio> requisitos() {
        ArrayList<Edificio> requisitos = new ArrayList<>();
        requisitos.add(new Guarida());
        return requisitos;
    }

    public void pasarTurno() {
        estado = estado.actualizar(listaDeFabricasDisponibles);
        vida.pasarTurno();
    }

    public FabricaMutalisco crearFabricaMutalisco() {
        return estado.crearFabricaMutalisco();
    }

    public void asignarListaDeUnidades(ArrayList<Fabrica> listaDeFabricasDisponibles) {
        this.listaDeFabricasDisponibles = listaDeFabricasDisponibles;
    }
}

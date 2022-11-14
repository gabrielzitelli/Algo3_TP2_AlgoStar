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

    private Recolectable estadoRecolectable = new NoRecolectable();
    private EstadoMoho estadoMoho = new ConMoho();
    private int valorVital = 1300;


    public Espiral() {
        this.vida = new VidaRegenerativa(valorVital);
        estado = new EstadoEspiralEnConstruccion(turnoParaEstarConstruido);
    }

    public static ArrayList<Edificio> requisitos() {
        ArrayList<Edificio> requisitos = new ArrayList<>();
        requisitos.add(new Guarida());
        return requisitos;
    }

    public void pasarTurno() {
        estado = estado.actualizar();
        vida.pasarTurno();
    }

    public FabricaMutalisco crearFabricaMutalisco() {
        return estado.crearFabricaMutalisco();
    }

    public void verificarConstruccion(Casilla unaCasilla){
        unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        unaCasilla.tieneEsteMoho(estadoMoho);
    }
}

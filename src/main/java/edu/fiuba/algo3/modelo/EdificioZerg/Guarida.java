package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.States.EstadoGuarida;
import edu.fiuba.algo3.modelo.States.EstadoGuaridaEnConstruccion;
import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;

import java.util.ArrayList;

public class Guarida extends Edificio {

    private EstadoGuarida estado;

    private int turnoParaEstarConstruido = 12;
    private int valorVital = 1250;


    public Guarida(){
        this.costoMineral = 200;
        this.costoGas = 100;
        this.estadoMoho = new ConMoho();
        this.estadoRecolectable = new NoRecolectable();
        //Aplicacion de patron State
        estado = new EstadoGuaridaEnConstruccion(turnoParaEstarConstruido);
        this.vida = new VidaRegenerativa(valorVital);
    }

    public static ArrayList<Edificio> requisitos() {
        ArrayList<Edificio> requisitos = new ArrayList<>();
        requisitos.add(new ReservaDeReproduccion());
        return requisitos;
    }

    public void pasarTurno() {
        estado = estado.actualizar();
        vida.pasarTurno();
    }

    public FabricaHidralisco crearFabricaHidralisco() {
        return estado.crearFabricaHidralisco();
    }
}

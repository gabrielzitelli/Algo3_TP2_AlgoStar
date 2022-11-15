package edu.fiuba.algo3.modelo.EdificioProtoss;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.States.EstadoPuertoEstelar;
import edu.fiuba.algo3.modelo.States.EstadoPuertoEstelarEnConstruccion;
import edu.fiuba.algo3.modelo.vida.VidaConEscudo;

import java.util.ArrayList;

public class PuertoEstelar extends Edificio {

    private EstadoPuertoEstelar estado;
    private int turnoParaEstarConstruido = 10;
    private int valorVital = 600;

    public PuertoEstelar() {
        this.costoGas = 150;
        this.costoMineral = 150;
        this.estadoRecolectable = new NoRecolectable();
        this.estadoCarga = new ConCarga();
        this.estadoMoho = new SinMoho();
        estado = new EstadoPuertoEstelarEnConstruccion(turnoParaEstarConstruido);
        this.vida = new VidaConEscudo(valorVital, valorVital);
    }

    public static ArrayList<Edificio> requisitos() {
        ArrayList<Edificio> requisitos = new ArrayList<>();
        requisitos.add(new Acceso());
        return requisitos;
    }

    public void pasarTurno() {
        estado = estado.actualizar();
        vida.pasarTurno();
    }

    public FabricaScout crearFabricaScout() {return estado.crearFabricaScout();}
}

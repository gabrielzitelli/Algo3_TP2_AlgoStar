package edu.fiuba.algo3.modelo.EdificioProtoss;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.States.EstadoAcceso;
import edu.fiuba.algo3.modelo.States.EstadoAccesoEnConstruccion;
import edu.fiuba.algo3.modelo.vida.VidaConEscudo;

public class Acceso extends Edificio {

    private int turnoParaEstarConstruido = 8;
    private int valorVital = 500;

    private EstadoAcceso estado;

    public Acceso(){
        this.costoGas = 0;
        this.costoMineral = 150;
        this.estadoCarga = new ConCarga();
        this.estadoMoho = new SinMoho();
        this.estadoRecolectable = new NoRecolectable();
        this.vida = new VidaConEscudo(valorVital, valorVital);
        estado = new EstadoAccesoEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno(){
        estado = estado.actualizar();
        vida.pasarTurno();
    }

    public FabricaDragon crearFabricaDragon() {

        return estado.crearFabricaDragon();
    }

    public FabricaZealot crearFabricaZealot(){
        return estado.crearFabricaZealot();
    }
}

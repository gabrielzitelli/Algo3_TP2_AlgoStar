package edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoGeneradorDeEnergia;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoGeneradorDeEnergiaEnConstruccion;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Vida.VidaConEscudo;

public class Pilon extends Edificio {

    private EstadoGeneradorDeEnergia estadoGeneradorDeEnergia;
    private int turnoParaEstarConstruido = 5;
    private int valorVital = 300;

    public Pilon(){
        this.costoGas = 0;
        this.costoMineral = 100;
        this.estadoRecolectable = new NoRecolectable();
        this.estadoMoho = new SinMoho();
        this.vida = new VidaConEscudo(valorVital, valorVital);

        estadoGeneradorDeEnergia = new EstadoGeneradorDeEnergiaEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno(){
        estadoGeneradorDeEnergia = estadoGeneradorDeEnergia.actualizar(coordenada);
        vida.pasarTurno();
    }
}

package edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoGeneradorDeEnergia;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoGeneradorDeEnergiaEnConstruccion;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
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
        this.suministroAportado = 5;
        this.superficieRequerida = new SuperficieTerrestre();
        estadoGeneradorDeEnergia = new EstadoGeneradorDeEnergiaEnConstruccion(turnoParaEstarConstruido);
    }

    @Override
    protected void destruirEdificio() {
        Mapa elMapa = Mapa.obtener();
        estadoGeneradorDeEnergia.disminuirSuministro(suministroAportado);
        elMapa.destruirEdificio(coordenada);
        estadoGeneradorDeEnergia.desenergizar(coordenada);
    }

    public void pasarTurno(){
        estadoGeneradorDeEnergia = estadoGeneradorDeEnergia.actualizar(coordenada);
        vida.pasarTurno();
    }

    @Override
    public void modificarPoblacion(Suministro suministro){
        estadoGeneradorDeEnergia.marcarSuministro(suministro, suministroAportado);
    }

    public void construirInmediatamente(){
        for (int i = 0; i < turnoParaEstarConstruido; i++)
            pasarTurno();
    }

    public void asignarSuministro(Suministro poblacionDelImperio){
        estadoGeneradorDeEnergia.marcarSuministro(poblacionDelImperio, 0);
    }
}

package edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoGeneradorDeEnergia;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoGeneradorDeEnergiaEnConstruccion;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.NoRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SinMoho;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Edificios.Vida.VidaConEscudo;

import java.util.ArrayList;

public class Pilon extends EdificioProtoss {

    private EstadoGeneradorDeEnergia estadoGeneradorDeEnergia;
    private int turnoParaEstarConstruido = 5;
    private int valorVital = 300;

    private static ArrayList<Edificio> requisitosEdilicios = new ArrayList<Edificio>();


    public Pilon(){
        this.costoGas = 0;
        this.costoMineral = 100;
        this.estadoRecolectable = new NoRecolectable();
        this.estadoMohoRequerido = new SinMoho();
        this.vida = new VidaConEscudo(valorVital, valorVital);
        this.suministroAportado = 5;
        this.superficieRequerida = new SuperficieTerrestre();
        estadoGeneradorDeEnergia = new EstadoGeneradorDeEnergiaEnConstruccion(turnoParaEstarConstruido);
        this.identificador = "pilon";
    }


    public ArrayList<Edificio> obtenerRequisitosEdilicios(){
        return requisitosEdilicios;
    }

    @Override
    protected void destruirEdificio() {
        super.destruirEdificio();

        estadoGeneradorDeEnergia.disminuirSuministro(suministroAportado);
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

    @Override
    protected String obtenerEstado() {
        return estadoGeneradorDeEnergia.getEstado();
    }
}

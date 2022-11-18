package edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoEdificioRecolector;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoEdificioRecolectorEnConstruccion;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.Vida.VidaConEscudo;

public class Asimilador extends Edificio {

    private EstadoEdificioRecolector estado;
    private int turnoParaEstarConstruido = 6;
    private Recurso gasDelImperio;
    private MaterialBruto volcanDeGas = null;
    private int cantidadDeExtraccionUnitaria = 20;
    private int valorVital = 450;

    public Asimilador(Recurso gasDelImperio){
        this.costoGas = 0;
        this.costoMineral = 100;
        this.estadoRecolectable = new GasRecolectable();
        this.estadoMoho = new SinMoho();
        this.vida = new VidaConEscudo(valorVital, valorVital);
        this.gasDelImperio = gasDelImperio;
        this.estado = new EstadoEdificioRecolectorEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno(){
        estado = estado.actualizar();
        this.extraer();
        vida.pasarTurno();
    }

    private void extraer(){
        estado.extraer(gasDelImperio, volcanDeGas, cantidadDeExtraccionUnitaria);
    }

    @Override
    public void verificarConstruccion(Casilla unaCasilla) {
        super.verificarConstruccion(unaCasilla);
        establecerSobreGas(unaCasilla.obtenerMaterial());
    }

    public void establecerSobreGas(MaterialBruto volcanDeGas){
        this.volcanDeGas = volcanDeGas;
    }
}
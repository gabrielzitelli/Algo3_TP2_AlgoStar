package edu.fiuba.algo3.modelo.EdificioProtoss;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.States.EstadoAsimilador;
import edu.fiuba.algo3.modelo.States.EstadoAsimiladorEnConstruccion;
import edu.fiuba.algo3.modelo.vida.VidaConEscudo;

public class Asimilador extends Edificio {
    private Recolectable estadoRecolectable = new GasRecolectable();
    private EstadoMoho estadoMoho = new SinMoho();

    private EstadoAsimilador estado;
    private int turnoParaEstarConstruido = 6;
    private Recurso gasDelImperio;
    private MaterialBruto volcanDeGas = null;
    private int valorVital = 450;

    public Asimilador(Recurso gasDelImperio){
        this.vida = new VidaConEscudo(valorVital, valorVital);
        this.gasDelImperio = gasDelImperio;
        this.estado = new EstadoAsimiladorEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno(){
        estado = estado.actualizar();
        this.extraer();
        vida.pasarTurno();
    }

    private void extraer(){
        estado.extraer(gasDelImperio, volcanDeGas);
    }

    @Override
    public void verificarConstruccion(Casilla unaCasilla) {
        unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        unaCasilla.tieneEsteMoho(estadoMoho);
        establecerSobreGas(unaCasilla.obtenerMaterial());
    }
    public void establecerSobreGas(MaterialBruto volcanDeGas){
        this.volcanDeGas = volcanDeGas;
    }
}

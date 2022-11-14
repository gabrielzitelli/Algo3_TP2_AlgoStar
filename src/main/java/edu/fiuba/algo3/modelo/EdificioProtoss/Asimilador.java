package edu.fiuba.algo3.modelo.EdificioProtoss;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.States.EstadoAsimilador;
import edu.fiuba.algo3.modelo.States.EstadoAsimiladorEnConstruccion;
import edu.fiuba.algo3.modelo.vida.VidaConEscudo;

public class Asimilador extends Edificio {
    protected Recolectable estadoRecolectable = new GasRecolectable();
    protected Cargable estadoCarga = new ConCarga();
    protected EstadoMoho estadoMoho = new SinMoho();

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
        vida.pasarTurno();
    }

    public void extraer(){
        estado.extraer(gasDelImperio, volcanDeGas);
    }

    @Override
    public void verificarConstruccion(Casilla unaCasilla) {
        unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        unaCasilla.tieneEstaCarga(estadoCarga);
        unaCasilla.tieneEsteMoho(estadoMoho);
        establecerSobreGas(unaCasilla.obtenerMaterial());
    }
    public void establecerSobreGas(MaterialBruto volcanDeGas){
        this.volcanDeGas = volcanDeGas;
    }
}

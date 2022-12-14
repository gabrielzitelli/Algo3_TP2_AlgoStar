package edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoRecolector;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoRecolectorEnConstruccion;
import edu.fiuba.algo3.modelo.Edificios.Vida.VidaConEscudo;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SinMoho;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;

import java.util.ArrayList;

public class Asimilador extends EdificioProtoss {

    private EstadoRecolector estado;
    private final Recurso gasDelImperio;
    private MaterialBruto volcanDeGas = null;

    private static final ArrayList<Edificio> requisitosEdilicios = new ArrayList<>();


    public Asimilador(Recurso gasDelImperio){
        this.costoGas = 0;
        this.costoMineral = 100;
        this.estadoRecolectable = new GasRecolectable();
        this.estadoMohoRequerido = new SinMoho();
        int valorVital = 450;
        this.vida = new VidaConEscudo(valorVital, valorVital);
        this.superficieRequerida = new SuperficieTerrestre();
        this.gasDelImperio = gasDelImperio;
        int turnoParaEstarConstruido = 6;
        this.estado = new EstadoRecolectorEnConstruccion(turnoParaEstarConstruido);
        this.identificador = "asimilador";
    }

    public ArrayList<Edificio> obtenerRequisitosEdilicios(){
        return requisitosEdilicios;
    }

    public void pasarTurno(){
        estado = estado.actualizar();
        this.extraer();
        vida.pasarTurno();
    }

    private void extraer(){
        int cantidadDeExtraccionUnitaria = 20;
        estado.extraer(gasDelImperio, volcanDeGas, cantidadDeExtraccionUnitaria);
    }

    @Override
    public void verificarColocable(Casilla unaCasilla) {
        super.verificarColocable(unaCasilla);
        establecerSobreGas(unaCasilla.obtenerMaterial());
    }

    public void establecerSobreGas(MaterialBruto volcanDeGas){
        this.volcanDeGas = volcanDeGas;
    }

    @Override
    protected String obtenerEstado() {
        return estado.getEstado();
    }
}

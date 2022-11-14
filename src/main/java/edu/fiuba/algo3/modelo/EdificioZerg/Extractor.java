package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.States.EstadoExtractor;
import edu.fiuba.algo3.modelo.States.EstadoExtractorEnConstruccion;
import edu.fiuba.algo3.modelo.UnidadesZerg.Zangano;
import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;

import java.util.LinkedList;

public class Extractor extends Edificio {

    private EstadoExtractor estado;
    private int turnoParaEstarConstruido = 6;
    private Recurso gasDelImperio;
    private MaterialBruto volcanDeGas = null;
    private LinkedList<Zangano> zanganosEmpleados = new LinkedList<>();

    private Recolectable estadoRecolectable = new GasRecolectable();
    private EstadoMoho estadoMoho = new ConMoho();
    private int valorVital = 750;


    public Extractor(Recurso gasDelImperio){
        this.vida = new VidaRegenerativa(valorVital);
        this.gasDelImperio = gasDelImperio;
        this.estado = new EstadoExtractorEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno(){
        estado = estado.actualizar();
        vida.pasarTurno();
    }

    public void extraer(){
        estado.extraer(gasDelImperio, volcanDeGas, zanganosEmpleados.size());
    }

    public void contratarZangano(Zangano zanganoAContratar){
        estado.contratarZangano(zanganoAContratar, zanganosEmpleados);
    }

    public void verificarConstruccion(Casilla unaCasilla){
        unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        unaCasilla.tieneEsteMoho(estadoMoho);
        establecerSobreGas(unaCasilla.obtenerMaterial());
    }

    public void establecerSobreGas(MaterialBruto volcanDeGas){
        this.volcanDeGas = volcanDeGas;
    }
}

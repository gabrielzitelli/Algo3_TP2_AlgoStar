package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.States.EstadoExtractor;
import edu.fiuba.algo3.modelo.States.EstadoExtractorEnConstruccion;
import edu.fiuba.algo3.modelo.UnidadesZerg.Zangano;

import java.util.LinkedList;

public class Extractor implements Edificio {

    private EstadoExtractor estado;
    private int turnoParaEstarConstruido = 6;
    private Recurso gasDelImperio;
    private MaterialBruto volcanDeGas = null;
    private LinkedList<Zangano> zanganosEmpleados = new LinkedList<>();

    protected Recolectable estadoRecolectable = new GasRecolectable();
    protected Cargable estadoCarga = new SinCarga();
    protected EstadoMoho estadoMoho = new ConMoho();

    public Extractor(Recurso gasDelImperio){
        this.gasDelImperio = gasDelImperio;
        this.estado = new EstadoExtractorEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno(){
        estado = estado.actualizar();
    }

    public void extraer(){
        estado.extraer(gasDelImperio, volcanDeGas, zanganosEmpleados.size());
    }

    public void contratarZangano(Zangano zanganoAContratar){
        estado.contratarZangano(zanganoAContratar, zanganosEmpleados);
    }

    public void verificarConstruccion(Casilla unaCasilla){
        unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        unaCasilla.tieneEstaCarga(estadoCarga);
        unaCasilla.tieneEsteMoho(estadoMoho);
        establecerSobreGas(unaCasilla.obtenerMaterial());
    }

    public void establecerSobreGas(MaterialBruto volcanDeGas){
        this.volcanDeGas = volcanDeGas;
    }
}

package edu.fiuba.algo3.modelo.Edificio_Zerg;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.MaterialBruto;
import edu.fiuba.algo3.modelo.States.EstadoExtractor;
import edu.fiuba.algo3.modelo.States.EstadoExtractorEnConstruccion;
import edu.fiuba.algo3.modelo.UnidadesZerg.Zangano;

import java.util.LinkedList;

public class Extractor {

    private EstadoExtractor estado;
    private int turnoParaEstarConstruido = 6;
    private Recurso gasDelImperio;
    private MaterialBruto volcanDeGas;
    private LinkedList<Zangano> zanganosEmpleados = new LinkedList<>();

    public Extractor(Recurso gasDelImperio, MaterialBruto volcanDeGas){
        this.gasDelImperio = gasDelImperio;
        this.volcanDeGas = volcanDeGas;
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
}

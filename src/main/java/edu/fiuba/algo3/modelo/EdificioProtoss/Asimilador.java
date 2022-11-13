package edu.fiuba.algo3.modelo.EdificioProtoss;

import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.GasBruto;
import edu.fiuba.algo3.modelo.States.EstadoAsimilador;
import edu.fiuba.algo3.modelo.States.EstadoAsimiladorEnConstruccion;

public class Asimilador {

    private EstadoAsimilador estado;
    private int turnoParaEstarConstruido = 6;
    private Recurso gasDelImperio;
    private GasBruto volcanDeGas;

    public Asimilador(Recurso gasDelImperio, GasBruto volcanDeGas){
        this.gasDelImperio = gasDelImperio;
        this.volcanDeGas = volcanDeGas;

        this.estado = new EstadoAsimiladorEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno(){
        estado = estado.actualizar();
    }

    public void extraer(){
        estado.extraer(gasDelImperio, volcanDeGas);
    }
}

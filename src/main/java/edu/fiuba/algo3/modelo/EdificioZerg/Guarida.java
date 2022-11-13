package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.States.EstadoGuarida;
import edu.fiuba.algo3.modelo.States.EstadoGuaridaEnConstruccion;

public class Guarida implements Edificio {

    private EstadoGuarida estado;

    private int turnoParaEstarConstruido = 12;


    protected Recolectable estadoRecolectable = new NoRecolectable();
    protected Cargable estadoCarga = new SinCarga();
    protected EstadoMoho estadoMoho = new ConMoho();

    public Guarida(){
        //Aplicacion de patron State
        estado = new EstadoGuaridaEnConstruccion(turnoParaEstarConstruido);
    }

    public void pasarTurno() {
        estado = estado.actualizar();
    }

    public FabricaHidralisco crearFabricaHidralisco() {
        return estado.crearFabricaHidralisco();
    }

    public void verificarConstruccion(Casilla unaCasilla){
        unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        unaCasilla.tieneEstaCarga(estadoCarga);
        unaCasilla.tieneEsteMoho(estadoMoho);
    }
}

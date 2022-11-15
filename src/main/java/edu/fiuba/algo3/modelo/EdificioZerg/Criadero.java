package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.States.EstadoCriadero;
import edu.fiuba.algo3.modelo.States.EstadoCriaderoEnConstruccion;
import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;

public class Criadero extends Edificio {

    //SUPUESTO: CRIADERO NO SE PUEDE PONER EN TERRENO CARGADO

    private EstadoCriadero estado;
    private int turnoParaEstarConstruido = 4;
    private int maxLarvas = 3;
    private int cantidadLarvas;
    private int valorVital = 500;

    public Criadero(){
        this.costoGas = 0;
        this.costoMineral = 50;
        this.estadoRecolectable = new NoRecolectable();
        this.estadoCarga = new SinCarga();
        this.vida = new VidaRegenerativa(valorVital);
        //Aplicacion de patron State
        estado = new EstadoCriaderoEnConstruccion(turnoParaEstarConstruido);
        cantidadLarvas = maxLarvas;
    }

    public UnidadZerg crearUnidad(Fabrica unaFabrica) {
        if (cantidadLarvas > 0) {
            cantidadLarvas--;
            return estado.crearUnidad(unaFabrica);
        }
        throw new ErrorCriaderoNoTieneMasLarvas();
    }

    private void regenerarUnaLarva(){
        if (cantidadLarvas < maxLarvas)
            cantidadLarvas++;
    }

    protected void destruirEdificio() {
        Mapa elMapa = Mapa.obtener();
        elMapa.destruirEdificio(coordenada);
        elMapa.expandirMoho(coordenada, 0);
    }

    public void pasarTurno(){
        estado = estado.actualizar(coordenada);
        this.regenerarUnaLarva();
        vida.pasarTurno();
    }
}

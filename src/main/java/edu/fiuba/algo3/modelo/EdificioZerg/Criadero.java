package edu.fiuba.algo3.modelo.EdificioZerg;

import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.States.EstadoCriadero;
import edu.fiuba.algo3.modelo.States.EstadoCriaderoEnConstruccion;
import edu.fiuba.algo3.modelo.UnidadesZerg.UnidadZerg;
import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.vida.VidaRegenerativa;

public class Criadero extends Edificio {

    private Recolectable estadoRecolectable = new NoRecolectable();
    private Cargable estadoCarga = new SinCarga();
    //SUPUESTO: CRIADERO NO SE PUEDE PONER EN TERRENO CARGADO

    private EstadoCriadero estado;
    private int turnoParaEstarConstruido = 4;
    private int maxLarvas = 3;
    private int cantidadLarvas;
    private Coordenada coordenada;
    private int valorVital = 500;

    public Criadero(){
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

    public void pasarTurno(){
        estado = estado.actualizar(coordenada);
        this.regenerarUnaLarva();
        vida.pasarTurno();
    }

    public void verificarConstruccion(Casilla unaCasilla){
        unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        unaCasilla.tieneEstaCarga(estadoCarga);
        coordenada = unaCasilla.obtenerCoordenada();
    }
}

package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.States.EstadoCriadero;
import edu.fiuba.algo3.modelo.States.EstadoCriaderoEnConstruccion;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.Vida.VidaRegenerativa;

import java.util.ArrayList;

public class Criadero extends Edificio {

    //SUPUESTO: CRIADERO NO SE PUEDE PONER EN TERRENO CARGADO

    private EstadoCriadero estado;
    private int turnoParaEstarConstruido = 4;
    private int maxLarvas = 3;
    private int cantidadLarvas;
    private int valorVital = 500;
    private ArrayList<Fabrica> listaDeFabricasDisponibles;
    private ArrayList<Unidad> unidades;

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

    public void crearUnidad(Fabrica unaFabrica) {
        verificarQueSePuedeFabricar(unaFabrica);
        if (cantidadLarvas > 0) {
            cantidadLarvas--;
            estado.crearUnidad(unaFabrica, unidades);
        }else {
            throw new ErrorCriaderoNoTieneMasLarvas();
        }
    }

    private void verificarQueSePuedeFabricar(Fabrica unaFabrica) {
        if (listaDeFabricasDisponibles == null)
            return;

        for (Fabrica fabricaDisponible : listaDeFabricasDisponibles){
            if (unaFabrica.esIgualA(fabricaDisponible))
                return;
        }

        throw new ErrorNoSeCumplenLosRequisitosDeEstaUnidad();
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

    public void asignarListaDeUnidades(ArrayList<Fabrica> listaDeFabricasDisponibles) {
        this.listaDeFabricasDisponibles = listaDeFabricasDisponibles;
    }

    public void asignarListaDeUnidadesImperio(ArrayList<Unidad> unidades){
        this.unidades = unidades;
    }
}

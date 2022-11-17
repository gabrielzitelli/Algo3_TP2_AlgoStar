package edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosRequisitosDeEstaUnidad;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.States.EstadoAcceso;
import edu.fiuba.algo3.modelo.States.EstadoAccesoEnConstruccion;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Vida.VidaConEscudo;

import java.util.ArrayList;

public class Acceso extends Edificio {

    private int turnoParaEstarConstruido = 8;
    private int valorVital = 500;
    private EstadoAcceso estado;
    private ArrayList<Fabrica> listaDeFabricasDisponibles;
    private ArrayList<Unidad> unidades;

    public Acceso(){
        this.costoGas = 0;
        this.costoMineral = 150;
        this.estadoCarga = new ConCarga();
        this.estadoMoho = new SinMoho();
        this.estadoRecolectable = new NoRecolectable();
        this.vida = new VidaConEscudo(valorVital, valorVital);
        estado = new EstadoAccesoEnConstruccion(turnoParaEstarConstruido);
    }

    public void crearUnidad(Fabrica unaFabrica) {
        verificarQueSePuedeFabricar(unaFabrica);
        estado.crearUnidad(unaFabrica, unidades);
    }

    private void verificarQueSePuedeFabricar(Fabrica unaFabrica) {
        for (Fabrica fabricaDisponible : listaDeFabricasDisponibles){
            if (unaFabrica.esIgualA(fabricaDisponible))
                return;
        }

        throw new ErrorNoSeCumplenLosRequisitosDeEstaUnidad();
    }

    public void pasarTurno(){
        estado = estado.actualizar();
        vida.pasarTurno();
    }

    public FabricaDragon crearFabricaDragon() {
        return estado.crearFabricaDragon();
    }

    public FabricaZealot crearFabricaZealot(){
        return estado.crearFabricaZealot();
    }

    public void asignarListaDeUnidades(ArrayList<Fabrica> listaDeFabricasDisponibles) {
        this.listaDeFabricasDisponibles = listaDeFabricasDisponibles;
        this.listaDeFabricasDisponibles.add(new FabricaZealot());
        this.listaDeFabricasDisponibles.add(new FabricaDragon());
    }

    public void asignarListaDeUnidadesImperio(ArrayList<Unidad> unidades){
        this.unidades = unidades;
    }
}

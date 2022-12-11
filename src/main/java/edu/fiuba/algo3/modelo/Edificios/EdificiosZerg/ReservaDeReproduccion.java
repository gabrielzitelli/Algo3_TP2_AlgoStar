package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitador;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitadorEnConstruccion;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaZerling;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Mapa.Casilla.ConMoho;
import edu.fiuba.algo3.modelo.Mapa.Casilla.NoRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Vida.VidaRegenerativa;

import java.util.ArrayList;
import java.util.Arrays;

public class ReservaDeReproduccion extends EdificioZerg {

    private EstadoHabilitador estadoHabilitador;
    private int turnoParaEstarConstruido = 12;
    private int valorVital = 1000;

    // Fabricas que el edificio habilita
    private ArrayList<Fabrica> listaFabricasAHabilitar = new ArrayList<Fabrica>();
    private FabricasDisponibles fabricasDisponibles;

    private static ArrayList<Edificio> requisitosEdilicios = new ArrayList<Edificio>();


    public ReservaDeReproduccion(){
        this.costoMineral = 150;
        this.costoGas = 0;
        this.estadoMohoRequerido = new ConMoho();
        this.estadoRecolectable = new NoRecolectable();
        this.vida = new VidaRegenerativa(valorVital);
        this.superficieRequerida = new SuperficieTerrestre();

        estadoHabilitador = new EstadoHabilitadorEnConstruccion(turnoParaEstarConstruido);

        listaFabricasAHabilitar.add(new FabricaZerling());
        this.identificador = "reserva_reproduccion";
    }

    public ArrayList<Edificio> obtenerRequisitosEdilicios(){
        return requisitosEdilicios;
    }

    protected void destruirEdificio() {
        super.destruirEdificio();
        fabricasDisponibles.disminuir(listaFabricasAHabilitar);
    }

    public void pasarTurno(){
        estadoHabilitador = estadoHabilitador.actualizar(listaFabricasAHabilitar, fabricasDisponibles);
        vida.pasarTurno();
    }

    public void asignarListaDeUnidades(FabricasDisponibles fabricasDisponibles) {
        this.fabricasDisponibles = fabricasDisponibles;
    }

    @Override
    protected String obtenerEstado() {
        return estadoHabilitador.getEstado();
    }
}

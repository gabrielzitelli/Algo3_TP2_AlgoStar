package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitador;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitadorEnConstruccion;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaMutalisco;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Mapa.Casilla.ConMoho;
import edu.fiuba.algo3.modelo.Mapa.Casilla.NoRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Vida.VidaRegenerativa;

import java.util.ArrayList;
import java.util.Arrays;

public class Espiral extends EdificioZerg {

    private EstadoHabilitador estadoHabilitador;
    private int turnoParaEstarConstruido = 10;
    private int valorVital = 1300;

    // Fabricas que el edificio habilita
    private ArrayList<Fabrica> listaFabricasAHabilitar = new ArrayList<Fabrica>();
    private FabricasDisponibles fabricasDisponibles;
    private static ArrayList<Edificio> requisitosEdilicios = new ArrayList<Edificio>(Arrays.asList(new Guarida()));

    public Espiral() {
        this.costoGas = 100;
        this.costoMineral = 150;
        this.estadoRecolectable = new NoRecolectable();
        this.estadoMoho = new ConMoho();
        this.vida = new VidaRegenerativa(valorVital);
        this.superficieRequerida = new SuperficieTerrestre();

        estadoHabilitador = new EstadoHabilitadorEnConstruccion(turnoParaEstarConstruido);

        listaFabricasAHabilitar.add(new FabricaMutalisco());
        this.identificador = "espiral";
    }

    public static ArrayList<Edificio> requisitosEdilicios() {
        return requisitosEdilicios;
    }

    public ArrayList<Edificio> obtenerRequisitosEdilicios(){
        return requisitosEdilicios;
    }

    protected void destruirEdificio() {
        super.destruirEdificio();
        fabricasDisponibles.disminuir(listaFabricasAHabilitar);
    }

    public void pasarTurno() {
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

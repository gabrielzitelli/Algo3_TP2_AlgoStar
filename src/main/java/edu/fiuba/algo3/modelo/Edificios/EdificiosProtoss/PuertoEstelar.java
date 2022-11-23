package edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoCreador;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoCreadorEnConstruccion;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitador;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitadorEnConstruccion;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Vida.VidaConEscudo;

import java.util.ArrayList;

public class PuertoEstelar extends Edificio {

    private EstadoHabilitador estadoHabilitador;
    private EstadoCreador estadoCreador;
    private int turnoParaEstarConstruido = 10;
    private int valorVital = 600;

    // Fabricas que el edificio habilita
    private ArrayList<Fabrica> listaFabricasAHabilitar = new ArrayList<Fabrica>();
    private FabricasDisponibles fabricasDisponibles;
    private ArrayList<Unidad> unidades;

    public PuertoEstelar() {
        this.costoGas = 150;
        this.costoMineral = 150;
        this.estadoRecolectable = new NoRecolectable();
        this.estadoCarga = new ConCarga();
        this.estadoMoho = new SinMoho();
        this.vida = new VidaConEscudo(valorVital, valorVital);
        this.superficieRequerida = new SuperficieTerrestre();

        estadoHabilitador = new EstadoHabilitadorEnConstruccion(turnoParaEstarConstruido);
        estadoCreador = new EstadoCreadorEnConstruccion(turnoParaEstarConstruido);

        listaFabricasAHabilitar.add(new FabricaScout());
    }

    public static ArrayList<Edificio> requisitos() {
        ArrayList<Edificio> requisitos = new ArrayList<>();
        requisitos.add(new Acceso());
        return requisitos;
    }

    public void crearUnidad(Fabrica unaFabrica) {
        estadoHabilitador.estaAptoParaCrearse(unaFabrica);
        estadoCreador.crearUnidad(unaFabrica, unidades, mineralDelImperio, gasDelImperio);
    }

    public void pasarTurno() {
        estadoHabilitador = estadoHabilitador.actualizar(listaFabricasAHabilitar, fabricasDisponibles);
        estadoCreador = estadoCreador.actualizar();
        vida.pasarTurno();
    }

    public void asignarListaDeUnidades(FabricasDisponibles fabricasDisponibles) {
        this.fabricasDisponibles = fabricasDisponibles;
        estadoCreador.asignarFabricasDisponibles(fabricasDisponibles);
    }

    public void asignarListaDeUnidadesImperio(ArrayList<Unidad> unidades){
        this.unidades = unidades;
    }
}

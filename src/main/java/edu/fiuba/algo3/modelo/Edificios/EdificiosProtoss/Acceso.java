package edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoCreador;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoCreadorEnConstruccion;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitador;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitadorEnConstruccion;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidades;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidadesDragon;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidadesZealot;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Edificios.Vida.VidaConEscudo;
import edu.fiuba.algo3.modelo.Excepciones.ErrorElEdificioNoTieneCarga;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

public class Acceso extends EdificioProtoss {

    private EstadoHabilitador estadoHabilitador;
    private EstadoCreador estadoCreador;
    private final int turnoParaEstarConstruido = 8;

    // Fabricas que el edificio habilita
    private final ArrayList<FabricasUnidades> listaFabricasAHabilitar = new ArrayList<>();
    private FabricasDisponibles fabricasDisponibles;
    private ArrayList<Unidad> unidades;

    private static final ArrayList<Edificio> requisitosEdilicios = new ArrayList<>();



    public Acceso(){
        this.costoGas = 0;
        this.costoMineral = 150;
        this.estadoCargaRequerido = new ConCarga();
        this.estadoMohoRequerido = new SinMoho();
        this.estadoRecolectable = new NoRecolectable();
        int valorVital = 500;
        this.vida = new VidaConEscudo(valorVital, valorVital);
        this.superficieRequerida = new SuperficieTerrestre();

        estadoHabilitador = new EstadoHabilitadorEnConstruccion(turnoParaEstarConstruido);
        estadoCreador = new EstadoCreadorEnConstruccion(turnoParaEstarConstruido, this.coordenada);

        listaFabricasAHabilitar.add(new FabricasUnidadesDragon());
        listaFabricasAHabilitar.add(new FabricasUnidadesZealot());

        this.identificador = "acceso";
    }

    public ArrayList<Edificio> obtenerRequisitosEdilicios(){
        return requisitosEdilicios;
    }

    public void verificarCarga() throws ErrorElEdificioNoTieneCarga {
        Mapa elMapa = Mapa.obtener();
        if (coordenada == null){
            return;
        }
        if (elMapa.estaEnergizado(coordenada)){
            throw new ErrorElEdificioNoTieneCarga();
        }
    }

    public void crearUnidad(FabricasUnidades unaFabricasUnidades) {
        this.verificarCarga();
        estadoHabilitador.estaAptoParaCrearse(unaFabricasUnidades);
        estadoCreador.crearUnidad(unaFabricasUnidades, unidades, mineralDelImperio, gasDelImperio);
    }

    public void pasarTurno(){
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

    public void construirInmediatamente(){
        for (int i = 0; i < turnoParaEstarConstruido; i++)
            pasarTurno();
    }

    @Override
    public void modificarPoblacion(Suministro suministro){
        estadoHabilitador.marcarSuministro(suministro, suministroAportado);
    }

    public void asignarSuministro(Suministro poblacionDelImperio){
        estadoHabilitador.marcarSuministro(poblacionDelImperio, 0);
    }

    @Override
    public void verificarColocable(Casilla unaCasilla){
        super.verificarColocable(unaCasilla);
        estadoCreador.colocarCoordenadaAlGestorDeCrianza(unaCasilla.obtenerCoordenada());
    }

    @Override
    protected String obtenerEstado() {
        return estadoHabilitador.getEstado();
    }

    public void estaAptaUnidadParaConstruir(FabricasUnidades unaFabricasUnidades){
        estadoHabilitador.estaAptoParaCrearseVerificacion(unaFabricasUnidades);
        estadoCreador.comprobarRequisitosMaterialesVerificacion(unaFabricasUnidades.crearUnidad(), mineralDelImperio, gasDelImperio);
        estadoCreador.verificarQueSePuedeFabricar(unaFabricasUnidades);
    }
}

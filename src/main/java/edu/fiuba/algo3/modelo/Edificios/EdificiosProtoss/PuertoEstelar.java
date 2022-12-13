package edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoCreador;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoCreadorEnConstruccion;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitador;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitadorEnConstruccion;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaScout;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Edificios.Vida.VidaConEscudo;
import edu.fiuba.algo3.modelo.Excepciones.ErrorElEdificioNoTieneCarga;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuertoEstelar extends EdificioProtoss {

    private EstadoHabilitador estadoHabilitador;
    private EstadoCreador estadoCreador;

    // Fabricas que el edificio habilita
    private final ArrayList<Fabrica> listaFabricasAHabilitar = new ArrayList<>();
    private FabricasDisponibles fabricasDisponibles;
    private ArrayList<Unidad> unidades;

    private static final ArrayList<Edificio> requisitosEdilicios = new ArrayList<>(List.of(new Acceso()));

    public PuertoEstelar() {
        this.costoGas = 150;
        this.costoMineral = 150;
        this.estadoRecolectable = new NoRecolectable();
        this.estadoCargaRequerido = new ConCarga();
        this.estadoMohoRequerido = new SinMoho();
        int valorVital = 600;
        this.vida = new VidaConEscudo(valorVital, valorVital);
        this.superficieRequerida = new SuperficieTerrestre();

        int turnoParaEstarConstruido = 10;
        estadoHabilitador = new EstadoHabilitadorEnConstruccion(turnoParaEstarConstruido);
        estadoCreador = new EstadoCreadorEnConstruccion(turnoParaEstarConstruido, this.coordenada);

        listaFabricasAHabilitar.add(new FabricaScout());
        this.identificador = "puerto_estelar";
    }

    public static ArrayList<Edificio> requisitosEdilicios() {
        return requisitosEdilicios;
    }

    public ArrayList<Edificio> obtenerRequisitosEdilicios(){
        return requisitosEdilicios;
    }

    public void verificarCarga() {
        Mapa elMapa = Mapa.obtener();
        if (coordenada == null){
            return;
        }
        if (elMapa.estaEnergizado(coordenada)){
            throw new ErrorElEdificioNoTieneCarga();
        }
    }

    public void crearUnidad(Fabrica unaFabrica) {
        this.verificarCarga();
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

    public void estaAptaUnidadParaConstruir(Fabrica unaFabrica){
        estadoHabilitador.estaAptoParaCrearseVerificacion(unaFabrica);
        estadoCreador.comprobarRequisitosMaterialesVerificacion(unaFabrica.crearUnidad(), mineralDelImperio, gasDelImperio);
        estadoCreador.verificarQueSePuedeFabricar(unaFabrica);
    }
}

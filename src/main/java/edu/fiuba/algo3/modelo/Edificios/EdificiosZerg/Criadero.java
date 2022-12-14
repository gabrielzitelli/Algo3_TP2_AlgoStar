package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.*;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidades;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidadesAmoSupremo;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidadesZangano;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Edificios.Vida.VidaRegenerativa;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCriaderoNoTieneMasLarvas;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Casilla.NoRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SinCarga;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;

import java.util.ArrayList;

public class Criadero extends EdificioZerg {

    private EstadoHabilitador estadoHabilitador;
    private EstadoCreador estadoCreador;
    private EstadoGeneradorDeMoho estadoGeneradorDeMoho;
    private final int turnoParaEstarConstruido = 4;
    private final int maxLarvas = 3;
    private int cantidadLarvas;

    // Fabricas que el edificio habilita
    private final ArrayList<FabricasUnidades> listaFabricasAHabilitar = new ArrayList<>();
    private FabricasDisponibles fabricasDisponibles;
    private ArrayList<Unidad> unidades;
    private static final ArrayList<Edificio> requisitosEdilicios = new ArrayList<>();

    public Criadero(){
        this.costoGas = 0;
        this.costoMineral = 200;
        this.estadoRecolectable = new NoRecolectable();
        this.estadoCargaRequerido = new SinCarga();
        int valorVital = 500;
        this.vida = new VidaRegenerativa(valorVital);
        this.superficieRequerida = new SuperficieTerrestre();
        cantidadLarvas = maxLarvas;

        this.suministroAportado = 5;

        estadoHabilitador = new EstadoHabilitadorEnConstruccion(turnoParaEstarConstruido);

        estadoCreador = new EstadoCreadorEnConstruccion(turnoParaEstarConstruido, this.coordenada);
        estadoGeneradorDeMoho = new EstadoGeneradorDeMohoEnConstruccion(turnoParaEstarConstruido);

        listaFabricasAHabilitar.add(new FabricasUnidadesZangano());
        listaFabricasAHabilitar.add(new FabricasUnidadesAmoSupremo());
        this.identificador = "criadero";
    }

    public ArrayList<Edificio> obtenerRequisitosEdilicios(){
        return requisitosEdilicios;
    }

    public void crearUnidad(FabricasUnidades unaFabricasUnidades) {

        if (cantidadLarvas > 0) {
            estadoHabilitador.estaAptoParaCrearse(unaFabricasUnidades);
            estadoCreador.crearUnidad(unaFabricasUnidades, unidades, mineralDelImperio, gasDelImperio);
            cantidadLarvas--;
        }else
            throw new ErrorCriaderoNoTieneMasLarvas();
    }

    private void regenerarUnaLarva(){
        if (cantidadLarvas < maxLarvas)
            cantidadLarvas++;
    }

    protected void destruirEdificio() {
        super.destruirEdificio();

        Mapa elMapa = Mapa.obtener();
        estadoHabilitador.disminuirSuministro(suministroAportado);
        elMapa.expandirMoho(coordenada, 0);
    }

    public void pasarTurno(){
        estadoHabilitador = estadoHabilitador.actualizar(listaFabricasAHabilitar, fabricasDisponibles);
        estadoCreador = estadoCreador.actualizar();
        estadoGeneradorDeMoho = estadoGeneradorDeMoho.actualizar(coordenada);
        this.regenerarUnaLarva();
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

    public void construirInmediatamente(){
        for (int i = 0; i < turnoParaEstarConstruido; i++)
            pasarTurno();
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
        if(cantidadLarvas <= 0)
            throw new ErrorCriaderoNoTieneMasLarvas();

        estadoCreador.verificarQueSePuedeFabricar(unaFabricasUnidades);
        estadoCreador.comprobarRequisitosMaterialesVerificacion(unaFabricasUnidades.crearUnidad(), mineralDelImperio, gasDelImperio);
        estadoHabilitador.estaAptoParaCrearseVerificacion(unaFabricasUnidades);
    }
}

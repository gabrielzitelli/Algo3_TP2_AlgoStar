package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Edificios.Estados.*;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.Vida.VidaRegenerativa;

import java.util.ArrayList;

public class Criadero extends EdificioZerg {

    private EstadoHabilitador estadoHabilitador;
    private EstadoCreador estadoCreador;
    private EstadoGeneradorDeMoho estadoGeneradorDeMoho;
    private int turnoParaEstarConstruido = 4;
    private int maxLarvas = 3;
    private int cantidadLarvas;
    private int valorVital = 500;

    // Fabricas que el edificio habilita
    private ArrayList<Fabrica> listaFabricasAHabilitar = new ArrayList<Fabrica>();
    private FabricasDisponibles fabricasDisponibles;
    private ArrayList<Unidad> unidades;

    public Criadero(){
        this.costoGas = 0;
        this.costoMineral = 200;
        this.estadoRecolectable = new NoRecolectable();
        this.estadoCarga = new SinCarga();
        this.vida = new VidaRegenerativa(valorVital);
        this.superficieRequerida = new SuperficieTerrestre();
        cantidadLarvas = maxLarvas;

        this.suministroAportado = 5;

        estadoHabilitador = new EstadoHabilitadorEnConstruccion(turnoParaEstarConstruido);

        estadoCreador = new EstadoCreadorEnConstruccion(turnoParaEstarConstruido, this.coordenada);
        estadoGeneradorDeMoho = new EstadoGeneradorDeMohoEnConstruccion(turnoParaEstarConstruido);

        listaFabricasAHabilitar.add(new FabricaZangano());
        listaFabricasAHabilitar.add(new FabricaAmoSupremo());
    }

    public void crearUnidad(Fabrica unaFabrica) {

        if (cantidadLarvas > 0) {
            estadoHabilitador.estaAptoParaCrearse(unaFabrica);
            estadoCreador.crearUnidad(unaFabrica, unidades, mineralDelImperio, gasDelImperio);
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
    public void verificarConstruccion(Casilla unaCasilla){
        super.verificarConstruccion(unaCasilla);
        estadoCreador.colocarCoordenadaAlGestorDeCrianza(unaCasilla.obtenerCoordenada());
    }

    @Override
    public Object obtenerEstado() {
        return estadoHabilitador.getEstado();
    }
}

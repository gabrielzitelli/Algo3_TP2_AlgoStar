package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.*;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.Vida.VidaRegenerativa;

import java.util.ArrayList;

public class Criadero extends Edificio {

    //SUPUESTO: CRIADERO NO SE PUEDE PONER EN TERRENO CARGADO

    private EstadoHabilitador estadoHabilitador;
    private EstadoCreador estadoCreador;
    private EstadoGeneradorDeMoho estadoGeneradorDeMoho;
    private int turnoParaEstarConstruido = 4;
    private int maxLarvas = 3;
    private int cantidadLarvas;
    private int valorVital = 500;

    // Fabricas que el edificio habilita
    private ArrayList<Fabrica> listaFabricasAHabilitar = new ArrayList<Fabrica>();
    private ArrayList<Fabrica> listaDeFabricasDisponibles;
    private ArrayList<Unidad> unidades;

    public Criadero(){
        this.costoGas = 0;
        this.costoMineral = 50;
        this.estadoRecolectable = new NoRecolectable();
        this.estadoCarga = new SinCarga();
        this.vida = new VidaRegenerativa(valorVital);
        cantidadLarvas = maxLarvas;

        estadoHabilitador = new EstadoHabilitadorEnConstruccion(turnoParaEstarConstruido);
        estadoCreador = new EstadoCreadorEnConstruccion(turnoParaEstarConstruido);
        estadoGeneradorDeMoho = new EstadoGeneradorDeMohoEnConstruccion(turnoParaEstarConstruido);

        listaFabricasAHabilitar.add(new FabricaZangano());
    }

    public void crearUnidad(Fabrica unaFabrica) {
        verificarQueSePuedeFabricar(unaFabrica);
        if (cantidadLarvas > 0) {
            cantidadLarvas--;
            estadoCreador.crearUnidad(unaFabrica, unidades);
        }else {
            throw new ErrorCriaderoNoTieneMasLarvas();
        }
    }

    private void verificarQueSePuedeFabricar(Fabrica unaFabrica) {
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
        estadoHabilitador = estadoHabilitador.actualizar(listaFabricasAHabilitar, listaDeFabricasDisponibles);
        estadoCreador = estadoCreador.actualizar();
        estadoGeneradorDeMoho = estadoGeneradorDeMoho.actualizar(coordenada);
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

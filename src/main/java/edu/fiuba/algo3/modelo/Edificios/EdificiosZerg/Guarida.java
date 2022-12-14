package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitador;
import edu.fiuba.algo3.modelo.Edificios.Estados.EstadoHabilitadorEnConstruccion;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidades;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasUnidadesHidralisco;
import edu.fiuba.algo3.modelo.Edificios.FabricasUnidades.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Edificios.Vida.VidaRegenerativa;
import edu.fiuba.algo3.modelo.Mapa.Casilla.ConMoho;
import edu.fiuba.algo3.modelo.Mapa.Casilla.NoRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;

import java.util.ArrayList;
import java.util.List;

public class Guarida extends EdificioZerg {

    private EstadoHabilitador estadoHabilitador;

    // Fabricas que el edificio habilita
    private final ArrayList<FabricasUnidades> listaFabricasAHabilitar = new ArrayList<>();
    private FabricasDisponibles fabricasDisponibles;
    private static final ArrayList<Edificio> requisitosEdilicios = new ArrayList<>(List.of(new ReservaDeReproduccion()));

    public Guarida(){
        this.costoMineral = 200;
        this.costoGas = 100;
        this.estadoMohoRequerido = new ConMoho();
        this.estadoRecolectable = new NoRecolectable();
        int valorVital = 1250;
        this.vida = new VidaRegenerativa(valorVital);
        this.superficieRequerida = new SuperficieTerrestre();

        int turnoParaEstarConstruido = 12;
        estadoHabilitador = new EstadoHabilitadorEnConstruccion(turnoParaEstarConstruido);

        listaFabricasAHabilitar.add(new FabricasUnidadesHidralisco());
        this.identificador = "guarida";
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

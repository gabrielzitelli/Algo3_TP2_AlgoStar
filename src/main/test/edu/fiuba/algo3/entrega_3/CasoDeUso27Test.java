package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaMutalisco;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaCriadero;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaEspiral;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaGuarida;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Devorador;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CasoDeUso27Test {
    final Mapa mapa = Mapa.obtener();
    Zerg imperioZerg;
    Criadero criadero;

    @BeforeEach
    public void setup(){
        mapa.reiniciarMapa();

        imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos();

        Coordenada coordenadaCriadero = new Coordenada(0, 0);
        Coordenada coordenadaReservaReproduccion = new Coordenada(1, 0);
        Coordenada coordenadaGuarida = new Coordenada(0, 1);
        Coordenada coordenadaEspiral = new Coordenada(1, 1);

        mapa.colocarOcupable(new Zangano(), coordenadaCriadero);
        imperioZerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        for (int i = 0; i < 4; i++)
            imperioZerg.terminarTurno();

        mapa.colocarOcupable(new Zangano(), coordenadaReservaReproduccion);
        imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), coordenadaReservaReproduccion);

        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        mapa.colocarOcupable(new Zangano(), coordenadaGuarida);
        imperioZerg.construirEdificio(new FabricaGuarida(), coordenadaGuarida);

        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        mapa.colocarOcupable(new Zangano(), coordenadaEspiral);
        imperioZerg.construirEdificio(new FabricaEspiral(), coordenadaEspiral);

        for (int i = 0; i < 10; i++)
            imperioZerg.terminarTurno();

        criadero = (Criadero) imperioZerg.conseguirEdificio(coordenadaCriadero);
    }

    @Test
    public void test01NoPuedoEvolucionarAUnMutaliscoADevoradadorSinTenerRecursos() {
        criadero.crearUnidad(new FabricaMutalisco());

        for (int i = 0; i < 7; i++)
            imperioZerg.terminarTurno();

        Unidad mutalisco = (Unidad) mapa.obtenerOcupable(new Coordenada(0, 2));

        //Nos aseguramos que no tengamos más recursos
        imperioZerg.abastecerDeRecursos(new Mineral(0), new Gas(0));

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class, () ->
                imperioZerg.evolucionarMutaliscoADevorador(mutalisco));
    }

    @Test
    public void test02PuedoEvolucionarAUnMutaliscoADevoradorTeniendoRecursos() {
        criadero.crearUnidad(new FabricaMutalisco());

        for (int i = 0; i < 7; i++)
            imperioZerg.terminarTurno();

        Unidad mutalisco = (Unidad) mapa.obtenerOcupable(new Coordenada(0, 2));

        imperioZerg.evolucionarMutaliscoADevorador(mutalisco);
        //movemos al mutalisco
        mutalisco.moverA(new Coordenada(0,7));

        //Tenemos el mutalisco todavía porque no han pasado los turnos
        assertTrue(imperioZerg.tieneUnidad(mutalisco));
        assertFalse(imperioZerg.tieneUnidad(new Devorador()));

        for (int i = 0; i < 3; i++)
            imperioZerg.terminarTurno();

        //Pasan 3 turnos y aun no tengo el guardian
        assertTrue(imperioZerg.tieneUnidad(mutalisco));
        assertFalse(imperioZerg.tieneUnidad(new Devorador()));

        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();

        //Ya no tenemos al mutalisco y tenemos al guardian
        assertTrue(imperioZerg.tieneUnidad(new Devorador()));
        assertFalse(imperioZerg.tieneUnidad(mutalisco));

        //el guardian está en la posición en la que estaba el mutalisco
        Unidad guardian = (Unidad) mapa.obtenerOcupable(new Coordenada(0,7));
        assertTrue(guardian.esIgualA(new Devorador()));
    }
}

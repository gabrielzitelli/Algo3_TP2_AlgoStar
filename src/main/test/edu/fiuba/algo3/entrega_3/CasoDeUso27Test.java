package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.FabricaMutalisco;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoHayMutaliscoParaEvolucionar;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Devorador;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Guardian;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso27Test {
    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01NoPuedoEvolucionarAUnMutaliscoADevoradadorSinTenerAntesUnMutalisco(){
        Zerg imperioZerg = new Zerg();

        assertThrows(ErrorNoHayMutaliscoParaEvolucionar.class,
                () -> imperioZerg.evolucionarMutaliscoADevorador());
    }
    @Test
    public void test02PuedoEvolucionarAUnMutaliscoADevoradorSiTengoLosRecursosSuficientes() {
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(5000), new Gas(1000));
        imperioZerg.construirCriadero(new Coordenada(0,0));

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirReservaDeReproduccion(new Coordenada(1,0));

        //esperamos a que se construya la reserva
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirGuarida(new Coordenada(0,1));

        //Esperamos a que se construya la guarida
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirEspiral(new Coordenada(1,1));

        //Espero a que se construya el espiral
        for (int i = 0; i < 10; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));

        assertDoesNotThrow(() -> criadero.crearUnidad(new FabricaMutalisco()));

        //Pasan los turnos y tenemos al mutalisco
        for (int i = 0; i < 7; i++)
            imperioZerg.terminarTurno();
        //Lo evolucionamos
        imperioZerg.evolucionarMutaliscoADevorador();
        //No han pasado turnos, aún no lo tenemos
        assertFalse(imperioZerg.tieneUnidad(new Devorador()));

        for (int i = 0; i < 4; i++){
            imperioZerg.terminarTurno();
        }
        //tenemos el guardian
        assertTrue(imperioZerg.tieneUnidad(new Devorador()));
    }
    @Test
    public void test03NoPuedoEvolucionarAUnMutaliscoNoTengoLosRecursosSuficientes() {
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(800), new Gas(300));
        imperioZerg.construirCriadero(new Coordenada(0,0));

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirReservaDeReproduccion(new Coordenada(1,0));

        //esperamos a que se construya la reserva
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirGuarida(new Coordenada(0,1));

        //Esperamos a que se construya la guarida
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirEspiral(new Coordenada(1,1));

        //Espero a que se construya el espiral
        for (int i = 0; i < 10; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));

        assertDoesNotThrow(() -> criadero.crearUnidad(new FabricaMutalisco()));

        //Pasan los turnos y tenemos al mutalisco
        for (int i = 0; i < 7; i++)
            imperioZerg.terminarTurno();
        //No podemos evolucionar porque no tenemos más recursos
        assertThrows(ErrorCantidadDeRecursoInsuficiente.class, () ->
                imperioZerg.evolucionarMutaliscoADevorador());
    }
}

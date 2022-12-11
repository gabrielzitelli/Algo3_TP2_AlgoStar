package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaMutalisco;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaCriadero;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaEspiral;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaGuarida;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoHayMutaliscoParaEvolucionar;
import edu.fiuba.algo3.modelo.Imperio.*;
import edu.fiuba.algo3.modelo.Mapa.*;

import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Guardian;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CasoDeUso21Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01NoPuedoEvolucionarAUnMutaliscoSinTenerAntesUnMutalisco(){
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(50), new Gas(50));

        assertThrows(ErrorNoHayMutaliscoParaEvolucionar.class,
                () -> imperioZerg.evolucionarMutaliscoAGuardian());
    }
    @Test
    public void test02PuedoEvolucionarAUnMutaliscoSiTengoLosRecursosSuficientes() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(5000), new Gas(1000));

        Coordenada coordenadaCriadero = new Coordenada(2, 2);
        elMapa.colocarOcupable(new Zangano(), coordenadaCriadero);
        imperioZerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        Coordenada coordenadaReserva = new Coordenada(1,0);
        elMapa.colocarOcupable(new Zangano(), coordenadaReserva);
        imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), coordenadaReserva);

        //esperamos a que se construya la reserva
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        Coordenada coordenadaGuarida = new Coordenada(0,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaGuarida);
        imperioZerg.construirEdificio(new FabricaGuarida(), coordenadaGuarida);

        //Esperamos a que se construya la guarida
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        Coordenada coordenadaEspiral = new Coordenada(1,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaEspiral);
        imperioZerg.construirEdificio(new FabricaEspiral(), coordenadaEspiral);

        //Espero a que se construya el espiral
        for (int i = 0; i < 10; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(new Coordenada(2,2));

        assertDoesNotThrow(() -> criadero.crearUnidad(new FabricaMutalisco()));

        //Pasan los turnos y tenemos al mutalisco
        for (int i = 0; i < 7; i++)
            imperioZerg.terminarTurno();

        //Lo evolucionamos
        imperioZerg.evolucionarMutaliscoAGuardian();

        //No han pasado turnos, aún no lo tenemos
        assertFalse(imperioZerg.tieneUnidad(new Guardian()));


        for (int i = 0; i < 4; i++){
            imperioZerg.terminarTurno();
        }

        //tenemos el guardian
        assertTrue(imperioZerg.tieneUnidad(new Guardian()));
    }
    @Test
    public void test03NoPuedoEvolucionarAUnMutaliscoNoTengoLosRecursosSuficientes() {
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(800), new Gas(300));

        Coordenada coordenadaCriadero = new Coordenada(0, 0);
        elMapa.colocarOcupable(new Zangano(), coordenadaCriadero);
        imperioZerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        Coordenada coordenadaReserva = new Coordenada(1,0);
        elMapa.colocarOcupable(new Zangano(), coordenadaReserva);
        imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), coordenadaReserva);

        //esperamos a que se construya la reserva
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        Coordenada coordenadaGuarida = new Coordenada(0,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaGuarida);
        imperioZerg.construirEdificio(new FabricaGuarida(), coordenadaGuarida);

        //Esperamos a que se construya la guarida
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        Coordenada coordenadaEspiral = new Coordenada(1,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaEspiral);
        imperioZerg.construirEdificio(new FabricaEspiral(), coordenadaEspiral);

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
                imperioZerg.evolucionarMutaliscoAGuardian());
    }
}

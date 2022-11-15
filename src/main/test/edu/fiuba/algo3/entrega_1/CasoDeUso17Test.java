package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosPreRequisitosDelEdificio;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso17Test {
    @Test
    public void test01NoPuedoConstruirUnaGuaridaSinTenerAntesUnaReservaDeReproduccion() {
        Zerg zerg = new Zerg();
        zerg.abastecerDeRecursos(new Recurso(250), new Recurso(100));
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        zerg.construirCriadero(new Coordenada(1,1));

        //Esperamos Cuatro Turnos Para La Construccion Del Criadero
        for (int i = 0; i < 4; i++){
            zerg.terminarTurno();
        }
        //Esperamos un turno para la expansión del criadero
        zerg.terminarTurno();

        //IntentamosConstruirGuarida
        assertThrows(ErrorNoSeCumplenLosPreRequisitosDelEdificio.class, () ->
                zerg.construirGuarida(new Coordenada(2,2)));

    }
    @Test
    public void test02PuedoConstruirUnaGuaridaTeniendoAntesUnaReservaDeReproduccion() {
        Zerg zerg = new Zerg();
        zerg.abastecerDeRecursos(new Recurso(400), new Recurso(100));
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        zerg.construirCriadero(new Coordenada(1,1));

        //Esperamos Cuatro Turnos Para La Construccion Del Criadero
        for (int i = 0; i < 4; i++){
            zerg.terminarTurno();
        }
        //Esperamos un turno para la expansión del criadero
        zerg.terminarTurno();
        //Construimos una reserva
        zerg.construirReservaDeReproduccion(new Coordenada(1,2));

        //IntentamosConstruirGuarida
        assertDoesNotThrow( () -> zerg.construirGuarida(new Coordenada(2,2)));
    }

    @Test
    public void test03NoPuedoConstruirUnEspiralSinTenerAntesUnaGuarida() {
        Zerg zerg = new Zerg();
        zerg.abastecerDeRecursos(new Recurso(200), new Recurso(100));
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        zerg.construirCriadero(new Coordenada(1,1));

        //Esperamos Cuatro Turnos Para La Construccion Del Criadero
        for (int i = 0; i < 4; i++){
            zerg.terminarTurno();
        }
        //Esperamos un turno para la expansión del criadero
        zerg.terminarTurno();

        //IntentamosConstruirGuarida
        assertThrows(ErrorNoSeCumplenLosPreRequisitosDelEdificio.class, () ->
                zerg.construirEspiral(new Coordenada(2,2)));

    }
    @Test
    public void test04PuedoConstruirUnEspiralTeniendoAntesUnaGuarida() {
        Zerg zerg = new Zerg();
        zerg.abastecerDeRecursos(new Recurso(550), new Recurso(200));
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        zerg.construirCriadero(new Coordenada(1,1));

        //Esperamos Cuatro Turnos Para La Construccion Del Criadero
        for (int i = 0; i < 4; i++){
            zerg.terminarTurno();
        }
        //Esperamos un turno para la expansión del criadero
        zerg.terminarTurno();
        //Construimos una reserva y una guarida
        zerg.construirReservaDeReproduccion(new Coordenada(1,2));
        zerg.construirGuarida(new Coordenada(2,1));

        //IntentamosConstruirGuarida
        assertDoesNotThrow( () -> zerg.construirEspiral(new Coordenada(2,2)));
    }
    @Test
    public void test05NoPuedoConstruirUnPuertoEstelarSinTenerAntesUnAcceso() {
        Protoss protoss = new Protoss();
        protoss.abastecerDeRecursos(new Recurso(250), new Recurso(150));
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        protoss.construirPilon(new Coordenada(0,0));
        //Esperamos Cuatro Turnos Para La Construccion Del pilon
        for (int i = 0; i < 5; i++){
            protoss.terminarTurno();
        }

        //IntentamosConstruirPuertoEstelar
        assertThrows(ErrorNoSeCumplenLosPreRequisitosDelEdificio.class, () ->
                protoss.construirPuertoEstelar(new Coordenada(1,1)));

    }
    @Test
    public void test06PuedoConstruirUnPuertoEstelarTeniendoAntesUnAcceso() {
        Protoss protoss = new Protoss();
        protoss.abastecerDeRecursos(new Recurso(400), new Recurso(150));
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        protoss.construirPilon(new Coordenada(0,0));
        //Esperamos Cuatro Turnos Para La Construccion Del pilon
        for (int i = 0; i < 5; i++){
            protoss.terminarTurno();
        }
        protoss.construirAcceso(new Coordenada(1,0));

        //IntentamosConstruirPuertoEstelar
        assertDoesNotThrow( () -> protoss.construirPuertoEstelar(new Coordenada( 1, 1)));

    }
}

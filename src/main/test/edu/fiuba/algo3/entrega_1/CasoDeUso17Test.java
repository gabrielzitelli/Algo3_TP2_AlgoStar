package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosPreRequisitosDelEdificio;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Zangano;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso17Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01NoPuedoConstruirUnaGuaridaSinTenerAntesUnaReservaDeReproduccion() {
        Zerg zerg = new Zerg();
        zerg.abastecerDeRecursos(new Mineral(250), new Gas(100));

        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(1,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaCriadero);
        zerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        //Esperamos Cuatro Turnos Para La Construccion Del Criadero
        for (int i = 0; i < 4; i++)
            zerg.terminarTurno();

        //Esperamos un turno para la expansión del criadero
        zerg.terminarTurno();

        Coordenada coordenadaGuarida = new Coordenada(2,2);
        elMapa.colocarOcupable(new Zangano(), coordenadaGuarida);

        //IntentamosConstruirGuarida
        assertThrows(ErrorNoSeCumplenLosPreRequisitosDelEdificio.class, () ->
                zerg.construirEdificio(new FabricaGuarida(),new Coordenada(2,2)));
    }

    @Test
    public void test02PuedoConstruirUnaGuaridaTeniendoAntesUnaReservaDeReproduccion() {
        Zerg zerg = new Zerg();
        zerg.abastecerDeRecursos(new Mineral(550), new Gas(100));

        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(1,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaCriadero);
        zerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        //Esperamos Cuatro Turnos Para La Construccion Del Criadero
        for (int i = 0; i < 4; i++)
            zerg.terminarTurno();

        //Esperamos un turno para la expansión del criadero
        zerg.terminarTurno();

        //Construimos una reserva
        Coordenada coordenadaReserva = new Coordenada(1,2);
        elMapa.colocarOcupable(new Zangano(), coordenadaReserva);
        zerg.construirEdificio(new FabricaReservaDeReproduccion(), coordenadaReserva);

        Coordenada coordenadaGuarida = new Coordenada(2,2);
        elMapa.colocarOcupable(new Zangano(), coordenadaGuarida);
        //IntentamosConstruirGuarida
        assertDoesNotThrow( () -> zerg.construirEdificio(new FabricaGuarida(), coordenadaGuarida));
    }

    @Test
    public void test03NoPuedoConstruirUnEspiralSinTenerAntesUnaGuarida() {
        Zerg zerg = new Zerg();
        zerg.abastecerDeRecursos(new Mineral(200), new Gas(100));

        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(1,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaCriadero);
        zerg.construirEdificio(new FabricaCriadero(), coordenadaCriadero);

        //Esperamos Cuatro Turnos Para La Construccion Del Criadero
        for (int i = 0; i < 4; i++)
            zerg.terminarTurno();

        //Esperamos un turno para la expansión del criadero
        zerg.terminarTurno();

        Coordenada coordenadaEspiral = new Coordenada(2,2);
        elMapa.colocarOcupable(new Zangano(), coordenadaEspiral);

        //IntentamosConstruirGuarida
        assertThrows(ErrorNoSeCumplenLosPreRequisitosDelEdificio.class, () ->
                zerg.construirEdificio(new FabricaEspiral(),new Coordenada(2,2)));
    }

    @Test
    public void test04PuedoConstruirUnEspiralTeniendoAntesUnaGuarida() {
        Zerg zerg = new Zerg();
        zerg.abastecerDeRecursos(new Mineral(700), new Gas(200));

        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaCriadero = new Coordenada(1,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaCriadero);
        zerg.construirEdificio(new FabricaCriadero(), new Coordenada(1,1));

        //Esperamos Cuatro Turnos Para La Construccion Del Criadero
        for (int i = 0; i < 4; i++)
            zerg.terminarTurno();

        //Esperamos un turno para la expansión del criadero
        zerg.terminarTurno();

        //Construimos una reserva y una guarida
        Coordenada coordenadaReserva = new Coordenada(1,2);
        elMapa.colocarOcupable(new Zangano(), coordenadaReserva);
        zerg.construirEdificio(new FabricaReservaDeReproduccion(), coordenadaReserva);

        Coordenada coordenadaGuarida = new Coordenada(2,1);
        elMapa.colocarOcupable(new Zangano(), coordenadaGuarida);
        zerg.construirEdificio(new FabricaGuarida(),coordenadaGuarida);

        //IntentamosConstruirEspiral
        Coordenada coordenadaEspiral = new Coordenada(2,2);
        elMapa.colocarOcupable(new Zangano(), coordenadaEspiral);
        assertDoesNotThrow(
                () -> zerg.construirEdificio(new FabricaEspiral(),coordenadaEspiral));
    }

    @Test
    public void test05NoPuedoConstruirUnPuertoEstelarSinTenerAntesUnAcceso() {
        Protoss protoss = new Protoss();

        protoss.abastecerDeRecursos(new Mineral(250), new Gas(150));
        protoss.construirEdificio(new FabricaPilon(), new Coordenada(0,0));

        //Esperamos Cuatro Turnos Para La Construccion Del pilon
        for (int i = 0; i < 5; i++)
            protoss.terminarTurno();

        //IntentamosConstruirPuertoEstelar
        assertThrows(ErrorNoSeCumplenLosPreRequisitosDelEdificio.class, () ->
                protoss.construirEdificio(new FabricaPuertoEstelar(),new Coordenada(1,1)));
    }

    @Test
    public void test06PuedoConstruirUnPuertoEstelarTeniendoAntesUnAcceso() {
        Protoss protoss = new Protoss();

        protoss.abastecerDeRecursos(new Mineral(400), new Gas(150));
        protoss.construirEdificio(new FabricaPilon(), new Coordenada(0,0));

        //Esperamos Cuatro Turnos Para La Construccion Del pilon
        for (int i = 0; i < 5; i++)
            protoss.terminarTurno();

        protoss.construirEdificio(new FabricaAcceso(), new Coordenada(1,0));

        //IntentamosConstruirPuertoEstelar
        assertDoesNotThrow( () -> protoss.construirEdificio(new FabricaPuertoEstelar(), new Coordenada( 1, 1)));
    }
}

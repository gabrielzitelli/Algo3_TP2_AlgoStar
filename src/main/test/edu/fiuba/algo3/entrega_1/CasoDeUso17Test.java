package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.Moho;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.excepciones.NoSeCumplenLosPreRequisitosDelEdificio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso17Test {

    @Test
    public void test01IntentoConstruirUnaGuaridaSinTenerAntesUnaReservaDeReproduccionYLanzaExcepcion() {
        Tablero tablero = new Tablero(20,20);
        Recurso minerales = new Recurso(200);
        Recurso gasVespeno = new Recurso(100);
        Zergs zergs = new Zergs(tablero, minerales, gasVespeno);
        Coordenadas coordenadasZangano = new Coordenadas(5,5);
        Zangano zangano = new Zangano(tablero,coordenadasZangano, minerales);

        assertThrows(NoSeCumplenLosPreRequisitosDelEdificio.class, () -> zergs.construirGuarida(zangano));
    }
    @Test
    public void test02IntentoConstruirUnaGuaridaTeniendoAntesUnaReservaDeReproduccionYNoLanzaExcepcion() {
        Tablero tablero = new Tablero(20,20);
        //Establezco los minerales iniciales
        Recurso minerales = new Recurso(1000);
        Recurso gasVespeno = new Recurso(1000);

        //Creo al imperio y genero las coordenadas donde van a estar los edificios
        Zergs zergs = new Zergs(tablero, minerales, gasVespeno);
        Coordenadas coordenadasGuarida = new Coordenadas(5,5);
        Coordenadas coordenadasReserva = new Coordenadas(6,6);

        //Me aseguro de que el terreno sea el adecuado para los edificios
        tablero.actualizarTerreno(coordenadasGuarida, 5, new Moho());

        //genero dos zanganos, uno para cada edificio
        Zangano zangano1 = new Zangano(tablero,coordenadasGuarida, minerales);
        Zangano zangano2 = new Zangano(tablero,coordenadasReserva, minerales);

        //Construyo un edificio
        zergs.construirReservaDeReproduccion(zangano2);

        assertDoesNotThrow(() -> zergs.construirGuarida(zangano1));
    }
    @Test
    public void test03IntentoConstruirUnEspiralSinTenerAntesUnaGuaridaYLanzaExcepcion() {
        Tablero tablero = new Tablero(20,20);
        //Establezco los minerales iniciales
        Recurso minerales = new Recurso(1000);
        Recurso gasVespeno = new Recurso(1000);

        //Creo al imperio y genero las coordenadas donde van a estar los edificios
        Zergs zergs = new Zergs(tablero, minerales, gasVespeno);
        Coordenadas coordenadasEspiral = new Coordenadas(5, 6);

        //Me aseguro de que el terreno sea el adecuado para los edificios
        tablero.actualizarTerreno(coordenadasEspiral, 5, new Moho());

        //genero dos zanganos, uno para cada edificio
        Zangano zangano = new Zangano(tablero,coordenadasEspiral, minerales);

        assertThrows(NoSeCumplenLosPreRequisitosDelEdificio.class, () -> zergs.construirEspiral(zangano));
    }
    @Test
    public void test04IntentoConstruirUnEspiralSiTeniendoAntesUnaGuaridaYNoLanzaExcepcion() {
        Tablero tablero = new Tablero(20,20);
        //Establezco los minerales iniciales
        Recurso minerales = new Recurso(1000);
        Recurso gasVespeno = new Recurso(1000);

        //Creo al imperio y genero las coordenadas donde van a estar los edificios
        Zergs zergs = new Zergs(tablero, minerales, gasVespeno);
        Coordenadas coordenadasEspiral = new Coordenadas(5, 6);
        Coordenadas coordenadasGuarida = new Coordenadas(5,5);
        Coordenadas coordenadasReserva = new Coordenadas(6,6);

        //Me aseguro de que el terreno sea el adecuado para los edificios
        tablero.actualizarTerreno(coordenadasEspiral, 5, new Moho());

        //genero dos zanganos, uno para cada edificio
        Zangano zangano = new Zangano(tablero,coordenadasEspiral, minerales);
        Zangano zangano2 = new Zangano(tablero,coordenadasReserva, minerales);
        Zangano zangano3 = new Zangano(tablero,coordenadasGuarida, minerales);

        //Construyo los edificios
        zergs.construirReservaDeReproduccion(zangano2);
        zergs.construirGuarida(zangano3);

        assertDoesNotThrow(() -> zergs.construirEspiral(zangano));
    }
    @Test
    public void test05IntentoConstruirUnPuestoEstelarSinTenerAntesUnAccesoYLanzaExcepcion() {
        Tablero tablero = new Tablero(20,20);
        Recurso minerales = new Recurso(5000);
        Recurso gasVespeno = new Recurso(5000);
        Protoss protoss = new Protoss(tablero, minerales, gasVespeno);

        assertThrows(NoSeCumplenLosPreRequisitosDelEdificio.class, () -> protoss.construirPuertoEstelar());
    }
    @Test
    public void test06IntentoConstruirUnPuertoEstelarTeniendoAntesUnaAccesoYNoLanzaExcepcion() {
        Tablero tablero = new Tablero(20,20);
        //Establezco los minerales iniciales
        Recurso minerales = new Recurso(5000);
        Recurso gasVespeno = new Recurso(5000);

        //Creo al imperio y genero las coordenadas donde van a estar los edificios
        Protoss protoss = new Protoss(tablero, minerales, gasVespeno);

        //Construyo un edificio
        protoss.construirAcceso();

        assertDoesNotThrow(() -> protoss.construirPuertoEstelar());
    }
}

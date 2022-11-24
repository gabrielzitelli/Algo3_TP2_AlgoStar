package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Pilon;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeConstruirEdificioSobreOtroEdificio;
import edu.fiuba.algo3.modelo.Imperio.*;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso24Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().recolocarBasesIniciales();
    }

    //El Imperio Zerg asienta una base en el lado superior del mapa porque son mas afines al frio del norte
    @Test
    public void test01ElMapaTieneUnaBaseDelLadoSuperiorDondeSeAsientanLosZerg(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();
        imperioZerg.inicializarAsentamientoPrimerTurno();

        Casilla casillaBase = elMapa.obtenerVolcanBaseLejanaPrimeraMitad();
        Coordenada coordenadaBase = casillaBase.obtenerCoordenada();
        Coordenada coordenadaAsentamientoZerg = new Coordenada(coordenadaBase.getCoordenadaX() -2, coordenadaBase.getCoordenadaY());

        //Se tira la excepcion porque en esa casilla ya hay un criadero, el asentamiento inicial zerg
        assertThrows(ErrorNoSePuedeConstruirEdificioSobreOtroEdificio.class,
                () -> elMapa.construirEdificio(new Criadero(), coordenadaAsentamientoZerg));
    }

    //El Imperio Protoss asienta una base en el lado inferior del mapa porque los cristales Khaydarin resuenan mejor en el sur
    @Test
    public void test02ElMapaTieneUnaBaseDelLadoInferiorDondeSeAsientanLosProtoss(){
        Mapa elMapa = Mapa.obtener();
        Protoss imperioProtoss = new Protoss();
        imperioProtoss.inicializarAsentamientoPrimerTurno();

        Casilla casillaBase = elMapa.obtenerVolcanBaseLejanaSegundaMitad();
        Coordenada coordenadaBase = casillaBase.obtenerCoordenada();
        Coordenada coordenadaAcceso = new Coordenada(coordenadaBase.getCoordenadaX() -2, coordenadaBase.getCoordenadaY());
        Coordenada coordenadaPilon = new Coordenada(coordenadaBase.getCoordenadaX() -3, coordenadaBase.getCoordenadaY()-1);

        //Se tira la excepcion porque en esa casilla ya hay un edificio (un acceso),  parte del asentamiento inicial protoss
        assertThrows(ErrorNoSePuedeConstruirEdificioSobreOtroEdificio.class,
                () -> elMapa.construirEdificio(new Pilon(), coordenadaAcceso));

        //Se tira la excepcion porque en esa casilla ya hay un edificio (un pilon),  parte del asentamiento inicial protoss
        assertThrows(ErrorNoSePuedeConstruirEdificioSobreOtroEdificio.class,
                () -> elMapa.construirEdificio(new Pilon(), coordenadaPilon));
    }

}

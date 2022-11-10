package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.excepciones.TerrenoNoCompatibleConEdificio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class CasoDeUso5Test {
    @Test
    public void IntentarConstruirExtractorEnLugarSinMohoLanzaExcepcion() {
        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new VolcanGasVespeno());
        Edificio extractor = new Extractor(nodoCompatible, new Recurso());
        Nodo nodo = new Nodo(new Neutro(), new VolcanGasVespeno());

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> nodo.construir(extractor) );
    }

    @Test
    public void IntentarConstruirReservaDeReproduccionEnLugarSinMohoLanzaExcepcion() {
        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new SinRecurso());
        Edificio reserva = new ReservaDeReproduccion(nodoCompatible);
        Nodo nodo = new Nodo(new Neutro(), new SinRecurso());

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> nodo.construir(reserva) );
    }

    @Test
    public void IntentarConstruirGuaridaEnLugarSinMohoLanzaExcepcion() {
        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new SinRecurso());
        Edificio guarida = new Guarida(nodoCompatible);
        Nodo nodo = new Nodo(new Neutro(), new SinRecurso());

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> nodo.construir(guarida) );
    }

    @Test
    public void IntentarConstruirEspiralEnLugarSinMohoLanzaExcepcion() {
        NodoCompatible nodoCompatible = new NodoCompatible(new Moho(), new SinRecurso());
        Edificio espiral = new Espiral(nodoCompatible);
        Nodo nodo = new Nodo(new Neutro(), new SinRecurso());

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> nodo.construir(espiral) );
    }

    @Test
    public void IntentarConstruirAccesoFueraDelRangoDeUnPilonLanzaExcepcion() {
        NodoCompatible nodoCompatible = new NodoCompatible(new Energia(), new SinRecurso());
        Edificio acceso = new Acceso(nodoCompatible, new Coordenadas(0,0));
        Nodo nodo = new Nodo();

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> nodo.construir(acceso));
    }

    @Test
    public void IntentarConstruirPuertoEstelarFueraDelRangoDeUnPilonLanzaExcepcion() {
        NodoCompatible nodoCompatible = new NodoCompatible(new Energia(), new SinRecurso());
        Edificio acceso = new Acceso(nodoCompatible, new Coordenadas(0,0));
        Nodo nodo = new Nodo();

        assertThrows(TerrenoNoCompatibleConEdificio.class, () -> nodo.construir(acceso));
    }
}

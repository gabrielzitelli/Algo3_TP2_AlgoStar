package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.Moho;
import edu.fiuba.algo3.modelo.Tablero.Neutro;
import edu.fiuba.algo3.modelo.Tablero.SinRecurso;
import edu.fiuba.algo3.modelo.Tablero.VolcanGasVespeno;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasoDeUso10 {
    /*
    Verificar que al dañar una construcción zerg, la misma recupera la vida por turnos hasta
    volver a tener el 100%.
     */


    @Test
    public void seConstruyeUnCriaderoSeLoDaniaYSuVidaEsLaEsperada(){
        Imperio zergs = new Zergs(new Recurso(), new Recurso());
        NodoCompatible nodoCompatibleCriadero = new NodoCompatible(new Moho(), new SinRecurso());
        Criadero criadero = new Criadero(nodoCompatibleCriadero, new Recurso(50), zergs);

        // Paso 4 turnos para que se construya el criadero
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();
        criadero.accionDeTurno();

        criadero.recibirDanio(200);

        int vidaEsperada = 300;

        assertEquals(vidaEsperada, criadero.getVida());
    }
    @Test
    public void seConstruyeUnCriaderoSeLoDaniaYRecuperaGradualmenteTodaSuVida(){
        Imperio zergs = new Zergs(new Recurso(), new Recurso());
        NodoCompatible nodoCompatibleCriadero = new NodoCompatible(new Moho(), new SinRecurso());
        Criadero criadero = new Criadero(nodoCompatibleCriadero, new Recurso(50), zergs);
        criadero.recibirDanio(200);

        criadero.accionDeTurno();
        criadero.accionDeTurno();

        int vidaEsperada = 500;

        assertEquals(vidaEsperada, criadero.getVida());

    }

    @Test
    public void seConstruyeUnaReservaDeReproduccionSeLoDaniaYSuVidaEsLaEsperada(){
        Imperio zergs = new Zergs(new Recurso(), new Recurso());
        NodoCompatible nodoCompatibleReserva = new NodoCompatible(new Moho(), new SinRecurso());
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(nodoCompatibleReserva);

        // Paso 12 turnos para que se construya la reservaDeReproduccion
        for (int i = 0; i < 12; i++){
            reservaDeReproduccion.accionDeTurno();
        }

        reservaDeReproduccion.recibirDanio(500);

        int vidaEsperada = 500;

        assertEquals(vidaEsperada, reservaDeReproduccion.getVida());
    }

    @Test
    public void seConstruyeUnaReservaDeReproduccionSeLoDaniaYSePasanSuficientesTurnosParaQueSeCure(){
        Imperio zergs = new Zergs(new Recurso(), new Recurso());
        NodoCompatible nodoCompatibleReserva = new NodoCompatible(new Moho(), new SinRecurso());
        ReservaDeReproduccion reservaDeReproduccion = new ReservaDeReproduccion(nodoCompatibleReserva);

        // Paso 12 turnos para que se construya la reservaDeReproduccion
        for (int i = 0; i < 12; i++){
            reservaDeReproduccion.accionDeTurno();
        }

        reservaDeReproduccion.recibirDanio(500);

        reservaDeReproduccion.accionDeTurno();
        reservaDeReproduccion.accionDeTurno();

        int vidaEsperada = 1000;

        assertEquals(vidaEsperada, reservaDeReproduccion.getVida());
    }

    @Test
    public void seConstruyeUnExtractorSeLoDaniaYSuVidaEsLaEsperada(){
        Imperio zergs = new Zergs(new Recurso(), new Recurso());
        NodoCompatible nodoCompatibleReserva = new NodoCompatible(new Neutro(), new VolcanGasVespeno());
        Extractor extractor = new Extractor(nodoCompatibleReserva, new Recurso(100));

        // Paso 12 turnos para que se construya la reservaDeReproduccion
        for (int i = 0; i < 6; i++){
            extractor.accionDeTurno();
        }

        extractor.recibirDanio(500);
        int vidaEsperada = 250;

        assertEquals(vidaEsperada, extractor.getVida());
    }

    @Test
    public void seConstruyeUnExtractorSeLoDaniaYPasaTurnosHastaQueEsteCurado(){
        Imperio zergs = new Zergs(new Recurso(), new Recurso());
        NodoCompatible nodoCompatibleReserva = new NodoCompatible(new Neutro(), new VolcanGasVespeno());
        Extractor extractor = new Extractor(nodoCompatibleReserva, new Recurso(100));

        // Paso 6 turnos para que se construya la reservaDeReproduccion
        for (int i = 0; i < 6; i++){
            extractor.accionDeTurno();
        }

        extractor.recibirDanio(500);

        extractor.accionDeTurno();
        extractor.accionDeTurno();
        extractor.accionDeTurno();

        int vidaEsperada = 750;

        assertEquals(vidaEsperada, extractor.getVida());
    }

    @Test
    public void seConstruyeUnaGuaridaSeLoDaniaYSuVidaEsLaEsperada(){
        Imperio zergs = new Zergs(new Recurso(), new Recurso());
        NodoCompatible nodoCompatibleGuarida= new NodoCompatible(new Moho(), new SinRecurso());
        Guarida guarida = new Guarida(nodoCompatibleGuarida);

        // Paso 12 turnos para que se construya la reservaDeReproduccion
        for (int i = 0; i < 12; i++){
            guarida.accionDeTurno();
        }

        guarida.recibirDanio(250);
        int vidaEsperada = 1000;

        assertEquals(vidaEsperada, guarida.getVida());
    }

    @Test
    public void seConstruyeUnaGuaridaSeLoDaniaYDespuesDeUnosTurnosSeRecuperaTodaLaVida(){
        Imperio zergs = new Zergs(new Recurso(), new Recurso());
        NodoCompatible nodoCompatibleGuarida= new NodoCompatible(new Moho(), new SinRecurso());
        Guarida guarida = new Guarida(nodoCompatibleGuarida);

        // Paso 12 turnos para que se construya la reservaDeReproduccion
        for (int i = 0; i < 12; i++){
            guarida.accionDeTurno();
        }

        guarida.recibirDanio(250);
        guarida.accionDeTurno();
        int vidaEsperada = 1250;

        assertEquals(vidaEsperada, guarida.getVida());
    }

    @Test
    public void seConstruyeUnaEspiralSeLoDaniaYSuVidaEsLaEsperada(){
        Imperio zergs = new Zergs(new Recurso(), new Recurso());
        NodoCompatible nodoCompatibleGuarida= new NodoCompatible(new Moho(), new SinRecurso());
        Espiral espiral = new Espiral(nodoCompatibleGuarida);

        // Paso 10 turnos para que se construya la reservaDeReproduccion
        for (int i = 0; i < 10; i++){
            espiral.accionDeTurno();
        }

        espiral.recibirDanio(350);
        int vidaEsperada = 1000;

        assertEquals(vidaEsperada, espiral.getVida());
    }

    @Test
    public void seConstruyeUnaEspiralSeLoDaniaYSePasanVariosTurnosYSeRecuperaTodaLaVida(){
        Imperio zergs = new Zergs(new Recurso(), new Recurso());
        NodoCompatible nodoCompatibleGuarida= new NodoCompatible(new Moho(), new SinRecurso());
        Espiral espiral = new Espiral(nodoCompatibleGuarida);

        // Paso 10 turnos para que se construya la reservaDeReproduccion
        for (int i = 0; i < 10; i++){
            espiral.accionDeTurno();
        }

        espiral.recibirDanio(350);
        espiral.accionDeTurno();
        espiral.accionDeTurno();
        int vidaEsperada = 1350;

        assertEquals(vidaEsperada, espiral.getVida());
    }
}

package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasoDeUso10 {
    /*
    Verificar que al dañar una construcción zerg, la misma recupera la vida por turnos hasta
    volver a tener el 100%.
     */


    @Test
    public void seConstruyeUnCriaderoSeLoDaniaYSuVidaEsLaEsperada(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new SinRecurso(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Moho(), new Coordenadas(0,0));
        Zergs zergs = new Zergs(tablero, new Recurso(), new Recurso());
        NodoCompatible nodoCompatibleCriadero = new NodoCompatible(new Moho(), new SinRecurso());
        Criadero criadero = new Criadero(tablero, nodoCompatibleCriadero, new Recurso(50), zergs);

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
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new SinRecurso(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Moho(), new Coordenadas(0,0));
        Zergs zergs = new Zergs(tablero,new Recurso(), new Recurso());
        NodoCompatible nodoCompatibleCriadero = new NodoCompatible(new Moho(), new SinRecurso());
        Criadero criadero = new Criadero(tablero, nodoCompatibleCriadero, new Recurso(50), zergs);
        criadero.recibirDanio(200);

        criadero.accionDeTurno();
        criadero.accionDeTurno();

        int vidaEsperada = 500;

        assertEquals(vidaEsperada, criadero.getVida());

    }

    @Test
    public void seConstruyeUnaReservaDeReproduccionSeLoDaniaYSuVidaEsLaEsperada(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new SinRecurso(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Moho(), new Coordenadas(0,0));
        Imperio zergs = new Zergs(tablero, new Recurso(), new Recurso());
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
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new SinRecurso(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Moho(), new Coordenadas(0,0));
        Imperio zergs = new Zergs(tablero, new Recurso(), new Recurso());
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
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new VolcanGasVespeno(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Moho(), new Coordenadas(0,0));
        Imperio zergs = new Zergs(tablero,new Recurso(), new Recurso());
        NodoCompatible nodoCompatibleReserva = new NodoCompatible(new Moho(), new VolcanGasVespeno());
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
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new VolcanGasVespeno(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Moho(), new Coordenadas(0,0));
        Imperio zergs = new Zergs(tablero,new Recurso(), new Recurso());
        NodoCompatible nodoCompatibleReserva = new NodoCompatible(new Moho(), new VolcanGasVespeno());
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
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new SinRecurso(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Moho(), new Coordenadas(0,0));
        Imperio zergs = new Zergs(tablero,new Recurso(), new Recurso());
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
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new SinRecurso(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Moho(), new Coordenadas(0,0));
        Imperio zergs = new Zergs(tablero,new Recurso(), new Recurso());
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
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new SinRecurso(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Moho(), new Coordenadas(0,0));
        Imperio zergs = new Zergs(tablero,new Recurso(), new Recurso());
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
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new SinRecurso(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Moho(), new Coordenadas(0,0));
        Imperio zergs = new Zergs(tablero,new Recurso(), new Recurso());
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

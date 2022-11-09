package edu.fiuba.algo3.casoDeUso10;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.Moho;
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
        NodoCompatible nodoCompatibleReserva = new NodoCompatible(new Moho(), new VolcanGasVespeno());
        Extractor extractor = new Extractor(nodoCompatibleReserva);

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


}

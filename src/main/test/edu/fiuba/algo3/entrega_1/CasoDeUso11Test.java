package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Objetivo:
Verificar que al dañar una construcción protoss sin quitarle todo el escudo, la misma
recupera su escudo por turnos hasta volver a tener el 100% del mismo
*/
public class CasoDeUso11Test {

    // Testeo NexoMineral

    @Test
    public void test01ConstruyoUnNexoMineralYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraDeManeraEsperada(){

        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new NodoMineral(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(50), new Recurso(0));
        Edificio unNexoMineral = protoss.construirNexoMineral(new Coordenadas(0, 0));

        // NexoMineral tiene 250E / 250 V
        unNexoMineral.recibirDanio(249);
        unNexoMineral.accionDeTurno();
        unNexoMineral.accionDeTurno();
        int vidaEsperada = 375;

        assertEquals(vidaEsperada, unNexoMineral.getVida());
    }

    @Test
    public void test02ConstruyoUnNexoMineralYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteAlPasarLosTurnosAdecuados(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new NodoMineral(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(50), new Recurso(0));
        Edificio unNexoMineral = protoss.construirNexoMineral(new Coordenadas(0, 0));

        // NexoMineral tiene 250E / 250 V
        unNexoMineral.recibirDanio(249);
        unNexoMineral.accionDeTurno();
        unNexoMineral.accionDeTurno();
        unNexoMineral.accionDeTurno();
        unNexoMineral.accionDeTurno();
        unNexoMineral.accionDeTurno();

        int vidaEsperada = 500;

        assertEquals(vidaEsperada, unNexoMineral.getVida());
    }

    @Test
    public void test03ConstruyoUnNexoMineralYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteYNoSeExcedeDelMaximo(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new NodoMineral(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(50), new Recurso(0));
        Edificio unNexoMineral = protoss.construirNexoMineral(new Coordenadas(0, 0));

        // NexoMineral tiene 250E / 250V
        unNexoMineral.recibirDanio(249);

        for(int i = 0; i < 10; i++)
            unNexoMineral.accionDeTurno();

        int vidaEsperada = 500;

        assertEquals(vidaEsperada, unNexoMineral.getVida());
    }

    // Testeo Pilon

    @Test
    public void test04ConstruyoUnPilonYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraDeManeraEsperada(){
        Tablero tablero = new Tablero(1, 1);
        NodoCompatible nodoCompatiblePilon = new NodoCompatible(new Neutro(), new SinRecurso());
        Pilon unPilon = new Pilon(tablero, nodoCompatiblePilon, new Coordenadas(0,0));

        // Pilon tiene 300E / 300V
        unPilon.recibirDanio(299);
        unPilon.accionDeTurno();
        unPilon.accionDeTurno();
        int vidaEsperada = 451;

        assertEquals(vidaEsperada, unPilon.getVida());
    }

    @Test
    public void test05ConstruyoUnPilonYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteAlPasarLosTurnosAdecuados(){
        Tablero tablero = new Tablero(1, 1);
        NodoCompatible nodoCompatiblePilon = new NodoCompatible(new Neutro(), new SinRecurso());
        Pilon unPilon = new Pilon(tablero, nodoCompatiblePilon, new Coordenadas(0,0));

        // Pilon tiene 300E / 300V
        unPilon.recibirDanio(299);

        unPilon.accionDeTurno();
        unPilon.accionDeTurno();
        unPilon.accionDeTurno();
        unPilon.accionDeTurno();

        int vidaEsperada = 600;

        assertEquals(vidaEsperada, unPilon.getVida());
    }

    @Test
    public void test06ConstruyoUnPilonYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteYNoSeExcedeDelMaximo(){
        Tablero tablero = new Tablero(1, 1);
        NodoCompatible nodoCompatiblePilon = new NodoCompatible(new Neutro(), new SinRecurso());
        Pilon unPilon = new Pilon(tablero, nodoCompatiblePilon, new Coordenadas(0,0));

        // Pilon tiene 300E / 300V
        unPilon.recibirDanio(299);

        for(int i = 0; i < 10; i++)
            unPilon.accionDeTurno();

        int vidaEsperada = 600;

        assertEquals(vidaEsperada, unPilon.getVida());
    }

    // Testeo Asimilador

    @Test
    public void test07ConstruyoUnAsimiladorYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraDeManeraEsperada(){

        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new VolcanGasVespeno(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(100), new Recurso(0));
        Edificio unAsimilador = protoss.construirAsimilador(new Coordenadas(0,0));

        // Asimilador tiene 450E / 450V
        unAsimilador.recibirDanio(449);
        unAsimilador.accionDeTurno();
        unAsimilador.accionDeTurno();
        int vidaEsperada = 675;

        assertEquals(vidaEsperada, unAsimilador.getVida());
    }

    @Test
    public void test08ConstruyoUnAsimiladorYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteAlPasarLosTurnosAdecuados(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new VolcanGasVespeno(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(100), new Recurso(0));
        Edificio unAsimilador = protoss.construirAsimilador(new Coordenadas(0,0));

        // Asimilador tiene 450E / 450V
        unAsimilador.recibirDanio(449);

        unAsimilador.accionDeTurno();
        unAsimilador.accionDeTurno();
        unAsimilador.accionDeTurno();
        unAsimilador.accionDeTurno();
        unAsimilador.accionDeTurno();

        int vidaEsperada = 900;

        assertEquals(vidaEsperada, unAsimilador.getVida());
    }

    @Test
    public void test09ConstruyoUnAsimiladorYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteYNoSeExcedeDelMaximo(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new VolcanGasVespeno(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(100), new Recurso(0));
        Edificio unAsimilador = protoss.construirAsimilador(new Coordenadas(0,0));

        // Asimilador tiene 450E / 450V
        unAsimilador.recibirDanio(449);

        for(int i = 0; i < 10; i++)
            unAsimilador.accionDeTurno();

        int vidaEsperada = 900;

        assertEquals(vidaEsperada, unAsimilador.getVida());
    }

    // Testeo Acceso

    @Test
    public void test10ConstruyoUnAccesoYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraDeManeraEsperada(){
        NodoCompatible nodoCompatibleAcceso = new NodoCompatible(new Energia(), new SinRecurso());
        Acceso unAcceso = new Acceso(nodoCompatibleAcceso, new Coordenadas(0,0));

        // Acceso tiene 500E / 500V
        unAcceso.recibirDanio(499);
        unAcceso.accionDeTurno();
        unAcceso.accionDeTurno();
        int vidaEsperada = 751;

        assertEquals(vidaEsperada, unAcceso.getVida());
    }

    @Test
    public void test11ConstruyoUnAccesoYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteAlPasarLosTurnosAdecuados(){
        NodoCompatible nodoCompatibleAcceso = new NodoCompatible(new Energia(), new SinRecurso());
        Acceso unAcceso = new Acceso(nodoCompatibleAcceso, new Coordenadas(0,0));

        // Acceso tiene 500E / 500V
        unAcceso.recibirDanio(499);

        unAcceso.accionDeTurno();
        unAcceso.accionDeTurno();
        unAcceso.accionDeTurno();
        unAcceso.accionDeTurno();

        int vidaEsperada = 1000;

        assertEquals(vidaEsperada, unAcceso.getVida());
    }

    @Test
    public void test12ConstruyoUnAccesoYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteYNoSeExcedeDelMaximo(){
        NodoCompatible nodoCompatibleAcceso = new NodoCompatible(new Energia(), new SinRecurso());
        Acceso unAcceso = new Acceso(nodoCompatibleAcceso, new Coordenadas(0,0));

        // Acceso tiene 500E / 500V
        unAcceso.recibirDanio(499);

        for(int i = 0; i < 10; i++)
            unAcceso.accionDeTurno();

        int vidaEsperada = 1000;

        assertEquals(vidaEsperada, unAcceso.getVida());
    }

    // Testeo Puerto Estelar

    @Test
    public void test13ConstruyoUnPuertoEstelarYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraDeManeraEsperada(){
        NodoCompatible nodoCompatiblePuertoEstelar = new NodoCompatible(new Energia(), new SinRecurso());
        PuertoEstelar unPuertoEstelar = new PuertoEstelar(nodoCompatiblePuertoEstelar, new Coordenadas(0,0));

        // PuertoEstelar tiene 600E / 600V
        unPuertoEstelar.recibirDanio(599);
        unPuertoEstelar.accionDeTurno();
        unPuertoEstelar.accionDeTurno();
        int vidaEsperada = 901;

        assertEquals(vidaEsperada, unPuertoEstelar.getVida());
    }

    @Test
    public void test14ConstruyoUnPuertoEstelarYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteAlPasarLosTurnosAdecuados(){
        NodoCompatible nodoCompatiblePuertoEstelar = new NodoCompatible(new Energia(), new SinRecurso());
        PuertoEstelar unPuertoEstelar = new PuertoEstelar(nodoCompatiblePuertoEstelar, new Coordenadas(0,0));

        // PuertoEstelar tiene 600E / 600V
        unPuertoEstelar.recibirDanio(599);

        unPuertoEstelar.accionDeTurno();
        unPuertoEstelar.accionDeTurno();
        unPuertoEstelar.accionDeTurno();
        unPuertoEstelar.accionDeTurno();

        int vidaEsperada = 1200;

        assertEquals(vidaEsperada, unPuertoEstelar.getVida());
    }

    @Test
    public void test15ConstruyoUnPuertoEstelarYAlHacerDanioMenorAlEscudoElEscudoSeRegeneraTotalmenteYNoSeExcedeDelMaximo(){
        NodoCompatible nodoCompatiblePuertoEstelar = new NodoCompatible(new Energia(), new SinRecurso());
        PuertoEstelar unPuertoEstelar = new PuertoEstelar(nodoCompatiblePuertoEstelar, new Coordenadas(0,0));

        // PuertoEstelar tiene 600E / 600V
        unPuertoEstelar.recibirDanio(599);

        for(int i = 0; i < 10; i++)
            unPuertoEstelar.accionDeTurno();

        int vidaEsperada = 1200;

        assertEquals(vidaEsperada, unPuertoEstelar.getVida());
    }
}

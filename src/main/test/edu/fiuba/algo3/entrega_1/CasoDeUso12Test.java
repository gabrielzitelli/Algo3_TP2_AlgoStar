package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Objetivo:
Verificar que al dañar una construcción protoss quitandole todo el escudo y parte de la
vida la misma recupera SOLO su escudo por turnos hasta volver a tener el 100% del
mismo.
*/
public class CasoDeUso12Test {

    // Testeo NexoMineral

    @Test
    public void test01ConstruyoUnNexoMineralYAlHacerDanioMayorAlEscudoYSeRegeneraDeManeraEsperadaYVidaNoSeRegenera(){

        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new NodoMineral(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(50), new Recurso(0));
        Edificio unNexoMineral = protoss.construirNexoMineral(new Coordenadas(0, 0));

        // NexoMineral tiene 250E / 250 V
        unNexoMineral.recibirDanio(499); // Queda con 1 de vida
        unNexoMineral.accionDeTurno();
        unNexoMineral.accionDeTurno();
        int vidaEsperada = 125; // 1 de vida + 124 de escudo

        assertEquals(vidaEsperada, unNexoMineral.getVida());
    }

    @Test
    public void test02ConstruyoUnNexoMineralHagoDanioMayorAlEscudoYSeRegeneraTotalmenteAlPasarLosTurnosAdecuadosYVidaNoSeRegenera(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new NodoMineral(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(50), new Recurso(0));
        Edificio unNexoMineral = protoss.construirNexoMineral(new Coordenadas(0, 0));

        // NexoMineral tiene 250E / 250 V
        unNexoMineral.recibirDanio(498); // Queda con 2 de vida
        unNexoMineral.accionDeTurno();
        unNexoMineral.accionDeTurno();
        unNexoMineral.accionDeTurno();
        unNexoMineral.accionDeTurno();
        unNexoMineral.accionDeTurno();

        int vidaEsperada = 252; // 2 de vida + 250 de escudo

        assertEquals(vidaEsperada, unNexoMineral.getVida());
    }

    @Test
    public void test03ConstruyoUnNexoMineralHagoDanioMayorAlEscudoYSeRegeneraTotalmenteYNoSeExcedeDelMaximoYVidaNoSeRegenera(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new NodoMineral(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(50), new Recurso(0));
        Edificio unNexoMineral = protoss.construirNexoMineral(new Coordenadas(0, 0));

        // NexoMineral tiene 250E / 250V
        unNexoMineral.recibirDanio(450); // Queda con 50 de vida

        for(int i = 0; i < 10; i++)
            unNexoMineral.accionDeTurno();

        int vidaEsperada = 300; // 50 de vida + 250 de escudo

        assertEquals(vidaEsperada, unNexoMineral.getVida());
    }

    // Testeo Pilon

    @Test
    public void test04ConstruyoUnPilonhagoDanioMayorAlEscudoYSeRegeneraDeManeraEsperadaYVidaNoSeRegenera(){
        Tablero tablero = new Tablero(1, 1);
        NodoCompatible nodoCompatiblePilon = new NodoCompatible(new Neutro(), new SinRecurso());
        Pilon unPilon = new Pilon(tablero, nodoCompatiblePilon, new Coordenadas(0,0));

        // Pilon tiene 300E / 300V
        unPilon.recibirDanio(599); // Queda con 1 de vida
        unPilon.accionDeTurno();
        unPilon.accionDeTurno();
        int vidaEsperada = 151; // 1 de vida + 150 de escudo

        assertEquals(vidaEsperada, unPilon.getVida());
    }

    @Test
    public void test05ConstruyoUnPilonHagoDanioMayorAlEscudoYSeRegeneraTotalmenteAlPasarLosTurnosAdecuadosYVidaNoSeRegenera(){
        Tablero tablero = new Tablero(1, 1);
        NodoCompatible nodoCompatiblePilon = new NodoCompatible(new Neutro(), new SinRecurso());
        Pilon unPilon = new Pilon(tablero, nodoCompatiblePilon, new Coordenadas(0,0));

        // Pilon tiene 300E / 300V
        unPilon.recibirDanio(598); // Queda con 2 de vida

        unPilon.accionDeTurno();
        unPilon.accionDeTurno();
        unPilon.accionDeTurno();
        unPilon.accionDeTurno();

        int vidaEsperada = 302; // 2 de vida + 300 de escudo

        assertEquals(vidaEsperada, unPilon.getVida());
    }

    @Test
    public void test06ConstruyoUnPilonHagoDanioMayorAlEscudoYSeRegeneraTotalmenteYNoSeExcedeDelMaximoYVidaNoSeRegenera(){
        Tablero tablero = new Tablero(1, 1);
        NodoCompatible nodoCompatiblePilon = new NodoCompatible(new Neutro(), new SinRecurso());
        Pilon unPilon = new Pilon(tablero, nodoCompatiblePilon, new Coordenadas(0,0));

        // Pilon tiene 300E / 300V
        unPilon.recibirDanio(599); // Queda con 1 de vida

        for(int i = 0; i < 10; i++)
            unPilon.accionDeTurno();

        int vidaEsperada = 301; // 1 de vida + 300 de escudo

        assertEquals(vidaEsperada, unPilon.getVida());
    }

    // Testeo Asimilador

    @Test
    public void test07ConstruyoUnAsimiladorHagoDanioMayorAlEscudoYSeRegeneraDeManeraEsperadaYVidaNoSeRegenera(){

        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new VolcanGasVespeno(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(100), new Recurso(0));
        Edificio unAsimilador = protoss.construirAsimilador(new Coordenadas(0,0));

        // Asimilador tiene 450E / 450V
        unAsimilador.recibirDanio(850); // Queda con 50 de vida
        unAsimilador.accionDeTurno();
        unAsimilador.accionDeTurno();
        int vidaEsperada = 274; // 50 de vida + 224 de escudo

        assertEquals(vidaEsperada, unAsimilador.getVida());
    }

    @Test
    public void test08ConstruyoUnAsimiladorHagoDanioMayorAlEscudoYSeRegeneraTotalmenteAlPasarLosTurnosAdecuadosYVidaNoSeRegenera(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new VolcanGasVespeno(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(100), new Recurso(0));
        Edificio unAsimilador = protoss.construirAsimilador(new Coordenadas(0,0));

        // Asimilador tiene 450E / 450V
        unAsimilador.recibirDanio(898); // Queda con 2 de vida

        unAsimilador.accionDeTurno();
        unAsimilador.accionDeTurno();
        unAsimilador.accionDeTurno();
        unAsimilador.accionDeTurno();
        unAsimilador.accionDeTurno();

        int vidaEsperada = 452; // 2 de vida + 450 de escudo

        assertEquals(vidaEsperada, unAsimilador.getVida());
    }

    @Test
    public void test09ConstruyoUnAsimiladorHagoDanioMayorAlEscudoYSeRegeneraTotalmenteYNoSeExcedeDelMaximoYVidaNoSeRegenera(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new VolcanGasVespeno(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(100), new Recurso(0));
        Edificio unAsimilador = protoss.construirAsimilador(new Coordenadas(0,0));

        // Asimilador tiene 450E / 450V
        unAsimilador.recibirDanio(899); // Queda con 1 de vida

        for(int i = 0; i < 10; i++)
            unAsimilador.accionDeTurno();

        int vidaEsperada = 451; // 1 de vida + 450 de escudo

        assertEquals(vidaEsperada, unAsimilador.getVida());
    }

    // Testeo Acceso

    @Test
    public void test10ConstruyoUnAccesoHagoDanioMayorAlEscudoYSeRegeneraDeManeraEsperadaYVidaNoSeRegenera(){
        Tablero tablero = new Tablero(10,10);
        Protoss protoss = new Protoss(tablero, new Recurso(1000), new Recurso(1000));
        Coordenadas coordenadasAcceso = new Coordenadas(5,5);
        tablero.establecerTerreno(new Energia(), coordenadasAcceso);

        Edificio unAcceso = protoss.construirAcceso(coordenadasAcceso);

        // Acceso tiene 500E / 500V
        unAcceso.recibirDanio(999); // Queda con 1 de vida
        unAcceso.accionDeTurno();
        unAcceso.accionDeTurno();
        int vidaEsperada = 251; // 1 de vida + 250 de escudo

        assertEquals(vidaEsperada, unAcceso.getVida());
    }

    @Test
    public void test11ConstruyoUnAccesoMayorHagoDanioMayorAlEscudoYSeRegeneraTotalmenteAlPasarLosTurnosAdecuadosYVidaNoSeRegenera(){
        Tablero tablero = new Tablero(10,10);
        Protoss protoss = new Protoss(tablero, new Recurso(1000), new Recurso(1000));
        Coordenadas coordenadasAcceso = new Coordenadas(5,5);
        tablero.establecerTerreno(new Energia(), coordenadasAcceso);

        Edificio unAcceso = protoss.construirAcceso(coordenadasAcceso);

        // Acceso tiene 500E / 500V
        unAcceso.recibirDanio(998); // Queda con 2 de vida

        unAcceso.accionDeTurno();
        unAcceso.accionDeTurno();
        unAcceso.accionDeTurno();
        unAcceso.accionDeTurno();

        int vidaEsperada = 502; // 2 de vida + 500 de escudo

        assertEquals(vidaEsperada, unAcceso.getVida());
    }

    @Test
    public void test12ConstruyoUnAccesoHagoDanioMayorAlEscudoYSeRegeneraTotalmenteYNoSeExcedeDelMaximoYVidaNoSeRegenera(){
        Tablero tablero = new Tablero(10,10);
        Protoss protoss = new Protoss(tablero, new Recurso(1000), new Recurso(1000));
        Coordenadas coordenadasAcceso = new Coordenadas(5,5);
        tablero.establecerTerreno(new Energia(), coordenadasAcceso);

        Edificio unAcceso = protoss.construirAcceso(coordenadasAcceso);

        // Acceso tiene 500E / 500V
        unAcceso.recibirDanio(999); // Queda con 1 de vida

        for(int i = 0; i < 10; i++)
            unAcceso.accionDeTurno();

        int vidaEsperada = 501; // 1 de vida + 500 de escudo

        assertEquals(vidaEsperada, unAcceso.getVida());
    }

    // Testeo Puerto Estelar

    @Test
    public void test13ConstruyoUnPuertoEstelarHagoDanioMayorAlEscudoYSeRegeneraDeManeraEsperadaYVidaNoSeRegenera(){
        NodoCompatible nodoCompatiblePuertoEstelar = new NodoCompatible(new Energia(), new SinRecurso());
        PuertoEstelar unPuertoEstelar = new PuertoEstelar(nodoCompatiblePuertoEstelar, new Coordenadas(0, 0));

        // PuertoEstelar tiene 600E / 600V
        unPuertoEstelar.recibirDanio(1199); // Queda con 1 de vida
        unPuertoEstelar.accionDeTurno();
        unPuertoEstelar.accionDeTurno();
        int vidaEsperada = 301; // 1 de vida + 300 de escudo

        assertEquals(vidaEsperada, unPuertoEstelar.getVida());
    }

    @Test
    public void test14ConstruyoUnPuertoEstelarHagoDanioMayorAlEscudoYSeRegeneraTotalmenteAlPasarLosTurnosAdecuadosYVidaNoSeRegenera(){
        NodoCompatible nodoCompatiblePuertoEstelar = new NodoCompatible(new Energia(), new SinRecurso());
        PuertoEstelar unPuertoEstelar = new PuertoEstelar(nodoCompatiblePuertoEstelar, new Coordenadas(0, 0));

        // PuertoEstelar tiene 600E / 600V
        unPuertoEstelar.recibirDanio(1198); // Queda con 2 de vida

        unPuertoEstelar.accionDeTurno();
        unPuertoEstelar.accionDeTurno();
        unPuertoEstelar.accionDeTurno();
        unPuertoEstelar.accionDeTurno();

        int vidaEsperada = 602;  // 2 de vida + 600 de escudo

        assertEquals(vidaEsperada, unPuertoEstelar.getVida());
    }

    @Test
    public void test15ConstruyoUnPuertoEstelarHagoDanioMayorAlEscudoYSeRegeneraTotalmenteYNoSeExcedeDelMaximoYVidaNoSeRegenera(){
        NodoCompatible nodoCompatiblePuertoEstelar = new NodoCompatible(new Energia(), new SinRecurso());
        PuertoEstelar unPuertoEstelar = new PuertoEstelar(nodoCompatiblePuertoEstelar, new Coordenadas(0, 0));

        // PuertoEstelar tiene 600E / 600V
        unPuertoEstelar.recibirDanio(1199);

        for(int i = 0; i < 10; i++)
            unPuertoEstelar.accionDeTurno();

        int vidaEsperada = 601; // 1 de vida + 600 de escudo

        assertEquals(vidaEsperada, unPuertoEstelar.getVida());
    }
}

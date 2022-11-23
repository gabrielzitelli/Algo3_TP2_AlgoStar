package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Mapa.*;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.*;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso18Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01VerificoQueSiAtacoUnoZerlingConOtroZerlingNueveVecesEsteMuere(){
        Mapa elMapa = Mapa.obtener();
        Zerling unZerling = new Zerling();
        Zerling ZerlingADaniar = new Zerling();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unZerling, coordenadaAtacante);
        elMapa.colocarUnaUnidad(ZerlingADaniar, coordenadaAtacado);

        // El zerling mata al otro zerling
        for (int i = 0; i < 9; i++)
            elMapa.atacar(coordenadaAtacante, coordenadaAtacado);

        // La casilla esta vacia
        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new Zerling(), coordenadaAtacado));
    }

    @Test
    public void test02VerificoQueSiAtacoUnZerlingConUnHidraliscoCuatroVecesEsteMuere(){
        Mapa elMapa = Mapa.obtener();
        Zerling unZerling = new Zerling();
        Hidralisco unHidralisco = new Hidralisco();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unZerling, coordenadaAtacado);
        elMapa.colocarUnaUnidad(unHidralisco, coordenadaAtacante);

        // El zerling mata al hidralisco
        for (int i = 0; i < 4; i++)
            elMapa.atacar(coordenadaAtacante, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new Zerling(), coordenadaAtacado));
    }

    @Test
    public void test03VerificoQueSiAtacoUnZerlingConUnMutaliscoCuatroVecesEsteMuere(){
        Mapa elMapa = Mapa.obtener();;
        Zerling unZerling = new Zerling();
        Mutalisco unMutalisco = new Mutalisco();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaAtacante);

        elMapa.colocarUnaUnidad(unZerling, coordenadaAtacado);
        elMapa.colocarUnaUnidad(unMutalisco, coordenadaAtacante);

        // El zerling mata al mutalisco
        for (int i = 0; i < 4; i++)
            elMapa.atacar(coordenadaAtacante, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new Zerling(), coordenadaAtacado));
    }

    @Test
    public void test04VerificoQueSiAtacoUnZerlingConUnGuardianDosVecesEsteMuere(){
        Mapa elMapa = Mapa.obtener();
        Zerling unZerling = new Zerling();
        Guardian unGuardian = new Guardian();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaAtacante);

        elMapa.colocarUnaUnidad(unZerling, coordenadaAtacado);
        elMapa.colocarUnaUnidad(unGuardian, coordenadaAtacante);

        // El zerling mata al guardian
        elMapa.atacar(coordenadaAtacante, coordenadaAtacado);
        elMapa.atacar(coordenadaAtacante, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new Zerling(), coordenadaAtacado));
    }

    @Test
    public void test05VerificoQueSiAtacoUnZerlingConUnZealotCincoVecesEsteMuere(){
        Mapa elMapa = Mapa.obtener();
        Zerling unZerling = new Zerling();
        Zealot unZealot = new Zealot();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unZerling, coordenadaAtacado);
        elMapa.colocarUnaUnidad(unZealot, coordenadaAtacante);

        // El zerling mata al zealot
        for (int i = 0; i < 5; i++)
            elMapa.atacar(coordenadaAtacante, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new Zerling(), coordenadaAtacante));
    }

    @Test
    public void test06VerificoQueSiAtacoUnZerlingConUnDragonDosVecesEsteMuere(){
        Mapa elMapa = Mapa.obtener();
        Zerling unZerling = new Zerling();
        Dragon unDragon = new Dragon();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unZerling, coordenadaAtacado);
        elMapa.colocarUnaUnidad(unDragon, coordenadaAtacante);

        // El zerling mata al dragon
        elMapa.atacar(coordenadaAtacante, coordenadaAtacado);
        elMapa.atacar(coordenadaAtacante, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new Zerling(), coordenadaAtacado));
    }

    @Test
    public void test07VerificoQueSiAtacoUnZerlingConUnScoutCincoVecesEsteMuere(){
        Mapa elMapa = Mapa.obtener();
        Zerling unZerling = new Zerling();
        Scout unScout = new Scout();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaAtacante);

        elMapa.colocarUnaUnidad(unZerling, coordenadaAtacado);
        elMapa.colocarUnaUnidad(unScout, coordenadaAtacante);

        // El zerling mata al scout
        for (int i = 0; i < 5; i++)
            elMapa.atacar(coordenadaAtacante, coordenadaAtacado);

        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new Zerling(), coordenadaAtacado));
    }
}

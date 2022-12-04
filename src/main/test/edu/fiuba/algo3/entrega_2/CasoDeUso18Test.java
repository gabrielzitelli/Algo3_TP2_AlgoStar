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
    public void test01VerificoQueSiAtacoUnoDragonConOtroZerlingNueveVecesEsteMuere() {
        Mapa elMapa = Mapa.obtener();
        Zerling unZerling = new Zerling();
        Dragon unDragon = new Dragon();
        Coordenada coordenadaAtacante = new Coordenada(0, 0);
        Coordenada coordenadaAtacado = new Coordenada(0, 1);

        elMapa.colocarUnaUnidad(unZerling, coordenadaAtacante);
        elMapa.colocarUnaUnidad(unDragon, coordenadaAtacado);

        // El zerling mata al otro zerling
        for (int i = 0; i < 45; i++){
            elMapa.atacar(coordenadaAtacante, coordenadaAtacado);
            unZerling.pasarTurno();
        }


        // La casilla esta vacia
        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new Zerling(), coordenadaAtacado));
    }

    @Test
    public void test02VerificoQueSiAtacoUnDragonConUnHidraliscoCuatroVecesEsteMuere(){
        Mapa elMapa = Mapa.obtener();
        Dragon unDragon = new Dragon();
        Hidralisco unHidralisco = new Hidralisco();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarUnaUnidad(unDragon, coordenadaAtacado);
        elMapa.colocarUnaUnidad(unHidralisco, coordenadaAtacante);


        for (int i = 0; i < 18; i++){
            elMapa.atacar(coordenadaAtacante, coordenadaAtacado);
            unHidralisco.pasarTurno();
        }


        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new Zerling(), coordenadaAtacado));
    }

    @Test
    public void test03VerificoQueSiAtacoUnDragonConUnMutalisco20VecesEsteMuere(){
        Mapa elMapa = Mapa.obtener();;
        Dragon unDragon = new Dragon();
        Mutalisco unMutalisco = new Mutalisco();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaAtacante);

        elMapa.colocarUnaUnidad(unDragon, coordenadaAtacado);
        elMapa.colocarUnaUnidad(unMutalisco, coordenadaAtacante);


        for (int i = 0; i < 20; i++){
            elMapa.atacar(coordenadaAtacante, coordenadaAtacado);
            unMutalisco.pasarTurno();
        }
        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new Zerling(), coordenadaAtacado));
    }

    @Test
    public void test04VerificoQueSiAtacoUnDragonConUnGuardian8VecesEsteMuere(){
        Mapa elMapa = Mapa.obtener();
        Dragon unDragon = new Dragon();
        Guardian unGuardian = new Guardian();
        Coordenada coordenadaAtacante = new Coordenada(0,0);
        Coordenada coordenadaAtacado = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaAtacante);

        elMapa.colocarUnaUnidad(unDragon, coordenadaAtacado);
        elMapa.colocarUnaUnidad(unGuardian, coordenadaAtacante);


        for ( int i = 0; i < 8 ; i++){
            elMapa.atacar(coordenadaAtacante, coordenadaAtacado);
            unGuardian.pasarTurno();
        }
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


        for (int i = 0; i < 5; i++){
            elMapa.atacar(coordenadaAtacante, coordenadaAtacado);
            unZealot.pasarTurno();
        }

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


        elMapa.atacar(coordenadaAtacante, coordenadaAtacado);
        unDragon.pasarTurno();
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


        for (int i = 0; i < 5; i++){
            elMapa.atacar(coordenadaAtacante, coordenadaAtacado);
            unScout.pasarTurno();
        }


        assertDoesNotThrow(() -> elMapa.colocarUnaUnidad(new Zerling(), coordenadaAtacado));
    }
}

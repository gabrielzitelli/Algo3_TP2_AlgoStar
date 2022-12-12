package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeMoverUnaUnidadQueNoExiste;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Scout;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso20Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01PuedoMoverUnaUnidadAUnaCoordenadaYLaCasillaDeDondeSalioQuedaVacia(){
        Mapa elMapa = Mapa.obtener();
        Zerling unZerling = new Zerling();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material
        elMapa.colocarOcupable(unZerling, coordenadaInicial);
        elMapa.moverUnidad(coordenadaInicial, coordenadaFinal);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertThrows(ErrorNoSePuedeMoverUnaUnidadQueNoExiste.class,
                () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test02PuedoMoverUnaUnidadAUnaCoordenadaYLuegoPuedoMoverlaDeNuevo(){
        Mapa elMapa = Mapa.obtener();
        Zerling unZerling = new Zerling();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaIntermedia = new Coordenada(0,1);
        Coordenada coordenadaFinal = new Coordenada(0,2);

        //Una unidad puede moverse sobre un material
        elMapa.colocarOcupable(unZerling, coordenadaInicial);
        elMapa.moverUnidad(coordenadaInicial, coordenadaIntermedia);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow(() -> elMapa.moverUnidad(coordenadaIntermedia, coordenadaFinal) );
    }

    @Test
    public void test03PuedoMoverUnZerlingAUnaCoordenadaTerrestre(){
        Mapa elMapa = Mapa.obtener();
        Zerling unZerling = new Zerling();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material
        elMapa.colocarOcupable(unZerling, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test04NoPuedoMoverUnZerlingAUnaCoordenadaAerea(){
        Mapa elMapa = Mapa.obtener();
        Zerling unZerling = new Zerling();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaFinal);
        //Una unidad puede moverse sobre un material

        elMapa.colocarOcupable(unZerling, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertThrows(ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible.class,
                () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test05PuedoMoverUnZanganoAUnaCoordenadaTerrestre(){
        Mapa elMapa = Mapa.obtener();
        Zangano unZangano = new Zangano();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material
        elMapa.colocarOcupable(unZangano, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test06NoPuedoMoverUnZanganoAUnaCoordenadaAerea(){
        Mapa elMapa = Mapa.obtener();
        Zangano unZangano = new Zangano();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaFinal);
        //Una unidad puede moverse sobre un material

        elMapa.colocarOcupable(unZangano, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertThrows(ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible.class,
                () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test07PuedoMoverUnHidraliscoAUnaCoordenadaTerrestre(){
        Mapa elMapa = Mapa.obtener();
        Hidralisco unHidralisco = new Hidralisco();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material
        elMapa.colocarOcupable(unHidralisco, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test08NoPuedoMoverUnHidraliscoAUnaCoordenadaAerea(){
        Mapa elMapa = Mapa.obtener();
        Hidralisco unHidralisco = new Hidralisco();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaFinal);
        //Una unidad puede moverse sobre un material

        elMapa.colocarOcupable(unHidralisco, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertThrows(ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible.class, () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test09PuedoMoverUnMutaliscoAUnaCoordenadaTerrestre(){
        Mapa elMapa = Mapa.obtener();
        Mutalisco unMutalisco = new Mutalisco();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material
        elMapa.colocarOcupable(unMutalisco, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test10PuedoMoverUnMutaliscoAUnaCoordenadaAerea(){
        Mapa elMapa = Mapa.obtener();
        Mutalisco unMutalisco = new Mutalisco();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaFinal);
        //Una unidad puede moverse sobre un material

        elMapa.colocarOcupable(unMutalisco, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test11PuedoMoverUnGuardianAUnaCoordenadaTerrestre(){
        Mapa elMapa = Mapa.obtener();
        Guardian unGuardian = new Guardian();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material
        elMapa.colocarOcupable(unGuardian, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test12PuedoMoverUnGuardianAUnaCoordenadaAerea(){
        Mapa elMapa = Mapa.obtener();
        Guardian unGuardian = new Guardian();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaFinal);
        //Una unidad puede moverse sobre un material

        elMapa.colocarOcupable(unGuardian, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test13PuedoMoverUnZealotAUnaCoordenadaTerrestre(){
        Mapa elMapa = Mapa.obtener();
        Zealot unZealot = new Zealot();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material
        elMapa.colocarOcupable(unZealot, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test14NoPuedoMoverUnZealotAUnaCoordenadaAerea(){
        Mapa elMapa = Mapa.obtener();
        Zealot unZealot = new Zealot();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaFinal);
        //Una unidad puede moverse sobre un material

        elMapa.colocarOcupable(unZealot, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertThrows(ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible.class, () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test15PuedoMoverUnDragonAUnaCoordenadaTerrestre(){
        Mapa elMapa = Mapa.obtener();
        Dragon unDragon = new Dragon();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material
        elMapa.colocarOcupable(unDragon, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test16NoPuedoMoverUnDragonAUnaCoordenadaAerea(){
        Mapa elMapa = Mapa.obtener();
        Dragon unDragon = new Dragon();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaFinal);
        //Una unidad puede moverse sobre un material

        elMapa.colocarOcupable(unDragon, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertThrows(ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible.class, () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test17PuedoMoverUnScoutAUnaCoordenadaTerrestre(){
        Mapa elMapa = Mapa.obtener();
        Scout unScout = new Scout();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material
        elMapa.colocarOcupable(unScout, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test18PuedoMoverUnScoutAUnaCoordenadaAerea(){
        Mapa elMapa = Mapa.obtener();
        Scout unScout = new Scout();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaFinal);
        //Una unidad puede moverse sobre un material

        elMapa.colocarOcupable(unScout, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }
}

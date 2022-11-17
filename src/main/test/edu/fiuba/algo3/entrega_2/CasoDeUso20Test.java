package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeMoverUnaUnidadQueNoExiste;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieAerea;
import edu.fiuba.algo3.modelo.Mapa.Casilla.SuperficieTerrestre;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.*;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Scout;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.Zealot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso20Test {

    private Mapa elMapa = Mapa.obtener();

    @BeforeEach
    public void setup(){
        elMapa.reiniciarMapa();
    }

    @Test
    public void test01PuedoMoverUnaUnidadAUnaCoordenadaYLaCasillaDeDondeSalioQuedaVacia(){
        Zerling unZerling = new Zerling();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unZerling, coordenadaInicial);
        elMapa.moverUnidad(coordenadaInicial, coordenadaFinal);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertThrows(ErrorNoSePuedeMoverUnaUnidadQueNoExiste.class, () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test02PuedoMoverUnaUnidadAUnaCoordenadaYLuegoPuedoMoverlaDeNuevo(){
        Zerling unZerling = new Zerling();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaIntermedia = new Coordenada(0,1);
        Coordenada coordenadaFinal = new Coordenada(0,2);

        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unZerling, coordenadaInicial);
        elMapa.moverUnidad(coordenadaInicial, coordenadaIntermedia);


        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow(() -> elMapa.moverUnidad(coordenadaIntermedia, coordenadaFinal) );
    }

    @Test
    public void test03PuedoMoverUnZerlingAUnaCoordenadaTerrestre(){
        Zerling unZerling = new Zerling();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unZerling, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test04NoPuedoMoverUnZerlingAUnaCoordenadaAerea(){
        Zerling unZerling = new Zerling();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieTerrestre(), coordenadaInicial);
        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaFinal);
        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unZerling, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertThrows(ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible.class, () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test05PuedoMoverUnZanganoAUnaCoordenadaTerrestre(){
        Zangano unZangano = new Zangano();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unZangano, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test06NoPuedoMoverUnZanganoAUnaCoordenadaAerea(){
        Zangano unZangano = new Zangano();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieTerrestre(), coordenadaInicial);
        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaFinal);
        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unZangano, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertThrows(ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible.class, () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test07PuedoMoverUnHidraliscoAUnaCoordenadaTerrestre(){
        Hidralisco unHidralisco = new Hidralisco();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unHidralisco, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test08NoPuedoMoverUnHidraliscoAUnaCoordenadaAerea(){
        Hidralisco unHidralisco = new Hidralisco();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieTerrestre(), coordenadaInicial);
        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaFinal);
        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unHidralisco, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertThrows(ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible.class, () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test09PuedoMoverUnMutaliscoAUnaCoordenadaTerrestre(){
        Mutalisco unMutalisco = new Mutalisco();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unMutalisco, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test10PuedoMoverUnMutaliscoAUnaCoordenadaAerea(){
        Mutalisco unMutalisco = new Mutalisco();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaFinal);
        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unMutalisco, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test11PuedoMoverUnGuardianAUnaCoordenadaTerrestre(){
        Guardian unGuardian = new Guardian();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unGuardian, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test12PuedoMoverUnGuardianAUnaCoordenadaAerea(){
        Guardian unGuardian = new Guardian();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaFinal);
        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unGuardian, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test13PuedoMoverUnZealotAUnaCoordenadaTerrestre(){
        Zealot unZealot = new Zealot();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unZealot, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test14NoPuedoMoverUnZealotAUnaCoordenadaAerea(){
        Zealot unZealot = new Zealot();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieTerrestre(), coordenadaInicial);
        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaFinal);
        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unZealot, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertThrows(ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible.class, () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test15PuedoMoverUnDragonAUnaCoordenadaTerrestre(){
        Dragon unDragon = new Dragon();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unDragon, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test16NoPuedoMoverUnDragonAUnaCoordenadaAerea(){
        Dragon unDragon = new Dragon();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieTerrestre(), coordenadaInicial);
        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaFinal);
        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unDragon, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertThrows(ErrorNoSePuedeColocarUnidadSobreSuperficieIncompatible.class, () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test17PuedoMoverUnScoutAUnaCoordenadaTerrestre(){
        Scout unScout = new Scout();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unScout, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }

    @Test
    public void test18PuedoMoverUnScoutAUnaCoordenadaAerea(){
        Scout unScout = new Scout();
        Coordenada coordenadaInicial = new Coordenada(0,0);
        Coordenada coordenadaFinal = new Coordenada(0,1);

        elMapa.colocarSuperficie(new SuperficieAerea(), coordenadaFinal);
        //Una unidad puede moverse sobre un material

        elMapa.colocarUnaUnidad(unScout, coordenadaInicial);

        //elMapa movera la unidad que este contenida en coordenadaInicial hacia coordenadaFinal
        assertDoesNotThrow( () -> elMapa.moverUnidad(coordenadaInicial, coordenadaFinal) );
    }
}

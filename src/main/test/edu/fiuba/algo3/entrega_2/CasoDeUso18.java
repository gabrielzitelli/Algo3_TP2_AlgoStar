package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.UnidadesProtoss.Dragon;
import edu.fiuba.algo3.modelo.UnidadesProtoss.Scout;
import edu.fiuba.algo3.modelo.UnidadesProtoss.Zealot;
import edu.fiuba.algo3.modelo.UnidadesZerg.Guardian;
import edu.fiuba.algo3.modelo.UnidadesZerg.Hidralisco;
import edu.fiuba.algo3.modelo.UnidadesZerg.Mutalisco;
import edu.fiuba.algo3.modelo.UnidadesZerg.Zerling;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso18 {

    @Test
    public void test01VerificoQueSiAtacoUnoZerlingConOtroZerlingNueveVecesEsteMuere(){
        Zerling unZerling = new Zerling();
        Zerling ZerlingADaniar = new Zerling();

        Coordenada unaCoordenada = new Coordenada(0,0);
        Coordenada otraCoordenada = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarUnaUnidad(unZerling, unaCoordenada);
        elMapa.colocarUnaUnidad(ZerlingADaniar, otraCoordenada);

        // Dejo al zerling a un ataque mas del otro zerling
        for (int i = 0; i < 8; i++) {
            elMapa.atacar(unaCoordenada, otraCoordenada);
        }

        assertThrows(ErrorVidaLlegoACero.class,() -> elMapa.atacar(unaCoordenada, otraCoordenada));
    }

    @Test
    public void test02VerificoQueSiAtacoUnZerlingConUnHidraliscoCuatroVecesEsteMuere(){
        Zerling unZerling = new Zerling();
        Hidralisco unHidralisco = new Hidralisco();

        Coordenada unaCoordenada = new Coordenada(0,0);
        Coordenada otraCoordenada = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarUnaUnidad(unZerling, otraCoordenada);
        elMapa.colocarUnaUnidad(unHidralisco, unaCoordenada);

        // Dejo al zerling a un ataque mas del hidralisco
        for (int i = 0; i < 3; i++) {
            elMapa.atacar(unaCoordenada, otraCoordenada);
        }

        assertThrows(ErrorVidaLlegoACero.class,() -> elMapa.atacar(unaCoordenada, otraCoordenada));
    }

    @Test
    public void test03VerificoQueSiAtacoUnZerlingConUnMutaliscoCuatroVecesEsteMuere(){
        Zerling unZerling = new Zerling();
        Mutalisco unMutalisco = new Mutalisco();

        Coordenada unaCoordenada = new Coordenada(0,0);
        Coordenada otraCoordenada = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarUnaUnidad(unZerling, otraCoordenada);
        elMapa.colocarUnaUnidad(unMutalisco, unaCoordenada);

        // Dejo al zerling a un ataque mas del mutalisco
        for (int i = 0; i < 3; i++) {
            elMapa.atacar(unaCoordenada, otraCoordenada);
        }

        assertThrows(ErrorVidaLlegoACero.class,() -> elMapa.atacar(unaCoordenada, otraCoordenada));
    }

    @Test
    public void test04VerificoQueSiAtacoUnZerlingConUnGuardianDosVecesEsteMuere(){
        Zerling unZerling = new Zerling();
        Guardian unGuardian = new Guardian();

        Coordenada unaCoordenada = new Coordenada(0,0);
        Coordenada otraCoordenada = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarUnaUnidad(unZerling, otraCoordenada);
        elMapa.colocarUnaUnidad(unGuardian, unaCoordenada);

        // Dejo al zerling a un ataque mas del guardian
        elMapa.atacar(unaCoordenada, otraCoordenada);

        assertThrows(ErrorVidaLlegoACero.class,() -> elMapa.atacar(unaCoordenada, otraCoordenada));
    }

    @Test
    public void test05VerificoQueSiAtacoUnZerlingConUnZealotCincoVecesEsteMuere(){
        Zerling unZerling = new Zerling();
        Zealot unZealot = new Zealot();

        Coordenada unaCoordenada = new Coordenada(0,0);
        Coordenada otraCoordenada = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarUnaUnidad(unZerling, otraCoordenada);
        elMapa.colocarUnaUnidad(unZealot, unaCoordenada);

        // Dejo al zerling a un ataque mas del Zealot
        for (int i = 0; i < 4; i++) {
            elMapa.atacar(unaCoordenada, otraCoordenada);
        }

        assertThrows(ErrorVidaLlegoACero.class,() -> elMapa.atacar(unaCoordenada, otraCoordenada));
    }

    @Test
    public void test06VerificoQueSiAtacoUnZerlingConUnDragonDosVecesEsteMuere(){
        Zerling unZerling = new Zerling();
        Dragon unDragon = new Dragon();

        Coordenada unaCoordenada = new Coordenada(0,0);
        Coordenada otraCoordenada = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarUnaUnidad(unZerling, otraCoordenada);
        elMapa.colocarUnaUnidad(unDragon, unaCoordenada);

        // Dejo al zerling a un ataque mas del Dragon
        elMapa.atacar(unaCoordenada, otraCoordenada);


        assertThrows(ErrorVidaLlegoACero.class,() -> elMapa.atacar(unaCoordenada, otraCoordenada));
    }

    @Test
    public void test07VerificoQueSiAtacoUnZerlingConUnScoutCincoVecesEsteMuere(){
        Zerling unZerling = new Zerling();
        Scout unScout = new Scout();

        Coordenada unaCoordenada = new Coordenada(0,0);
        Coordenada otraCoordenada = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarUnaUnidad(unZerling, otraCoordenada);
        elMapa.colocarUnaUnidad(unScout, unaCoordenada);

        // Dejo al zerling a un ataque mas del Scout
        for (int i = 0; i < 4; i++) {
            elMapa.atacar(unaCoordenada, otraCoordenada);
        }

        assertThrows(ErrorVidaLlegoACero.class,() -> elMapa.atacar(unaCoordenada, otraCoordenada));
    }
}

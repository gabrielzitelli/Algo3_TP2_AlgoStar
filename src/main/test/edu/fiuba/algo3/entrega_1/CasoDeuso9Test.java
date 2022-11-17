package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Mapa.*;
import edu.fiuba.algo3.modelo.Ataque.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CasoDeuso9Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01UnEdificioSigueEnergizadoSiDosPilonesLeDanEnergia(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaAcceso = new Coordenada(0,2);
        Coordenada coordenadaPilon1 = new Coordenada(0,0);
        Coordenada coordenadaPilon2 = new Coordenada(0,4);
        Acceso unAcceso = new Acceso();
        Pilon pilon1 = new Pilon();
        Pilon pilon2 = new Pilon();

        elMapa.construirEdificio(pilon1, coordenadaPilon1);
        elMapa.construirEdificio(pilon2, coordenadaPilon2);

        for (int i = 0; i < 5; i++) {
            pilon1.pasarTurno();
            pilon2.pasarTurno();
        }

        elMapa.construirEdificio(unAcceso, coordenadaAcceso);

        for (int i = 0; i < 8; i++)
            unAcceso.pasarTurno();

        assertDoesNotThrow(() -> unAcceso.crearFabricaDragon());
    }

    @Test
    public void test02UnEdificioSigueEnergizadoSiSeDestruyeUnoDeLosDosPilonesQueLeDanEnergia(){
        Mapa elMapa = Mapa.obtener();
        Coordenada coordenadaAcceso = new Coordenada(0,2);
        Coordenada coordenadaPilon1 = new Coordenada(0,0);
        Coordenada coordenadaPilon2 = new Coordenada(0,4);
        Acceso unAcceso = new Acceso();
        Pilon pilon1 = new Pilon();
        Pilon pilon2 = new Pilon();
        Ataque unAtaque = new Ataque( new Danio(600) );

        elMapa.construirEdificio(pilon1, coordenadaPilon1);
        elMapa.construirEdificio(pilon2, coordenadaPilon2);

        for (int i = 0; i < 5; i++) {
            pilon1.pasarTurno();
            pilon2.pasarTurno();
        }

        elMapa.construirEdificio(unAcceso, coordenadaAcceso);

        for (int i = 0; i < 8; i++)
            unAcceso.pasarTurno();

        pilon2.aplicarAtaque(unAtaque);

        unAcceso.pasarTurno();
        pilon1.pasarTurno();
        pilon2.pasarTurno();

        assertDoesNotThrow(() -> unAcceso.crearFabricaDragon());
    }
}

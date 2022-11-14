package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.EdificioZerg.Criadero;
import edu.fiuba.algo3.modelo.EdificioZerg.ReservaDeReproduccion;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.danioYAtaque.Ataque;
import edu.fiuba.algo3.modelo.danioYAtaque.DanioBasico;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CasoDeUso13Test {

    @Test
    public void test01SeDestruyeUnCriaderoYsePuedeSerguirConstruyendoSobreElMohoQueDejo(){
        // En esta prueba genero un criadero para poner moho sobre una casilla y luego
        // construir una reserva de produccion
        Coordenada coordenadaCriadero = new Coordenada(0,1);
        Criadero criadero = new Criadero();
        Ataque unAtaque = new Ataque( new DanioBasico(500) );

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.construirEdificio(criadero, coordenadaCriadero);

        for (int i = 0; i < 5; i++)
            criadero.pasarTurno();

        criadero.aplicarAtaque(unAtaque);

        assertDoesNotThrow(() -> elMapa.construirEdificio(new ReservaDeReproduccion(), coordenadaCriadero));
    }
}

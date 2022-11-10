package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Coordenadas;
import edu.fiuba.algo3.modelo.Guarida;
import edu.fiuba.algo3.modelo.NodoCompatible;
import edu.fiuba.algo3.modelo.Tablero.Moho;
import edu.fiuba.algo3.modelo.Tablero.SinRecurso;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CasoDeUso13Test {
    @Test
    public void test01SeDestruyeUnCriaderoYSePuedeConstruirEnElMohoQueDejo() {
        Tablero tablero = new Tablero(20,20);
        Coordenadas coordenadasCriadero = new Coordenadas(5,5);
        tablero.establecerTerreno(new Moho(), coordenadasCriadero);
        //imitar Comportamiento en caso de actualizar
        tablero.actualizarTerreno(coordenadasCriadero, 5, new Moho());

        Guarida guarida = new Guarida(new NodoCompatible(new Moho(), new SinRecurso()));

        assertDoesNotThrow(() -> tablero.construir(guarida, new Coordenadas(10, 5)));
    }

}

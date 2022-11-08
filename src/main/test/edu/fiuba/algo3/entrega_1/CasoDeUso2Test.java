package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.excepciones.EdificioEnConstruccion;
import org.junit.jupiter.api.Test;

public class CasoDeUso2Test {
    @Test
    public void test01IntentoConstruirUnCriaderoYSoloPuedoUsarloDespuesDeQueEsteConstruido() {

        Zergs imperio = new Zergs();
        Tablero tablero = new Tablero(5, 5);
        Jugador jugador = new Jugador(imperio, tablero);

        imperio.construirCriadero(new Zangano(new Coordenadas(2,3)));
        //Intento utilizarlo y no puedo
        try {
            //criadero.criarZangano();
        }
        catch (EdificioEnConstruccion edificioEnConstruccion){}



    }
}

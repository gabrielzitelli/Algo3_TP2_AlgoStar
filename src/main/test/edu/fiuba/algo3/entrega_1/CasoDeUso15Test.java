package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Coordenadas;
import edu.fiuba.algo3.modelo.Edificio;
import edu.fiuba.algo3.modelo.Protoss;
import edu.fiuba.algo3.modelo.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Neutro;
import edu.fiuba.algo3.modelo.Tablero.NodoMineral;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.VolcanGasVespeno;
import edu.fiuba.algo3.modelo.excepciones.CriaderoSinLarvas;
import edu.fiuba.algo3.modelo.excepciones.RecursosAgotados;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso15Test {

    @Test
    public void Test1SeConstruyeUnNexoMineralSobreUnNodoMineralEIntentoRecolectarLuegoDeGastarlo(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new NodoMineral(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(50), new Recurso(0));
        Edificio nexoMineral = protoss.construirNexoMineral(new Coordenadas(0, 0));
        nexoMineral.accionDeTurno();
        nexoMineral.accionDeTurno();
        nexoMineral.accionDeTurno();
        nexoMineral.accionDeTurno();
        for ( int i = 1; i <= 200 ; i++){
            nexoMineral.accionDeTurno();
        }

        assertThrows(RecursosAgotados.class, () -> nexoMineral.accionDeTurno());

    }

    @Test
    public void Test2SeConstruyeUnAsimiladorSobreVolcanGastoElGeiserEIntentoRecolectarDespuesDeGastarlo(){
        Tablero tablero = new Tablero(1, 1);
        tablero.establecerRecurso(new VolcanGasVespeno(), new Coordenadas(0,0));
        tablero.establecerTerreno(new Neutro(), new Coordenadas(0,0));

        Protoss protoss = new Protoss(tablero ,new Recurso(100), new Recurso(0));
        Edificio asimilador = protoss.construirAsimilador(new Coordenadas(0,0));

        asimilador.accionDeTurno();
        asimilador.accionDeTurno();
        asimilador.accionDeTurno();
        asimilador.accionDeTurno();
        asimilador.accionDeTurno();
        asimilador.accionDeTurno();
        for ( int i = 1; i <= 250 ; i++){
            asimilador.accionDeTurno();
        }

        assertThrows(RecursosAgotados.class, () -> asimilador.accionDeTurno());

    }
}

package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Mapa.Casilla.GasRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Casilla.MineralRecolectable;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.Test;

public class CasoDeUso15Test {
    @Test
    public void test01NoSePuedeRecolectarGasDespuesDeAgotarTodoElRecursoDelVolcan() {
        Protoss protoss = new Protoss();
        protoss.abastecerDeRecursos(new Recurso(100), new Recurso(0));
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadasGas = new Coordenada(0,0);

        mapa.colocarMaterial(new GasRecolectable(), coordenadasGas);
        protoss.construirAsimilador(coordenadasGas);
        //Construyo el asimilador
        for (int i = 0; i < 5; i++){
            protoss.terminarTurno();
        }
        //  Saco completamente el material
        for (int i = 0; i < 250; i++){
            protoss.terminarTurno();
        }
        //Verifico haber sacado la totalidad
        assert(protoss.tienesEstaCantidadDeGas(5000));

        //Intento minar otra vez y no obtengo nada
        protoss.terminarTurno();
        assert(protoss.tienesEstaCantidadDeGas(5000));
    }

    @Test
    public void test02NoSePuedeRecolectarMineralDespuesDeAgotarTodoElRecursoDeUnNodoMineral(){
        Protoss protoss = new Protoss();
        protoss.abastecerDeRecursos(new Recurso(50), new Recurso(0));
        Mapa mapa = Mapa.obtener();
        mapa.reiniciarMapa();
        Coordenada coordenadaMineral = new Coordenada(0,0);

        mapa.colocarMaterial(new MineralRecolectable(), coordenadaMineral);
        protoss.construirNexoMineral(coordenadaMineral);

        //Construyo el nexo
        for (int i = 0; i < 3; i++){
            protoss.terminarTurno();
        }
        //Gasto el mineral
        for (int i = 0; i < 200; i++) {
            protoss.terminarTurno();
        }
        assert(protoss.tienesEstaCantidadDeMineral(2000));

        //Pasa otro turno y no obteno mas
        protoss.terminarTurno();
        assert (protoss.tienesEstaCantidadDeMineral(2000));
    }
}

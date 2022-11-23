package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.AlgoStar.AlgoStar;
import edu.fiuba.algo3.modelo.Imperio.Imperio;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import org.junit.jupiter.api.Test;

public class CasoDeUso32Test {

    @Test
    public void test01PuedoEmpezarUnJuegoYterminarlo(){
        /*
        * Empiezo una partida a traves de algo star, luego teletransporto un dragon
        * hacia la base Zerg y la destruyo
        */
        AlgoStar algoStar = new AlgoStar();
        Imperio protoss = new Protoss();
        Imperio zergs = new Zerg();

        algoStar.asignarJugador("lalala01", "rojo", protoss);
        algoStar.asignarJugador("XZangano", "azul", zergs);

        


    }
}

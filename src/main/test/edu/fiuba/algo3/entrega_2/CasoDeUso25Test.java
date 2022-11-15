package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.AlgoStar.AlgoStar;
import edu.fiuba.algo3.modelo.Excepciones.*;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso25Test {
    @Test
    public void test01PuedoAsignarDosJugadoresPeroNoTres(){
        AlgoStar algoStar = new AlgoStar();
        algoStar.asignarJugador("miguel", "rojo", new Zerg());
        algoStar.asignarJugador("marcos", "azul", new Protoss());

        assertThrows(ErrorSoloPuedenJugarDosPersonasAlMismoTiempo.class, () ->
                algoStar.asignarJugador("sofia16", "verde", new Zerg()));
    }
    @Test
    public void test02NoPuedoAsignarDosJugadoresConElMismoNombre(){
        AlgoStar algoStar = new AlgoStar();
        algoStar.asignarJugador("miguel", "rojo", new Zerg());

        assertThrows(ErrorDosJugadoresNoPuedenTenerElMismoNombre.class, () ->
                algoStar.asignarJugador("miguel", "verde", new Protoss()));
    }
    @Test
    public void test03NoPuedoAsignarDosJugadoresConElMismoColor(){
        AlgoStar algoStar = new AlgoStar();
        algoStar.asignarJugador("miguel", "rojo", new Zerg());

        assertThrows(ErrorDosJugadoresNoPuedenTenerElMismoColor.class, () ->
                algoStar.asignarJugador("sofia69", "rojo", new Protoss()));
    }
    @Test
    public void test04NoPuedoAsignarDosJugadoresConElMismoImperio(){
        AlgoStar algoStar = new AlgoStar();
        algoStar.asignarJugador("miguel", "rojo", new Protoss());

        assertThrows(ErrorDosJugadoresNoPuedenTenerElMismoImperio.class, () ->
                algoStar.asignarJugador("sofia69", "verde", new Protoss()));
    }
    @Test
    public void test05NoPuedoAsignarUnJugadorConUnNombreDeMenosDe6Caracteres() {
        AlgoStar algoStar = new AlgoStar();

        assertThrows(ErrorELNombreDelJugadorDebeSerMayorA6Caracteres.class ,
                () -> algoStar.asignarJugador("aia", "purpura", new Protoss()));
    }
}

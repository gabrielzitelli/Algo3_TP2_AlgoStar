package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CasoDeUso1Test {

    @Test
    public void test01ConsumoUnaLarvaYLuegoVuelvoATenerLasTres(){
        Criadero unCriadero = new Criadero();

        ArrayList<Fabrica> unidadesDisponibles = new ArrayList<>();
        unidadesDisponibles.add(new FabricaZangano());
        unCriadero.asignarListaDeUnidades(unidadesDisponibles);

        //Construyo el edificio
        for(int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        // Consumo una larva
        unCriadero.crearUnidad(new FabricaZangano());

        // Paso un turno para tener 3 larvas de nuevo
        unCriadero.pasarTurno();

        // Consumo dos larvas
        unCriadero.crearUnidad(new FabricaZangano());
        unCriadero.crearUnidad(new FabricaZangano());

        // Al consumir la tercera larva no me tira error
        assertDoesNotThrow( () -> unCriadero.crearUnidad(new FabricaZangano()));
    }

    @Test
    public void test02ConsumoDosLarvasYLuegoVuelvoATenerLasTres(){
        Criadero unCriadero = new Criadero();

        ArrayList<Fabrica> unidadesDisponibles = new ArrayList<>();
        unidadesDisponibles.add(new FabricaZangano());
        unCriadero.asignarListaDeUnidades(unidadesDisponibles);

        //Construyo el edificio
        for(int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        // Consumo dos larvas
        unCriadero.crearUnidad(new FabricaZangano());
        unCriadero.crearUnidad(new FabricaZangano());

        // Paso dos turnos para tener 3 larvas de nuevo
        unCriadero.pasarTurno();
        unCriadero.pasarTurno();

        // Consumo dos larvas
        unCriadero.crearUnidad(new FabricaZangano());
        unCriadero.crearUnidad(new FabricaZangano());

        // Al consumir la tercera larva no me tira error
        assertDoesNotThrow( () -> unCriadero.crearUnidad(new FabricaZangano()));

    }

    @Test
    public void test03ConsumoTresLarvasYLuegoVuelvoATenerLasTres(){
        Criadero unCriadero = new Criadero();

        ArrayList<Fabrica> unidadesDisponibles = new ArrayList<>();
        unidadesDisponibles.add(new FabricaZangano());
        unCriadero.asignarListaDeUnidades(unidadesDisponibles);

        //Construyo el edificio
        for(int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        // Consumo tres larvas
        unCriadero.crearUnidad(new FabricaZangano());
        unCriadero.crearUnidad(new FabricaZangano());
        unCriadero.crearUnidad(new FabricaZangano());

        // Paso tres turnos para tener 3 larvas de nuevo
        unCriadero.pasarTurno();
        unCriadero.pasarTurno();
        unCriadero.pasarTurno();

        // Consumo dos larvas
        unCriadero.crearUnidad(new FabricaZangano());
        unCriadero.crearUnidad(new FabricaZangano());

        // Al consumir la tercera larva no me tira error
        assertDoesNotThrow( () -> unCriadero.crearUnidad(new FabricaZangano()));

    }
}

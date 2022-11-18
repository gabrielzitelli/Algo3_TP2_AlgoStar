package edu.fiuba.algo3.testDeClases.edificiosTests;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoEstaConstruido;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosRequisitosDeEstaUnidad;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EspiralTest {

    @Test
    public void test01UnEspiralNoEstaConstruidoEn9Turnos() {
        ArrayList<Fabrica> unidadesDisponibles = new ArrayList<>();

        Espiral unEspiral = new Espiral();
        unEspiral.asignarListaDeUnidades(unidadesDisponibles);

        for (int i = 0; i < 9; i++)
            unEspiral.pasarTurno();

        // Construyo criadero
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(unidadesDisponibles);
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> unCriadero.crearUnidad(new FabricaMutalisco()));
    }

    @Test
    public void test02UnEspiralEstaConstruidoEn10Turnos() {
        ArrayList<Fabrica> unidadesDisponibles = new ArrayList<>();

        Espiral unEspiral = new Espiral();
        unEspiral.asignarListaDeUnidades(unidadesDisponibles);

        for (int i = 0; i < 10; i++)
            unEspiral.pasarTurno();

        // Construyo criadero
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(unidadesDisponibles);
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertDoesNotThrow(() -> unCriadero.crearUnidad(new FabricaMutalisco()));
    }
}

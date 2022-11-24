package edu.fiuba.algo3.testDeClases.edificiosTests;

import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.Ataque.Danio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Edificios.FabricasDisponibles;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosRequisitosDeEstaUnidad;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EspiralTest {

    @Test
    public void test01UnEspiralNoEstaConstruidoEn9Turnos() {
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();
        Espiral unEspiral = new Espiral();
        unEspiral.asignarListaDeUnidades(fabricasDisponibles);

        for (int i = 0; i < 9; i++)
            unEspiral.pasarTurno();

        // Construyo criadero
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarSuministro(new Suministro(4));
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> unCriadero.crearUnidad(new FabricaMutalisco()));
    }

    @Test
    public void test02UnEspiralEstaConstruidoEn10Turnos() {
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();
        Espiral unEspiral = new Espiral();
        unEspiral.asignarListaDeUnidades(fabricasDisponibles);

        for (int i = 0; i < 10; i++)
            unEspiral.pasarTurno();

        // Construyo criadero
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarRecursos(new Mineral(1000), new Gas(1000));
        unCriadero.asignarSuministro(new Suministro(4));
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertDoesNotThrow(() -> unCriadero.crearUnidad(new FabricaMutalisco()));
    }

    @Test
    public void test03SiSeDestruyeUnEspiralNoSePuedeCrearLaUnidadQueHabilita() {
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();

        // Construyo criadero
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarSuministro(new Suministro(4));
        elMapa.construirEdificio(unCriadero, new Coordenada(0,0));
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        // Construyo espiral
        Espiral unEspiral = new Espiral();
        elMapa.construirEdificio(unEspiral, new Coordenada(1,0));
        unEspiral.asignarListaDeUnidades(fabricasDisponibles);
        for (int i = 0; i < 10; i++)
            unEspiral.pasarTurno();

        unEspiral.recibirAtaque(new Ataque(new Danio(1300)));

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> unCriadero.crearUnidad(new FabricaMutalisco()));
    }
}

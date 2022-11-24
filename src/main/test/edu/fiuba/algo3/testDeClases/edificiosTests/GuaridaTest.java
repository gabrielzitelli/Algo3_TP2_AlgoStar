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

public class GuaridaTest {

    @Test
    public void test01UnaGuaridaNoSeConstruyeEn11Turnos(){
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();
        Guarida unaGuarida = new Guarida();
        unaGuarida.asignarListaDeUnidades(fabricasDisponibles);

        for(int i = 0; i < 11; i++)
            unaGuarida.pasarTurno();

        // Construyo criadero
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarSuministro(new Suministro(2));
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> unCriadero.crearUnidad(new FabricaHidralisco()));
    }

    @Test
    public void test02GuaridaSePuedeConstruirEn12Turnos(){
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();
        Guarida unaGuarida = new Guarida();
        unaGuarida.asignarListaDeUnidades(fabricasDisponibles);

        for(int i = 0; i < 12; i++)
            unaGuarida.pasarTurno();

        // Construyo criadero
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarRecursos(new Mineral(1000), new Gas(1000));
        unCriadero.asignarSuministro(new Suministro(2));
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        assertDoesNotThrow(() -> unCriadero.crearUnidad(new FabricaHidralisco()));
    }

    @Test
    public void test03SiSeDestruyeUnaGuaridaNoSePuedeCrearLaUnidadQueHabilita() {
        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();
        FabricasDisponibles fabricasDisponibles = new FabricasDisponibles();

        // Construyo criadero
        Criadero unCriadero = new Criadero();
        unCriadero.asignarListaDeUnidades(fabricasDisponibles);
        unCriadero.asignarSuministro(new Suministro(2));
        elMapa.construirEdificio(unCriadero, new Coordenada(0,0));
        for (int i = 0; i < 4; i++)
            unCriadero.pasarTurno();

        // Construyo guarida
        Guarida unaGuarida = new Guarida();
        elMapa.construirEdificio(unaGuarida, new Coordenada(1,0));
        unaGuarida.asignarListaDeUnidades(fabricasDisponibles);
        for (int i = 0; i < 12; i++)
            unaGuarida.pasarTurno();

        unaGuarida.recibirAtaque(new Ataque(new Danio(1250)));

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> unCriadero.crearUnidad(new FabricaHidralisco()));
    }
}

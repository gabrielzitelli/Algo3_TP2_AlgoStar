package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaAcceso;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaCriadero;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.FabricaGuarida;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosRequisitosDeEstaUnidad;
import edu.fiuba.algo3.modelo.Imperio.*;
import edu.fiuba.algo3.modelo.Mapa.*;
import edu.fiuba.algo3.modelo.Unidades.UnidadesProtoss.*;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CasoDeUso22Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01TengoCriaderoYPuedoConstruirUnZangano() {
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(225), new Gas(0));
        imperioZerg.construirEdificio(new FabricaCriadero(), new Coordenada(0,0));

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));

        assertDoesNotThrow(() -> criadero.crearUnidad(new FabricaZangano()));

        //pasa un turno y tengo un zangano
        imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tieneUnidad(new Zangano()));
    }

    @Test
    public void test02SiNoTengoReservaDeReproduccionNoPuedoConstruirUnZerlign() {
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(200), new Gas(0));
        imperioZerg.construirCriadero(new Coordenada(0,0));

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> criadero.crearUnidad(new FabricaZerling()));
    }

    @Test
    public void test03SiTengoReservaDeReproduccionPuedoConstruirUnZerlign() {
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(1000), new Gas(0));
        imperioZerg.construirCriadero(new Coordenada(0,0));

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirReservaDeReproduccion(new Coordenada(1,0));

        //esperamos a que se construya la reserva
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));

        assertDoesNotThrow(() -> criadero.crearUnidad(new FabricaZerling()));

        //No pasan los turnos, aun no tengo al Zerlign
        assertFalse(imperioZerg.tieneUnidad(new Zerling()));

        //Pasan dos turnos y lo tenemos
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();
        imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tieneUnidad(new Zerling()));
    }

    @Test
    public void test04SiNoTengoGuaridaNoPuedoConstruirUnHidralisco() {
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(5000), new Gas(1000));
        imperioZerg.construirCriadero(new Coordenada(0,0));

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> criadero.crearUnidad(new FabricaHidralisco()));
    }

    @Test
    public void test05SiTengoGuaridaPuedoConstruirUnHidralisco() {
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(5000), new Gas(1000));
        imperioZerg.construirCriadero(new Coordenada(0,0));

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirReservaDeReproduccion(new Coordenada(1,0));

        //esperamos a que se construya la reserva
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirEdificio(new FabricaGuarida(), new Coordenada(0, 1));

        //Esperamos a que se construya la guarida
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));

        assertDoesNotThrow(() -> criadero.crearUnidad(new FabricaHidralisco()));
        //No pasan los turnos, aun no tengo al Hidralisco
        assertFalse(imperioZerg.tieneUnidad(new Hidralisco()));

        //Pasan los turnos y lo tenemos
        for (int i = 0; i < 4; i++)
            imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tieneUnidad(new Hidralisco()));
    }

    @Test
    public void test06SiNoTengoEspiralNoPuedoConstruirUnMutalisco() {
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(5000), new Gas(1000));
        imperioZerg.construirCriadero(new Coordenada(0,0));

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> criadero.crearUnidad(new FabricaMutalisco()));
    }

    @Test
    public void test07SiTengoEspiralPuedoConstruirUnHidralisco() {
        Zerg imperioZerg = new Zerg();

        imperioZerg.abastecerDeRecursos(new Mineral(5000), new Gas(1000));
        imperioZerg.construirCriadero(new Coordenada(0,0));

        //Esperamos a que se construya el criadero
        for(int i = 0; i < 5; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirReservaDeReproduccion(new Coordenada(1,0));

        //esperamos a que se construya la reserva
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirGuarida(new Coordenada(0,1));

        //Esperamos a que se construya la guarida
        for (int i = 0; i < 12; i++)
            imperioZerg.terminarTurno();

        imperioZerg.construirEspiral(new Coordenada(1,1));

        //Espero a que se construya el espiral
        for (int i = 0; i < 10; i++)
            imperioZerg.terminarTurno();

        //obtenemos el edificio
        Edificio criadero = imperioZerg.conseguirEdificio(new Coordenada(0,0));

        assertDoesNotThrow(() -> criadero.crearUnidad(new FabricaMutalisco()));
        //No pasan los turnos, aun no tengo al Hidralisco
        assertFalse(imperioZerg.tieneUnidad(new Mutalisco()));

        //Pasan los turnos y lo tenemos
        for (int i = 0; i < 7; i++)
            imperioZerg.terminarTurno();

        assertTrue(imperioZerg.tieneUnidad(new Mutalisco()));
    }

    @Test
    public void test08TengoAccesoYPuedoConstruirUnZealor() {
        Protoss imperioProtoss = new Protoss();

        imperioProtoss.abastecerDeRecursos();
        imperioProtoss.construirPilon(new Coordenada(0,0));

        //construimos el pilon
        for (int i = 0; i < 5; i++)
            imperioProtoss.terminarTurno();

        imperioProtoss.construirEdificio(new FabricaAcceso(), new Coordenada( 1, 0));

        //construimos el Acceso
        for (int i = 0; i < 8; i++)
            imperioProtoss.terminarTurno();

        Edificio acceso = imperioProtoss.conseguirEdificio(new Coordenada(1,0));

        assertDoesNotThrow(() -> acceso.crearUnidad(new FabricaZealot()));
        //No pasan los turnos, aun no tengo al Zealot
        assertFalse(imperioProtoss.tieneUnidad(new Zealot()));

        //Pasan los turnos y lo tenemos
        for (int i = 0; i < 4; i++)
            imperioProtoss.terminarTurno();

        assertTrue(imperioProtoss.tieneUnidad(new Zealot()));
    }

    @Test
    public void test09TengoAccesoYPuedoConstruirUnDragon() {
        Protoss imperioProtoss = new Protoss();

        imperioProtoss.abastecerDeRecursos();
        imperioProtoss.construirPilon(new Coordenada(0,0));

        //construimos el pilon
        for (int i = 0; i < 5; i++)
            imperioProtoss.terminarTurno();

        imperioProtoss.construirEdificio(new FabricaAcceso(), new Coordenada( 1, 0));

        //construimos el Acceso
        for (int i = 0; i < 8; i++)
            imperioProtoss.terminarTurno();

        Edificio acceso = imperioProtoss.conseguirEdificio(new Coordenada(1,0));

        assertDoesNotThrow(() -> acceso.crearUnidad(new FabricaDragon()));
        //No pasan los turnos, aun no tengo al Zealot
        assertFalse(imperioProtoss.tieneUnidad(new Dragon()));

        //Pasan los turnos y lo tenemos
        for (int i = 0; i < 6; i++)
            imperioProtoss.terminarTurno();

        assertTrue(imperioProtoss.tieneUnidad(new Dragon()));
    }

    @Test
    public void test10NoTengoPuertoEstelarYNoPuedoConstruirUnScout() {
        Protoss imperioProtoss = new Protoss();

        imperioProtoss.abastecerDeRecursos();
        imperioProtoss.construirPilon(new Coordenada(0,0));

        //construimos el pilon
        for (int i = 0; i < 5; i++)
            imperioProtoss.terminarTurno();

        imperioProtoss.construirEdificio(new FabricaAcceso(), new Coordenada( 1, 0));

        //construimos el Acceso
        for (int i = 0; i < 8; i++)
            imperioProtoss.terminarTurno();

        Edificio acceso = imperioProtoss.conseguirEdificio(new Coordenada(1,0));

        assertThrows(ErrorNoSeCumplenLosRequisitosDeEstaUnidad.class,
                () -> acceso.crearUnidad((new FabricaScout())));
    }

    @Test
    public void test11TengoPuertoEstelarYPuedoConstruirUnScout() {
        Protoss imperioProtoss = new Protoss();

        imperioProtoss.abastecerDeRecursos();
        imperioProtoss.construirPilon(new Coordenada(0,0));

        //construimos el pilon
        for (int i = 0; i < 5; i++)
            imperioProtoss.terminarTurno();

        imperioProtoss.construirEdificio(new FabricaAcceso(), new Coordenada( 1, 0));

        //construimos el Acceso
        for (int i = 0; i < 8; i++)
            imperioProtoss.terminarTurno();

        imperioProtoss.construirPuertoEstelar(new Coordenada(0,1));

        //construimos el puerto estelar
        for (int i = 0; i < 10; i++)
            imperioProtoss.terminarTurno();

        Edificio acceso = imperioProtoss.conseguirEdificio(new Coordenada(1,0));

        assertDoesNotThrow(() -> acceso.crearUnidad(new FabricaScout()));
        //No pasan los turnos, aun no tengo al Zealot
        assertFalse(imperioProtoss.tieneUnidad(new Scout()));

        //Pasan los turnos y lo tenemos
        for (int i = 0; i < 9; i++)
            imperioProtoss.terminarTurno();

        assertTrue(imperioProtoss.tieneUnidad(new Scout()));
    }
}

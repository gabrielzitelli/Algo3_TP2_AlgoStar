package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.excepciones.CriaderoSinLarvas;
import edu.fiuba.algo3.modelo.excepciones.EdificioNoEnergizado;
import edu.fiuba.algo3.modelo.excepciones.TerrenoNoCompatibleConEdificio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso9Test {

    @Test
    public void test01TengoUnEdificioEnergizadoPorDosPilonesyRompoUnoYSigueFuncionando(){
        Tablero tablero = new Tablero(9, 9);
        Coordenadas unaCoordenada = new Coordenadas(0, 0);
        Coordenadas otraCoordenada = new Coordenadas(5, 0);
        Coordenadas coordenadaEnergizadaPorAmbos = new Coordenadas(2, 0);

        tablero.establecerRecurso(new SinRecurso(), unaCoordenada);
        tablero.establecerTerreno(new Neutro(), unaCoordenada);
        tablero.establecerRecurso(new SinRecurso(), otraCoordenada);
        tablero.establecerTerreno(new Neutro(), otraCoordenada);

        Protoss protoss = new Protoss(tablero, new Recurso(1000), new Recurso(0));

        Edificio unPilon = protoss.construirPilon(unaCoordenada);
        Edificio otroPilon = protoss.construirPilon(otraCoordenada);

        unPilon.accionDeTurno();
        otroPilon.accionDeTurno();

        Edificio unAcceso = protoss.construirAcceso(coordenadaEnergizadaPorAmbos);

        unPilon.recibirDanio(1000);
        otroPilon.accionDeTurno();

        assertDoesNotThrow(() -> unAcceso.accionDeTurno());
    }

    @Test
    public void test02TengoUnEdificioEnergizadoPorDosPilonesySacoLosDosYDejaDeFuncionar() {
        Tablero tablero = new Tablero(9, 9);
        Coordenadas unaCoordenada = new Coordenadas(0, 0);
        Coordenadas otraCoordenada = new Coordenadas(5, 0);
        Coordenadas coordenadaEnergizadaPorAmbos = new Coordenadas(2, 0);

        Protoss protoss = new Protoss(tablero, new Recurso(1000), new Recurso(0));

        Edificio unPilon = protoss.construirPilon(unaCoordenada);
        Edificio otroPilon = protoss.construirPilon(otraCoordenada);

        unPilon.accionDeTurno();
        otroPilon.accionDeTurno();

        Edificio unAcceso = protoss.construirAcceso(coordenadaEnergizadaPorAmbos);

        unPilon.recibirDanio(1000);
        otroPilon.recibirDanio(1000);

        assertThrows(EdificioNoEnergizado.class, () -> unAcceso.accionDeTurno());
    }
}

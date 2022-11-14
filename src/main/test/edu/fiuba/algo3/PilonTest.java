package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.EdificioProtoss.Acceso;
import edu.fiuba.algo3.modelo.EdificioProtoss.Pilon;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PilonTest{
    @Test
    public void test01PuedoCrearUnPilon(){
        Pilon unPilon = new Pilon();

        assertNotNull(unPilon);
    }
    @Test
    public void test02UnPilonNoSeConstruyeEn4TurnosPorLoTantoNoPuedoConstruirNadaAlrededor(){
        Mapa elmapa = Mapa.obtener();
        elmapa.reiniciarMapa();
        Coordenada coordenadasPilon = new Coordenada(2,2);
        Pilon unpilon = new Pilon();
        elmapa.construirEdificio(unpilon, coordenadasPilon);

        Acceso unAcceso = new Acceso();
        Coordenada coordenadaAcceso = new Coordenada(2,3);

        unpilon.pasarTurno();
        unpilon.pasarTurno();
        unpilon.pasarTurno();
        unpilon.pasarTurno();
        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class, () -> elmapa.construirEdificio(unAcceso, coordenadaAcceso));
    }
    @Test
    public void test03UnPilonSiSeConstruyeEn5TurnosPorLoTantoPuedoConstruirAlrededor(){
        Mapa elmapa = Mapa.obtener();
        elmapa.reiniciarMapa();
        Coordenada coordenadasPilon = new Coordenada(2,2);
        Pilon unpilon = new Pilon();
        elmapa.construirEdificio(unpilon, coordenadasPilon);

        Acceso unAcceso = new Acceso();
        Coordenada coordenadaAcceso = new Coordenada(2,3);

        unpilon.pasarTurno();
        unpilon.pasarTurno();
        unpilon.pasarTurno();
        unpilon.pasarTurno();
        unpilon.pasarTurno();
        assertDoesNotThrow(() -> elmapa.construirEdificio(unAcceso, coordenadaAcceso));

    }
}

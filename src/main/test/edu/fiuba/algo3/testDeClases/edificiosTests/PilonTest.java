package edu.fiuba.algo3.testDeClases.edificiosTests;

import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Acceso;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Pilon;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Imperio.Suministro;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PilonTest{

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01PuedoCrearUnPilon(){
        Pilon unPilon = new Pilon();

        assertNotNull(unPilon);
    }

    @Test
    public void test02UnPilonNoSeConstruyeEn4TurnosPorLoTantoNoPuedoConstruirNadaAlrededor(){
        Mapa elmapa = Mapa.obtener();
        Coordenada coordenadasPilon = new Coordenada(2,2);
        Coordenada coordenadaAcceso = new Coordenada(2,3);
        Pilon unpilon = new Pilon();
        Acceso unAcceso = new Acceso();

        elmapa.colocarOcupable(unpilon, coordenadasPilon);

        for (int i = 0; i < 4; i++)
            unpilon.pasarTurno();

        assertThrows(ErrorEdificioNoSePuedeConstruirEnEstaCasilla.class, () -> elmapa.colocarOcupable(unAcceso, coordenadaAcceso));
    }

    @Test
    public void test03UnPilonSiSeConstruyeEn5TurnosPorLoTantoPuedoConstruirAlrededor(){
        Mapa elmapa = Mapa.obtener();
        Coordenada coordenadasPilon = new Coordenada(2,2);
        Coordenada coordenadaAcceso = new Coordenada(2,3);
        Pilon unpilon = new Pilon();
        Acceso unAcceso = new Acceso();
        unpilon.asignarSuministro(new Suministro(0));

        elmapa.colocarOcupable(unpilon, coordenadasPilon);

        //Construyo Pilon
        for (int i = 0; i < 5; i++)
            unpilon.pasarTurno();

        assertDoesNotThrow(() -> elmapa.colocarOcupable(unAcceso, coordenadaAcceso));
    }
}

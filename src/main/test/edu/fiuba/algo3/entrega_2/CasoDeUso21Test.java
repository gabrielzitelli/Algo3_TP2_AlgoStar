package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Imperio.*;
import edu.fiuba.algo3.modelo.Mapa.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso21Test {

    @BeforeEach
    public void setup(){
        Mapa.obtener().reiniciarMapa();
    }

    @Test
    public void test01NoPuedoEvolucionarAUnMutaliscoSinLosRecursosSuficientes(){
        Mapa elMapa = Mapa.obtener();
        Zerg imperioZerg = new Zerg();
        Coordenada unaCoordenada = new Coordenada(0,0);

        imperioZerg.abastecerDeRecursos(new Recurso(50), new Recurso(50));

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> imperioZerg.evolucionarMutaliscoAGuardian(unaCoordenada));
    }
}

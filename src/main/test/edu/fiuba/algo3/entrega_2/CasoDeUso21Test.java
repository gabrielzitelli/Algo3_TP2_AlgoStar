package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Imperio.Recurso;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso21Test {

    @Test
    public void test01NoPuedoEvolucionarAUnMutaliscoSinLosRecursosSuficientes(){
        Zerg imperioZerg = new Zerg();
        imperioZerg.abastecerDeRecursos(new Recurso(50), new Recurso(50));

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        Coordenada unaCoordenada = new Coordenada(0,0);

        assertThrows(ErrorCantidadDeRecursoInsuficiente.class,
                () -> imperioZerg.evolucionarMutaliscoAGuardian(unaCoordenada));

    }

}

package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Excepciones.ErrorVidaLlegoACero;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.UnidadesZerg.Zerling;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CasoDeUso18 {

    @Test
    public void test01VerificoQueSiAtacoUnoZerlingConOtroZerlingNueveVecesEsteMuere(){
        Zerling unZerling = new Zerling();
        Zerling ZerlingADaniar = new Zerling();

        Coordenada unaCoordenada = new Coordenada(0,0);
        Coordenada otraCoordenada = new Coordenada(0,1);

        Mapa elMapa = Mapa.obtener();
        elMapa.reiniciarMapa();

        elMapa.colocarUnaUnidad(unZerling, unaCoordenada);
        elMapa.colocarUnaUnidad(ZerlingADaniar, otraCoordenada);

        // Dejo al zerling a un ataque mas del otro zerling
        for (int i = 0; i < 8; i++) {
            elMapa.atacar(unaCoordenada, otraCoordenada);
        }

        assertThrows(ErrorVidaLlegoACero.class,() -> elMapa.atacar(unaCoordenada, otraCoordenada));
    }
}

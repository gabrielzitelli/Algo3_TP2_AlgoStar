package edu.fiuba.algo3.modelo.Unidades.EstadoUnidad;

import edu.fiuba.algo3.modelo.Excepciones.ErrorUnidadYaSeMovioEsteTurno;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;

public class NoCaminadora implements Caminar{

    private final int rangoDeCaminata;

    public NoCaminadora(int rangoDeCaminata) {
        this.rangoDeCaminata = rangoDeCaminata;
    }

    @Override
    public Caminar caminar(Coordenada coordenadaInicial, Coordenada coordenadaDestino){
        throw new ErrorUnidadYaSeMovioEsteTurno();
    }

    @Override
    public Caminar pasarTurno() {
        return new Caminadora(rangoDeCaminata);
    }

    @Override
    public String toString() {
        return "no_caminar";
    }
}

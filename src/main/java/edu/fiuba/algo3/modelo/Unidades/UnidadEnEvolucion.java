package edu.fiuba.algo3.modelo.Unidades;

import edu.fiuba.algo3.modelo.Mapa.Mapa;

public class UnidadEnEvolucion {
    Unidad unidadAEvolucionar;
    Unidad unidadEvolucionada;

    public UnidadEnEvolucion(Unidad unidadAEvolucionar, Unidad unidadEvolucionada){
        this.unidadAEvolucionar = unidadAEvolucionar;
        this.unidadEvolucionada = unidadEvolucionada;
    }

    public void pasarTurno(){
        this.unidadEvolucionada.pasarTurno();

        if (unidadEvolucionada.estaConstruida()){
            unidadAEvolucionar.destruirUnidad();
            Mapa.obtener().colocarOcupable(unidadEvolucionada, unidadAEvolucionar.coordenada);
        }
    }
    public boolean unidadYaEvoluciono() {
        return unidadEvolucionada.estaConstruida();
    }

    public Unidad obtenerUnidad() {
        return unidadEvolucionada;
    }
}

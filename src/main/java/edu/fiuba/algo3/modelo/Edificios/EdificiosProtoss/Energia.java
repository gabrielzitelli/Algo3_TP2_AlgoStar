package edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;

public class Energia {

    int radioDeEnergia = 3;

    public void energizar(Coordenada origenDeExpansion) {
        if (origenDeExpansion != null){
            Mapa elMapa = Mapa.obtener();
            elMapa.abastecerEnergia(origenDeExpansion, radioDeEnergia);
        }
    }
    public void desenergizar(Coordenada origenDeExpansion) {
        if (origenDeExpansion != null){
            Mapa elMapa = Mapa.obtener();
            elMapa.desabastecerEnergia(origenDeExpansion, radioDeEnergia);
        }
    }
}

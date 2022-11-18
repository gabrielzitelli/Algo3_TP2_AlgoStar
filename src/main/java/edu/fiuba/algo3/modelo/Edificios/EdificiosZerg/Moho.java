package edu.fiuba.algo3.modelo.Edificios.EdificiosZerg;

import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;

public class Moho {

    private int radioInicial = 5;
    private int radioActual;
    private int turnosParaExpandir = 2;
    private int contadorTurnos;

    public Moho(){
        radioActual = radioInicial;
        contadorTurnos = turnosParaExpandir;
    }

    public void expandir(Coordenada origenDeExpansion){
        contadorTurnos++;

        if(contadorTurnos >= turnosParaExpandir && origenDeExpansion != null){
            Mapa elMapa = Mapa.obtener();
            elMapa.expandirMoho(origenDeExpansion, radioActual);
            radioActual++;
            contadorTurnos = 0;
        }
    }
}
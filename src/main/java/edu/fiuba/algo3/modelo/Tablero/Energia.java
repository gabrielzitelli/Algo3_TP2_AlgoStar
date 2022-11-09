package edu.fiuba.algo3.modelo.Tablero;

public class Energia implements Terreno {

    @Override
    public boolean igualA(Terreno otroTerreno) {
        Energia energia = new Energia();
        return (energia.getClass().equals(otroTerreno.getClass()));
    }
}

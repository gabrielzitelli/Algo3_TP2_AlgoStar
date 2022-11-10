package edu.fiuba.algo3.modelo.Tablero;

public class Moho implements Terreno {

    @Override
    public boolean igualA(Terreno terreno) {
        Moho moho = new Moho();
        return (moho.getClass().equals(terreno.getClass()));
    }
}

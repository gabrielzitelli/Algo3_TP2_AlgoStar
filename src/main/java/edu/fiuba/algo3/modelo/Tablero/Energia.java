package edu.fiuba.algo3.modelo.Tablero;

public class Energia implements Terreno {

    private int cargas = 0;

    @Override
    public boolean igualA(Terreno otroTerreno) {
        Energia energia = new Energia();
        return (energia.getClass().equals(otroTerreno.getClass()));
    }

    @Override
    public Terreno actualizarTerreno(Terreno nuevoTerreno) {
        return nuevoTerreno;
    }

    @Override
    public Terreno cargarTerreno() {
        cargas ++;
        return this;
    }

    @Override
    public Terreno descargarTerreno() {
        cargas --;
        if (cargas <= 0) return new Neutro();
        return this;
    }
}

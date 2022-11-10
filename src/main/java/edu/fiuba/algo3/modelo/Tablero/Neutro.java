package edu.fiuba.algo3.modelo.Tablero;

public class Neutro implements Terreno {
    @Override
    public boolean igualA(Terreno terreno) {
        Neutro neutro = new Neutro();
        return (neutro.getClass().equals(terreno.getClass()));
    }

    @Override
    public Terreno actualizarTerreno(Terreno nuevoTerreno) {
        return nuevoTerreno;
    }

    @Override
    public Terreno cargarTerreno() {
        return new Energia();
    }

    @Override
    public Terreno descargarTerreno() {
        return this;
    }
}

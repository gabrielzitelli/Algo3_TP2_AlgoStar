package edu.fiuba.algo3.modelo.Tablero;

public interface Terreno {
    boolean igualA(Terreno terreno);
    Terreno actualizarTerreno(Terreno nuevoTerreno);

    Terreno cargarTerreno();
    Terreno descargarTerreno();
}

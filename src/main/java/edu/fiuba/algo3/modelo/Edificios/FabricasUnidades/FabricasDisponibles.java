package edu.fiuba.algo3.modelo.Edificios.FabricasUnidades;

import java.util.ArrayList;
import java.util.HashMap;

public class FabricasDisponibles {

    private final HashMap<String, Integer> disponibles = new HashMap<>();

    private final int valorInicial = 0;
    private final int valorModificador = 1;

    // Verifica que la fabrica se encuentre
    public boolean verificar(FabricasUnidades fabricasUnidades) {
        String clave = fabricasUnidades.getClass().getName();
        if (disponibles.get(clave) == null)
            return false;

        return disponibles.get(clave) > valorInicial;
    }

    // Aumenta o agrega las fabricas pasadas a las disponibles
    public void aumentar(ArrayList<FabricasUnidades> fabricasAAumentar) {
        for (FabricasUnidades fabricasUnidades : fabricasAAumentar) {
            String clave = fabricasUnidades.getClass().getName();

            if (!disponibles.containsKey(clave)) {
                disponibles.put(clave, valorInicial + valorModificador);
                continue;
            }

            disponibles.replace(clave, disponibles.get(clave) + valorModificador);
        }
    }

    // Disminuye o quita las fabricas pasadas a las disponibles
    public void disminuir(ArrayList<FabricasUnidades> fabricasADisminuir) {
        for (FabricasUnidades fabricasUnidades : fabricasADisminuir) {
            String clave = fabricasUnidades.getClass().getName();
            disponibles.replace(clave, disponibles.get(clave) - valorModificador);
        }
    }
}

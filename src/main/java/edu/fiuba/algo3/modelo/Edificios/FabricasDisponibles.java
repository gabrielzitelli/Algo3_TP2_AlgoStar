package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Fabrica;

import java.util.ArrayList;
import java.util.HashMap;

public class FabricasDisponibles {

    private HashMap<String, Integer> disponibles = new HashMap<>();

    private int valorInicial = 0;
    private int valorModificador = 1;

    // Verifica que la fabrica se encuentre
    public boolean verificar(Fabrica fabrica) {
        String clave = fabrica.getClass().getName();
        if (disponibles.get(clave) == null)
            return false;

        return disponibles.get(clave) > valorInicial;
    }

    // Aumenta o agrega las fabricas pasadas a las disponibles
    public void aumentar(ArrayList<Fabrica> fabricasAAumentar) {
        for (Fabrica fabrica : fabricasAAumentar) {
            String clave = fabrica.getClass().getName();

            if (!disponibles.containsKey(clave)) {
                disponibles.put(clave, valorInicial + valorModificador);
                continue;
            }

            disponibles.replace(clave, disponibles.get(clave) + valorModificador);
        }
    }

    // Disminuye o quita las fabricas pasadas a las disponibles
    public void disminuir(ArrayList<Fabrica> fabricasADisminuir) {
        for (Fabrica fabrica : fabricasADisminuir) {
            String clave = fabrica.getClass().getName();
            disponibles.replace(clave, disponibles.get(clave) - valorModificador);
        }
    }
}

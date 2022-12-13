package edu.fiuba.algo3.modelo.ConvertidorJson;

import edu.fiuba.algo3.modelo.AlgoStar.Jugador;
import edu.fiuba.algo3.modelo.Excepciones.ErrorObjetoNoSePuedeConvertirAJSON;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;
import org.json.simple.JSONObject;

public class ConvertidorJSON {

    //Para casilla
    public static final String SUPERFICIE = "superficie";
    public static final String RECURSO = "recurso";
    public static final String CARGA = "carga";
    public static final String MOHO = "moho";
    public static final String OCUPABLE = "ocupable";
    public static final String ESTADO = "estado";
    public static final String VIDA = "vidaActual";
    public static final String VIDAMAX = "vidaMaxima";
    public static final String ESCUDO = "escudoActual";
    public static final String ESCUDOMAX = "escudoMaximo";
    public static final String CANTIDAD_UNIDADES = "cantidad_unidades";

    public static final String IMPERIO = "imperio";
    public static final String NOMBRE = "nombre";
    public static final String COLOR = "color";

    public static final String MINERAL = "mineral";
    public static final String GAS = "gas";
    public static final String POBLACION = "poblacion";
    public static final String SUMINISTRO = "suministro";
    public static final String CAMINAR = "estado_caminar";
    public static final String ATACAR = "estado_atacar";

    public static final String RANGO_ATAQUE = "rango_ataque";


    public static JSONObject convertirAJSON(Object objeto){

        if (!sePuedeConvertir(objeto)){
            throw new ErrorObjetoNoSePuedeConvertirAJSON();
        }

        JSONObject json = new JSONObject();
        String[] lista;
        lista = objeto.toString().split(" ");

        for (int i = 0; i < (lista.length) - 1; i += 2){
            json.put(lista[i], lista[i + 1]);
        }
        return json;
    }

    private static boolean sePuedeConvertir(Object objeto) {
        if (objeto.getClass().equals(Jugador.class))
            return true;
        else if (Ocupable.class.isAssignableFrom(objeto.getClass()))
            return true;
        else return Casilla.class.isAssignableFrom(objeto.getClass());
    }
}

package edu.fiuba.algo3.modelo.ConvertidorJson;

import edu.fiuba.algo3.modelo.AlgoStar.Jugador;
import edu.fiuba.algo3.modelo.Excepciones.ErrorObjetoNoSePuedeConvertirAJSON;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;
import org.json.simple.JSONObject;

public class ConvertidorJSON {

    //Para casilla
    public static String SUPERFICIE = "superficie";
    public static String RECURSO = "recurso";
    public static String CARGA = "carga";
    public static String MOHO = "moho";
    public static String OCUPABLE = "ocupable";
    public static String ESTADO = "estado";
    public static String VIDA = "vidaActual";
    public static String VIDAMAX = "vidaMaxima";
    public static String ESCUDO = "escudoActual";
    public static String ESCUDOMAX = "escudoMaximo";
    public static String CANTIDAD_UNIDADES = "cantidad_unidades";

    public static String IMPERIO = "imperio";
    public static String NOMBRE = "nombre";
    public static String COLOR = "color";

    public static String MINERAL = "mineral";
    public static String GAS = "gas";
    public static String POBLACION = "poblacion";
    public static String SUMINISTRO = "suministro";
    public static String CAMINAR = "estado_caminar";
    public static String ATACAR = "estado_atacar";

    public static String RANGO_ATAQUE = "rango_ataque";


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

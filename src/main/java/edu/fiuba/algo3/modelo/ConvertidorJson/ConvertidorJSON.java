package edu.fiuba.algo3.modelo.ConvertidorJson;

import edu.fiuba.algo3.modelo.AlgoStar.Jugador;
import edu.fiuba.algo3.modelo.Excepciones.ErrorObjetoNoSePuedeConvertirAJSON;
import edu.fiuba.algo3.modelo.Mapa.Casilla.Casilla;
import org.json.simple.JSONObject;

public class ConvertidorJSON {

    //Para casilla
    public static String SUPERFICIE = "superficie";
    public static String RECURSO = "recurso";
    public static String CARGA = "carga";
    public static String MOHO = "moho";
    public static String OCUPABLE = "ocupable";
    public static String ESTADO = "estado";
    public static String VIDA = "vida";
    public static String ESCUDO = "escudo";

    public static String IMPERIO = "imperio";
    public static String NOMBRE = "nombre";
    public static String COLOR = "color";


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
        else return Casilla.class.isAssignableFrom(objeto.getClass());
    }
}

package edu.fiuba.algo3.modelo.AlgoStar;

public class Logger {

    private static Logger unicaInstancia = new Logger();

    private Logger() {
    }

    public static Logger obtener() {
        return unicaInstancia;
    }

    public void log(String mensajeLogeado) {
        System.out.println(mensajeLogeado);
    }
}

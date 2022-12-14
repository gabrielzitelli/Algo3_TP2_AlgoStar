package edu.fiuba.algo3.modelo.AlgoStar;

public class Logger {

    private static final Logger unicaInstancia = new Logger();

    private static boolean loggerActivado = true;

    private Logger() {
    }

    public static Logger obtener() {
        return unicaInstancia;
    }

    public void toggle(boolean bool){
        loggerActivado = bool;
    }

    public void log(String mensajeLogeado) {
        if (loggerActivado)
            System.out.println(mensajeLogeado);
    }
}

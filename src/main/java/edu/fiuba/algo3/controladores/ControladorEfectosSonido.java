package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Excepciones.ErrorIdentificadorNoCorrespondeConNingunaCancionCargada;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Objects;

public class ControladorEfectosSonido {

    private final HashMap<String, MediaPlayer> archivosEfectosSonido = new HashMap<>();
    private MediaPlayer efecto;
    private final HashMap<String, MediaPlayer> archivosMusica = new HashMap<>();
    private MediaPlayer reproduccionActual;
    private double volumenMusica = 0.6;
    private double volumenEfectos = 0.6;

    private static ControladorEfectosSonido instanciaUnica;

    private ControladorEfectosSonido(){}

    public static ControladorEfectosSonido obtenerControlador() {
        if (instanciaUnica != null)
            return instanciaUnica;
        instanciaUnica = new ControladorEfectosSonido();
        return instanciaUnica;
    }

    public void reproducirFX(String identificador) {
        if (!archivosEfectosSonido.containsKey(identificador))
            throw new ErrorIdentificadorNoCorrespondeConNingunaCancionCargada();

        if (efecto != null)
            efecto.stop();

        efecto = archivosEfectosSonido.get(identificador);
        efecto.setVolume(volumenEfectos);
        efecto.play();
    }

    /*** Llamarse al principio de la ejecucion del programa, o entre escenas
     * para asi no bajar el rendimiento, cargar las canciones que se vayan a usar
     * Toma una direccion del archivo en Resources y un identificador, este ultimo para
     * poder acceder al archivo de sonido luego***/
    public void cargarMusica(String direccion, String identificador) {
        Media media = new Media(Objects.requireNonNull(getClass().getResource("/sound/" + direccion).toExternalForm()));
        archivosMusica.put(identificador, new MediaPlayer(media));
    }

    public void cargarSonido(String direccion, String identificador) {
        Media audio = new Media(Objects.requireNonNull(getClass().getResource("/sound/" + direccion).toExternalForm()));
        archivosEfectosSonido.put(identificador, new MediaPlayer(audio));
    }

    public void reproducirMusica(String identificador) {
        if (!archivosMusica.containsKey(identificador))
            throw new ErrorIdentificadorNoCorrespondeConNingunaCancionCargada();

        if (reproduccionActual != null)
            reproduccionActual.stop();

        reproduccionActual = archivosMusica.get(identificador);
        reproduccionActual.setVolume(volumenMusica);
        reproduccionActual.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                reproduccionActual.seek(Duration.ZERO);
            }
        });
        reproduccionActual.play();
    }

    public void modificarVolumenMusica(double valor) {
        if (valor >= 0 && valor <= 100) {
            volumenMusica = valor / 100;
            reproduccionActual.setVolume(volumenMusica);
        }
    }
    public void modificarVolumenEfectos(double valor) {
        if (valor >= 0 && valor <= 100) {
            volumenEfectos = valor / 100;
        }
    }
    public void detenerMusica() {
        reproduccionActual.stop();
    }
}

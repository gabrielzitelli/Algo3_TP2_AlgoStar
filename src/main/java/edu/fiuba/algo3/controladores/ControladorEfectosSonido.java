package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.Excepciones.ErrorIdentificadorNoCorrespondeConNingunaCancionCargada;
import javafx.beans.property.SimpleDoubleProperty;
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
    private final double volumenDefault = 0.6;

    private final SimpleDoubleProperty volumenMusicaProperty = new SimpleDoubleProperty(volumenDefault);
    private final SimpleDoubleProperty volumenFx = new SimpleDoubleProperty(volumenDefault);

    private static ControladorEfectosSonido instanciaUnica;

    private ControladorEfectosSonido(){
    }

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
        efecto.play();
    }
    public void reproducirMusica(String identificador) {
        if (!archivosMusica.containsKey(identificador))
            throw new ErrorIdentificadorNoCorrespondeConNingunaCancionCargada();

        if (reproduccionActual != null)
            reproduccionActual.stop();

        reproduccionActual = archivosMusica.get(identificador);
        reproduccionActual.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                reproduccionActual.seek(Duration.ZERO);
            }
        });
        reproduccionActual.play();
    }


    /*** Llamarse al principio de la ejecucion del programa, o entre escenas
     * para asi no bajar el rendimiento, cargar las canciones que se vayan a usar
     * Toma una direccion del archivo en Resources y un identificador, este ultimo para
     * poder acceder al archivo de sonido luego***/
    public void cargarMusica(String direccion, String identificador) {
        cargarArchivo(direccion, identificador, archivosMusica, volumenMusicaProperty);
    }

    public void cargarSonido(String direccion, String identificador) {
        cargarArchivo(direccion, identificador, archivosEfectosSonido, volumenFx);
    }

    private void cargarArchivo(String direccion, String identificador, HashMap<String, MediaPlayer> contenedor, SimpleDoubleProperty volumen){
        Media media = new Media(Objects.requireNonNull(getClass().getResource("/sound/" + direccion).toExternalForm()));
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.volumeProperty().bindBidirectional(volumen);
        contenedor.put(identificador, mediaPlayer);
    }

    public void modificarVolumenMusica(double valor) {
        modificarVolumen(valor, volumenMusicaProperty);
    }
    public void modificarVolumenEfectos(double valor) {
        modificarVolumen(valor, volumenFx);
    }

    private void modificarVolumen(double valor, SimpleDoubleProperty volumen) {
        if (valor >= 0 && valor <= 100){
            volumen.set(valor / 100);
        }
    }
    public void detenerMusica() {
        reproduccionActual.stop();
    }

    public SimpleDoubleProperty obtenerVolumenMusica(){
        return volumenMusicaProperty;
    }

    public SimpleDoubleProperty obtenerVolumenFx(){
        return volumenFx;
    }
}

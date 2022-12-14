package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controladores.ControladorEfectosSonido;
import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios.*;
import edu.fiuba.algo3.controladores.MapaControlador;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosPreRequisitosDelEdificio;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Objects;

public class ZanganoVista extends UnidadZergVista {

    public ZanganoVista() {
        this.tile = new Tile("unidades_zerg/32px/zangano.png");
        this.identificador = "zangano";
        this.info = "Zangano";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/zanganoRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/creacion/zanganoRawCrear.png")));
        this.archivoDeSonido = "zangano";
    }

    @Override
    public void manejarBotones(Button[] arrayBotones, Pane[] arrayWrappersBotonesEdificio, Coordenada coordenada, String imperioDeJugadorActual, MapaControlador mapaControlador) {
        super.manejarBotones(arrayBotones, arrayWrappersBotonesEdificio, coordenada, imperioDeJugadorActual, mapaControlador);

        if (!imperioDeJugadorActual.equalsIgnoreCase("zerg"))
            return;

        this.desactivarBotonAtacar(arrayBotones[1], arrayWrappersBotonesEdificio[1]);
        this.activarBotonesEvolucionEnEdificios(arrayBotones, arrayWrappersBotonesEdificio, coordenada, imperioDeJugadorActual, mapaControlador);
    }

    private void desactivarBotonAtacar(Button botonAtacar, Pane wrapperBotonAtacar){
        botonAtacar.setDisable(true);
        botonAtacar.setVisible(false);
        botonAtacar.setTooltip(null);
        Tooltip.install(wrapperBotonAtacar, new Tooltip(null));
    }

    private void activarBotonesEvolucionEnEdificios(Button[] arrayBotones, Pane[] arrayWrappersBotonesEdificio, Coordenada coordenada, String imperioDeJugadorActual, MapaControlador mapaControlador){
        Zerg imperioZerg = (Zerg)App.algoStar.conseguirJugadorActual().conseguirImperio();

        Button botonCrearCriadero = arrayBotones[1];
        Button botonCrearReserva = arrayBotones[2];
        Button botonCrearExtractor = arrayBotones[3];
        Button botonCrearGuarida = arrayBotones[4];
        Button botonCrearEspiral = arrayBotones[5];

        double anchoBoton = botonCrearCriadero.getPrefWidth();
        double altoBoton = botonCrearCriadero.getPrefWidth();

        crearBotonDeEdificio(botonCrearCriadero, new CriaderoVista(), anchoBoton, altoBoton);
        crearBotonDeEdificio(botonCrearReserva, new ReservaVista(), anchoBoton, altoBoton);
        crearBotonDeEdificio(botonCrearExtractor, new ExtractorVista(), anchoBoton, altoBoton);
        crearBotonDeEdificio(botonCrearGuarida, new GuaridaVista(), anchoBoton, altoBoton);
        crearBotonDeEdificio(botonCrearEspiral, new EspiralVista(), anchoBoton, altoBoton);

        ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();

        botonCrearCriadero.setOnAction( event -> {
            mapaControlador.desactivarAccionesDeAtaqueYMovimiento();
            sonido.reproducirFX("boton");
            imperioZerg.construirEdificio(new FabricaCriadero(), coordenada);
        });
        botonCrearReserva.setOnAction( event -> {
            mapaControlador.desactivarAccionesDeAtaqueYMovimiento();
            sonido.reproducirFX("boton");
            imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), coordenada);
        });
        botonCrearExtractor.setOnAction( event -> {
            mapaControlador.desactivarAccionesDeAtaqueYMovimiento();
            sonido.reproducirFX("boton");
            imperioZerg.construirEdificio(new FabricaExtractor(), coordenada);
        });
        botonCrearGuarida.setOnAction( event -> {
            mapaControlador.desactivarAccionesDeAtaqueYMovimiento();
            sonido.reproducirFX("boton");
            imperioZerg.construirEdificio(new FabricaGuarida(), coordenada);
        });
        botonCrearEspiral.setOnAction( event -> {
            mapaControlador.desactivarAccionesDeAtaqueYMovimiento();
            sonido.reproducirFX("boton");
            imperioZerg.construirEdificio(new FabricaEspiral(), coordenada);
        });

        prepararHabilitacionDeBoton(botonCrearCriadero, new Criadero(), arrayWrappersBotonesEdificio[1], new CriaderoVista(), imperioZerg, coordenada);
        prepararHabilitacionDeBoton(botonCrearReserva, new ReservaDeReproduccion(), arrayWrappersBotonesEdificio[2], new ReservaVista(), imperioZerg, coordenada);
        prepararHabilitacionDeBoton(botonCrearExtractor, new Extractor(new Gas(0)), arrayWrappersBotonesEdificio[3], new ExtractorVista(), imperioZerg, coordenada);
        prepararHabilitacionDeBoton(botonCrearGuarida, new Guarida(), arrayWrappersBotonesEdificio[4], new GuaridaVista(), imperioZerg, coordenada);
        prepararHabilitacionDeBoton(botonCrearEspiral, new Espiral(), arrayWrappersBotonesEdificio[5], new EspiralVista(), imperioZerg, coordenada);
    }

    private void crearBotonDeEdificio(Button boton, OcupableVista vistaEdificio, double ancho, double alto){
        ImageView imagenEdificio = new ImageView();
        imagenEdificio.setPreserveRatio(false);
        imagenEdificio.setSmooth(true);

        vistaEdificio.renderAdentroDeImageViewBoton(imagenEdificio);

        imagenEdificio.setFitWidth(ancho);
        imagenEdificio.setFitHeight(alto);

        DropShadow efectorBordePNGBlanco = new DropShadow( 1, Color.WHITE );
        imagenEdificio.setEffect(efectorBordePNGBlanco);

        boton.setVisible(true);
        boton.setDisable(false);
        boton.setGraphic(imagenEdificio);
    }

    private void prepararHabilitacionDeBoton(Button boton, EdificioZerg unEdificio, Pane wrapperBoton, EdificioVista edificioVista, Zerg imperioZerg, Coordenada coordenada){
        String informacionEdificio = unEdificio.toString();
        String identificadorEdificio = edificioVista.getInfo();
        String requisitosEdificio = edificioVista.getEdificiosRequisitos();
        String costoMineral = obtenerAtributoDeString(informacionEdificio, "costoMineral");
        String costoGas = obtenerAtributoDeString(informacionEdificio, "costoGas");
        String informacionDeCosto = String.format("%s\n%s Minerales necesarios: %s\n%s Gas necesario: %s",requisitosEdificio, emojiBulletPoint, costoMineral, emojiBulletPoint, costoGas);

        try{
            imperioZerg.verificarConstruccionDeEdificio(unEdificio, coordenada);
            boton.setTooltip(new Tooltip("EVOLUCIONAR EN " + identificadorEdificio.toUpperCase() + informacionDeCosto));

        }catch (ErrorCantidadDeRecursoInsuficiente exception){
            boton.setDisable(true);
            String informacionNoSePuedeConstruir = "\n" + emojiAdvertenciaUnidode + " No hay suficientes recursos como para construir este edificio";
            Tooltip.install(wrapperBoton, new Tooltip("EVOLUCIONAR EN " + identificadorEdificio.toUpperCase() + informacionNoSePuedeConstruir + informacionDeCosto));

        } catch (ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada exception){
            boton.setDisable(true);
            String informacionNoSePuedeConstruir = "\n" + emojiAdvertenciaUnidode + " No se puede construir este edificio en esta casilla ocupada";
            Tooltip.install(wrapperBoton, new Tooltip("EVOLUCIONAR EN " + identificadorEdificio.toUpperCase() + informacionNoSePuedeConstruir + informacionDeCosto));

        } catch (ErrorEdificioNoSePuedeConstruirEnEstaCasilla exception) {
            boton.setDisable(true);
            String informacionNoSePuedeConstruir = "\n" + emojiAdvertenciaUnidode + " No se puede construir este edificio sobre esta casilla por sus características";
            Tooltip.install(wrapperBoton, new Tooltip("EVOLUCIONAR EN " + identificadorEdificio.toUpperCase() + informacionNoSePuedeConstruir + informacionDeCosto));

        } catch (ErrorNoSeCumplenLosPreRequisitosDelEdificio exception) {
            boton.setDisable(true);
            String informacionNoSePuedeConstruir = "\n" + emojiAdvertenciaUnidode + " No se puede construir este edificio porque falta construir sus edificios requisito";
            Tooltip.install(wrapperBoton, new Tooltip("EVOLUCIONAR EN " + identificadorEdificio.toUpperCase() + informacionNoSePuedeConstruir + informacionDeCosto));
        }
    }
}

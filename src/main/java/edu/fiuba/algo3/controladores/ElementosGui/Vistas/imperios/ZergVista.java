package edu.fiuba.algo3.controladores.ElementosGui.Vistas.imperios;

import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios.*;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.*;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeConstruirEdificioSobreOtroEdificio;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Objects;

public class ZergVista {

    protected String obtenerAtributoDeString(String stringFormateado, String tipoAtributo){
        String[] tokensJugador = stringFormateado.split(" ");
        String atributoDeseado = null;

        for (int i = 0; i < tokensJugador.length; i++) {
            if(Objects.equals(tokensJugador[i], tipoAtributo))
                atributoDeseado = new String(tokensJugador[i + 1]);
        }

        return atributoDeseado;
    }

    protected void crearBotonDeEdificio(Button boton, OcupableVista vistaEdificio, double ancho, double alto){
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

    public void manejarBotones(Coordenada coordenada, Button[] arrayBotones, Pane[] arrayWrappersBotonesConstruirEdificio, Zerg imperioZerg) {

        Button botonCrearCriadero = arrayBotones[0];
        Button botonCrearReserva = arrayBotones[1];
        Button botonCrearExtractor = arrayBotones[2];
        Button botonCrearGuarida = arrayBotones[3];
        Button botonCrearEspiral = arrayBotones[4];

        double anchoBoton = botonCrearCriadero.getPrefWidth();
        double altoBoton = botonCrearCriadero.getPrefWidth();

        crearBotonDeEdificio(botonCrearCriadero, new CriaderoVista(), anchoBoton, altoBoton);
        crearBotonDeEdificio(botonCrearReserva, new ReservaVista(), anchoBoton, altoBoton);
        crearBotonDeEdificio(botonCrearExtractor, new ExtractorVista(), anchoBoton, altoBoton);
        crearBotonDeEdificio(botonCrearGuarida, new GuaridaVista(), anchoBoton, altoBoton);
        crearBotonDeEdificio(botonCrearEspiral, new EspiralVista(), anchoBoton, altoBoton);

        botonCrearCriadero.setOnAction( event -> imperioZerg.construirEdificio(new FabricaCriadero(),coordenada));
        botonCrearReserva.setOnAction( event ->imperioZerg.construirEdificio(new FabricaReservaDeReproduccion(), coordenada));
        botonCrearExtractor.setOnAction( event -> imperioZerg.construirEdificio(new FabricaExtractor(), coordenada));
        botonCrearGuarida.setOnAction( event -> imperioZerg.construirEdificio(new FabricaGuarida(), coordenada));
        botonCrearEspiral.setOnAction( event -> imperioZerg.construirEdificio(new FabricaEspiral(), coordenada));

        prepararHabilitacionDeBoton(botonCrearCriadero, new Criadero(), arrayWrappersBotonesConstruirEdificio[0], new CriaderoVista(), imperioZerg, coordenada);
        prepararHabilitacionDeBoton(botonCrearReserva, new ReservaDeReproduccion(), arrayWrappersBotonesConstruirEdificio[1], new ReservaVista(), imperioZerg, coordenada);
        prepararHabilitacionDeBoton(botonCrearExtractor, new Extractor(new Gas(0)), arrayWrappersBotonesConstruirEdificio[2], new ExtractorVista(), imperioZerg, coordenada);
        prepararHabilitacionDeBoton(botonCrearGuarida, new Guarida(), arrayWrappersBotonesConstruirEdificio[3], new GuaridaVista(), imperioZerg, coordenada);
        prepararHabilitacionDeBoton(botonCrearEspiral, new Espiral(), arrayWrappersBotonesConstruirEdificio[4], new EspiralVista(), imperioZerg, coordenada);
    }

    private void prepararHabilitacionDeBoton(Button boton, Edificio unEdificio, Pane wrapperBoton, OcupableVista ocupableVista, Zerg imperioZerg, Coordenada coordenada){
        String informacionEdificio = unEdificio.toString();
        String identificadorEdificio = ocupableVista.getInfo();
        String costoMineral = obtenerAtributoDeString(informacionEdificio, "costoMineral");
        String costoGas = obtenerAtributoDeString(informacionEdificio, "costoGas");

        if(coordenada == null){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("No se ha seleccionado una casilla"));
            return;
        }

        try{
            imperioZerg.verificarConstruccionDeEdificio(unEdificio, coordenada);
            boton.setTooltip(new Tooltip("CONSTRUIR " + identificadorEdificio.toUpperCase() + "\n Minerales necesarios: " + costoMineral + "\n Gas necesario: " + costoGas));
        } catch (ErrorCantidadDeRecursoInsuficiente exception){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("CONSTRUIR " + identificadorEdificio.toUpperCase() + "\nNo hay suficientes recursos como para construir este edificio" + "\n Minerales necesarios: " + costoMineral + "\n Gas necesario: " + costoGas));
        } catch (ErrorNoSePuedeConstruirEdificioSobreOtroEdificio exception){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("CONSTRUIR " + identificadorEdificio.toUpperCase() + "\nNo se puede construir este edificio en esta casilla ocupada" + "\n Minerales necesarios: " + costoMineral + "\n Gas necesario: " + costoGas));
        } catch (ErrorEdificioNoSePuedeConstruirEnEstaCasilla exception) {
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("CONSTRUIR " + identificadorEdificio.toUpperCase() + "\nNo se puede construir este edificio sobre esta casilla por sus características" + "\n Minerales necesarios: " + costoMineral + "\n Gas necesario: " + costoGas));
        }
    }
}

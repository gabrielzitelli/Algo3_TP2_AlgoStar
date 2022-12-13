package edu.fiuba.algo3.controladores.ElementosGui.Vistas.imperios;

import edu.fiuba.algo3.controladores.ControladorEfectosSonido;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.Vista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios.*;
import edu.fiuba.algo3.modelo.Edificios.Edificio;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.*;
import edu.fiuba.algo3.modelo.Edificios.FabricasEdificios.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEdificioNoSePuedeConstruirEnEstaCasilla;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosPreRequisitosDelEdificio;
import edu.fiuba.algo3.modelo.Imperio.Gas;
import edu.fiuba.algo3.modelo.Imperio.Mineral;
import edu.fiuba.algo3.modelo.Imperio.Protoss;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Objects;

public class ProtossVista extends Vista {
    protected String obtenerAtributoDeString(String stringFormateado, String tipoAtributo){
        String[] tokensJugador = stringFormateado.split(" ");
        String atributoDeseado = null;

        for (int i = 0; i < tokensJugador.length; i++) {
            if(Objects.equals(tokensJugador[i], tipoAtributo))
                atributoDeseado = tokensJugador[i + 1];
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

    public void manejarBotones(Coordenada coordenada, Button[] arrayBotones, Pane[] arrayWrappersBotonesConstruirEdificio, Protoss imperioProtoss) {

        Button botonCrearNexoMineral = arrayBotones[0];
        Button botonCrearAsimilador = arrayBotones[1];
        Button botonCrearPilon = arrayBotones[2];
        Button botonCrearAcceso = arrayBotones[3];
        Button botonCrearPuertoEstelar = arrayBotones[4];

        double anchoBoton = botonCrearNexoMineral.getPrefWidth();
        double altoBoton = botonCrearNexoMineral.getPrefWidth();

        crearBotonDeEdificio(botonCrearNexoMineral, new NexoMineralVista(), anchoBoton, altoBoton);
        crearBotonDeEdificio(botonCrearAsimilador, new AsimiladorVista(), anchoBoton, altoBoton);
        crearBotonDeEdificio(botonCrearPilon, new PilonVista(), anchoBoton, altoBoton);
        crearBotonDeEdificio(botonCrearAcceso, new AccesoVista(), anchoBoton, altoBoton);
        crearBotonDeEdificio(botonCrearPuertoEstelar, new PuertoEstelarVista(), anchoBoton, altoBoton);

        ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();

        botonCrearNexoMineral.setOnAction( event -> {

            sonido.reproducirFX("boton");
            imperioProtoss.construirEdificio(new FabricaNexoMineral(), coordenada);
        });
        botonCrearAsimilador.setOnAction( event -> {
            sonido.reproducirFX("boton");
            imperioProtoss.construirEdificio(new FabricaAsimilador(), coordenada);
        });
        botonCrearPilon.setOnAction( event -> {
            sonido.reproducirFX("boton");
            imperioProtoss.construirEdificio(new FabricaPilon(), coordenada);
        });
        botonCrearAcceso.setOnAction( event -> {
            sonido.reproducirFX("boton");
            imperioProtoss.construirEdificio(new FabricaAcceso(), coordenada);
        });
        botonCrearPuertoEstelar.setOnAction( event -> {
            sonido.reproducirFX("boton");
            imperioProtoss.construirEdificio(new FabricaPuertoEstelar(), coordenada);
        });

        prepararHabilitacionDeBoton(botonCrearNexoMineral, new NexoMineral(new Mineral(0)), arrayWrappersBotonesConstruirEdificio[0], new NexoMineralVista(), imperioProtoss, coordenada);
        prepararHabilitacionDeBoton(botonCrearAsimilador, new Asimilador(new Gas(0)), arrayWrappersBotonesConstruirEdificio[1], new AsimiladorVista(), imperioProtoss, coordenada);
        prepararHabilitacionDeBoton(botonCrearPilon, new Pilon(), arrayWrappersBotonesConstruirEdificio[2], new PilonVista(), imperioProtoss, coordenada);
        prepararHabilitacionDeBoton(botonCrearAcceso, new Acceso(), arrayWrappersBotonesConstruirEdificio[3], new AccesoVista(), imperioProtoss, coordenada);
        prepararHabilitacionDeBoton(botonCrearPuertoEstelar, new PuertoEstelar(), arrayWrappersBotonesConstruirEdificio[4], new PuertoEstelarVista(), imperioProtoss, coordenada);
    }

    private void prepararHabilitacionDeBoton(Button boton, Edificio unEdificio, Pane wrapperBoton, EdificioVista edificioVista, Protoss imperioProtoss, Coordenada coordenada){
        String informacionEdificio = unEdificio.toString();
        String identificadorEdificio = edificioVista.getInfo();
        String costoMineral = obtenerAtributoDeString(informacionEdificio, "costoMineral");
        String costoGas = obtenerAtributoDeString(informacionEdificio, "costoGas");
        String requisitosEdificio = edificioVista.getEdificiosRequisitos();
        String informacionDeCosto = String.format("%s\n%s Minerales necesarios: %s\n%s Gas necesario: %s",requisitosEdificio, emojiBulletPoint, costoMineral, emojiBulletPoint, costoGas);

        if(coordenada == null){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("No se ha seleccionado una casilla"));
            return;
        }

        try{
            imperioProtoss.verificarConstruccionDeEdificio(unEdificio, coordenada);
            boton.setTooltip(new Tooltip("CONSTRUIR " + identificadorEdificio.toUpperCase() + informacionDeCosto));

        } catch (ErrorCantidadDeRecursoInsuficiente exception){
            boton.setDisable(true);
            String informacionNoSePuedeConstruir = "\n" + emojiAdvertenciaUnidode + " No hay suficientes recursos como para construir este edificio";
            Tooltip.install(wrapperBoton, new Tooltip("CONSTRUIR " + identificadorEdificio.toUpperCase() + informacionNoSePuedeConstruir + informacionDeCosto));

        } catch (ErrorNoSeCumplenLosPreRequisitosDelEdificio exception){
            boton.setDisable(true);
            String informacionNoSePuedeConstruir = "\n" + emojiAdvertenciaUnidode + " No se puede construir este edificio porque no se cumplen los prerequisitos";
            Tooltip.install(wrapperBoton, new Tooltip("CONSTRUIR " + identificadorEdificio.toUpperCase() + informacionNoSePuedeConstruir + informacionDeCosto));

        } catch (ErrorEdificioNoSePuedeConstruirEnEstaCasilla exception) {
            boton.setDisable(true);
            String informacionNoSePuedeConstruir = "\n" + emojiAdvertenciaUnidode + " No se puede construir este edificio sobre esta casilla por sus características";
            Tooltip.install(wrapperBoton, new Tooltip("CONSTRUIR " + identificadorEdificio.toUpperCase() + informacionNoSePuedeConstruir + informacionDeCosto));
        }
    }
}

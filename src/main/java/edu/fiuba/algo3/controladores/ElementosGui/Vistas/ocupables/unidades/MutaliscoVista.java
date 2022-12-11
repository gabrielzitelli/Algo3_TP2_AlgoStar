package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.controladores.MapaControlador;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Excepciones.ErrorEstaUnidadYaEstaEvolucionando;
import edu.fiuba.algo3.modelo.Imperio.Zerg;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Devorador;
import edu.fiuba.algo3.modelo.Unidades.UnidadesZerg.Guardian;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.json.simple.JSONObject;

import java.util.Objects;

public class MutaliscoVista extends UnidadZergVista {

    public MutaliscoVista() {
        this.tile = new Tile("unidades_zerg/32px/mutalisco.png");
        this.identificador = "mutalisco";
        this.info = "Mutalisco";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/mutaliscoRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/unidades_zerg/original/creacion/mutaliscoRawCrear.png")));
        this.requisitosUnidad = "\n" + emojiInformacionUnicode + " Requiere: Espiral";
    }

    @Override
    public void manejarBotones(Button[] arrayBotones, Pane[] arrayWrappersBotonesEdificio, Coordenada coordenada, String imperioDeJugadorActual, MapaControlador mapaControlador) {
        if(!imperioDeJugadorActual.equalsIgnoreCase("zerg"))
            return;

        super.manejarBotones(arrayBotones,arrayWrappersBotonesEdificio,coordenada,imperioDeJugadorActual,mapaControlador);

        Unidad mutalisco = (Unidad) Mapa.obtener().obtenerOcupable(coordenada);
        Zerg imperioZerg = (Zerg) App.algoStar.conseguirJugadorActual().conseguirImperio();

        Button botonCrearGuardian = arrayBotones[2];
        Button botonCrearDevorador = arrayBotones[3];
        double anchoBoton = botonCrearGuardian.getPrefWidth();
        double altoBoton = botonCrearGuardian.getPrefWidth();

        crearBotonDeUnidad(botonCrearGuardian, new GuardianVista(), anchoBoton, altoBoton);
        crearBotonDeUnidad(botonCrearDevorador, new DevoradorVista(), anchoBoton, altoBoton);

        botonCrearGuardian.setOnAction( event -> imperioZerg.evolucionarMutaliscoAGuardian(mutalisco));
        botonCrearDevorador.setOnAction( event -> imperioZerg.evolucionarMutaliscoADevorador(mutalisco));

        //verificamos que se puede evolucionar

        prepararHabilitacionDeBoton(botonCrearGuardian, new Guardian(), imperioZerg, arrayWrappersBotonesEdificio[2], mutalisco);
        prepararHabilitacionDeBoton(botonCrearDevorador, new Devorador(), imperioZerg, arrayWrappersBotonesEdificio[3], mutalisco);
    }

    private void prepararHabilitacionDeBoton(Button boton, Unidad unidad, Zerg imperioZerg, Pane wrapperBoton, Unidad mutalisco) {
        JSONObject guardianJSON = ConvertidorJSON.convertirAJSON(unidad);

        String identificadorUnidad = (String) guardianJSON.get(ConvertidorJSON.OCUPABLE);
        String costoMineral = (String) guardianJSON.get("costoMineral");
        String costoGas = (String) guardianJSON.get("costoGas");

        try {
            imperioZerg.estaAptoParaEvolucionarA(unidad, mutalisco);
            boton.setTooltip(new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + "\n Minerales necesarios: " + costoMineral + "\n Gas necesario: " + costoGas));
        } catch (ErrorCantidadDeRecursoInsuficiente error) {
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + " \nNo hay suficientes recursos como para crear a esta unidad" + "\n Minerales necesarios: " + costoMineral + "\n Gas necesario: " + costoGas));
        } catch (ErrorEstaUnidadYaEstaEvolucionando error) {
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("Esta unidad ya está evolucionando"));
        }
    }
}

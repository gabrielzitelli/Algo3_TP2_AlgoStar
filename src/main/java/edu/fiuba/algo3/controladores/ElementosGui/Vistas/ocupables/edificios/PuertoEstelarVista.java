package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.ScoutVista;
import edu.fiuba.algo3.controladores.MapaControlador;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.PuertoEstelar;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaScout;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosRequisitosDeEstaUnidad;
import edu.fiuba.algo3.modelo.Excepciones.ErrorSuperaMaximoDePoblacionActual;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;

import java.util.Objects;

public class PuertoEstelarVista extends OcupableVista {

    public PuertoEstelarVista() {
        this.tile = new Tile("edificios_protoss/32px/puerto_estelar.png");
        this.identificador = "puerto_estelar";
        this.info = "Puerto estelar";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/puerto_estelarRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/construccion/puerto_estelarRawConstruir.png")));
    }

    @Override
    public void aplicarTextoEscudo(Text textoEscudo, JSONObject ocupableJson){
        textoEscudo.setText(ocupableJson.get(ConvertidorJSON.ESCUDO) + "/" + ocupableJson.get(ConvertidorJSON.ESCUDOMAX));
    }

    public void manejarBotones(Button[] arrayBotones, Pane[] arrayWrappersBotonesEdificio, Coordenada coordenada, String imperioDeJugadorActual, MapaControlador mapaControlador){
        if(!imperioDeJugadorActual.equalsIgnoreCase("protoss"))
            return;

        PuertoEstelar estePuerto = (PuertoEstelar) Mapa.obtener().obtenerOcupable(coordenada);

        Button botonCrearScout = arrayBotones[0];

        double anchoBoton = botonCrearScout.getPrefWidth();
        double altoBoton = botonCrearScout.getPrefWidth();

        crearBotonDeUnidad(botonCrearScout, new ScoutVista(), anchoBoton, altoBoton);

        botonCrearScout.setOnAction( event -> estePuerto.crearUnidad(new FabricaScout()));

        prepararHabilitacionDeBoton(botonCrearScout, new FabricaScout(), estePuerto, arrayWrappersBotonesEdificio[0], new ScoutVista());
    }

    private void prepararHabilitacionDeBoton(Button boton, Fabrica unaFabrica, PuertoEstelar unPuerto, Pane wrapperBoton, OcupableVista ocupableVista){
        Unidad unidadACrear = unaFabrica.crearUnidad();

        String informacionUnidad = unidadACrear.toString();
        String identificadorUnidad = ocupableVista.getInfo();
        String costoMineral = obtenerAtributoDeString(informacionUnidad, "costoMineral");
        String costoGas = obtenerAtributoDeString(informacionUnidad, "costoGas");
        String poblacionNecesaria = Integer.toString( unaFabrica.obtenerPoblacionNecesariaInstancia() ) ;

        if(unPuerto.toString().contains(" estado en_construccion")){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("El acceso de encuentra en construcción"));
            return;
        }

        try{
            unPuerto.estaAptaUnidadParaConstruir(unaFabrica);
            boton.setTooltip(new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + "\n Minerales necesarios: " + costoMineral + "\n Gas necesario: " + costoGas + "\n Ocupa: " + poblacionNecesaria + " de población"));
        } catch (ErrorNoSeCumplenLosRequisitosDeEstaUnidad exception){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + "\nNo está disponible el edificio que permite construir esta unidad" + "\n Minerales necesarios: " + costoMineral + "\n Gas necesario: " + costoGas + "\n Ocupa: " + poblacionNecesaria + " de población"));
        } catch (ErrorCantidadDeRecursoInsuficiente exception){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + "\nNo hay suficientes recursos como para crear a esta unidad" + "\n Minerales necesarios: " + costoMineral + "\n Gas necesario: " + costoGas + "\n Ocupa: " + poblacionNecesaria + " de población"));
        } catch (ErrorSuperaMaximoDePoblacionActual exception){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + "\nNo hay suministro de población suficiente para crear a esta unidad" + "\n Minerales necesarios: " + costoMineral + "\n Gas necesario: " + costoGas + "\n Ocupa: " + poblacionNecesaria + " de población"));
        }
    }
}

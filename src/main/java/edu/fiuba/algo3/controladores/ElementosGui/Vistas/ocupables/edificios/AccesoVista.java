package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ControladorEfectosSonido;
import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.DragonVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.UnidadVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.ZealotVista;
import edu.fiuba.algo3.controladores.MapaControlador;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Acceso;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaDragon;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.FabricaZealot;
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

public class AccesoVista extends EdificioVista {

    public AccesoVista() {
        this.tile = new Tile("edificios_protoss/32px/acceso.png");
        this.identificador = "acceso";
        this.info = "Acceso";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/accesoRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/construccion/accesoRawConstruir.png")));
        this.edificiosRequisitos = "\n" + emojiInformacionUnicode + " Requiere: Suelo Cargado y sin Moho";
    }

    @Override
    public void aplicarTextoEscudo(Text textoEscudo, JSONObject ocupableJson){
        textoEscudo.setText(ocupableJson.get(ConvertidorJSON.ESCUDO) + "/" + ocupableJson.get(ConvertidorJSON.ESCUDOMAX));
    }

    public void manejarBotones(Button[] arrayBotones, Pane[] arrayWrappersBotonesEdificio, Coordenada coordenada, String imperioDeJugadorActual, MapaControlador mapaControlador){
        if(!imperioDeJugadorActual.equalsIgnoreCase("protoss"))
            return;

        Acceso esteAcceso = (Acceso) Mapa.obtener().obtenerOcupable(coordenada);

        Button botonCrearZealot = arrayBotones[0];
        Button botonCrearDragon = arrayBotones[1];
        double anchoBoton = botonCrearZealot.getPrefWidth();
        double altoBoton = botonCrearZealot.getPrefWidth();

        ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();

        crearBotonDeUnidad(botonCrearZealot, new ZealotVista(), anchoBoton, altoBoton);
        crearBotonDeUnidad(botonCrearDragon, new DragonVista(), anchoBoton, altoBoton);

        botonCrearZealot.setOnAction( event -> {
            sonido.reproducirFX("boton");
            esteAcceso.crearUnidad(new FabricaZealot());
        });
        botonCrearDragon.setOnAction( event -> {
            sonido.reproducirFX("boton");
            esteAcceso.crearUnidad(new FabricaDragon());
        });

        prepararHabilitacionDeBoton(botonCrearZealot, new FabricaZealot(), esteAcceso, arrayWrappersBotonesEdificio[0], new ZealotVista());
        prepararHabilitacionDeBoton(botonCrearDragon, new FabricaDragon(), esteAcceso, arrayWrappersBotonesEdificio[1], new DragonVista());
    }

    private void prepararHabilitacionDeBoton(Button boton, Fabrica unaFabrica, Acceso unAcceso, Pane wrapperBoton, UnidadVista unidadVista){
        Unidad unidadACrear = unaFabrica.crearUnidad();

        String informacionUnidad = unidadACrear.toString();
        String identificadorUnidad = unidadVista.getInfo();
        String costoMineral = obtenerAtributoDeString(informacionUnidad, "costoMineral");
        String costoGas = obtenerAtributoDeString(informacionUnidad, "costoGas");
        String poblacionNecesaria = Integer.toString( unaFabrica.obtenerPoblacionNecesariaInstancia() ) ;
        String requisitosUnidad = unidadVista.getRequisitosUnidad();
        String informacionDeCosto = String.format("%s\n%s Minerales necesarios: %s\n%s Gas necesario: %s\n%s Ocupa: %s de población", requisitosUnidad, emojiBulletPoint, costoMineral, emojiBulletPoint, costoGas, emojiBulletPoint, poblacionNecesaria);

        if(unAcceso.toString().contains(" estado en_construccion")){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("El acceso de encuentra en construcción"));
            return;
        }

        try{
            unAcceso.estaAptaUnidadParaConstruir(unaFabrica);
            boton.setTooltip(new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + informacionDeCosto));

        } catch (ErrorNoSeCumplenLosRequisitosDeEstaUnidad exception){
            boton.setDisable(true);
            String informacionNoSePuedeConstruir = "\n" + emojiAdvertenciaUnidode + " No está disponible el edificio que permite construir esta unidad";
            Tooltip.install(wrapperBoton, new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + informacionNoSePuedeConstruir + informacionDeCosto));

        } catch (ErrorCantidadDeRecursoInsuficiente exception){
            boton.setDisable(true);
            String informacionNoSePuedeConstruir = "\n" + emojiAdvertenciaUnidode + " No hay suficientes recursos como para crear a esta unidad";
            Tooltip.install(wrapperBoton, new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + informacionNoSePuedeConstruir + informacionDeCosto));

        } catch (ErrorSuperaMaximoDePoblacionActual exception){
            boton.setDisable(true);
            String informacionNoSePuedeConstruir = "\n" + emojiAdvertenciaUnidode + " No hay suministro de población suficiente para crear a esta unidad";
            Tooltip.install(wrapperBoton, new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + informacionNoSePuedeConstruir + informacionDeCosto));
        }
    }
}

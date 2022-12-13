package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ControladorEfectosSonido;
import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.ScoutVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.UnidadVista;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;

import java.util.Objects;

public class PuertoEstelarVista extends EdificioVista {

    public PuertoEstelarVista() {
        this.tile = new Tile("edificios_protoss/32px/puerto_estelar.png");
        this.identificador = "puerto_estelar";
        this.info = "Puerto estelar";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/puerto_estelarRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/construccion/puerto_estelarRawConstruir.png")));
        this.edificiosRequisitos = "\n" + emojiInformacionUnicode + " Requiere: Suelo Cargado y sin Moho, Acceso";
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

        botonCrearScout.setOnAction( event -> {
            ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();
            sonido.reproducirFX("boton");
            estePuerto.crearUnidad(new FabricaScout());
        });

        prepararHabilitacionDeBoton(botonCrearScout, new FabricaScout(), estePuerto, arrayWrappersBotonesEdificio[0], new ScoutVista());
    }

    private void prepararHabilitacionDeBoton(Button boton, Fabrica unaFabrica, PuertoEstelar unPuerto, Pane wrapperBoton, UnidadVista unidadVista){
        Unidad unidadACrear = unaFabrica.crearUnidad();

        String informacionUnidad = unidadACrear.toString();
        String identificadorUnidad = unidadVista.getInfo();
        String costoMineral = obtenerAtributoDeString(informacionUnidad, "costoMineral");
        String costoGas = obtenerAtributoDeString(informacionUnidad, "costoGas");
        String poblacionNecesaria = Integer.toString( unaFabrica.obtenerPoblacionNecesariaInstancia() ) ;
        String requisitosUnidad = unidadVista.getRequisitosUnidad();
        String informacionDeCosto = String.format("%s\n%s Minerales necesarios: %s\n%s Gas necesario: %s\n%s Ocupa: %s de población", requisitosUnidad, emojiBulletPoint, costoMineral, emojiBulletPoint, costoGas, emojiBulletPoint, poblacionNecesaria);

        if(unPuerto.toString().contains(" estado en_construccion")){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("El acceso de encuentra en construcción"));
            return;
        }

        try{
            unPuerto.estaAptaUnidadParaConstruir(unaFabrica);
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

    public void actualizarEfectoSiEstaEnConstruccion(ImageView imageView, Coordenada coordenada){
        PuertoEstelar estePuertoEstelar = (PuertoEstelar) Mapa.obtener().obtenerOcupable(coordenada);

        if(estePuertoEstelar.toString().contains(" estado en_construccion"))
            aplicarEfectoBlancoYNegroImageView(imageView);
    }
}

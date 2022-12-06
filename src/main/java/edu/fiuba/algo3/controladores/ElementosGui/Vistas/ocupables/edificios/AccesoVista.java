package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.DragonVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.ZealotVista;
import edu.fiuba.algo3.modelo.Edificios.EdificiosProtoss.Acceso;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCriaderoNoTieneMasLarvas;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosRequisitosDeEstaUnidad;
import edu.fiuba.algo3.modelo.Excepciones.ErrorSuperaMaximoDePoblacionActual;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Objects;

public class AccesoVista extends OcupableVista {

    public AccesoVista() {
        this.tile = new Tile("edificios_protoss/32px/acceso.png");
        this.identificador = "acceso";
        this.info = "Acceso";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/accesoRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_protoss/original/construccion/accesoRawConstruir.png")));
    }

    @Override
    public void aplicarTextoEscudo(Text textoEscudo, String stringOcupable){
        String escudoActual = obtenerAtributoDeString(stringOcupable, "escudoActual");
        String escudoMaxima = obtenerAtributoDeString(stringOcupable, "escudoMaximo");
        textoEscudo.setText(escudoActual + "/" + escudoMaxima);
    }

    public void manejarBotones(Button[] arrayBotones, Pane[] arrayWrappersBotonesEdificio, Coordenada coordenada, String imperioDeJugadorActual){
        if(!imperioDeJugadorActual.equalsIgnoreCase("protoss"))
            return;

        Acceso esteAcceso = (Acceso) Mapa.obtener().obtenerOcupable(coordenada);

        Button botonCrearZealot = arrayBotones[0];
        Button botonCrearDragon = arrayBotones[1];
        double anchoBoton = botonCrearZealot.getPrefWidth();
        double altoBoton = botonCrearZealot.getPrefWidth();

        crearBotonDeUnidad(botonCrearZealot, new ZealotVista(), anchoBoton, altoBoton);
        crearBotonDeUnidad(botonCrearDragon, new DragonVista(), anchoBoton, altoBoton);

        botonCrearZealot.setOnAction( event -> esteAcceso.crearUnidad(new FabricaZealot()));
        botonCrearDragon.setOnAction( event -> esteAcceso.crearUnidad(new FabricaDragon()));

        prepararHabilitacionDeBoton(botonCrearZealot, new FabricaZealot(), esteAcceso, arrayWrappersBotonesEdificio[0], new ZealotVista());
        prepararHabilitacionDeBoton(botonCrearDragon, new FabricaDragon(), esteAcceso, arrayWrappersBotonesEdificio[1], new DragonVista());
    }

    private void prepararHabilitacionDeBoton(Button boton, Fabrica unaFabrica, Acceso unAcceso, Pane wrapperBoton, OcupableVista ocupableVista){
        Unidad unidadACrear = unaFabrica.crearUnidad();

        String informacionUnidad = unidadACrear.toString();
        String identificadorUnidad = ocupableVista.getInfo();
        String costoMineral = obtenerAtributoDeString(informacionUnidad, "costoMineral");
        String costoGas = obtenerAtributoDeString(informacionUnidad, "costoGas");
        String poblacionNecesaria = Integer.toString( unaFabrica.obtenerPoblacionNecesariaInstancia() ) ;

        if(unAcceso.toString().contains(" estado en_construccion")){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("El acceso de encuentra en construcción"));
            return;
        }

        try{
            unAcceso.estaAptaUnidadParaConstruir(unaFabrica);
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

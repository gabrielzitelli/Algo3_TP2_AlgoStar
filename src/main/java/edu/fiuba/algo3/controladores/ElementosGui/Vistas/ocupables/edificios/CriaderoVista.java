package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

import edu.fiuba.algo3.controladores.ControladorEfectosSonido;
import edu.fiuba.algo3.controladores.ElementosGui.Tile;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.OcupableVista;
import edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.unidades.*;
import edu.fiuba.algo3.controladores.MapaControlador;
import edu.fiuba.algo3.modelo.Edificios.EdificiosZerg.Criadero;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.*;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCantidadDeRecursoInsuficiente;
import edu.fiuba.algo3.modelo.Excepciones.ErrorCriaderoNoTieneMasLarvas;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSeCumplenLosRequisitosDeEstaUnidad;
import edu.fiuba.algo3.modelo.Excepciones.ErrorSuperaMaximoDePoblacionActual;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Objects;


public class CriaderoVista extends OcupableVista {

    Tile piso = new Tile("superficies/32px/moho_transparente.png");
    public CriaderoVista(){
        this.tile = new Tile("edificios_zerg/32px/criadero.png");
        this.identificador = "criadero";
        this.info = "Criadero";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_zerg/original/criaderoRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_zerg/original/construccion/criaderoRawConstruir.png")));
    }

    @Override
    public void render(GraphicsContext gc, int x, int y) {
        piso.render(gc, x, y);
        super.render(gc,x,y);
    }

    public void manejarBotones(Button[] arrayBotones, Pane[] arrayWrappersBotonesEdificio, Coordenada coordenada, String imperioDeJugadorActual, MapaControlador mapaControlador) {
        if (!imperioDeJugadorActual.equalsIgnoreCase("zerg"))
            return;

        Criadero esteCriadero = (Criadero) Mapa.obtener().obtenerOcupable(coordenada);

        Button botonCrearZangano = arrayBotones[0];
        Button botonCrearZerling = arrayBotones[1];
        Button botonCrearHidralisco = arrayBotones[2];
        Button botonCrearMutalisco = arrayBotones[3];
        Button botonCrearAmoSupremo = arrayBotones[4];

        double anchoBoton = botonCrearZangano.getPrefWidth();
        double altoBoton = botonCrearZangano.getPrefWidth();

        ControladorEfectosSonido sonido = ControladorEfectosSonido.obtenerControlador();

        crearBotonDeUnidad(botonCrearZangano, new ZanganoVista(), anchoBoton, altoBoton);
        crearBotonDeUnidad(botonCrearZerling, new ZerlingVista(), anchoBoton, altoBoton);
        crearBotonDeUnidad(botonCrearHidralisco, new HidraliscoVista(), anchoBoton, altoBoton);
        crearBotonDeUnidad(botonCrearMutalisco, new MutaliscoVista(), anchoBoton, altoBoton);
        crearBotonDeUnidad(botonCrearAmoSupremo, new AmoSupremoVista(), anchoBoton, altoBoton);

        botonCrearZangano.setOnAction( event -> {
            sonido.reproducirFX("boton");
            esteCriadero.crearUnidad(new FabricaZangano());
        });
        botonCrearZerling.setOnAction( event -> {
            sonido.reproducirFX("boton");
            esteCriadero.crearUnidad(new FabricaZerling());
        });
        botonCrearHidralisco.setOnAction( event -> {
            sonido.reproducirFX("boton");
            esteCriadero.crearUnidad(new FabricaHidralisco());
        });
        botonCrearMutalisco.setOnAction( event -> {
            sonido.reproducirFX("boton");
            esteCriadero.crearUnidad(new FabricaMutalisco());
        });
        botonCrearAmoSupremo.setOnAction( event -> {
            sonido.reproducirFX("boton");
            esteCriadero.crearUnidad(new FabricaAmoSupremo());
        });

        prepararHabilitacionDeBoton(botonCrearZangano, new FabricaZangano(), esteCriadero, arrayWrappersBotonesEdificio[0], new ZanganoVista());
        prepararHabilitacionDeBoton(botonCrearZerling, new FabricaZerling(), esteCriadero, arrayWrappersBotonesEdificio[1], new ZerlingVista());
        prepararHabilitacionDeBoton(botonCrearHidralisco, new FabricaHidralisco(), esteCriadero, arrayWrappersBotonesEdificio[2], new HidraliscoVista());
        prepararHabilitacionDeBoton(botonCrearMutalisco, new FabricaMutalisco(), esteCriadero, arrayWrappersBotonesEdificio[3], new MutaliscoVista());
        prepararHabilitacionDeBoton(botonCrearAmoSupremo, new FabricaAmoSupremo(), esteCriadero, arrayWrappersBotonesEdificio[4], new AmoSupremoVista());
    }

    private void prepararHabilitacionDeBoton(Button boton, Fabrica unaFabrica, Criadero unCriadero, Pane wrapperBoton, OcupableVista ocupableVista){
        Unidad unidadACrear = unaFabrica.crearUnidad();

        String informacionUnidad = unidadACrear.toString();
        String identificadorUnidad = ocupableVista.getInfo();
        String costoMineral = obtenerAtributoDeString(informacionUnidad, "costoMineral");
        String costoGas = obtenerAtributoDeString(informacionUnidad, "costoGas");
        String poblacionNecesaria = Integer.toString( unaFabrica.obtenerPoblacionNecesariaInstancia() ) ;

        if(unCriadero.toString().contains(" estado en_construccion")){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("El criadero de encuentra en construcción"));
            return;
        }

        try{
            unCriadero.estaAptaUnidadParaConstruir(unaFabrica);
            boton.setTooltip(new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + "\n Minerales necesarios: " + costoMineral + "\n Gas necesario: " + costoGas + "\n Ocupa: " + poblacionNecesaria + " de población"));
        }catch (ErrorNoSeCumplenLosRequisitosDeEstaUnidad exception){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + "\nNo está disponible el edificio que permite construir esta unidad" + "\n Minerales necesarios: " + costoMineral + "\n Gas necesario: " + costoGas + "\n Ocupa: " + poblacionNecesaria + " de población"));
        }  catch (ErrorCriaderoNoTieneMasLarvas exception){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + "\nNo hay larvas disponibles para crear esta unidad" + "\n Minerales necesarios: " + costoMineral + "\n Gas necesario: " + costoGas + "\n Ocupa: " + poblacionNecesaria + " de población"));
        } catch (ErrorCantidadDeRecursoInsuficiente exception){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + "\nNo hay suficientes recursos como para crear a esta unidad" + "\n Minerales necesarios: " + costoMineral + "\n Gas necesario: " + costoGas + "\n Ocupa: " + poblacionNecesaria + " de población"));
        } catch (ErrorSuperaMaximoDePoblacionActual exception){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + "\nNo hay suministro de población suficiente para crear a esta unidad" + "\n Minerales necesarios: " + costoMineral + "\n Gas necesario: " + costoGas + "\n Ocupa: " + poblacionNecesaria + " de población"));
        }
    }
}

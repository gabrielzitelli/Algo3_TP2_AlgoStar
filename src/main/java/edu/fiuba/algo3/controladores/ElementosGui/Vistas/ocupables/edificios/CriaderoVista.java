package edu.fiuba.algo3.controladores.ElementosGui.Vistas.ocupables.edificios;

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


public class CriaderoVista extends EdificioVista {

    Tile piso = new Tile("superficies/32px/moho_transparente.png");
    public CriaderoVista(){
        this.tile = new Tile("edificios_zerg/32px/criadero.png");
        this.identificador = "criadero";
        this.info = "Criadero";
        this.imagenParaDisplay = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_zerg/original/criaderoRaw.png")));
        this.imagenParaBoton = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/edificios_zerg/original/evolucion/criaderoRawEvolucion.png")));
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

        crearBotonDeUnidad(botonCrearZangano, new ZanganoVista(), anchoBoton, altoBoton);
        crearBotonDeUnidad(botonCrearZerling, new ZerlingVista(), anchoBoton, altoBoton);
        crearBotonDeUnidad(botonCrearHidralisco, new HidraliscoVista(), anchoBoton, altoBoton);
        crearBotonDeUnidad(botonCrearMutalisco, new MutaliscoVista(), anchoBoton, altoBoton);
        crearBotonDeUnidad(botonCrearAmoSupremo, new AmoSupremoVista(), anchoBoton, altoBoton);

        botonCrearZangano.setOnAction( event -> esteCriadero.crearUnidad(new FabricaZangano()));
        botonCrearZerling.setOnAction( event -> esteCriadero.crearUnidad(new FabricaZerling()));
        botonCrearHidralisco.setOnAction( event -> esteCriadero.crearUnidad(new FabricaHidralisco()));
        botonCrearMutalisco.setOnAction( event -> esteCriadero.crearUnidad(new FabricaMutalisco()));
        botonCrearAmoSupremo.setOnAction( event -> esteCriadero.crearUnidad(new FabricaAmoSupremo()));

        prepararHabilitacionDeBoton(botonCrearZangano, new FabricaZangano(), esteCriadero, arrayWrappersBotonesEdificio[0], new ZanganoVista());
        prepararHabilitacionDeBoton(botonCrearZerling, new FabricaZerling(), esteCriadero, arrayWrappersBotonesEdificio[1], new ZerlingVista());
        prepararHabilitacionDeBoton(botonCrearHidralisco, new FabricaHidralisco(), esteCriadero, arrayWrappersBotonesEdificio[2], new HidraliscoVista());
        prepararHabilitacionDeBoton(botonCrearMutalisco, new FabricaMutalisco(), esteCriadero, arrayWrappersBotonesEdificio[3], new MutaliscoVista());
        prepararHabilitacionDeBoton(botonCrearAmoSupremo, new FabricaAmoSupremo(), esteCriadero, arrayWrappersBotonesEdificio[4], new AmoSupremoVista());
    }

    private void prepararHabilitacionDeBoton(Button boton, Fabrica unaFabrica, Criadero unCriadero, Pane wrapperBoton, UnidadVista unidadVista){
        Unidad unidadACrear = unaFabrica.crearUnidad();

        String informacionUnidad = unidadACrear.toString();
        String identificadorUnidad = unidadVista.getInfo();
        String costoMineral = obtenerAtributoDeString(informacionUnidad, "costoMineral");
        String costoGas = obtenerAtributoDeString(informacionUnidad, "costoGas");
        String poblacionNecesaria = Integer.toString( unaFabrica.obtenerPoblacionNecesariaInstancia() );
        String requisitosUnidad = unidadVista.getRequisitosUnidad();
        String informacionDeCosto = String.format("%s\n%s Minerales necesarios: %s\n%s Gas necesario: %s\n%s Ocupa: %s de población", requisitosUnidad, emojiBulletPoint, costoMineral, emojiBulletPoint, costoGas, emojiBulletPoint, poblacionNecesaria);

        if(unCriadero.toString().contains(" estado en_construccion")){
            boton.setDisable(true);
            Tooltip.install(wrapperBoton, new Tooltip("El criadero de encuentra en construcción"));
            return;
        }

        try{
            unCriadero.estaAptaUnidadParaConstruir(unaFabrica);
            boton.setTooltip(new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + informacionDeCosto));
            
        }catch (ErrorNoSeCumplenLosRequisitosDeEstaUnidad exception){
            boton.setDisable(true);
            String informacionNoSePuedeConstruir = "\n" + emojiAdvertenciaUnidode + " No está disponible el edificio que permite construir esta unidad";
            Tooltip.install(wrapperBoton, new Tooltip("CREAR " + identificadorUnidad.toUpperCase() + informacionNoSePuedeConstruir + informacionDeCosto));
        
        }  catch (ErrorCriaderoNoTieneMasLarvas exception){
            boton.setDisable(true);
            String informacionNoSePuedeConstruir = "\n" + emojiAdvertenciaUnidode + " No hay larvas disponibles para crear esta unidad";
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

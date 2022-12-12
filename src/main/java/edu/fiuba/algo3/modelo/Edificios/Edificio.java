package edu.fiuba.algo3.modelo.Edificios;

import edu.fiuba.algo3.modelo.AlgoStar.Logger;
import edu.fiuba.algo3.modelo.Ataque.Ataque;
import edu.fiuba.algo3.modelo.ConvertidorJson.ConvertidorJSON;
import edu.fiuba.algo3.modelo.Edificios.Fabricas.Fabrica;
import edu.fiuba.algo3.modelo.Edificios.Vida.Vida;
import edu.fiuba.algo3.modelo.Excepciones.ErrorElEdificioNoPuedeContratarUnidades;
import edu.fiuba.algo3.modelo.Excepciones.ErrorElEdificioNoPuedeCrearUnidades;
import edu.fiuba.algo3.modelo.Excepciones.ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada;
import edu.fiuba.algo3.modelo.Imperio.*;
import edu.fiuba.algo3.modelo.Mapa.Casilla.*;
import edu.fiuba.algo3.modelo.Mapa.Coordenada;
import edu.fiuba.algo3.modelo.Mapa.Mapa;
import edu.fiuba.algo3.modelo.Unidades.Ocupable;
import edu.fiuba.algo3.modelo.Unidades.Unidad;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import org.json.simple.JSONObject;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public abstract class Edificio implements Ocupable {

    protected Vida vida;
    protected Coordenada coordenada;
    protected Recolectable estadoRecolectable;
    protected Cargable estadoCargaRequerido;
    protected EstadoMoho estadoMohoRequerido;
    protected Superficie superficieRequerida;
    protected int costoMineral = 0;
    protected int costoGas = 0;
    protected Mineral mineralDelImperio;
    protected Gas gasDelImperio;
    protected int suministroAportado;
    protected boolean estaDestruido = false;

    protected String identificador;

    public abstract void pasarTurno();

    public abstract boolean perteneceAImperio(Imperio imperio);
    public abstract ArrayList<Edificio> obtenerRequisitosEdilicios();

    public void verificarColocable(Casilla unaCasilla){
        if (estadoRecolectable != null)
            unaCasilla.tieneEsteRecoletable(estadoRecolectable);
        if (estadoMohoRequerido != null)
            unaCasilla.tieneEsteMoho(estadoMohoRequerido);
        if (estadoCargaRequerido != null)
            unaCasilla.tieneEstaCarga(estadoCargaRequerido);

        unaCasilla.tieneEstaSuperficie(superficieRequerida);

        coordenada = unaCasilla.obtenerCoordenada();
    }

    public void actualizarColocable(Casilla unaCasilla) {
        verificarColocable(unaCasilla);
    }

    public ArrayList<Recurso> requisitosMateriales() {
        ArrayList<Recurso> requisitosMateriales = new ArrayList<>();
        requisitosMateriales.add(new Mineral(costoMineral));
        requisitosMateriales.add(new Gas(costoGas));
        return requisitosMateriales;
    }

    public void recibirAtaque(Ataque unAtaque) {
        try {

            JSONObject edificioJSON = ConvertidorJSON.convertirAJSON(this);

            String nombreEdificio = (String) edificioJSON.get(ConvertidorJSON.OCUPABLE);
            int vidaAntes = parseInt((String)edificioJSON.get(ConvertidorJSON.VIDA));

            this.vida.aplicarAtaque(unAtaque.ataqueTerrestre());

            edificioJSON = ConvertidorJSON.convertirAJSON(this);

            int vidaActual = parseInt((String) edificioJSON.get(ConvertidorJSON.VIDA));
            int daño = vidaAntes - vidaActual;

            Logger.obtener().log("el Edificio '" + nombreEdificio + "' [X: " +
                    coordenada.getCoordenadaX() + ", Y: " + coordenada.getCoordenadaY() + "] " +
                    "recibió " + daño + " de daño. Su vida restante es: " + vidaActual);
        }
        catch (Exception ErrorVidaLlegoACero){
            if (coordenada != null)
                this.destruirEdificio();
        }
    }

    protected void destruirEdificio() {
        Mapa elMapa = Mapa.obtener();
        elMapa.quitarOcupable(coordenada);
        this.estaDestruido = true;
        Logger.obtener().log("Ha sido destruido el edificio " + this.getClass().getSimpleName() +
                " que estaba ubicado en la casilla [X: " + coordenada.getCoordenadaX() + ", Y: " + coordenada.getCoordenadaY() + "].");
    }

    public boolean esIgualA(Edificio edificio) {
        return this.getClass().equals(edificio.getClass());
    }

    public void crearUnidad(Fabrica unaFabrica) {
        throw new ErrorElEdificioNoPuedeCrearUnidades();
    }

    public void contratarUnidad(Unidad unidad) {
        throw new ErrorElEdificioNoPuedeContratarUnidades();
    }

    public void modificarPoblacion(Suministro poblacion){}

    public boolean estaDestruido(){
        return this.estaDestruido;
    }
    public void asignarRecursos(Mineral mineralesDelImperio, Gas gasDelImperio) {
        this.mineralDelImperio = mineralesDelImperio;
        this.gasDelImperio = gasDelImperio;
    }

    protected abstract String obtenerEstado();
    @Override
    public String toString() {
        return "ocupable " + this.identificador + " costoMineral " + this.costoMineral + " costoGas " + this.costoGas + " estado " + this.obtenerEstado() + vida.toString();
    }
    
    public boolean esDeEsteTipo(Class claseAAverificar){
        return Edificio.class.equals(claseAAverificar);
    }

    public void construirSobreCasillaOcupadaVerificacion(Ocupable ocupable, CasillaVacia copiaVaciaDeUnaCasilla){
        throw new ErrorNoSePuedeColocarOcupableEnUnaCasillaOcupada();
    }
}

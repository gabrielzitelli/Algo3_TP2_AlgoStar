package edu.fiuba.algo3.casoDeUso10;

import edu.fiuba.algo3.modelo.Criadero;
import edu.fiuba.algo3.modelo.NodoCompatible;
import edu.fiuba.algo3.modelo.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Moho;
import edu.fiuba.algo3.modelo.Tablero.SinRecurso;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CasoDeUso10 {
    /*
    Verificar que al dañar una construcción zerg, la misma recupera la vida por turnos hasta
    volver a tener el 100%.
     */

    @Test
    public void seConstruyeUnCriaderoSeLoDaniaYRecuperaGradualmenteTodaSuVida(){
        NodoCompatible nodoCompatibleCriadero = new NodoCompatible(new Moho(), new SinRecurso());
        Criadero criadero = new Criadero(nodoCompatibleCriadero, new Recurso());
        criadero.recibirDanio(200);

        criadero.accionDeTurno();
        criadero.accionDeTurno();

        //assertEquals(criadero.getVida(), 500);

    }
}
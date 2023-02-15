
package aec_1;

import aec_1.Algoritmo.TipoCruce;
import aec_1.Algoritmo.TipoOperacion;
import aec_1.Algoritmo.TipoSeleccion;
import java.util.Arrays;
import java.util.Random;

/*Clase Principal para probar el algoritmo genetico
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        // Parametros de configuracion del algoritmo
        int TAMANO_POBLACION = 5;
        int TAMANO_ELITE = 1;
        int NUMERO_MAXIMO_GENERACIONES = 50;
        TipoSeleccion TIPO_SELECCION = TipoSeleccion.TORNEO;
        double PROBABILIDAD_CRUCE = 0.5;
        TipoCruce TIPO_CRUCE = TipoCruce.UNIFORME;
        double PROBABILIDAD_MUTACION = 0.2;
        TipoOperacion TIPO_OPERACION = TipoOperacion.MAXIMIZAR;
        
        // Propiedades de los individuos
        int limiteInferior = 0;
        int limiteSuperior = 1;
        int toleranciaRepresentacion = 3;
        
        // Creacion de la pobleacion inicial: se crearan TAMANO_POBLACION cromosomas
        // concretos de la Funcion1 de forma aleatoria
        Cromosoma[] poblacion = new Cromosoma[TAMANO_POBLACION];
        for (int i = 0; i < TAMANO_POBLACION; i++) {
            poblacion[i] = new Funcion1(limiteInferior,
            limiteSuperior, toleranciaRepresentacion);
        }
        
        // Ejecucion del algoritmo
        Algoritmo algoritmo = new Algoritmo();
        algoritmo.setNumeroMaximoGeneraciones(NUMERO_MAXIMO_GENERACIONES);
        algoritmo.setTamanoElite(TAMANO_ELITE);
        algoritmo.setTipoSeleccion(TIPO_SELECCION);
        algoritmo.setProbabilidadCruce(PROBABILIDAD_CRUCE);
        algoritmo.setTipoCruce(TIPO_CRUCE);
        algoritmo.setProbabilidadMutacion(PROBABILIDAD_MUTACION);
        algoritmo.ejecutar(poblacion, TIPO_OPERACION);
    
    }
}
    


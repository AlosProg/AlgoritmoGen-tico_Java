
package aec_1;

import java.util.Comparator;

/*Clase que implementa de la interfaz Comparator el metodo compare para comparar la aptitud de cada individuo
 */
public class OrdenacionMinimizacion implements Comparator<Cromosoma> {
    
    //Constructor    
    public OrdenacionMinimizacion() {
    }
    @Override
    public int compare(Cromosoma cr1, Cromosoma cr2) {//Metodo sobreescrito que va a comparar la aptitud de dos individuos
        
        int resultado = 0;

        if (((Cromosoma) cr1).aptitud() > ((Cromosoma) cr2).aptitud()) {
            resultado = 1;//Si la aptitud de un individuo x es mayor que la de un individuo y  se devolvera valor 1
        } else if (((Cromosoma) cr1).aptitud() == ((Cromosoma) cr2).aptitud()) {
            resultado = 0;//Si la aptitud de un individuo x es igual que la de un individuo y  se devolvera valor 0
        } else {
            resultado = -1;//En otro caso devolvera valor -1
        }

        //Devuelve el resultado de la comparaciÃ³n
        return resultado;
    }
    
}

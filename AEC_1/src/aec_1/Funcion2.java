
package aec_1;

/*Clase con una funcion que extendera de la clase Cromosoma
 */
public class Funcion2 extends Cromosoma {
    
    //Constructor que coge un cromosoma concreto y le aplica la funcion, indicada más abajo, a minimizar
    public Funcion2 (double limiteInferior, double limiteSuperior, int toleranciaRepresentacion) {
        super(limiteInferior, limiteSuperior, toleranciaRepresentacion);
    }

    @Override
    public double aptitud() {
        double x = (double) fenotipo();
        return (Math.sin(x) / (1 + Math.sqrt(x) + (Math.cos(x)/(1 + x))));
    }
}



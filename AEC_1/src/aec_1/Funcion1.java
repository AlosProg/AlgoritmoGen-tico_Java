
package aec_1;

/*Clase con una funcion que extendera de la clase Cromosoma
 */
public class Funcion1 extends Cromosoma {
    
    //Constructor que coge un cromosoma concreto y le aplica la funcion, indicada más abajo, a maximizar
    public Funcion1(double limiteInferior, double limiteSuperior, int toleranciaRepresentacion) {
        super(limiteInferior, limiteSuperior, toleranciaRepresentacion);
    }

    @Override
    public double aptitud() {
        double x = (double) fenotipo();
        return x + Math.abs(Math.sin(32 * Math.PI * x));
    }
 }


package aec_1;

/*Clase con una funcion que extendera de la clase Cromosoma
 */
public class Funcion3 extends Cromosoma {
    
    //Constructor que coge un cromosoma concreto y le aplica la funcion, indicada más abajo, a maximizar
    public Funcion3(double limiteInferior, double limiteSuperior, int toleranciaRepresentacion) {
        super(limiteInferior, limiteSuperior, toleranciaRepresentacion);
    }

    @Override
    public double aptitud() {
        double x = (double) fenotipo();
        return (Math.sin(x) / (1 + (Math.cos(7 * x)/ (1 + x))));
    }
}


package aec_1;

import aec_1.Algoritmo.TipoSeleccion;
import java.util.Random;

/*Clase con métodos auxiliares utilizados por los métodos de la clase Cromosoma
 */

public class Utilidades {
    
    //Metodo que va a calcular la longitud de nuestro cromosoma según los parámetros que le digamos
    //Lo utiliza el método crearCromosoma() de la clase Cromosoma
    public static int obtenerLongitud(double limiteInferior, double limiteSuperior, int tolerancia) {
        double resta = limiteSuperior - limiteInferior;
        double division = resta / Math.pow(10, tolerancia * (-1));
        double suma = 1 + division;
        double logaritmo = Math.log10(suma) / Math.log10(2);
        int redondeo = (int) Math.rint(logaritmo) + 1;
        return redondeo;
    }
    
    //Metodo para generar un bit aleatorio de tipo 0 o 1
    //Lo utiliza el metodo crearCromosoma() de la clase Cromosoma
    public static boolean generarAleatorio(){
        Random random = new Random();
        return random.nextBoolean();
    }
    
    //Metodo que devuelve el valor decimal(entero) de array de booleanos
    //Lo utiliza el metodo fenotipo() de la clase Cromosoma
    public static int toIntValue(boolean[] genes) {
        int resultado = 0;
        boolean [] invertido = new boolean[genes.length];
        for (int i=0, j=genes.length-1; i<genes.length; i++, j--) { //con este for creamos un...
            invertido[j] = genes[i];//...array al reves del pasado por parametro
        }
        genes = invertido;// pasamos lo valores del array invertido a nuestro array del parametro y calculamos el valor entero
        for (int i = 0; i < genes.length ; i++){
            if (genes[i] == true){
                resultado = (int) (resultado + Math.pow(2, i));
            }
            else {
                resultado = resultado + 0;
            }
        }
        return resultado;
    }
    
    //Metodo que devuelve un entero aletoriamente de un rango entre 0 y el entero que se pase por parametro
    //Lo utiliza el metodo mutar() de la clase Cromosoma
    public static int generarAleatorioEnteros(int length) {
        Random random = new Random();
        return random.nextInt(length); 
    }
    //Metodo para generar aleatorios de enteros entre un rango determinado
    public static int generarAleatorioRangoEnteros(int minimo, int maximo){
        Random ran = new Random();
        int randomNum = minimo + ran.nextInt((maximo - minimo) - 1);
        return randomNum;
    }
    
    //Metodo para generar aleatorios de decimales entre un rango determinado
    public static double generarAleatorioRangoDouble(double minimo, double maximo){
        return Math.random()*(minimo - maximo)+ maximo;
    }
    
    //Metodo para generar un decimal con un tamanio maximo
    public static double generarAleatorioDouble(double tamano){
        return new java.util.Random().nextDouble();
    }
}

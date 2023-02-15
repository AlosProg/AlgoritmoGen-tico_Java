
package aec_1;

import java.util.Arrays;


/*Clase abstracta Cromosoma para crear los individuos de una poblacion
 */
public abstract class Cromosoma implements Cloneable {
    
    private boolean[] genes;
    private double limiteInferior;
    private double limiteSuperior;
    private int toleranciaRepresentacion;
    
    
    //Constructor para lo genes únicamente
    public Cromosoma(boolean[] genes) {
        this.genes = genes;
    }
    
    //Metodo Constructor
    public Cromosoma(double limiteInferior, double limiteSuperior, int toleranciaRepresentacion) {
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
        this.toleranciaRepresentacion = toleranciaRepresentacion;
        // Se crea el genotipo aleatoriamente
        this.crearCromosoma();
    }
    
    //Metodo para crear el cromosoma en funcion de sus parametros:
    //primero: nos calculará la longitud del cromosoma
    //segundo: nos generara los alelos de forma aleatoria del cromosoma creado 
    private void crearCromosoma() {
        int longitudCromosoma = Utilidades.obtenerLongitud(limiteInferior, limiteSuperior,toleranciaRepresentacion);
        genes = new boolean[longitudCromosoma];
        for (int i = 0; i < longitudCromosoma; i++) {
            genes[i] = Utilidades.generarAleatorio();
        }
        System.out.println(Arrays.toString(genes)); // prueba de cromosoma por pantalla
    }
    
    //Metodo que calcula el fenotipo de un individuo
    public double fenotipo() {
        int valorDecimal = Utilidades.toIntValue(genes);
        double fenotipo = limiteInferior + (limiteSuperior - limiteInferior) * valorDecimal / (Math.pow(2, genes.length) - 1);
        return fenotipo;
    }
    
    //Metodo para obtener un clon de un individuo cuando se requiera
    public Cromosoma clone() throws CloneNotSupportedException{        
        Cromosoma cloned = (Cromosoma)super.clone();
        return cloned;
    }
    
    //Metodo que cambia un solo bit aleatoriamente de un individuo
    public void mutar(){
        int posicion = Utilidades.generarAleatorioEnteros(genes.length);
        genes[posicion] = !genes[posicion];
        //System.out.println(Arrays.toString(genes));
    }

    //Metodo para obtener lo genes de un individuo
    public boolean [] getGenes(){
        return genes;
    }

    //Metodo para establecer genes de un individuo
    public void setGenes (boolean [] individuo){
        genes = individuo;
    }
    
    //Metodo que transforma los genes de un individuo de booleanos a binarios
    public String toString(){
        String genesBinarios = "";
        for (int i = 0; i < genes.length; i++){
            if (genes[i] == true){
                genesBinarios += "1";
            }
            else{
                genesBinarios += "0";
            }
        }
        return genesBinarios;
    }
    
    //Metodo abstracto para que cada cromosoma concreto elija la función de aptitud que quiera
    public abstract double aptitud();

    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
}

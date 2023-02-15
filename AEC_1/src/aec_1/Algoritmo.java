
package aec_1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/*Clase que contiene el algoritmo genético para conseguir maximizar los mejores individuos de una población dada
 */
public class Algoritmo {
    /* Atributos del algoritmo puestos por defecto */
    private int numeroMaximoGeneraciones;
    private int tamanoElite;
    private double probabilidadCruce;
    private double probabilidadMutacion;

    /*Enumeracion para tipo de ejecucion*/
    public enum TipoOperacion {
        MAXIMIZAR, MINIMIZAR
    }
    public enum TipoCruce{
        SPX, UNIFORME
    }
    public enum TipoSeleccion {
        RULETA, TORNEO
    }
    
    
    /* Atributos propios para la ejecucion del algoritmo */
    private Cromosoma[] poblacion;
    private int numeroGeneracionActual;
    private Cromosoma mejorIndividuo;
    private TipoSeleccion tipoSeleccion;
    private TipoCruce tipoCruce;
    private TipoOperacion tipoOperacion;
    
    //Constructor de la clase
    public Algoritmo() {
    }
    
    //Getters & Setters 
    public int getNumeroMaximoGeneraciones() {
        return numeroMaximoGeneraciones;
    }

    public int getTamanoElite() {
        return tamanoElite;
    }

    public double getProbabilidadCruce() {
        return probabilidadCruce;
    }

    public double getProbabilidadMutacion() {
        return probabilidadMutacion;
    }

    public Cromosoma[] getPoblacion() {
        return poblacion;
    }

    public int getNumeroGeneracionActual() {
        return numeroGeneracionActual;
    }

    public Cromosoma getMejorIndividuo() {
        return mejorIndividuo;
    }

    public void setNumeroMaximoGeneraciones(int numeroMaximoGeneraciones) {
        this.numeroMaximoGeneraciones = numeroMaximoGeneraciones;
    }

    public void setTamanoElite(int tamanoElite) {
        this.tamanoElite = tamanoElite;
    }

    public void setProbabilidadCruce(double probabilidadCruce) {
        this.probabilidadCruce = probabilidadCruce;
    }

    public void setProbabilidadMutacion(double probabilidadMutacion) {
        this.probabilidadMutacion = probabilidadMutacion;
    }

    public void setPoblacion(Cromosoma[] poblacion) {
        this.poblacion = poblacion;
    }

    public void setNumeroGeneracionActual(int numeroGeneracionActual) {
        this.numeroGeneracionActual = numeroGeneracionActual;
    }

    public void setMejorIndividuo(Cromosoma mejorIndividuo) {
        this.mejorIndividuo = mejorIndividuo;
    }

    public TipoSeleccion getTipoSeleccion() {
        return tipoSeleccion;
    }

    public TipoCruce getTipoCruce() {
        return tipoCruce;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoSeleccion(TipoSeleccion tipoSeleccion) {
        this.tipoSeleccion = tipoSeleccion;
    }

    public void setTipoCruce(TipoCruce tipoCruce) {
        this.tipoCruce = tipoCruce;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }
    
    
    /* Metodo principal de la actividad */
    public void ejecutar(Cromosoma[] poblacionActual, TipoOperacion tipoOperacion) throws CloneNotSupportedException {
        int contadorGeneraciones = 0; //Contador de numero de generaciones
        this.poblacion = poblacionActual;
        this.tipoOperacion = tipoOperacion;
        
        //Bucle general del algoritmo
        while (contadorGeneraciones < numeroMaximoGeneraciones){ 
            Cromosoma[] poblacionTemporal = new Cromosoma[this.poblacion.length];//Creamos la poblacion temporal de mismo tamaño que la poblacionm actual
            Cromosoma[] padres = new Cromosoma[this.poblacion.length-tamanoElite];//poblacion a cruzar, la cantidad sera el tamano de la elite
            Cromosoma[] descendientes = new Cromosoma[2];//poblacion de descendientes
            //Se ordena la poblacion en funcion de la operacion conforme a sus aptitudes
            if (tipoOperacion == TipoOperacion.MAXIMIZAR) {
                Arrays.sort(this.poblacion, new OrdenacionMaximizacion());
            } else {
                Arrays.sort(this.poblacion, new OrdenacionMinimizacion());
            }
            //Si hay elitismo indicando el tamano de la elite...
            if (tamanoElite > 0) {
                for (int i = 0; i < tamanoElite; i++) {//Vamos metiendo en poblacion temporal los mejores(tamanoElite) de la poblacion
                    poblacionTemporal[i]=this.poblacion[i].clone();
                }
            }
            
            //Mientras la poblacion temporal no este llena hacemos eleccion de los dos tipos de Seleccion: Torneo y Ruleta
            int i = poblacionTemporal.length-1;
            while (poblacionTemporal[i]==null){
                //SELECCION POR TORNEO CON MAXIMIZAR
                if (tipoSeleccion == TipoSeleccion.TORNEO && tipoOperacion == TipoOperacion.MAXIMIZAR) {
                    //Escogemos dos cromosomas aleatoriamente del rango de la poblacion actual
                    for (int x = 0; x<padres.length;x++){//vamos rellenando el array de padres hasta que se acabe
                        int aleatorio1 = Utilidades.generarAleatorioEnteros(this.poblacion.length);
                        int aleatorio2 = Utilidades.generarAleatorioEnteros(this.poblacion.length);
                        //Los vamos metiendo en el array padres para luego cruzarlos
                        if (this.poblacion[aleatorio1].aptitud()>this.poblacion[aleatorio2].aptitud()){
                            padres[x]=this.poblacion[aleatorio1].clone();
                        }
                        else if(aleatorio1==aleatorio2){
                            padres[x]=this.poblacion[aleatorio1].clone();
                        }
                        else if(this.poblacion[aleatorio1].aptitud()<this.poblacion[aleatorio2].aptitud()){
                            padres[x]=this.poblacion[aleatorio2].clone();
                        }
                    }
                }
                //SELECCION POR TORNEO CON MINIMIZAR
                else if(tipoSeleccion == TipoSeleccion.TORNEO && tipoOperacion == TipoOperacion.MINIMIZAR){
                    for (int x = 0; x<padres.length;x++){//vamos rellenando el array de padres hasta que se acabe
                        int aleatorio1 = Utilidades.generarAleatorioEnteros(this.poblacion.length);
                        int aleatorio2 = Utilidades.generarAleatorioEnteros(this.poblacion.length);
                    
                        if (this.poblacion[aleatorio1].aptitud()<this.poblacion[aleatorio2].aptitud()){
                            padres[x]=this.poblacion[aleatorio1].clone();
                        }
                        else if(aleatorio1==aleatorio2){
                            padres[x]=this.poblacion[aleatorio1].clone();
                        }
                        else if(this.poblacion[aleatorio1].aptitud()>this.poblacion[aleatorio2].aptitud()){
                            padres[x]=this.poblacion[aleatorio2].clone();
                        }
                    }
                }
                //SELECCION POR RULETA CON MAXIMIZAR
                else if(tipoSeleccion == TipoSeleccion.RULETA && tipoOperacion == TipoOperacion.MAXIMIZAR){
                    double totalAptitudes = 0;
                    double[] fitnessNormalizado = new double[this.poblacion.length];//creamos array de fitness normalizado con el tamano de poblacion actual
                    double[] fitnessAcumulado = new double[this.poblacion.length];//creamos array de fitness acumulado con el tamano de poblacion actual
                    
                    //Ahora calcularemos el total de aptitudes de la poblacion actual
                    for (i = 0; i < this.poblacion.length; i++) {
                        totalAptitudes += this.poblacion[i].aptitud();
                    }
                    
                    //Ahora vamos calculando el Fitness Normalizado de cada individuo
                    for (i = 0; i < this.poblacion.length; i++) {
                        fitnessNormalizado[i] = this.poblacion[i].aptitud() / totalAptitudes;
                    }
                    //Ahora se calcula el Fitness Acumulado de cada individuo
                    for (i = 0; i < this.poblacion.length; i++) {
                        if (i == 0) {
                            fitnessAcumulado[i] = fitnessNormalizado[i];
                        } 
                        else {
                            fitnessAcumulado[i] = fitnessNormalizado[i] + fitnessAcumulado[i - 1];
                        }
                    }
                    //Se ordena de forma ascendente el fitness acumulado
                    Arrays.sort(fitnessAcumulado);
                    //Se crean numeros aleatorios para comparar los fitness acumulados
                    for (int x = 0; x<padres.length;x++){
                        double aleatorio = Utilidades.generarAleatorioRangoDouble(0, 1);
                        for (i = 0; i<fitnessAcumulado.length;i++){
                            if (i==0){//Si el aleatorio esta entre 0 y el valor del primer elemento...
                                if (0<=aleatorio && aleatorio<fitnessAcumulado[i]){
                                    padres[x]=this.poblacion[i].clone();
                                }
                            }
                            else if (i==fitnessAcumulado.length-1){//Si el aleatorio esta entre el valor del ultimo elemento y 1...
                                if (fitnessAcumulado[i]<=aleatorio && aleatorio<1){
                                    padres[x]=this.poblacion[i].clone();
                                }
                            }
                            else {//Si el aleatorio esta entre cualquier valor intermedio
                                if (fitnessAcumulado[i]<=aleatorio && aleatorio<fitnessAcumulado[i+1]){
                                    padres[x]=this.poblacion[i].clone();
                                }
                            }
                        }
                    }
                }
                //SELECCION POR RULETA CON MINIMIZAR
                else if(tipoSeleccion == TipoSeleccion.RULETA && tipoOperacion == TipoOperacion.MINIMIZAR){
                    double totalAptitudes = 0;
                    double[] fitnessNormalizado = new double[this.poblacion.length];//creamos array de fitness normalizado con el tamano de poblacion actual
                    double[] fitnessAcumulado = new double[this.poblacion.length];//creamos array de fitness acumulado con el tamano de poblacion actual
                    //Se calcula el factor de escalado
                    double factorCorrecion = this.poblacion[0].aptitud() - this.poblacion[this.poblacion.length - 1].aptitud();
                    //Se suma a cada aptitud el factor de escalado
                    for (i = 0; i < this.poblacion.length; i++) {
                        totalAptitudes += this.poblacion[i].aptitud() + factorCorrecion;
                    } 
                    //Se calcula el fitness normalizado con el factor de escalado
                    for (i = 0; i < this.poblacion.length; i++) {
                        fitnessNormalizado[i] = (this.poblacion[i].aptitud() + factorCorrecion) / totalAptitudes;
                    }
                    //Ahora se calcula el Fitness Acumulado de cada individuo con el factor de escalado
                    for (i = 0; i < this.poblacion.length; i++) {
                        if (i == 0) {
                            fitnessAcumulado[i] = fitnessNormalizado[i];
                        } 
                        else {
                            fitnessAcumulado[i] = fitnessNormalizado[i] + fitnessAcumulado[i - 1];
                        }
                    }
                    //Se rellena ahora la poblacion a cruzar de padres con esos individuos
                    for (int x = 0; x<padres.length;x++){
                        double aleatorio = Utilidades.generarAleatorioRangoDouble(0, 1);
                        for (i = 0; i<fitnessAcumulado.length;i++){
                            if (i==0){//Si el aleatorio esta entre 0 y el valor del primer elemento...
                                if (0<=aleatorio && aleatorio<fitnessAcumulado[i]){
                                    padres[x]=this.poblacion[i].clone();
                                }
                            }
                            else if (i==fitnessAcumulado.length-1){//Si el aleatorio esta entre el valor del ultimo elemento y 1...
                                if (fitnessAcumulado[i]<=aleatorio && aleatorio<1){
                                    padres[x]=this.poblacion[i].clone();
                                }
                            }
                            else {//Si el aleatorio esta entre cualquier valor intermedio
                                if (fitnessAcumulado[i]<=aleatorio && aleatorio<fitnessAcumulado[i+1]){
                                    padres[x]=this.poblacion[i].clone();
                                }
                            }
                        }
                    }
                }
                    
                //COMENZAMOS CON LOS CRUCES   
                double aleatorio = Utilidades.generarAleatorioDouble(1.0);
                if (aleatorio > probabilidadCruce){ //Si el numero aleatorio es mayor que la probabilidad 
                    //se crean dos numeros aleatorios
                    int num1 = Utilidades.generarAleatorioEnteros(padres.length);
                    int num2 = Utilidades.generarAleatorioEnteros(padres.length);
                    boolean[][] genotipoHijos;
                    //Estos numeros aleatoriosd indicarán los dos padres elegidos a cruzar
                    boolean[] padre1 = padres[num1].getGenes();
                    boolean[] padre2 = padres[num2].getGenes();
                    //Se clonan los padres y se meten en el array de individuos a cruzar
                    Cromosoma[] individuosACruzar = new Cromosoma[2];
                    individuosACruzar[0] = padres[num1].clone();
                    individuosACruzar[1] = padres[num2].clone();
                    
                    descendientes = individuosACruzar;
                    //Se cruzan los dos padres dependiendo del tipo de cruce
                    if (tipoCruce == TipoCruce.SPX) {
                        genotipoHijos = cruceSpx(padre1, padre2);
                    } 
                    else {
                        genotipoHijos = cruceUniforme(padre1, padre2);
                    }
                    //En los descendientes se introducen los genotipos cruzados de los dos padres
                    descendientes[0].setGenes(genotipoHijos[0]);
                    descendientes[1].setGenes(genotipoHijos[1]);
                    
                    //Se genera aleatorio para ver si hay mutacion, si es mayor que la probabilidad se mutan los descendientes
                    double aleatorio2 = Utilidades.generarAleatorioDouble(1.0);
                    if (aleatorio2 > probabilidadMutacion) {
                        descendientes[0].mutar();
                        descendientes[1].mutar();
                    }
                    // se aniaden a poblacion temporal el primer descendiente cruzado
                    poblacionTemporal[i]=descendientes[0];
                    i--;
                    if (poblacionTemporal[i]==null){//si hay hueco para el segundo descendiente se mete tambien en poblacion temporal
                        poblacionTemporal[i]=descendientes[1];
                        i--;
                    }
                }
                
                else {//Si no hay cruce aniadimos padres a poblacion temporal
                    int k = poblacionTemporal.length-1;
                    int j = 0;
                    while (poblacionTemporal[k]==null){
                        poblacionTemporal[i]=padres[j];
                        k--;
                        j++;
                    }
                }
                
            }
            //Una vez hecha la seleccion y/o cruce se ordena de nuevo la poblacion
            if (tipoOperacion == TipoOperacion.MAXIMIZAR) {
                Arrays.sort(poblacionTemporal, new OrdenacionMaximizacion());
            } 
            else{
                Arrays.sort(poblacionTemporal, new OrdenacionMinimizacion());
            }
            
            //Se aumenta el contador de generaciones
            contadorGeneraciones++;
            
            //Se establece como nueva población actual la población temporal
            this.poblacion = poblacionTemporal;
            
            //El mejor individuo sera el primero de la poblacion ya que esta previamente ordenado
            mejorIndividuo = this.poblacion[0];
            
            //Se saca por pantalla las caracteristicas de cada generacion
            String cadena = "";
            cadena += "Generación " + String.valueOf(contadorGeneraciones) + " de " + numeroMaximoGeneraciones;
            cadena += ": Mejor individuo = Cromosoma[genes=[" + mejorIndividuo.toString()+"],";
            cadena += "fenotipo=" + mejorIndividuo.fenotipo()+",";
            cadena += "aptitud=" + mejorIndividuo.aptitud();
            System.out.println(cadena);
        }
    }
    //Metodo de cruce por un punto(SPX) que devuelve los descendientes
    private boolean[][] cruceSpx(boolean[] padre1, boolean[] padre2) {
        
        //Se cresn los genotipos de los descendientes
        boolean[][] descendientes = new boolean[2][padre1.length];

        //Se genera punto de cruce
        int ptoCruce = Utilidades.generarAleatorioEnteros(padre1.length - 1);

        //Se genera primer segmento de los descendientes
        for (int i = 0; i < ptoCruce; i++) {
            descendientes[0][i] = padre1[i];
            descendientes[1][i] = padre2[i];
        }

        //Se genera segundo segmento de los descendientes     
        for (int i = ptoCruce; i < padre1.length; i++) {
            descendientes[0][i] = padre2[i];
            descendientes[1][i] = padre1[i];
        }

        //Se devuelven los descendientes
        return descendientes;
    }
    
    private boolean[][] cruceUniforme(boolean[] padre1, boolean[] padre2) {

        //Se crea la máscara
        boolean[] mascara = new boolean[padre1.length];

        //Se genera de forma aleatoria la máscara
        for (int i = 0; i < mascara.length; i++) {
            mascara[i] = (Utilidades.generarAleatorio());
        }

        //Se crean los descendientes
        boolean[][] descendientes = new boolean[2][padre1.length];

        //En funcion de la mascara se van rellenando los descendientes
        for (int i = 0; i < mascara.length; i++) {
            if (mascara[i] == true) {
                descendientes[0][i] = padre1[i];
                descendientes[1][i] = padre2[i];
            } 
            else {
                descendientes[0][i] = padre2[i];
                descendientes[1][i] = padre1[i];
            }
        }

        //Devuelvo el genotipo de los hijos
        return descendientes;
    }
}

    
    
    


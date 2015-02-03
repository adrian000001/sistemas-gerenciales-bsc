/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafico;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author brayme2
 */
public class grafo {
    
    private List<nodo> listaNodos = new ArrayList <nodo>();
    private List<arista> listaAristas = new ArrayList <arista>();
    
    //agregar nodo
    public void agregarNodo(String nombre){
        nodo a = new nodo(nombre);
        listaNodos.add(a);
    }    
    //agregar nodo con euristica
    public void agregarNodo(String nombre, int euristica){
        nodo a = new nodo(nombre,euristica);        
        listaNodos.add(a);
    }
     public void agregarNodo(String nombre, String label, String color, String tipoArea){
        nodo a = new nodo(nombre,label, color, tipoArea);        
        listaNodos.add(a);
    }
    public void agregarNodo(String nombre, int euristica, String id){    
        nodo a = new nodo(nombre,euristica);
        a.setIdNodo(id);
        listaNodos.add(a);
    }
    
    public void conectarNodo(String nombreNodoA, String nombreNodoB){
        nodo a = null ;
        nodo b = null;
        
       
        //Agregar nodo A y B a la arista
        Iterator<nodo> nombreIterator = this.listaNodos.iterator();        
        while(nombreIterator.hasNext()){
            nodo elemento = nombreIterator.next();
            if(elemento.getNombreNodo().equals(nombreNodoA)){
                a = elemento;
            }
            else if(elemento.getNombreNodo().equals(nombreNodoB)){
                b = elemento;
            }
            if(a != null && b!= null){//Salga del bucle cuando los dos valores se han encontrado
                break;
            }
            
        }
        arista ar = new arista(a,b);
        listaAristas.add(ar);
        
        //Agregar la arista al nodo padre
        nombreIterator = this.listaNodos.iterator();
        int contador = 0;
        while(nombreIterator.hasNext()){
            nodo elemento = nombreIterator.next();
            if(elemento.getNombreNodo().equals(nombreNodoA)){
                listaNodos.get(contador).agregarArista(ar);
                break;              
            }
            contador++;            
        }
    }

    //Conectar nodos con pesos
    public void conectarNodo(String nombreNodoA, String nombreNodoB, int peso){
        nodo a = null ;
        nodo b = null;
        
       
        //Agregar nodo A y B a la arista
        Iterator<nodo> nombreIterator = this.listaNodos.iterator();        
        while(nombreIterator.hasNext()){
            nodo elemento = nombreIterator.next();
            if(elemento.getNombreNodo().equals(nombreNodoA)){
                a = elemento;
            }
            else if(elemento.getNombreNodo().equals(nombreNodoB)){
                b = elemento;
            }
            if(a != null && b!= null){//Salga del bucle cuando los dos valores se han encontrado
                break;
            }
            
        }
        arista ar = new arista(a,b,peso);
        listaAristas.add(ar);
        //Agregar la arista al nodo padre
        nombreIterator = this.listaNodos.iterator();
        int contador = 0;
        while(nombreIterator.hasNext()){
            nodo elemento = nombreIterator.next();
            if(elemento.getNombreNodo().equals(nombreNodoA)){
                listaNodos.get(contador).agregarArista(ar);
                break;              
            }
            contador++;            
        }
    }
    
    public String buscarPorID(String id){
        Iterator<nodo> nombreIterator = this.listaNodos.iterator();   
        nombreIterator = this.listaNodos.iterator();
        int contador = 0;
        String nombreDelNodoEncontrado="";
        while(nombreIterator.hasNext()){
            nodo elemento = nombreIterator.next();
            if(elemento.getIdNodo().equals(id)){
                nombreDelNodoEncontrado = elemento.getNombreNodo();
                break;              
            }
            contador++;        
        }
        return nombreDelNodoEncontrado;
    }
     public nodo buscarPorNombre(String nombre){
        Iterator<nodo> nombreIterator = this.listaNodos.iterator();   
        nombreIterator = this.listaNodos.iterator();
        int contador = 0;
        nodo n=null;
        while(nombreIterator.hasNext()){
            nodo elemento = nombreIterator.next();
            if(elemento.getNombreNodo().equals(nombre)){
                n = elemento;
                break;              
            }
            contador++;        
        }
        return n;
    }

    public void generarAleatorio(int numeroNodos){
        for (int i = 0; i < numeroNodos; i++) {        
            nodo a = new nodo(Character.toString((char)(i+65)),i+5);
            listaNodos.add(a);
        }
        
        int aux=0;
        //Conectar aleatoriamente los nodos
        for (Iterator<nodo> it = listaNodos.iterator(); it.hasNext();) {        
            nodo object = it.next();            
            aux++;
            for (int i = aux; i < numeroNodos; i++) {
                int peso = (int) (Math.random()*10);
                conectarNodo(object.getNombreNodo(), listaNodos.get(i).getNombreNodo(),peso);                
            }
        }
    }
    
    public void Limpiar(){
        for (Iterator<nodo> it = listaNodos.iterator(); it.hasNext();) {
            nodo object = it.next();
            object.setVisitado(false);
        }
            
    }
    
    public void AgregarDobleDireccionTodasAristas(){
        ArrayList<arista> aristaPorAgregar= new ArrayList<arista>();
        for (Iterator<arista> it = this.listaAristas.iterator(); it.hasNext();) {//Añadimos a la lista todas las aristas
            arista a = it.next();
            aristaPorAgregar.add(a);            
        }
        
        for (Iterator<arista> it = aristaPorAgregar.iterator(); it.hasNext();) {//Agregamos la otra dirección
                arista object = it.next();
                this.conectarNodo(object.getNodoB().getNombreNodo(), object.getNodoA().getNombreNodo());
        }
     
    }
    
    public List<nodo> getListaNodos() {
        return listaNodos;
    }

    public List<arista> getListaAristas() {
        return listaAristas;
    }
    
    
}

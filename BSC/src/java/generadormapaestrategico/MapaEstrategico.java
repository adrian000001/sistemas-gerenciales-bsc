/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadormapaestrategico;



import generadormapaestrategico.GraphViz;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 *
 * @author adrian
 */
class Nodo {
    
    String valor;    
    ArrayList<Nodo> hijos;

    Nodo(String newData) {
      hijos = new ArrayList<>();
      valor = newData;
    } 
  } 
public class MapaEstrategico {
  
     

    /**
     * @param args the command line arguments
     */      
    private Nodo raiz; 
    GraphViz graficador = new GraphViz();      
        
   
  public MapaEstrategico() {
    raiz = null; 
    graficador.addln(graficador.start_graph());
   
  } 

   

    
    
    
  public Nodo buscarRecursivo(String data) { 
    return(buscarRecursivo(raiz, data)); 
  }     
  
  //Sobre carga de funciones/métodos
  Nodo resultado = null;
  private Nodo buscarRecursivo(Nodo node, String valorBuscado) { 
    if (node==null) 
      resultado = null;      

    if (valorBuscado.compareTo(node.valor)==0)        
      resultado = node; 
          
    for(Nodo tmp: node.hijos)    
      buscarRecursivo(tmp, valorBuscado); 
    
    return resultado;
  }      

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
    
  public void insertarNodo(String valor, String padre)
  {      
      raiz = insertarNodo(raiz, valor, padre);
  }
    
  private Nodo insertarNodo(Nodo nodo, String valor, String padre)
  {              
      if(nodo == null)
          nodo = new Nodo(valor);                                                                                             
      else 
      {          
          Nodo nodoPadre = buscarRecursivo(padre);
          if(nodoPadre != null)          
              nodoPadre.hijos.add(new Nodo(valor));
          else
              nodo.hijos.add(new Nodo(valor));
      }
      return nodo;
  }
  
  //Devolvemos el numero de nodo, buscandolo por valor
  public int buscarNodo(String valorNodo)
  {      
      return nodosVisitados.indexOf(valorNodo);
  }
  private ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
  public void finalizarGrafica()
  {
      System.out.println("\n");
      graficador.addln(graficador.end_graph());
          System.out.println(graficador.getDotSource());
          String ruta = ec.getRealPath("img");
          String type = "png";
          System.out.println("ruta0"+ruta);
          File out = new File(ruta+"\\arbol." + type);
          System.out.println("jeyyyyyy");
          graficador.writeGraphToFile(graficador.getGraph(graficador.getDotSource(), type), out);
          
  }
  
  static int contadorNodos = 0;
  static int contadorNulos = 0;
  static ArrayList<String> nodosVisitados = new ArrayList<>();
  private void encontrarNodos(Nodo nodo, MapaEstrategico arbol)
  {
      if(nodo != null)
      {
          //System.out.println("\nEstoy en el nodo " + nodo.valor + " ");
          String nulo = "null";
          //Creación del nodo
          graficador.addln(String.format("%s [ label=<%s> ]", contadorNodos, nodo.valor));                         
          nodosVisitados.add(nodo.valor);  
          //System.out.println("Nuevo nodo registrado: " + nodo.valor + " con indice: " + contadorNodos);
          contadorNodos++;
          
          for(Nodo tmp: nodo.hijos)
          {
            if(tmp == null)
            {
                nulo = "null" + contadorNulos;
                contadorNulos++;
                graficador.addln(String.format("%s[shape=point]", nulo));                                       
            }                        
            encontrarNodos(tmp, arbol);                                            
          }
      }
  }      
  static int nuevoContadorNulos = 0;
  public void asignarPadreHijo(Nodo nodo, MapaEstrategico arbol)
  {
      if(nodo != null)
      {         
          for(Nodo tmp: nodo.hijos)
          {
              if(tmp != null)
                  //Agregamos el hijo izquierdo a la gráfica
                  graficador.addln(String.format(
                      "%d -> %d", buscarNodo(nodo.valor), buscarNodo(tmp.valor)));   
              else
                  graficador.addln(String.format(
                      "%d -> %s", buscarNodo(nodo.valor), "null" + nuevoContadorNulos++));                                                                
              asignarPadreHijo(tmp, arbol);             
          }
      }
  }
  
  public void graficarArbol(MapaEstrategico arbol)
  {            
      graficador.addln("ordering = out");  
      encontrarNodos(raiz, arbol);                 
      asignarPadreHijo(raiz, arbol);
      finalizarGrafica();
  }

  static int tamaño = 0;
  public int obtenerTamaño(Nodo nodo, MapaEstrategico arbol)
  {
      if(nodo != null)
      {
          tamaño++;
          for(Nodo tmp: nodo.hijos)
            obtenerTamaño(tmp, arbol);                    
      }
      return tamaño;
  }
   
}

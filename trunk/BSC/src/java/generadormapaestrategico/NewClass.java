/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadormapaestrategico;

import generadormapaestrategico.MapaEstrategico;
import java.util.List;
import modelo.Nodosobj;

/**
 *
 * @author adrian
 */
public class NewClass {
    private List<Nodosobj> items;

    public NewClass(List<Nodosobj> items) {
        this.items = items;
       this.generarArbol();
    }

    public List<Nodosobj> getItems() {
        return items;
    }

    public void setItems(List<Nodosobj> items) {
        this.items = items;
    }
  
  
  public void generarArbol()
  {
       MapaEstrategico mp = new MapaEstrategico();
       System.out.println(items.size()+"000");
       for (int i = 0; i < items.size(); i++) {
            
            mp.insertarNodo(items.get(i).getObjOrigen(), items.get(i).getObjDestino());
        }
        mp.graficarArbol(mp);   // Graficacion de la matriz
        System.out.println("El tamaño es: " + mp.obtenerTamaño(mp.getRaiz(), mp));
  }
   
}

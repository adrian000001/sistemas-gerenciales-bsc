/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafico;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author brayme2
 */
public class generaDot {

    private FileWriter fichero = null;
    private PrintWriter pw = null;

    public void generar(grafo g) {

        try {
            fichero = new FileWriter("prueba.txt");
            pw = new PrintWriter(fichero);

            pw.println("digraph G\r\n{");
            pw.println("graph [ dpi = 300 ]");

            declaraNodosAristas("Financiera", "0", g, "lightgrey", "Financiera");
            declaraNodosAristas("Empresarial", "1", g, "lightgrey", "Clientes");
            declaraNodosAristas("Procesos", "2", g, "lightgrey", "Procesos Internos");
            declaraNodosAristas("Aprendizaje", "3", g, "lightgrey", "Aprendizaje y Conocimientos");

            for (Iterator<arista> it = g.getListaAristas().iterator(); it.hasNext();) {//escribe las conexiones
                arista object = it.next();
                if (object.getNodoA().getTipoArea().equals(object.getNodoB().getTipoArea()) == false) {//Verifica que la arista este dentro del grupo financieros.
                    pw.println(object.getNodoA().getNombreNodo() + "->" + object.getNodoB().getNombreNodo());
                }
            }

            //Da las propiedades a los nodos el color y la etiqueda o label.
            for (Iterator<nodo> it = g.getListaNodos().iterator(); it.hasNext();) {
                nodo object = it.next();
                String label = object.getLabel();
                label = saltoLinea(label);
                pw.println(object.getNombreNodo() + "[" + "style=filled," + "color=" + object.getColor() + "," + "label=" + "\"" + label + "\"" + "]");
            }

            pw.println("}");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
           // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void declaraNodosAristas(String area, String idArea, grafo g, String color, String label) {//Declara los nodos y las aritas dentro de un área dada
        pw.println("subgraph cluster" + idArea + " {");
        pw.println("style=filled;");
        pw.println("color=" + color);

        for (Iterator<nodo> it = g.getListaNodos().iterator(); it.hasNext();) {
            nodo object = it.next();
            if (object.getTipoArea().equals(area)) {
                pw.println(object.getNombreNodo());
            }
        }

        for (Iterator<arista> it = g.getListaAristas().iterator(); it.hasNext();) {//escribe las conexiones
            arista object = it.next();
            if ((object.getNodoA().getTipoArea().equals(object.getNodoB().getTipoArea())) && ((object.getNodoA().getTipoArea().equals(area)) && object.getNodoB().getTipoArea().equals(area))) {//Verifica que la arista este dentro del grupo financieros.

                pw.println(object.getNodoA().getNombreNodo() + "->" + object.getNodoB().getNombreNodo());
            }
        }
        pw.println("label = \"" + label + "\"");
        pw.println("}");
    }

    private String saltoLinea(String oracion) {
        String neoOracion = " ";
        StringTokenizer stTexto = new StringTokenizer(oracion);
        int cont = 0;
        while (stTexto.hasMoreElements()) {
            cont++;
            neoOracion += stTexto.nextElement() + " ";
            if (cont == 3) {
                neoOracion += "\n";
                cont = 0;
            }
        }
        return neoOracion;
    }

}

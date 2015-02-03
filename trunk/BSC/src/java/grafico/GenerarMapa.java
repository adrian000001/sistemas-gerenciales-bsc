/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafico;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import modelo.Nodosobj;
import modelo.Objetivoestrategico;
import modelo.Objetivoestrategicoindicador;
import modelo.Semaforo;

/**
 *
 * @author Eddison
 */
@Named(value = "bean")
@ApplicationScoped
public class GenerarMapa {

    private List<Objetivoestrategico> objEstrategicos;
    private List<Nodosobj> relaciones;
    private ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

    public GenerarMapa(List<Objetivoestrategico> objEstrategicos, List<Nodosobj> relaciones) {
        this.objEstrategicos = objEstrategicos;
        this.relaciones = relaciones;
    }

    public void main() {

        try {
            //Creamos nuestro grafo

            grafo g = new grafo();
            for (Objetivoestrategico ob : objEstrategicos) {
                char color=' ';
                ArrayList<Objetivoestrategicoindicador> indicadores = new ArrayList();
                ArrayList aux=new ArrayList();
                for (Objetivoestrategicoindicador ind : ob.getObjetivoestrategicoindicadorCollection()) {
                    
                    indicadores.add(ind);

                }
                
                    
                
                System.out.println("hiiiiii"+indicadores.size());
                Objetivoestrategicoindicador i=indicadores.get(indicadores.size()-1);
                for (Semaforo sem : i.getIndicador().getSemaforoCollection()) {
                    if(i.getValorActual()>=sem.getLimiteInferior().doubleValue() && i.getValorActual()<=sem.getLimiteSuperior().doubleValue())
                    {
                        
                        if (color!=' '){
                            color=sem.getColor();
                        }  
                        else{
                            color='v';
                        }
                        
                            
                        
                    }
                
                
                    if(color=='v')
                    {System.out.println("aquiii "+color);
                        g.agregarNodo(String.valueOf(ob.getIdObjetivoEstrategico()),
                        ob.getNombre(), "green", ob.getIdPerspectiva().getNombre());
                        //aux.add(String.valueOf(ob.getIdObjetivoEstrategico())+"!"+)
                    }
                    if(color=='n')
                    {
                        System.out.println("aquiii "+color);
                        g.agregarNodo(String.valueOf(ob.getIdObjetivoEstrategico()),
                        ob.getNombre(), "yellow", ob.getIdPerspectiva().getNombre());
            
                    }
                    if(color=='r')
                    {
                        System.out.println("aquiii "+color);
                        g.agregarNodo(String.valueOf(ob.getIdObjetivoEstrategico()),
                        ob.getNombre(), "red", ob.getIdPerspectiva().getNombre());
            
                    }
                    
                    
                 
                }
                g.agregarNodo(String.valueOf(ob.getIdObjetivoEstrategico()),
                        ob.getNombre(), "lightgrey", ob.getIdPerspectiva().getNombre());
            }

            for (Nodosobj nodo : relaciones) {
                g.conectarNodo(nodo.getObjOrigen(), nodo.getObjDestino());
            }

//      grafo g = new grafo();
            // g.agregarNodo("1","Minimizar costos para aumentar la calidad de los productos.","lightblue","financiera");
//      g.agregarNodo("2","Mejorar la rentabilidad de la organización.","red","financiera");
//      g.agregarNodo("3","Aumentar las ventas para tener mayores ingresos.","red","financiera");
//      g.agregarNodo("4","Mejorar la calidad de los productos para la satisfacción de los clientes.","yellow","clientes");
//      g.agregarNodo("5","Crear una línea de producción con materiales de mejor calidad.","blue","clientes");
//      g.agregarNodo("6","Implementar nueva estrategia de ventas online.","lightblue","clientes");
//      g.agregarNodo("7","Efectuar estrategias de atención y servicios para asegurar la fidelidad de los clientes.","lightblue","clientes");
//      g.agregarNodo("8","Gestionar eficientemente los procesos de producción.","blue","procesosInternos");
//      g.agregarNodo("9","Mejorar la asistencia de entrega de pedidos.","blue","procesosInternos");
//      g.agregarNodo("10","Optimizar la atención de los pedidos.","blue","procesosInternos");
//      g.agregarNodo("11","Disponer de materia prima de calidad.","blue","procesosInternos");
//      g.agregarNodo("12","Potencializar la especialización del personal en nuevas áreas de producción.","blue","aprendizaje");
//      g.agregarNodo("13","Capacitar al personal para mejorar el espíritu competitivo.","blue","aprendizaje");
//      g.agregarNodo("14","Desarrollar una plan estratégico para la contratación del personal.","blue","aprendizaje");
//      g.agregarNodo("15","Disponer de abastecedores de materia prima propios de la empresa.","blue","aprendizaje");
//      
//      g.conectarNodo("2", "3");
//      g.conectarNodo("2", "1");
//      
//      g.conectarNodo("3", "6");
//      g.conectarNodo("3", "7");
//      g.conectarNodo("3", "5");
//      g.conectarNodo("3", "4");
//      
//      g.conectarNodo("1", "5");
//      
//      g.conectarNodo("6", "9");
//      g.conectarNodo("6", "10");
//      
//      g.conectarNodo("7", "10");
//      g.conectarNodo("5", "8");
//      
//      g.conectarNodo("9", "8");
//      g.conectarNodo("9", "11");
//      
//      g.conectarNodo("9", "13");
//      g.conectarNodo("9", "14");
//      
//      g.conectarNodo("10", "13");
//      g.conectarNodo("8", "14");
//      g.conectarNodo("8", "12");
//      g.conectarNodo("11", "15");
//      
            generaDot gen = new generaDot();
            gen.generar(g);

            String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";

            String fileInputPath = "prueba.txt";
            //String fileOutputPath = "web\\imagenes\\grafo.jpg";
            String ruta = ec.getRealPath("img");
            String fileOutputPath = ruta + "/grafo.jpg";
            String tParam = "-Tjpg";
            String tOParam = "-o";

            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Runtime rt = Runtime.getRuntime();

            rt.exec(cmd);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }

    }

    public void imprimir() {
        Document document = new Document(PageSize.A2);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, baos);
            //METADATA

            document.open();
            String nombretabla = "ESTUDIANTES INSCRITOS\n";
            document.add(new Paragraph(nombretabla));
            DateFormat formatter = new SimpleDateFormat("dd/MM/yy '-' hh:mm:ss:");

            Date currentDate = new Date();
            String date = formatter.format(currentDate);
            document.add(new Paragraph("Fecha Generado: " + date));
            document.add(new Paragraph("\n"));

            Image imageen;
            Image foto = Image.getInstance("grafo.jpg");
            foto.scaleToFit(1000, 1000);
            foto.setAlignment(Chunk.ALIGN_MIDDLE);

            document.add(foto);

            document.close();

            FacesContext context = FacesContext.getCurrentInstance();
            Object response = context.getExternalContext().getResponse();
            if (response instanceof HttpServletResponse) {
                HttpServletResponse hsr = (HttpServletResponse) response;
                hsr.setContentType("application/pdf");

                hsr.setHeader("Content-disposition", "filename=" + nombretabla + ".pdf");

                try {
                    ServletOutputStream out = hsr.getOutputStream();
                    baos.writeTo(out);
                    out.flush();
                    out.close();
                } catch (IOException ex) {
                    System.out.println("Error:  " + ex.getMessage());
                }
                context.responseComplete();
            }

        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public List<Objetivoestrategico> getObjEstrategicos() {
        return objEstrategicos;
    }

    public List<Nodosobj> getRelaciones() {
        return relaciones;
    }

    public void setObjEstrategicos(List<Objetivoestrategico> objEstrategicos) {
        this.objEstrategicos = objEstrategicos;
    }

    public void setRelaciones(List<Nodosobj> relaciones) {
        this.relaciones = relaciones;
    }
}

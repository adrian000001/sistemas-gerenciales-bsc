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

            grafo g1 = new grafo();
            for (Objetivoestrategico ob : objEstrategicos) {
                System.out.println("33333"+ob.getNombre());
                String color = "v";
                ArrayList<Objetivoestrategicoindicador> indicadores = new ArrayList();
                ArrayList aux = new ArrayList();
                for (Objetivoestrategicoindicador ind : ob.getObjetivoestrategicoindicadorCollection()) {
                    indicadores.add(ind);
                }

                
                if(indicadores.size()!=0)
                {
                    System.out.println("hiiiiii" + indicadores.size());
                Objetivoestrategicoindicador i = indicadores.get(indicadores.size() - 1);
                for (Semaforo sem : i.getIndicador().getSemaforoCollection()) {
                    System.out.println(i.getValorActual()+"valor actual");
                    double d=i.getValorActual();
                    if (d==0) {

                        color = "v";
                    }
                    else
                    {
                        if (i.getValorActual() >= sem.getLimiteInferior().doubleValue() && i.getValorActual() <= sem.getLimiteSuperior().doubleValue()) {

                        color = ""+sem.getColor();
                    }
                    }
                    
                    
                    System.out.println("este color: "+color);
                    if (color.equals("v")) {
                        System.out.println("aquiii " + color);
                        g1.agregarNodo(String.valueOf(ob.getIdObjetivoEstrategico()),
                                ob.getNombre(), "chartreuse3", ob.getIdPerspectiva().getNombre());
                        //aux.add(String.valueOf(ob.getIdObjetivoEstrategico())+"!"+)
                    }
                    if (color.equals("n")) {
                        System.out.println("aquiii " + color);
                        g1.agregarNodo(String.valueOf(ob.getIdObjetivoEstrategico()),
                                ob.getNombre(), "orange", ob.getIdPerspectiva().getNombre());
                    }
                    if (color.equals("r")) {
                        System.out.println("aquiii " + color);
                        
                        g1.agregarNodo(String.valueOf(ob.getIdObjetivoEstrategico()),
                                ob.getNombre(), "firebrick1", ob.getIdPerspectiva().getNombre());

                    }

                }}
                else
                {
                  g1.agregarNodo(String.valueOf(ob.getIdObjetivoEstrategico()),
                        ob.getNombre(), "lightgrey", ob.getIdPerspectiva().getNombre());
              
                }
               }

            for (Nodosobj nodo : relaciones) {
                g1.conectarNodo(nodo.getObjOrigen(), nodo.getObjDestino());
            }

            generaDot gen = new generaDot();
            if(g1!=null)
            {
            gen.generar(g1);
    
            }
            
            String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";

            String fileInputPath = "prueba.txt";
            //String fileOutputPath = "web\\imagenes\\grafo.jpg";
            String ruta = ec.getRealPath("img");
            System.out.println("heyyyy "+ruta);
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

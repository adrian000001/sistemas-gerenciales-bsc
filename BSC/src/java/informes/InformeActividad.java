/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informes;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelo.Actividad;
import modelo.Objetivoestrategico;
import modelo.Objetivoestrategicoindicador;

/**
 *
 * @author adrian
 */
public class InformeActividad {

    private String descripsion;
    private List<Objetivoestrategico> items;

    public InformeActividad(String descripsion, List<Objetivoestrategico> items) {
        this.descripsion = descripsion;
        this.items = items;
    }

    public String getDescripsion() {
        return descripsion;
    }

    public void setDescripsion(String descripsion) {
        this.descripsion = descripsion;
    }

    public List<Objetivoestrategico> getItems() {
        return items;
    }

    public void setItems(List<Objetivoestrategico> items) {
        this.items = items;
    }
    private ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

    private static void agregarLineasEnBlanco(Paragraph parrafo, int nLineas) {
        for (int i = 0; i < nLineas; i++) {
            parrafo.add(new Paragraph(" "));
        }
    }

    public void generarInforme() {
        try {
            Document document = new Document();
             Paragraph ParrafoHoja = new Paragraph();
            String parrafo = this.getDescripsion();
            String ruta = ec.getRealPath("img");
            PdfWriter.getInstance(document, new FileOutputStream(ruta + "\\" + "actividades.pdf"));

            Font fuente = new Font();
            fuente.setColor(255, 0, 0);
            fuente.setSize(30);
            
            Font fuente3 = new Font();
            fuente3.setColor(255, 255, 255);
            fuente3.setSize(30);
            
            PdfPTable tabla2 = new PdfPTable(1);
               tabla2.getDefaultCell().setBackgroundColor(BaseColor.BLUE);
            tabla2.setWidthPercentage(110f);
             tabla2.addCell(new Paragraph("UNIDAD EDUCATIVA",fuente3));
              tabla2.addCell(new Paragraph("'MISIONEROS OBLATOS'",fuente3));
         
            
            
            
            
            Paragraph p = new Paragraph(parrafo, fuente);
            
            agregarLineasEnBlanco(ParrafoHoja, 3);
            
            document.open();
            document.add(tabla2);
            document.add(p);
            document.add(ParrafoHoja);
            Font fuente1 = new Font();
            fuente1.setColor(0, 0, 0);
            fuente1.setSize(5);
            
            Font fuente2 = new Font();
            fuente2.setColor(255,0 , 0);
            fuente2.setSize(5);
            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(107f);
            
            
            Font fuente4 = new Font();
            fuente4.setColor(0, 0, 0);
            fuente4.setSize(8);
            
// Paragraph p4 = new Paragraph(parrafo, fuente4);
            
           
            //tabla.addCell(new Paragraph("OBJESTRATEGICO",fuente2));
            tabla.addCell(new Paragraph("RESPONSABLE",fuente2));
            tabla.addCell(new Paragraph("CONTROL",fuente2));
            tabla.addCell(new Paragraph("FECHA INICIO",fuente2));
            tabla.addCell(new Paragraph("FECHA FIN",fuente2));
            tabla.addCell(new Paragraph("AVANCE",fuente2));
            int cont=0;
            tabla.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
            for (int i = 0; i < this.getItems().size(); i++) {
                // Paragraph p1 =new Paragraph(parrafo,fuente);
                String num=String.valueOf(i);
                  Paragraph p4 = new Paragraph("Objetivo: "+num+" --> "+this.getItems().get(i).getNombre(), fuente4);
                  document.add(p4);
                  Paragraph p8 = new Paragraph();
                  agregarLineasEnBlanco(p8, 1);
                  document.add(p8);
                  
                for (Actividad a: this.getItems().get(i).getActividadCollection()) {
                    System.out.println("");
                  if(a.getIdObjetivoEstrategico().getIdObjetivoEstrategico()==(this.getItems().get(i).getIdObjetivoEstrategico()))
                  {
                      cont=1;
                              tabla.addCell(new Paragraph(a.getIdObjetivoEstrategico().getNombre(), fuente1));
                
                tabla.addCell(new Paragraph(a.getIdPersonaResponsable().getNombre(), fuente1));
                tabla.addCell(new Paragraph(a.getControl(), fuente1));
                tabla.addCell(new Paragraph(a.getFechaInicio().toString(), fuente1));
                tabla.addCell(new Paragraph(a.getFechaFin().toString(), fuente1));
                tabla.addCell(new Paragraph(a.getAvance().toString(), fuente1));
        
                  }
             }
                 Paragraph p7 = new Paragraph();
                if(cont==1)
                {
                 document.add(tabla);   
                 cont=0;
                  agregarLineasEnBlanco(p7, 2);
                  document.add(p7);
                }
                else
                {
                     Font fuente5 = new Font();
            fuente5.setColor(0, 0, 255);
            fuente5.setSize(10);
           
                    document.add(new Paragraph("No contiene actividades registradas",fuente5));
                 agregarLineasEnBlanco(p7, 2);
                  document.add(p7);
                }
            }
           
            document.add(new Paragraph(new Date().toString()));

            document.close();
            System.out.println("aquiiiiii");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InformeActividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(InformeActividad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InformeActividad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelo.Objetivoestrategicoindicador;

/**
 *
 * @author adrian
 */
public class Informe {

    private String descripsion;
    private List<Objetivoestrategicoindicador> items;

    public Informe(String descripsion, List<Objetivoestrategicoindicador> items) {
        this.descripsion = descripsion;
        this.items = items;
    }

    public String getDescripsion() {
        return descripsion;
    }

    public void setDescripsion(String descripsion) {
        this.descripsion = descripsion;
    }

    public List<Objetivoestrategicoindicador> getItems() {
        return items;
    }

    public void setItems(List<Objetivoestrategicoindicador> items) {
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
            PdfWriter.getInstance(document, new FileOutputStream(ruta + "\\" + "cuadromandointegral.pdf"));

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
            PdfPTable tabla = new PdfPTable(12);
            tabla.setWidthPercentage(107f);

            tabla.addCell(new Paragraph("PERSPECTIVA",fuente2));
            tabla.addCell(new Paragraph("OBJESTRATEGICO",fuente2));
            tabla.addCell(new Paragraph("INDICADOR",fuente2));
            tabla.addCell(new Paragraph("KPI",fuente2));
            tabla.addCell(new Paragraph("UNIDADES",fuente2));
            tabla.addCell(new Paragraph("RESPONSABLE",fuente2));
            tabla.addCell(new Paragraph("BASE",fuente2));
            tabla.addCell(new Paragraph("META",fuente2));
            tabla.addCell(new Paragraph("VALORACTUAL",fuente2));
            tabla.addCell(new Paragraph("SEMAFORO",fuente2));
            tabla.addCell(new Paragraph("DESEMPENO",fuente2));
            tabla.addCell(new Paragraph("TIEMPO MEDICION",fuente2));
            
            tabla.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
            for (int i = 0; i < this.getItems().size(); i++) {
                // Paragraph p1 =new Paragraph(parrafo,fuente);

                System.out.println("aquiiiiiiiiiiii" + this.getItems().get(i).getSemaforo());

                tabla.addCell(new Paragraph(this.getItems().get(i).getObjetivoestrategico().getIdPerspectiva().getNombre(), fuente1));
                tabla.addCell(new Paragraph(this.getItems().get(i).getObjetivoestrategico().getNombre(), fuente1));
                tabla.addCell(new Paragraph(this.getItems().get(i).getIndicador().getNombre(), fuente1));
                tabla.addCell(new Paragraph(this.getItems().get(i).getIndicador().getKpi(), fuente1));
                tabla.addCell(new Paragraph(this.getItems().get(i).getIndicador().getUnidades(), fuente1));
                tabla.addCell(new Paragraph(this.getItems().get(i).getIndicador().getIdPersonaResponsable().getNombre(), fuente1));
                tabla.addCell(new Paragraph(String.valueOf(this.getItems().get(i).getIndicador().getBase()), fuente1));
                tabla.addCell(new Paragraph(String.valueOf(this.getItems().get(i).getIndicador().getMeta()), fuente1));
                tabla.addCell(new Paragraph(String.valueOf(this.getItems().get(i).getValorActual()), fuente1));
                if (this.getItems().get(i).getSemaforo() == null) {
                    tabla.addCell(new Paragraph(this.getItems().get(i).getSemaforo(), fuente1));
                } else {
                    Image foto = Image.getInstance("C:\\Users\\adrian\\Documents\\NetBeansProjects\\trunk\\BSC\\web\\" + this.getItems().get(i).getSemaforo());
                    foto.scaleToFit(2, 2);
                    foto.setAlignment(Chunk.ALIGN_MIDDLE);
                    foto.setTransparency(new int[]{0x00, 0x10});
                    tabla.addCell(foto);

                }

                tabla.addCell(new Paragraph(String.valueOf(this.getItems().get(i).getDesempeño()), fuente1));
                tabla.addCell(new Paragraph(String.valueOf(this.getItems().get(i).getIndicador().getTiempoMedicion()), fuente1));
            }
            document.add(tabla);
            document.add(new Paragraph(new Date().toString()));

            document.close();
            System.out.println("aquiiiiii");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Informe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Informe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Informe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

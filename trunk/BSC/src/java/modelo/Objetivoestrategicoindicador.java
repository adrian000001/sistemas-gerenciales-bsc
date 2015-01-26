/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Farfan
 */
@Entity
@Table(name = "objetivoestrategicoindicador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objetivoestrategicoindicador.findAll", query = "SELECT o FROM Objetivoestrategicoindicador o"),
    @NamedQuery(name = "Objetivoestrategicoindicador.findByIdObjetivoEstrategico", query = "SELECT o FROM Objetivoestrategicoindicador o WHERE o.objetivoestrategicoindicadorPK.idObjetivoEstrategico = :idObjetivoEstrategico"),
    @NamedQuery(name = "Objetivoestrategicoindicador.findByIdIndicador", query = "SELECT o FROM Objetivoestrategicoindicador o WHERE o.objetivoestrategicoindicadorPK.idIndicador = :idIndicador"),
    @NamedQuery(name = "Objetivoestrategicoindicador.findByMeta", query = "SELECT o FROM Objetivoestrategicoindicador o WHERE o.meta = :meta"),
    @NamedQuery(name = "Objetivoestrategicoindicador.findByDefinicion", query = "SELECT o FROM Objetivoestrategicoindicador o WHERE o.definicion = :definicion"),
    @NamedQuery(name = "Objetivoestrategicoindicador.findByAclaracion", query = "SELECT o FROM Objetivoestrategicoindicador o WHERE o.aclaracion = :aclaracion"),
    @NamedQuery(name = "Objetivoestrategicoindicador.findByConceptualizacion", query = "SELECT o FROM Objetivoestrategicoindicador o WHERE o.conceptualizacion = :conceptualizacion")})
public class Objetivoestrategicoindicador implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String findByIdObjetivoEstrategico="Objetivoestrategicoindicador.findByIdObjetivoEstrategico";
    @EmbeddedId
    protected ObjetivoestrategicoindicadorPK objetivoestrategicoindicadorPK;
    @Size(max = 100)
    @Column(name = "meta")
    private String meta;
    @Size(max = 100)
    @Column(name = "definicion")
    private String definicion;
    @Size(max = 250)
    @Column(name = "aclaracion")
    private String aclaracion;
    @Size(max = 250)
    @Column(name = "conceptualizacion")
    private String conceptualizacion;
    @JoinColumn(name = "idIndicador", referencedColumnName = "idIndicador", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Indicador indicador;
    @JoinColumn(name = "idObjetivoEstrategico", referencedColumnName = "idObjetivoEstrategico", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Objetivoestrategico objetivoestrategico;
    

    public Objetivoestrategicoindicador() {
    }

    public Objetivoestrategicoindicador(ObjetivoestrategicoindicadorPK objetivoestrategicoindicadorPK) {
        this.objetivoestrategicoindicadorPK = objetivoestrategicoindicadorPK;
    }

    public Objetivoestrategicoindicador(int idObjetivoEstrategico, int idIndicador) {
        this.objetivoestrategicoindicadorPK = new ObjetivoestrategicoindicadorPK(idObjetivoEstrategico, idIndicador);
    }

    public ObjetivoestrategicoindicadorPK getObjetivoestrategicoindicadorPK() {
        return objetivoestrategicoindicadorPK;
    }

    public void setObjetivoestrategicoindicadorPK(ObjetivoestrategicoindicadorPK objetivoestrategicoindicadorPK) {
        this.objetivoestrategicoindicadorPK = objetivoestrategicoindicadorPK;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    public String getAclaracion() {
        return aclaracion;
    }

    public void setAclaracion(String aclaracion) {
        this.aclaracion = aclaracion;
    }

    public String getConceptualizacion() {
        return conceptualizacion;
    }

    public void setConceptualizacion(String conceptualizacion) {
        this.conceptualizacion = conceptualizacion;
    }

    public Indicador getIndicador() {
        return indicador;
    }
    
    public double getValorActual(){
        Historial res=null;
        int con=0;
        for (Historial h:indicador.getHistorialCollection())
            if (con==0){
                res=h;
            }else{
                if (h.getFechaMedicion().after(res.getFechaMedicion()))
                    res=h;
            }
        
        if (res!=null){
            return res.getValor().doubleValue();
        
        }
        else return 0;
    }
    
    public String getSemaforo(){
        String res = null;
        double valorActual=this.getValorActual();
        System.out.println("VALOR SEM " +valorActual);
            for (Semaforo s:indicador.getSemaforoCollection()){
                System.out.println("SEMAINF " +s.getLimiteInferior());
                System.out.println("SEMASUP " +s.getLimiteSuperior());
                if (valorActual>=s.getLimiteInferior().doubleValue() && valorActual<=s.getLimiteSuperior().doubleValue()){
                    if (s.getColor()=='v') res="/resources/images/verde.png";else
                        if (s.getColor()=='n') res="/resources/images/naranja.png"; else
                            res="/resources/images/rojo.png";
                    break;
                }
            }
        return res;
    }                          

     public  List<Semaforo2> getSemaforoIndicador(){
       ArrayList<Semaforo2> listaColor =new ArrayList();
      
            for (Semaforo s:indicador.getSemaforoCollection()){
                Semaforo2 s2=new Semaforo2();
                if(s.getColor()=='v'){
                    s2.setColor("/resources/images/verde.png");
                    
                }
                if(s.getColor()=='n')
                {
                     s2.setColor("/resources/images/naranja.png");
                }
                if(s.getColor()=='r')
                {
                     s2.setColor("/resources/images/rojo.png");
                }
                System.out.println("SEMAINF " +s.getLimiteInferior());
                System.out.println("SEMASUP " +s.getLimiteSuperior());
                s2.setLi(String.valueOf(s.getLimiteInferior()));
                s2.setLs(String.valueOf(s.getLimiteSuperior()));
                listaColor.add(s2);
                
            }
        return listaColor;
    }
    
    public double getDesempeño(){
        double res;
        double valorActual=this.getValorActual();
        res=(valorActual-indicador.getBase().doubleValue())/(indicador.getMeta().doubleValue()-indicador.getBase().doubleValue());
        return res;
    }

    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }

    public Objetivoestrategico getObjetivoestrategico() {
        return objetivoestrategico;
    }

    public void setObjetivoestrategico(Objetivoestrategico objetivoestrategico) {
        this.objetivoestrategico = objetivoestrategico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (objetivoestrategicoindicadorPK != null ? objetivoestrategicoindicadorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objetivoestrategicoindicador)) {
            return false;
        }
        Objetivoestrategicoindicador other = (Objetivoestrategicoindicador) object;
        if ((this.objetivoestrategicoindicadorPK == null && other.objetivoestrategicoindicadorPK != null) || (this.objetivoestrategicoindicadorPK != null && !this.objetivoestrategicoindicadorPK.equals(other.objetivoestrategicoindicadorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Objetivoestrategicoindicador[ objetivoestrategicoindicadorPK=" + objetivoestrategicoindicadorPK + " ]";
    }
    
}

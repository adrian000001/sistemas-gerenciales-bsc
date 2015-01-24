/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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

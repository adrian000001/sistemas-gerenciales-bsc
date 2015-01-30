/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Farfan
 */
@Entity
@Table(name = "componenteformla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Componenteformla.findAll", query = "SELECT c FROM Componenteformla c"),
    @NamedQuery(name = "Componenteformla.findByIdcomponenteformla", query = "SELECT c FROM Componenteformla c WHERE c.idcomponenteformla = :idcomponenteformla"),
    @NamedQuery(name = "Componenteformla.findByDescripcion", query = "SELECT c FROM Componenteformla c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Componenteformla.findByUnidad", query = "SELECT c FROM Componenteformla c WHERE c.unidad = :unidad")})
public class Componenteformla implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomponenteformla")
    private Integer idcomponenteformla;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 20)
    @Column(name = "unidad")
    private String unidad;
    @OneToMany(mappedBy = "idcomponenteformula")
    private Collection<Detallehistorial> detallehistorialCollection;
    @JoinColumn(name = "idindicador", referencedColumnName = "idIndicador")
    @ManyToOne
    private Indicador idindicador;

    public Componenteformla() {
    }

    public Componenteformla(Integer idcomponenteformla) {
        this.idcomponenteformla = idcomponenteformla;
    }

    public Integer getIdcomponenteformla() {
        return idcomponenteformla;
    }

    public void setIdcomponenteformla(Integer idcomponenteformla) {
        this.idcomponenteformla = idcomponenteformla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    @XmlTransient
    public Collection<Detallehistorial> getDetallehistorialCollection() {
        return detallehistorialCollection;
    }

    public void setDetallehistorialCollection(Collection<Detallehistorial> detallehistorialCollection) {
        this.detallehistorialCollection = detallehistorialCollection;
    }

    public Indicador getIdindicador() {
        return idindicador;
    }

    public void setIdindicador(Indicador idindicador) {
        this.idindicador = idindicador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcomponenteformla != null ? idcomponenteformla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Componenteformla)) {
            return false;
        }
        Componenteformla other = (Componenteformla) object;
        if ((this.idcomponenteformla == null && other.idcomponenteformla != null) || (this.idcomponenteformla != null && !this.idcomponenteformla.equals(other.idcomponenteformla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Componenteformla[ idcomponenteformla=" + idcomponenteformla + " ]";
    }
    
}

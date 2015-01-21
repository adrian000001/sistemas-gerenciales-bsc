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
@Table(name = "perspectiva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perspectiva.findAll", query = "SELECT p FROM Perspectiva p"),
    @NamedQuery(name = "Perspectiva.findByIdPerspectiva", query = "SELECT p FROM Perspectiva p WHERE p.idPerspectiva = :idPerspectiva"),
    @NamedQuery(name = "Perspectiva.findByNombre", query = "SELECT p FROM Perspectiva p WHERE p.nombre = :nombre")})
public class Perspectiva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPerspectiva")
    private Integer idPerspectiva;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idPerspectiva")
    private Collection<Objetivoestrategico> objetivoestrategicoCollection;

    public Perspectiva() {
    }

    public Perspectiva(Integer idPerspectiva) {
        this.idPerspectiva = idPerspectiva;
    }

    public Integer getIdPerspectiva() {
        return idPerspectiva;
    }

    public void setIdPerspectiva(Integer idPerspectiva) {
        this.idPerspectiva = idPerspectiva;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Objetivoestrategico> getObjetivoestrategicoCollection() {
        return objetivoestrategicoCollection;
    }

    public void setObjetivoestrategicoCollection(Collection<Objetivoestrategico> objetivoestrategicoCollection) {
        this.objetivoestrategicoCollection = objetivoestrategicoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerspectiva != null ? idPerspectiva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perspectiva)) {
            return false;
        }
        Perspectiva other = (Perspectiva) object;
        if ((this.idPerspectiva == null && other.idPerspectiva != null) || (this.idPerspectiva != null && !this.idPerspectiva.equals(other.idPerspectiva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Perspectiva[ idPerspectiva=" + idPerspectiva + " ]";
    }
    
}

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Farfan
 */
@Entity
@Table(name = "estrategiaglobal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estrategiaglobal.findAll", query = "SELECT e FROM Estrategiaglobal e"),
    @NamedQuery(name = "Estrategiaglobal.findByIdEstrategiaGlobal", query = "SELECT e FROM Estrategiaglobal e WHERE e.idEstrategiaGlobal = :idEstrategiaGlobal"),
    @NamedQuery(name = "Estrategiaglobal.findByEstrategia", query = "SELECT e FROM Estrategiaglobal e WHERE e.estrategia = :estrategia")})
public class Estrategiaglobal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstrategiaGlobal")
    private Integer idEstrategiaGlobal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "estrategia")
    private String estrategia;
    @OneToMany(mappedBy = "idEstrategiaGlobal")
    private Collection<Objetivoestrategico> objetivoestrategicoCollection;

    public Estrategiaglobal() {
    }

    public Estrategiaglobal(Integer idEstrategiaGlobal) {
        this.idEstrategiaGlobal = idEstrategiaGlobal;
    }

    public Estrategiaglobal(Integer idEstrategiaGlobal, String estrategia) {
        this.idEstrategiaGlobal = idEstrategiaGlobal;
        this.estrategia = estrategia;
    }

    public Integer getIdEstrategiaGlobal() {
        return idEstrategiaGlobal;
    }

    public void setIdEstrategiaGlobal(Integer idEstrategiaGlobal) {
        this.idEstrategiaGlobal = idEstrategiaGlobal;
    }

    public String getEstrategia() {
        return estrategia;
    }

    public void setEstrategia(String estrategia) {
        this.estrategia = estrategia;
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
        hash += (idEstrategiaGlobal != null ? idEstrategiaGlobal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estrategiaglobal)) {
            return false;
        }
        Estrategiaglobal other = (Estrategiaglobal) object;
        if ((this.idEstrategiaGlobal == null && other.idEstrategiaGlobal != null) || (this.idEstrategiaGlobal != null && !this.idEstrategiaGlobal.equals(other.idEstrategiaGlobal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Estrategiaglobal[ idEstrategiaGlobal=" + idEstrategiaGlobal + " ]";
    }
    
}

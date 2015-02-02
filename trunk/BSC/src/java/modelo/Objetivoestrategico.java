/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "objetivoestrategico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objetivoestrategico.findAll", query = "SELECT o FROM Objetivoestrategico o"),
    @NamedQuery(name = "Objetivoestrategico.findByIdObjetivoEstrategico", query = "SELECT o FROM Objetivoestrategico o WHERE o.idObjetivoEstrategico = :idObjetivoEstrategico"),
    @NamedQuery(name = "Objetivoestrategico.findByNumero", query = "SELECT o FROM Objetivoestrategico o WHERE o.numero = :numero"),
  })
public class Objetivoestrategico implements Serializable {
    private static final long serialVersionUID = 1L;
     public static final String nodos="Objetivoestrategico.findByNodo";
    public static String findByIdObjetivoEstrategico="Objetivoestrategico.findByIdObjetivoEstrategico";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idObjetivoEstrategico")
    private Integer idObjetivoEstrategico;
    @Column(name = "numero")
    private Integer numero;
    @Size(max = 1000)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "idEstrategiaGlobal", referencedColumnName = "idEstrategiaGlobal")
    @ManyToOne
    private Estrategiaglobal idEstrategiaGlobal;
    @JoinColumn(name = "idPerspectiva", referencedColumnName = "idPerspectiva")
    @ManyToOne
    private Perspectiva idPerspectiva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objetivoestrategico")
    private Collection<Objetivoestrategicoindicador> objetivoestrategicoindicadorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObjetivoEstrategico")
    private Collection<Actividad> actividadCollection;

    public Objetivoestrategico() {
    }

    public Objetivoestrategico(Integer idObjetivoEstrategico) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
    }

    public Integer getIdObjetivoEstrategico() {
        return idObjetivoEstrategico;
    }

    public void setIdObjetivoEstrategico(Integer idObjetivoEstrategico) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estrategiaglobal getIdEstrategiaGlobal() {
        return idEstrategiaGlobal;
    }

    public void setIdEstrategiaGlobal(Estrategiaglobal idEstrategiaGlobal) {
        this.idEstrategiaGlobal = idEstrategiaGlobal;
    }

    public Perspectiva getIdPerspectiva() {
        return idPerspectiva;
    }

    public void setIdPerspectiva(Perspectiva idPerspectiva) {
        this.idPerspectiva = idPerspectiva;
    }

    @XmlTransient
    public Collection<Objetivoestrategicoindicador> getObjetivoestrategicoindicadorCollection() {
        return objetivoestrategicoindicadorCollection;
    }

    public void setObjetivoestrategicoindicadorCollection(Collection<Objetivoestrategicoindicador> objetivoestrategicoindicadorCollection) {
        this.objetivoestrategicoindicadorCollection = objetivoestrategicoindicadorCollection;
    }

    @XmlTransient
    public Collection<Actividad> getActividadCollection() {
        return actividadCollection;
    }

    public void setActividadCollection(Collection<Actividad> actividadCollection) {
        this.actividadCollection = actividadCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObjetivoEstrategico != null ? idObjetivoEstrategico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objetivoestrategico)) {
            return false;
        }
        Objetivoestrategico other = (Objetivoestrategico) object;
        if ((this.idObjetivoEstrategico == null && other.idObjetivoEstrategico != null) || (this.idObjetivoEstrategico != null && !this.idObjetivoEstrategico.equals(other.idObjetivoEstrategico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Objetivoestrategico[ idObjetivoEstrategico=" + idObjetivoEstrategico + " ]";
    }
    
}

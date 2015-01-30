/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Farfan
 */
@Entity
@Table(name = "detallehistorial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallehistorial.findAll", query = "SELECT d FROM Detallehistorial d"),
    @NamedQuery(name = "Detallehistorial.findByIddetallehistorial", query = "SELECT d FROM Detallehistorial d WHERE d.iddetallehistorial = :iddetallehistorial"),
    @NamedQuery(name = "Detallehistorial.findByValor", query = "SELECT d FROM Detallehistorial d WHERE d.valor = :valor")})
public class Detallehistorial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetallehistorial")
    private Integer iddetallehistorial;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @JoinColumn(name = "idcomponenteformula", referencedColumnName = "idcomponenteformla")
    @ManyToOne
    private Componenteformla idcomponenteformula;
    @JoinColumn(name = "idhistorial", referencedColumnName = "idHistorial")
    @ManyToOne
    private Historial idhistorial;

    public Detallehistorial() {
    }

    public Detallehistorial(Integer iddetallehistorial) {
        this.iddetallehistorial = iddetallehistorial;
    }

    public Integer getIddetallehistorial() {
        return iddetallehistorial;
    }

    public void setIddetallehistorial(Integer iddetallehistorial) {
        this.iddetallehistorial = iddetallehistorial;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Componenteformla getIdcomponenteformula() {
        return idcomponenteformula;
    }

    public void setIdcomponenteformula(Componenteformla idcomponenteformula) {
        this.idcomponenteformula = idcomponenteformula;
    }

    public Historial getIdhistorial() {
        return idhistorial;
    }

    public void setIdhistorial(Historial idhistorial) {
        this.idhistorial = idhistorial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetallehistorial != null ? iddetallehistorial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallehistorial)) {
            return false;
        }
        Detallehistorial other = (Detallehistorial) object;
        if ((this.iddetallehistorial == null && other.iddetallehistorial != null) || (this.iddetallehistorial != null && !this.iddetallehistorial.equals(other.iddetallehistorial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Detallehistorial[ iddetallehistorial=" + iddetallehistorial + " ]";
    }
    
}

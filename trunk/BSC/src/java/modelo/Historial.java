/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Farfan
 */
@Entity
@Table(name = "historial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historial.findAll", query = "SELECT h FROM Historial h"),
    @NamedQuery(name = "Historial.findByIdHistorial", query = "SELECT h FROM Historial h WHERE h.idHistorial = :idHistorial"),
    @NamedQuery(name = "Historial.findByValor", query = "SELECT h FROM Historial h WHERE h.valor = :valor"),
    @NamedQuery(name = "Historial.findByIdIndicador", query = "SELECT h FROM Historial h WHERE h.idIndicador.idIndicador = :idIndicador ORDER BY h.fechaMedicion"),
    @NamedQuery(name = "Historial.findByFechaMedicion", query = "SELECT h FROM Historial h WHERE h.fechaMedicion = :fechaMedicion")})
public class Historial implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String findByIdIndicador="Historial.findByIdIndicador";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHistorial")
    private Integer idHistorial;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "fechaMedicion")
    @Temporal(TemporalType.DATE)
    private Date fechaMedicion;
    @JoinColumn(name = "idIndicador", referencedColumnName = "idIndicador")
    @ManyToOne(optional = false)
    private Indicador idIndicador;
    @OneToMany(mappedBy = "idhistorial")
    private Collection<Detallehistorial> detallehistorialCollection;

    public Historial() {
    }

    public Historial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getFechaMedicion() {
        return fechaMedicion;
    }

    public void setFechaMedicion(Date fechaMedicion) {
        this.fechaMedicion = fechaMedicion;
    }

    public Indicador getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(Indicador idIndicador) {
        this.idIndicador = idIndicador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorial != null ? idHistorial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historial)) {
            return false;
        }
        Historial other = (Historial) object;
        if ((this.idHistorial == null && other.idHistorial != null) || (this.idHistorial != null && !this.idHistorial.equals(other.idHistorial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Historial[ idHistorial=" + idHistorial + " ]";
    }

    @XmlTransient
    public Collection<Detallehistorial> getDetallehistorialCollection() {
        return detallehistorialCollection;
    }

    public void setDetallehistorialCollection(Collection<Detallehistorial> detallehistorialCollection) {
        this.detallehistorialCollection = detallehistorialCollection;
    }
    
}

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
@Table(name = "semaforo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Semaforo.findAll", query = "SELECT s FROM Semaforo s"),
    @NamedQuery(name = "Semaforo.findByIdSemaforo", query = "SELECT s FROM Semaforo s WHERE s.idSemaforo = :idSemaforo"),
    @NamedQuery(name = "Semaforo.findByColor", query = "SELECT s FROM Semaforo s WHERE s.color = :color"),
    @NamedQuery(name = "Semaforo.findByLimiteInferior", query = "SELECT s FROM Semaforo s WHERE s.limiteInferior = :limiteInferior"),
    @NamedQuery(name = "Semaforo.findByLimiteSuperior", query = "SELECT s FROM Semaforo s WHERE s.limiteSuperior = :limiteSuperior"),
    @NamedQuery(name = "Semaforo.findByIdIndicador", query = "SELECT s FROM Semaforo s WHERE s.idIndicador.idIndicador = :idIndicador")})
public class Semaforo implements Serializable {
    private static final long serialVersionUID = 1L;
    public static String findByIdIndicador="Semaforo.findByIdIndicador";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSemaforo")
    private Integer idSemaforo;
    @Column(name = "color")
    private Character color;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "limiteInferior")
    private BigDecimal limiteInferior;
    @Column(name = "limiteSuperior")
    private BigDecimal limiteSuperior;
    @JoinColumn(name = "idIndicador", referencedColumnName = "idIndicador")
    @ManyToOne(optional = false)
    private Indicador idIndicador;

    public Semaforo() {
    }

    public Semaforo(Integer idSemaforo) {
        this.idSemaforo = idSemaforo;
    }

    public Integer getIdSemaforo() {
        return idSemaforo;
    }

    public void setIdSemaforo(Integer idSemaforo) {
        this.idSemaforo = idSemaforo;
    }

    public Character getColor() {
        return color;
    }

    public void setColor(Character color) {
        this.color = color;
    }

    public BigDecimal getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(BigDecimal limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public BigDecimal getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(BigDecimal limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
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
        hash += (idSemaforo != null ? idSemaforo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Semaforo)) {
            return false;
        }
        Semaforo other = (Semaforo) object;
        if ((this.idSemaforo == null && other.idSemaforo != null) || (this.idSemaforo != null && !this.idSemaforo.equals(other.idSemaforo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Semaforo[ idSemaforo=" + idSemaforo + " ]";
    }
    
}

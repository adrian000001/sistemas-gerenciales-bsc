/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Farfan
 */
@Embeddable
public class ObjetivoestrategicoindicadorPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idObjetivoEstrategico")
    private int idObjetivoEstrategico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idIndicador")
    private int idIndicador;

    public ObjetivoestrategicoindicadorPK() {
    }

    public ObjetivoestrategicoindicadorPK(int idObjetivoEstrategico, int idIndicador) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
        this.idIndicador = idIndicador;
    }

    public int getIdObjetivoEstrategico() {
        return idObjetivoEstrategico;
    }

    public void setIdObjetivoEstrategico(int idObjetivoEstrategico) {
        this.idObjetivoEstrategico = idObjetivoEstrategico;
    }

    public int getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(int idIndicador) {
        this.idIndicador = idIndicador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idObjetivoEstrategico;
        hash += (int) idIndicador;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjetivoestrategicoindicadorPK)) {
            return false;
        }
        ObjetivoestrategicoindicadorPK other = (ObjetivoestrategicoindicadorPK) object;
        if (this.idObjetivoEstrategico != other.idObjetivoEstrategico) {
            return false;
        }
        if (this.idIndicador != other.idIndicador) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ObjetivoestrategicoindicadorPK[ idObjetivoEstrategico=" + idObjetivoEstrategico + ", idIndicador=" + idIndicador + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adrian
 */
@Entity
@Table(name = "nodosobj", catalog = "bsc", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nodosobj.findAll", query = "SELECT n FROM Nodosobj n"),
    @NamedQuery(name = "Nodosobj.findById", query = "SELECT n FROM Nodosobj n WHERE n.id = :id"),
    @NamedQuery(name = "Nodosobj.findByObjOrigen", query = "SELECT n FROM Nodosobj n WHERE n.objOrigen = :objOrigen"),
    @NamedQuery(name = "Nodosobj.findByObjDestino", query = "SELECT n FROM Nodosobj n WHERE n.objDestino = :objDestino")})
public class Nodosobj implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 400)
    @Column(name = "objOrigen", length = 400)
    private String objOrigen;
    @Size(max = 400)
    @Column(name = "objDestino", length = 400)
    private String objDestino;

    public Nodosobj() {
    }

    public Nodosobj(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjOrigen() {
        return objOrigen;
    }

    public void setObjOrigen(String objOrigen) {
        this.objOrigen = objOrigen;
    }

    public String getObjDestino() {
        return objDestino;
    }

    public void setObjDestino(String objDestino) {
        this.objDestino = objDestino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nodosobj)) {
            return false;
        }
        Nodosobj other = (Nodosobj) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Nodosobj[ id=" + id + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastercbr.table;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "KASUS", catalog = "", schema = "MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kasus.findAll", query = "SELECT k FROM Kasus k")
    , @NamedQuery(name = "Kasus.findByIdKasus", query = "SELECT k FROM Kasus k WHERE k.idKasus = :idKasus")})
public class Kasus implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_KASUS", nullable = false)
    @GeneratedValue
    private Long idKasus;
    @OneToOne(cascade = {CascadeType.ALL})
    private RekamMedis rekamMedis;
    
//    @OneToMany
    private List<Long> gejalaList = new LinkedList<>();

    public static final String PROP_GEJALALIST = "gejalaList";

    /**
     * Get the value of gejalaList
     *
     * @return the value of gejalaList
     */
    public List<Long> getGejalaList() {
        return gejalaList;
    }

    /**
     * Set the value of gejalaList
     *
     * @param gejalaList new value of gejalaList
     */
    public void setGejalaList(List<Long> gejalaList) {
        List<Long> oldGejalaList = this.gejalaList;
        this.gejalaList = gejalaList;
        changeSupport.firePropertyChange(PROP_GEJALALIST, oldGejalaList, gejalaList);
    }


    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */




//    private List<Gejala> gejalas;

    public RekamMedis getRekamMedis() {
        return rekamMedis;
    }

    public void setRekamMedis(RekamMedis rekamMedis) {
        this.rekamMedis = rekamMedis;
    }
    
    public Kasus() {
    }

    public Kasus(Long idKasus) {
        this.idKasus = idKasus;
    }

    public Long getIdKasus() {
        return idKasus;
    }

    public void setIdKasus(Long idKasus) {
        Long oldIdKasus = this.idKasus;
        this.idKasus = idKasus;
//        changeSupport.firePropertyChange("idKasus", oldIdKasus, idKasus);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKasus != null ? idKasus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kasus)) {
            return false;
        }
        Kasus other = (Kasus) object;
        if ((this.idKasus == null && other.idKasus != null) || (this.idKasus != null && !this.idKasus.equals(other.idKasus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mastercbr.table.Kasus[ idKasus=" + idKasus + " ]";
    }

    
}

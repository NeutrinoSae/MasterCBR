/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastercbr.table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_KASUS", nullable = false)
    @GeneratedValue
    private Long idKasus;
    
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kasusIdKasus")
    private Collection<RekamMedis> rekamMedisCollection;

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
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

    public Collection<RekamMedis> getRekamMedisCollection() {
        return rekamMedisCollection;
    }

    public void setRekamMedisCollection(Collection<RekamMedis> rekamMedisCollection) {
        this.rekamMedisCollection = rekamMedisCollection;
    }
    
}

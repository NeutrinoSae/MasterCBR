/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastercbr.table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "GEJALA", catalog = "", schema = "MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gejala.findAll", query = "SELECT g FROM Gejala g")
    , @NamedQuery(name = "Gejala.findByIdGejala", query = "SELECT g FROM Gejala g WHERE g.idGejala = :idGejala")
    , @NamedQuery(name = "Gejala.findByNamaGejala", query = "SELECT g FROM Gejala g WHERE g.namaGejala = :namaGejala")
    , @NamedQuery(name = "Gejala.findByValue", query = "SELECT g FROM Gejala g WHERE g.value = :value")
    , @NamedQuery(name = "Gejala.findByKeterangan", query = "SELECT g FROM Gejala g WHERE g.keterangan = :keterangan")})
public class Gejala implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_GEJALA", nullable = false)
    @GeneratedValue
    private Long idGejala;
    @Column(name = "NAMA_GEJALA", length = 255)
    private String namaGejala;
    @Column(name = "VALUE", precision = 4)
    private Double value;
    @Column(name = "KETERANGAN", length = 255)
    private String keterangan;

    public Gejala() {
        this.value = 1d;
        this.namaGejala = "Gejala "+idGejala;
    }
    @Transient
    private boolean pilihan;

    public static final String PROP_PILIHAN = "pilihan";

    /**
     * Get the value of pilihan
     *
     * @return the value of pilihan
     */
    public boolean isPilihan() {
        return pilihan;
    }

    /**
     * Set the value of pilihan
     *
     * @param pilihan new value of pilihan
     */
    public void setPilihan(boolean pilihan) {
        boolean oldPilihan = this.pilihan;
        this.pilihan = pilihan;
        changeSupport.firePropertyChange(PROP_PILIHAN, oldPilihan, pilihan);
    }

    public Gejala(Long idGejala) {
        this.value = 1d;
        this.idGejala = idGejala;
    }

    public Long getIdGejala() {
        return idGejala;
    }

    public void setIdGejala(Long idGejala) {
        Long oldIdGejala = this.idGejala;
        this.idGejala = idGejala;
        changeSupport.firePropertyChange("idGejala", oldIdGejala, idGejala);
    }

    public String getNamaGejala() {
        return namaGejala;
    }

    public void setNamaGejala(String namaGejala) {
        String oldNamaGejala = this.namaGejala;
        this.namaGejala = namaGejala;
        changeSupport.firePropertyChange("namaGejala", oldNamaGejala, namaGejala);
    }

    public Double getValue() {
        return value;
    }

    public String getInfo()
    {
        return "[G-" + idGejala + "]";
    }    
    public void setValue(Double value) {
        Double oldValue = this.value;
        this.value = value;
        changeSupport.firePropertyChange("value", oldValue, value);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGejala != null ? idGejala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gejala)) {
            return false;
        }
        Gejala other = (Gejala) object;
        if ((this.idGejala == null && other.idGejala != null) || (this.idGejala != null && !this.idGejala.equals(other.idGejala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mastercbr.table.Gejala[ idGejala=" + idGejala + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}

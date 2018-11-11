/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastercbr.table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "REKAM_MEDIS", catalog = "", schema = "MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RekamMedis.findAll", query = "SELECT r FROM RekamMedis r")
    , @NamedQuery(name = "RekamMedis.findByRmId", query = "SELECT r FROM RekamMedis r WHERE r.rmId = :rmId")
    , @NamedQuery(name = "RekamMedis.findByTanggalKonsultasi", query = "SELECT r FROM RekamMedis r WHERE r.tanggalKonsultasi = :tanggalKonsultasi")})
public class RekamMedis implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="rekammedis")     
    @Column(name = "RM_ID", nullable = false)
    private Long rmId;
    @Column(name = "TANGGAL_KONSULTASI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalKonsultasi;
    
    @JoinColumn(name = "PASIEN_ID_PASIEN", referencedColumnName = "ID_PASIEN")
    @ManyToOne
    private Pasien pasienIdPasien;
    
    @OneToOne(mappedBy = "rekamMedis", cascade = {CascadeType.ALL})
    private Kasus kasus;
//    private Kasus kasus;

    public String getInfo()
    {
        return "[RM-" + rmId + "]";
    }
    public RekamMedis() {
        this.kasus = new Kasus();
        this.tanggalKonsultasi = new Date();
    }

    public static final String PROP_KASUS = "kasus";

    /**
     * Get the value of kasus
     *
     * @return the value of kasus
     */
    public Kasus getKasus() {
        return kasus;
    }

    /**
     * Set the value of kasus
     *
     * @param kasus new value of kasus
     */
    public void setKasus(Kasus kasus) {
        Kasus oldKasus = this.kasus;
        this.kasus = kasus;
        changeSupport.firePropertyChange(PROP_KASUS, oldKasus, kasus);
    }


    public RekamMedis(Long rmId) {
        this.kasus = new Kasus();
        this.tanggalKonsultasi = new Date();
        this.rmId = rmId;
    }

    public Long getRmId() {
        return rmId;
    }

    public void setRmId(Long rmId) {
        Long oldRmId = this.rmId;
        this.rmId = rmId;
        changeSupport.firePropertyChange("rmId", oldRmId, rmId);
    }

    public Date getTanggalKonsultasi() {
        return tanggalKonsultasi;
    }

    public void setTanggalKonsultasi(Date tanggalKonsultasi) {
        Date oldTanggalKonsultasi = this.tanggalKonsultasi;
        this.tanggalKonsultasi = tanggalKonsultasi;
        changeSupport.firePropertyChange("tanggalKonsultasi", oldTanggalKonsultasi, tanggalKonsultasi);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rmId != null ? rmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RekamMedis)) {
            return false;
        }
        RekamMedis other = (RekamMedis) object;
        if ((this.rmId == null && other.rmId != null) || (this.rmId != null && !this.rmId.equals(other.rmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RM[" + rmId + " ]";
    }

    public Pasien getPasienIdPasien() {
        return pasienIdPasien;
    }

    public void setPasienIdPasien(Pasien pasienIdPasien) {
        Pasien oldPasienIdPasien = this.pasienIdPasien;
        this.pasienIdPasien = pasienIdPasien;
        changeSupport.firePropertyChange("pasienIdPasien", oldPasienIdPasien, pasienIdPasien);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }


    
}

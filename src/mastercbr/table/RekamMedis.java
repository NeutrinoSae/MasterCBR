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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @GeneratedValue
    @Column(name = "RM_ID", nullable = false)
    private Long rmId;
    @Column(name = "TANGGAL_KONSULTASI")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalKonsultasi;
    
    @JoinColumn(name = "PASIEN_ID_PASIEN", referencedColumnName = "ID_PASIEN")
    @ManyToOne
    private Pasien pasienIdPasien;
    
    @JoinColumn(name = "KASUS_ID_KASUS", referencedColumnName = "ID_KASUS")
    @ManyToOne
    private Kasus kasusIdKasus;


    public RekamMedis() {
        this.tanggalKonsultasi = new Date();
    }

    public RekamMedis(Long rmId) {
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
        return "mastercbr.table.RekamMedis[ rmId=" + rmId + " ]";
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

    public Kasus getKasusIdKasus() {
        return kasusIdKasus;
    }

    public void setKasusIdKasus(Kasus kasusIdKasus) {
        Kasus oldKasusIdKasus = this.kasusIdKasus;
        this.kasusIdKasus = kasusIdKasus;
        changeSupport.firePropertyChange("kasusIdKasus", oldKasusIdKasus, kasusIdKasus);
    }
    
}

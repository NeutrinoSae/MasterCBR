/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastercbr.table;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "PASIEN", catalog = "", schema = "MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pasien.findAll", query = "SELECT p FROM Pasien p")
    , @NamedQuery(name = "Pasien.findByIdPasien", query = "SELECT p FROM Pasien p WHERE p.idPasien = :idPasien")
    , @NamedQuery(name = "Pasien.findByNama", query = "SELECT p FROM Pasien p WHERE p.nama = :nama")
    , @NamedQuery(name = "Pasien.findByTanggalLahir", query = "SELECT p FROM Pasien p WHERE p.tanggalLahir = :tanggalLahir")
    , @NamedQuery(name = "Pasien.findByTempatLahir", query = "SELECT p FROM Pasien p WHERE p.tempatLahir = :tempatLahir")
    , @NamedQuery(name = "Pasien.findByJenisKelamin", query = "SELECT p FROM Pasien p WHERE p.jenisKelamin = :jenisKelamin")
    , @NamedQuery(name = "Pasien.findByAlamat", query = "SELECT p FROM Pasien p WHERE p.alamat = :alamat")
    , @NamedQuery(name = "Pasien.findByKeterangan", query = "SELECT p FROM Pasien p WHERE p.keterangan = :keterangan")
    , @NamedQuery(name = "Pasien.findByKelompokUmur", query = "SELECT p FROM Pasien p WHERE p.kelompokUmur = :kelompokUmur")})
public class Pasien implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue
    @Column(name = "ID_PASIEN", nullable = false)
    private Long idPasien;
    @Column(name = "NAMA", length = 255)
    private String nama;
    @Column(name = "TANGGAL_LAHIR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalLahir = new Date(100, 0, 1);
    @Column(name = "TEMPAT_LAHIR", length = 255)
    private String tempatLahir;
    @Column(name = "JENIS_KELAMIN")
    private Boolean jenisKelamin = Boolean.FALSE;
    @Column(name = "ALAMAT", length = 255)
    private String alamat;
    @Column(name = "KETERANGAN", length = 255)
    private String keterangan;
    @Column(name = "KELOMPOK_UMUR")
    private Integer kelompokUmur;

    public Pasien() {
    }

    public Pasien(Long idPasien) {
        this.idPasien = idPasien;
    }

    public Long getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(Long idPasien) {
        Long oldIdPasien = this.idPasien;
        this.idPasien = idPasien;
        changeSupport.firePropertyChange("idPasien", oldIdPasien, idPasien);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        String oldNama = this.nama;
        this.nama = nama;
        changeSupport.firePropertyChange("nama", oldNama, nama);
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        Date oldTanggalLahir = this.tanggalLahir;
        this.tanggalLahir = tanggalLahir;
        changeSupport.firePropertyChange("tanggalLahir", oldTanggalLahir, tanggalLahir);
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        String oldTempatLahir = this.tempatLahir;
        this.tempatLahir = tempatLahir;
        changeSupport.firePropertyChange("tempatLahir", oldTempatLahir, tempatLahir);
    }

    public String getJenisKelaminString()
    {
        if (jenisKelamin) {
            return "LAKI-LAKI";
        }
            return "PEREMPUAN";
    }
    public void setJenisKelaminString(String jenisKelaminString)
    {
        String oldJenisKelaminString = getJenisKelaminString();
        if (jenisKelaminString.equals("LAKI-LAKI")) {
            setJenisKelamin(true);
        }
        else {
            setJenisKelamin(false);
        }
        changeSupport.firePropertyChange("jenisKelaminString", oldJenisKelaminString, jenisKelaminString);
    }
    public Boolean getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Boolean jenisKelamin) {
        Boolean oldJenisKelamin = this.jenisKelamin;
        this.jenisKelamin = jenisKelamin;
        changeSupport.firePropertyChange("jenisKelamin", oldJenisKelamin, jenisKelamin);
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        String oldAlamat = this.alamat;
        this.alamat = alamat;
        changeSupport.firePropertyChange("alamat", oldAlamat, alamat);
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        String oldKeterangan = this.keterangan;
        this.keterangan = keterangan;
        changeSupport.firePropertyChange("keterangan", oldKeterangan, keterangan);
    }

    public Integer getKelompokUmur() {
        return kelompokUmur;
    }

    public void setKelompokUmur(Integer kelompokUmur) {
        Integer oldKelompokUmur = this.kelompokUmur;
        this.kelompokUmur = kelompokUmur;
        changeSupport.firePropertyChange("kelompokUmur", oldKelompokUmur, kelompokUmur);
    }

    public Integer getUmur()
    {
        LocalDate BD = LocalDate.of(
                tanggalLahir.getYear() + 1900
                ,tanggalLahir.getMonth() + 1 
                , tanggalLahir.getDate());
        LocalDate now = LocalDate.now(); 
        int x = Period.between(BD, now).getYears();
        if (x <= 20) {
            setKelompokUmur(1);
        }
        if (x >= 20) {
            setKelompokUmur(2);
        }
        if (x > 50) {
            setKelompokUmur(3);
        }
        return x;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPasien != null ? idPasien.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pasien)) {
            return false;
        }
        Pasien other = (Pasien) object;
        if ((this.idPasien == null && other.idPasien != null) || (this.idPasien != null && !this.idPasien.equals(other.idPasien))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mastercbr.table.Pasien[ idPasien=" + idPasien + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}

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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SEED
 */
@Entity
@Table(name = "PENYAKIT", catalog = "", schema = "MASTER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Penyakit.findAll", query = "SELECT p FROM Penyakit p")
    , @NamedQuery(name = "Penyakit.findByIdPenyakit", query = "SELECT p FROM Penyakit p WHERE p.idPenyakit = :idPenyakit")
    , @NamedQuery(name = "Penyakit.findBySolusi", query = "SELECT p FROM Penyakit p WHERE p.solusi = :solusi")
    , @NamedQuery(name = "Penyakit.findByNamaPenyakit", query = "SELECT p FROM Penyakit p WHERE p.namaPenyakit = :namaPenyakit")
    , @NamedQuery(name = "Penyakit.findByKeterangan", query = "SELECT p FROM Penyakit p WHERE p.keterangan = :keterangan")})
public class Penyakit implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PENYAKIT", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="penyakit")     
    private Long idPenyakit;
    @Column(name = "SOLUSI", length = 255)
    private String solusi;
    @Basic(optional = false)
    @Column(name = "NAMA_PENYAKIT", length = 255)
    private String namaPenyakit;
    @Column(name = "KETERANGAN", length = 255)
    private String keterangan;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "penyakitIdPenyakit")
    private Collection<Kasus> kasusCollection;

    public Penyakit() {
        this.namaPenyakit = "nama penyakit";
        this.solusi = "solusi";
    }

    public Penyakit(Long idPenyakit) {
        this.namaPenyakit = "nama penyakit";
        this.solusi = "solusi";
        this.idPenyakit = idPenyakit;
    }

    public Penyakit(Long idPenyakit, String namaPenyakit) {
        this.namaPenyakit = "nama penyakit";
        this.solusi = "solusi";
        this.idPenyakit = idPenyakit;
        this.namaPenyakit = namaPenyakit;
    }

    public Long getIdPenyakit() {
        return idPenyakit;
    }

    public void setIdPenyakit(Long idPenyakit) {
        Long oldIdPenyakit = this.idPenyakit;
        this.idPenyakit = idPenyakit;
        changeSupport.firePropertyChange("idPenyakit", oldIdPenyakit, idPenyakit);
    }

    public String getSolusi() {
        return solusi;
    }

    public void setSolusi(String solusi) {
        String oldSolusi = this.solusi;
        this.solusi = solusi;
        changeSupport.firePropertyChange("solusi", oldSolusi, solusi);
    }

    public String getNamaPenyakit() {
        return namaPenyakit;
    }

    public void setNamaPenyakit(String namaPenyakit) {
        String oldNamaPenyakit = this.namaPenyakit;
        this.namaPenyakit = namaPenyakit;
        changeSupport.firePropertyChange("namaPenyakit", oldNamaPenyakit, namaPenyakit);
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
        hash += (idPenyakit != null ? idPenyakit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Penyakit)) {
            return false;
        }
        Penyakit other = (Penyakit) object;
        if ((this.idPenyakit == null && other.idPenyakit != null) || (this.idPenyakit != null && !this.idPenyakit.equals(other.idPenyakit))) {
            return false;
        }
        return true;
    }
    
    public String getInfo()
    {
        return "[P-" + idPenyakit + "]";
    }

    @Override
    public String toString() {
        return "[P-" + idPenyakit + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public Collection<Kasus> getKasusCollection() {
        return kasusCollection;
    }

    public void setKasusCollection(Collection<Kasus> kasusCollection) {
        this.kasusCollection = kasusCollection;
    }
    
}

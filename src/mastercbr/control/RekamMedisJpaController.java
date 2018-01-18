/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastercbr.control;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mastercbr.control.exceptions.NonexistentEntityException;
import mastercbr.table.Pasien;
import mastercbr.table.Kasus;
import mastercbr.table.RekamMedis;

/**
 *
 * @author SEED
 */
public class RekamMedisJpaController implements Serializable {

    public RekamMedisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RekamMedis rekamMedis) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pasien pasienIdPasien = rekamMedis.getPasienIdPasien();
            if (pasienIdPasien != null) {
                pasienIdPasien = em.getReference(pasienIdPasien.getClass(), pasienIdPasien.getIdPasien());
                rekamMedis.setPasienIdPasien(pasienIdPasien);
            }
            Kasus kasusIdKasus = rekamMedis.getKasusIdKasus();
            if (kasusIdKasus != null) {
                kasusIdKasus = em.getReference(kasusIdKasus.getClass(), kasusIdKasus.getIdKasus());
                rekamMedis.setKasusIdKasus(kasusIdKasus);
            }
            em.persist(rekamMedis);
            if (pasienIdPasien != null) {
                pasienIdPasien.getRekamMedisCollection().add(rekamMedis);
                pasienIdPasien = em.merge(pasienIdPasien);
            }
            if (kasusIdKasus != null) {
                kasusIdKasus.getRekamMedisCollection().add(rekamMedis);
                kasusIdKasus = em.merge(kasusIdKasus);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RekamMedis rekamMedis) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RekamMedis persistentRekamMedis = em.find(RekamMedis.class, rekamMedis.getRmId());
            Pasien pasienIdPasienOld = persistentRekamMedis.getPasienIdPasien();
            Pasien pasienIdPasienNew = rekamMedis.getPasienIdPasien();
            Kasus kasusIdKasusOld = persistentRekamMedis.getKasusIdKasus();
            Kasus kasusIdKasusNew = rekamMedis.getKasusIdKasus();
            if (pasienIdPasienNew != null) {
                pasienIdPasienNew = em.getReference(pasienIdPasienNew.getClass(), pasienIdPasienNew.getIdPasien());
                rekamMedis.setPasienIdPasien(pasienIdPasienNew);
            }
            if (kasusIdKasusNew != null) {
                kasusIdKasusNew = em.getReference(kasusIdKasusNew.getClass(), kasusIdKasusNew.getIdKasus());
                rekamMedis.setKasusIdKasus(kasusIdKasusNew);
            }
            rekamMedis = em.merge(rekamMedis);
            if (pasienIdPasienOld != null && !pasienIdPasienOld.equals(pasienIdPasienNew)) {
                pasienIdPasienOld.getRekamMedisCollection().remove(rekamMedis);
                pasienIdPasienOld = em.merge(pasienIdPasienOld);
            }
            if (pasienIdPasienNew != null && !pasienIdPasienNew.equals(pasienIdPasienOld)) {
                pasienIdPasienNew.getRekamMedisCollection().add(rekamMedis);
                pasienIdPasienNew = em.merge(pasienIdPasienNew);
            }
            if (kasusIdKasusOld != null && !kasusIdKasusOld.equals(kasusIdKasusNew)) {
                kasusIdKasusOld.getRekamMedisCollection().remove(rekamMedis);
                kasusIdKasusOld = em.merge(kasusIdKasusOld);
            }
            if (kasusIdKasusNew != null && !kasusIdKasusNew.equals(kasusIdKasusOld)) {
                kasusIdKasusNew.getRekamMedisCollection().add(rekamMedis);
                kasusIdKasusNew = em.merge(kasusIdKasusNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = rekamMedis.getRmId();
                if (findRekamMedis(id) == null) {
                    throw new NonexistentEntityException("The rekamMedis with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RekamMedis rekamMedis;
            try {
                rekamMedis = em.getReference(RekamMedis.class, id);
                rekamMedis.getRmId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rekamMedis with id " + id + " no longer exists.", enfe);
            }
            Pasien pasienIdPasien = rekamMedis.getPasienIdPasien();
            if (pasienIdPasien != null) {
                pasienIdPasien.getRekamMedisCollection().remove(rekamMedis);
                pasienIdPasien = em.merge(pasienIdPasien);
            }
            Kasus kasusIdKasus = rekamMedis.getKasusIdKasus();
            if (kasusIdKasus != null) {
                kasusIdKasus.getRekamMedisCollection().remove(rekamMedis);
                kasusIdKasus = em.merge(kasusIdKasus);
            }
            em.remove(rekamMedis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RekamMedis> findRekamMedisEntities() {
        return findRekamMedisEntities(true, -1, -1);
    }

    public List<RekamMedis> findRekamMedisEntities(int maxResults, int firstResult) {
        return findRekamMedisEntities(false, maxResults, firstResult);
    }

    private List<RekamMedis> findRekamMedisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RekamMedis.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RekamMedis findRekamMedis(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RekamMedis.class, id);
        } finally {
            em.close();
        }
    }

    public int getRekamMedisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RekamMedis> rt = cq.from(RekamMedis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

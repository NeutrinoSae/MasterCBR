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
import mastercbr.control.exceptions.PreexistingEntityException;
import mastercbr.table.Pasien;

/**
 *
 * @author SEED
 */
public class PasienJpaController implements Serializable {

    public PasienJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pasien pasien) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pasien);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPasien(pasien.getIdPasien()) != null) {
                throw new PreexistingEntityException("Pasien " + pasien + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pasien pasien) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pasien = em.merge(pasien);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pasien.getIdPasien();
                if (findPasien(id) == null) {
                    throw new NonexistentEntityException("The pasien with id " + id + " no longer exists.");
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
            Pasien pasien;
            try {
                pasien = em.getReference(Pasien.class, id);
                pasien.getIdPasien();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pasien with id " + id + " no longer exists.", enfe);
            }
            em.remove(pasien);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pasien> findPasienEntities() {
        return findPasienEntities(true, -1, -1);
    }

    public List<Pasien> findPasienEntities(int maxResults, int firstResult) {
        return findPasienEntities(false, maxResults, firstResult);
    }

    private List<Pasien> findPasienEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pasien.class));
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

    public Pasien findPasien(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pasien.class, id);
        } finally {
            em.close();
        }
    }

    public int getPasienCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pasien> rt = cq.from(Pasien.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

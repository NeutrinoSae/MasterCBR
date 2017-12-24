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
import mastercbr.table.Kasus;

/**
 *
 * @author SEED
 */
public class KasusJpaController implements Serializable {

    public KasusJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Kasus kasus) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(kasus);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKasus(kasus.getIdKasus()) != null) {
                throw new PreexistingEntityException("Kasus " + kasus + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Kasus kasus) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            kasus = em.merge(kasus);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = kasus.getIdKasus();
                if (findKasus(id) == null) {
                    throw new NonexistentEntityException("The kasus with id " + id + " no longer exists.");
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
            Kasus kasus;
            try {
                kasus = em.getReference(Kasus.class, id);
                kasus.getIdKasus();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kasus with id " + id + " no longer exists.", enfe);
            }
            em.remove(kasus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Kasus> findKasusEntities() {
        return findKasusEntities(true, -1, -1);
    }

    public List<Kasus> findKasusEntities(int maxResults, int firstResult) {
        return findKasusEntities(false, maxResults, firstResult);
    }

    private List<Kasus> findKasusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Kasus.class));
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

    public Kasus findKasus(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Kasus.class, id);
        } finally {
            em.close();
        }
    }

    public int getKasusCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Kasus> rt = cq.from(Kasus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

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
import mastercbr.table.Gejala;

/**
 *
 * @author SEED
 */
public class GejalaJpaController implements Serializable {

    public GejalaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Gejala gejala) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(gejala);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Gejala gejala) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            gejala = em.merge(gejala);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = gejala.getIdGejala();
                if (findGejala(id) == null) {
                    throw new NonexistentEntityException("The gejala with id " + id + " no longer exists.");
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
            Gejala gejala;
            try {
                gejala = em.getReference(Gejala.class, id);
                gejala.getIdGejala();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The gejala with id " + id + " no longer exists.", enfe);
            }
            em.remove(gejala);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Gejala> findGejalaEntities() {
        return findGejalaEntities(true, -1, -1);
    }

    public List<Gejala> findGejalaEntities(int maxResults, int firstResult) {
        return findGejalaEntities(false, maxResults, firstResult);
    }

    private List<Gejala> findGejalaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Gejala.class));
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

    public Gejala findGejala(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Gejala.class, id);
        } finally {
            em.close();
        }
    }

    public int getGejalaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Gejala> rt = cq.from(Gejala.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

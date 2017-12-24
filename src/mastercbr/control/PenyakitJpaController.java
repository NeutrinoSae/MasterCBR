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
import mastercbr.table.Penyakit;

/**
 *
 * @author SEED
 */
public class PenyakitJpaController implements Serializable {

    public PenyakitJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Penyakit penyakit) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(penyakit);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPenyakit(penyakit.getIdPenyakit()) != null) {
                throw new PreexistingEntityException("Penyakit " + penyakit + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Penyakit penyakit) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            penyakit = em.merge(penyakit);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = penyakit.getIdPenyakit();
                if (findPenyakit(id) == null) {
                    throw new NonexistentEntityException("The penyakit with id " + id + " no longer exists.");
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
            Penyakit penyakit;
            try {
                penyakit = em.getReference(Penyakit.class, id);
                penyakit.getIdPenyakit();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The penyakit with id " + id + " no longer exists.", enfe);
            }
            em.remove(penyakit);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Penyakit> findPenyakitEntities() {
        return findPenyakitEntities(true, -1, -1);
    }

    public List<Penyakit> findPenyakitEntities(int maxResults, int firstResult) {
        return findPenyakitEntities(false, maxResults, firstResult);
    }

    private List<Penyakit> findPenyakitEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Penyakit.class));
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

    public Penyakit findPenyakit(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Penyakit.class, id);
        } finally {
            em.close();
        }
    }

    public int getPenyakitCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Penyakit> rt = cq.from(Penyakit.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

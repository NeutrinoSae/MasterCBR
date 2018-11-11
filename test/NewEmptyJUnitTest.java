/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mastercbr.form.cbrMethod;
import mastercbr.table.Kasus;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SEED
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {
         System.out.println("NewEmptyJUnitTest.hello()");
        EntityManager entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("MasterCBRPU").createEntityManager();
        Query query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT k FROM Kasus k");
        List<Kasus> list = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(query.getResultList());
        
        System.out.println("list = " + list);
        Kasus find = entityManager.find(Kasus.class, 56l);
        list.remove(find);
        System.out.println("find = " + find.getGejalaList());
                
        List<Kasus> retrieveCosineSimilarity = cbrMethod.retrieveCosineSimilarity(list, find);
         for (Kasus kasus : retrieveCosineSimilarity) {
             System.out.println("kasus = " + kasus.getSimiliarity());
         }
//        List<Kasus> indexingNaiveBayes = cbrMethod.indexingNaiveBayes(list,find);
//        System.out.println("indexingNaiveBayes = " + indexingNaiveBayes);
     }
}

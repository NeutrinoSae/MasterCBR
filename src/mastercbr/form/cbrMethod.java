/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastercbr.form;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import mastercbr.table.Gejala;
import mastercbr.table.Kasus;

/**
 *
 * @author SEED
 */
public class cbrMethod {
    
    private static final EntityManager entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("MasterCBRPU").createEntityManager();
    
    public static List<Kasus> sorensonCoefficient(Kasus Baru, List<Kasus> lama)
        {
            EntityManager entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("MasterCBRPU").createEntityManager();        
            Query query1 = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT g FROM Gejala g");
            List<Gejala> resultList = query1.getResultList();
            int jumlahGejala = resultList.size();
            List<Long> gejalaListBaru = Baru.getGejalaList();
            for (Kasus kasusLama : lama) {
                int m11 = 0;
                int m10 = 0;
                int m01 = 0;
                int m00 = 0;
                List<Long> gejalaListLama = kasusLama.getGejalaList();
                for (Gejala gejala : resultList) {
                    Long idGejala = gejala.getIdGejala();
                    if (gejalaListBaru.contains(idGejala) && gejalaListLama.contains(idGejala)) {
                        m11++;
                    }
                    else if (gejalaListBaru.contains(idGejala))
                    {
                        m10++;
                    }
                    else if (gejalaListLama.contains(idGejala))
                    {
                        m01++;
                    }
                    else {
                        m00++;
                    }
                }                
                System.out.println("kasus = " + kasusLama);
                double y = m11;
                y = y * 2d;
                double x = m11 + m10 + m01; 
                x = x * 2d;
                double temp = y / x;
                System.out.println("temp = " + temp);
                kasusLama.setSimiliarity(temp);
            }            
            return lama;
        }    
    
    public static List<Kasus> indexingNaiveBayes(List<Kasus> data)
    {   
        System.out.println("mastercbr.form.cbrMethod.indexingNaiveBayes()");
    
        return data;
    }

    public static List<Kasus> indexingNaiveBayes(List<Kasus> list, Kasus find) {
        System.out.println("mastercbr.form.cbrMethod.indexingNaiveBayes()");
    
        return list;
    }
    public static List<Kasus> retrieveCosineSimilarity (List<Kasus> list, Kasus find) {
        System.out.println("mastercbr.form.cbrMethod.indexingNaiveBayes()");
        
        for (Kasus kasus : list) {
            double xy = getXY(find, kasus);
            double absoluteX = getAbsolute(find.getGejalaList());
            double absoluteY = getAbsolute(kasus.getGejalaList());
            double absoluteXY = absoluteX*absoluteY;
            double similiarity = xy / absoluteXY;
            kasus.setSimiliarity(similiarity);
            
        }
        
        return list;
    }
    private static double getAbsolute(List<Long> list)
    {
        double absolute = 0d;
        for (Long l : list) {
            absolute = absolute + (1 * 1);            
        }
        absolute = Math.sqrt(absolute);
        return absolute;
    }
    private static double getXY(Kasus X, Kasus Y)
    {
        double xy = 0d;        
        List<Long> gejalaListX = X.getGejalaList();
        List<Long> gejalaListY = Y.getGejalaList();
        
        for (Long l : gejalaListX) {
            if (gejalaListY.contains(l)) {
                Gejala find = entityManager.find(Gejala.class, l);
                Double bobot = find.getValue();
                xy = xy + (bobot);
            }
        }
        return xy;
    }
    
}

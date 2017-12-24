/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastercbr;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mastercbr.control.LoginJpaController;
import mastercbr.form.loginFrame;
import mastercbr.table.Login;

/**
 *
 * @author SEED
 */
public class MasterCBR {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("mastercbr.MasterCBR.main()");
        LoginJpaController jpaController = new LoginJpaController(javax.persistence.Persistence.createEntityManagerFactory("MasterCBRPU"));
        List<Login> findLoginEntities = jpaController.findLoginEntities();
        if (findLoginEntities.size() < 1) {
            Login login = new Login();
            login.setUsername("ADMIN");
            login.setPassword("ADMIN");
            try {
                jpaController.create(login);
            } catch (Exception ex) {
                Logger.getLogger(MasterCBR.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        loginFrame.main(args);
        // TODO code application logic here
    }
    
}

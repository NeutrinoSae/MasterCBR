/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastercbr.form;

import java.awt.HeadlessException;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author SEED
 */
public class formMain extends javax.swing.JFrame {

    /**
     * Creates new form formMain
     */
    public formMain() {
        initComponents();
//        ImageIcon home = new javax.swing.ImageIcon(getClass().getResource("/mastercbr/main.jpg"));
        ImageIcon home = new javax.swing.ImageIcon(getClass().getResource("/mastercbr/main.jpeg"));
        Image image = home.getImage(); // transform it 
        Image newimg = image.getScaledInstance(this.getSize().width,600,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon imageIcon = new ImageIcon(newimg);
        jLabel1.setIcon(imageIcon);
        this.setExtendedState(6);
    }

    public formMain(String title) throws HeadlessException {
        super(title);
        initComponents();
        this.jTabbedPane1.remove(2);
        this.jTabbedPane1.remove(2);
        this.jTabbedPane1.remove(2);
        this.jTabbedPane1.remove(2);
//        ImageIcon home = new javax.swing.ImageIcon(getClass().getResource("/mastercbr/main.jpeg"));
        ImageIcon home = new javax.swing.ImageIcon(getClass().getResource("/mastercbr/main.jpg"));
        Image image = home.getImage(); // transform it 
        Image newimg = image.getScaledInstance(this.getSize().width,600,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon imageIcon = new ImageIcon(newimg);
        jLabel1.setIcon(imageIcon);
        this.setExtendedState(6);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        formKonsultasiPasien1 = new mastercbr.form.formKonsultasiPasien();
        formKasus1 = new mastercbr.form.formKasus();
        formGejala1 = new mastercbr.form.formGejala();
        formDiagnosaKasus1 = new mastercbr.form.formDiagnosaKasus();
        formPakar1 = new mastercbr.form.formPakar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MASTER CBR");
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setFocusable(false);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Home", jPanel1);
        jTabbedPane1.addTab("Konsultasi Pasien", formKonsultasiPasien1);
        jTabbedPane1.addTab("Kasus", formKasus1);
        jTabbedPane1.addTab("Gejala", formGejala1);
        jTabbedPane1.addTab("Penyakit", formDiagnosaKasus1);
        jTabbedPane1.addTab("Login", formPakar1);

        getContentPane().add(jTabbedPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private mastercbr.form.formDiagnosaKasus formDiagnosaKasus1;
    private mastercbr.form.formGejala formGejala1;
    private mastercbr.form.formKasus formKasus1;
    private mastercbr.form.formKonsultasiPasien formKonsultasiPasien1;
    private mastercbr.form.formPakar formPakar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}

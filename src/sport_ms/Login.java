/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sport_ms;

import Database.DBConnection;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author dinet
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    Connection con = null, con2 = null;

    public Login() {
        initComponents();
        con = DBConnection.getDbConnect().connect();

    }

    public void changeimage() {
//   Image image=GenerateImage.toImage(true);  //this generates an image file

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUserName = new javax.swing.JTextField();
        pwdPassword = new javax.swing.JPasswordField();
        lblLogin = new javax.swing.JLabel();
        lblErruser = new javax.swing.JLabel();
        lblErrpw = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblUsername1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblBackgroud = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUserName.setBorder(null);
        getContentPane().add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, 350, 30));

        pwdPassword.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        pwdPassword.setBorder(null);
        getContentPane().add(pwdPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 360, 30));

        lblLogin.setFont(new java.awt.Font("Trebuchet MS", 0, 36)); // NOI18N
        lblLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogin.setText("LogIn");
        getContentPane().add(lblLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 140, 40));

        lblErruser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblErruser.setForeground(new java.awt.Color(255, 0, 0));
        lblErruser.setText("Enter Your Username");
        lblErruser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblErruserMouseClicked(evt);
            }
        });
        getContentPane().add(lblErruser, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 350, 30));

        lblErrpw.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblErrpw.setForeground(new java.awt.Color(255, 0, 0));
        lblErrpw.setText("Enter Your Password");
        lblErrpw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblErrpwMouseClicked(evt);
            }
        });
        getContentPane().add(lblErrpw, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 260, 350, 30));

        lblPassword.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        lblPassword.setText("Password");
        getContentPane().add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, -1));

        lblUsername.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("Login");
        lblUsername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUsernameMouseClicked(evt);
            }
        });
        getContentPane().add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 359, 360, 40));

        lblUsername1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        lblUsername1.setText("User Name");
        getContentPane().add(lblUsername1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, -1, -1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8_close_window_30px.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, 40, 40));

        lblBackgroud.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/7.png"))); // NOI18N
        getContentPane().add(lblBackgroud, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblUsernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUsernameMouseClicked
        String username = txtUserName.getText();
        char password[] = pwdPassword.getPassword();

        if (username.equals("")) {
            txtUserName.setVisible(false);
            lblErruser.setVisible(true);
        } else if (password.length == 0) {
            pwdPassword.setVisible(false);
            lblErrpw.setVisible(true);
        }
        
        if((!username.equals("")) && password.length>0){
        Login_Portal lp = new Login_Portal();
        lp.UserType(username, password);
        }
    }//GEN-LAST:event_lblUsernameMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        lblErruser.setVisible(false);
        lblErrpw.setVisible(false);
    }//GEN-LAST:event_formWindowOpened

    private void lblErruserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblErruserMouseClicked
        lblErruser.setVisible(false);
        txtUserName.setVisible(true);
        txtUserName.requestFocusInWindow();
    }//GEN-LAST:event_lblErruserMouseClicked

    private void lblErrpwMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblErrpwMouseClicked
        lblErrpw.setVisible(false);
        pwdPassword.setVisible(true);
        pwdPassword.requestFocusInWindow();
    }//GEN-LAST:event_lblErrpwMouseClicked

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblBackgroud;
    private javax.swing.JLabel lblErrpw;
    private javax.swing.JLabel lblErruser;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsername1;
    private javax.swing.JPasswordField pwdPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sport_ms;

import Database.DBConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dinet
 */
public class Login_Portal {

    Connection con = null;

    public Login_Portal() {
        con = DBConnection.getDbConnect().connect();
    }

    public void UserType(String username, char[] pass) {
        System.out.println(username);
        saveLoggedUser(username);
        String sql = "select * from user where userId='" + username + "';";
        System.out.println(sql);
        String category = username.substring(0, 3).toLowerCase();
        
        System.out.println(category);
        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            System.out.println(res);
            if (res.next()==false)
                System.out.println("Invalid Entry");
            while (res.next()) {
                if (java.util.Arrays.equals(pass, res.getString(2).toCharArray())) {

                    if (category.equals("Adm")) {
                        System.out.println("login as admin");
                    } else if (category.equals("Ins")) {
                        System.out.println("Login as Instructor");
                    } else if (category.equals("Clk")) {
                        System.out.println("Login as Clerk");
                    } else if (category.equals("Ass")) {
                        System.out.println("Login as Assistant");
                    } else if (category.equals("Dir")) {
                        System.out.println("Login as Director");
                    } else {
                        System.out.println("Invalid Charactor \n Error:ULx0001");
                    }
                }
                else
                    System.out.println("Password is not ");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Login_Portal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void saveLoggedUser(String username){
        try {
            FileReader reader=new FileReader("src\\Config\\Config.properties");
            Properties prop=new Properties();
            prop.load(reader);
            
            File file=new File("src\\Config\\Config.properties");
            OutputStream ous= new FileOutputStream(file);
            
            
            prop.setProperty("LoggedUser",username);
            prop.store(ous,null);
            ous.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Login_Portal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Login_Portal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

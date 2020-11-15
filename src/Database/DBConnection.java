/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dinet
 */
public class DBConnection {
    private static DBConnection dbConnect = new DBConnection();
    private DBConnection() {
        
    }

    public static DBConnection getDbConnect() {
        return dbConnect;
    }
    
    
    public Connection connect(){
        Connection con=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sportms","root","");
            
//            String url ="jdbc:mysql://sportms-lserver.mysql.database.azure.com:3306/{your_database}?useSSL=true&requireSSL=false";
//            con = DriverManager.getConnection(url, "Dineth@sportms-lserver", "Yasuru@234");
            System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    public static void main(String[] args) {
          Connection con=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sportms","root","");
            
//            String url ="jdbc:mysql://sportms-lserver.mysql.database.azure.com:3306/sportms?useSSL=true&requireSSL=false"; 
//            con = DriverManager.getConnection(url, "Dineth@sportms-lserver", "Yasuru@234");
            System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            System.out.println("not");
        } catch (SQLException ex) {
            System.out.println("no");
        }
    }
}

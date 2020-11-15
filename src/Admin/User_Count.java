/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dinet
 */
public class User_Count {

    Connection con=null;

    public User_Count() {
        con=DBConnection.getDbConnect().connect();
    }
    
    
    public int count() {
        int c=0;
        String sql="select * from user";
        try {
            Statement stmt=con.createStatement();
            ResultSet res=stmt.executeQuery(sql);
            while (res.next()) {                
                ++c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User_Count.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return c;
    }
}

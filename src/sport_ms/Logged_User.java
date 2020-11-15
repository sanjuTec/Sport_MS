/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sport_ms;

import Admin.Admin_Dash;
import Database.DBConnection;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dinet
 */
public class Logged_User {

    String userName = null;
    String userId = null;
    String position = null;
    String password = null;
    String email = null;
    int contact = 0;
    String nic = null;

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getPosition() {
        return position;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getContact() {
        return contact;
    }

    public String getNic() {
        return nic;
    }

    Connection con = null;

    public Logged_User() {
        con = DBConnection.getDbConnect().connect();
    }

    String loggedUser = null;

    public void querry() {
        String sql = "select * from user, staff where user.userId ='" + loggedUser + "' AND staff.staffId='" + loggedUser + "';";
        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                userName = res.getString(4);
                userId = res.getString(1);
                password = res.getString(2);
                email = res.getString(5);
                contact = res.getInt(7);
                nic = res.getString(8);
                position = res.getString(6);
//                password=res.getString(6)
                System.out.println(userId+userName+email+contact+position+nic);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Logged_User.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void fill() {
        try {
            FileReader reader = new FileReader("src\\Config\\Config.properties");
            Properties prop = new Properties();
            prop.load(reader);
            loggedUser = prop.getProperty("LoggedUser");
            System.out.println(loggedUser+"here ");
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Admin_Dash.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Admin_Dash.class.getName()).log(Level.SEVERE, null, ex);
        }
        querry();
    }
}

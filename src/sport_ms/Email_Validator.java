/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sport_ms;

import org.apache.commons.validator.routines.EmailValidator;

/**
 *
 * @author dinet
 */
public class Email_Validator {
    
    String Email;
    
    private String validEmail(String email){
        boolean validity=EmailValidator.getInstance().isValid(email);
        if (validity==true)
            return email;
        else
            return "invalid";
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    
}

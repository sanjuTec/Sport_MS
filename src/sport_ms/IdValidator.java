/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sport_ms;

/**
 *
 * @author dinet
 */
public class IdValidator {
    
    String id;
    
    private String idvalid(String id){
        int len=id.length();
        if (len==10 || len== 15 ){
            char last=id.charAt(len-1);
            System.out.println(last);
                
        }
        return id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        System.out.println(id);
    }
    
    
}

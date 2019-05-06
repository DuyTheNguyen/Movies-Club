/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.UserDTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import session.UserManagementRemote;

/**
 *
 * @author 101036886
 */
@Named(value = "loginManagedBean")
@SessionScoped
public class LoginManagedBean implements Serializable {

    @EJB
    private UserManagementRemote userManagement;

    private String userID;
    
    private String password;
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password  = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean() {
        
    }

    public String checkUserID() {
        //Check whether user id exists
        if (userManagement.hasUser(userID)) {
            //Get user
            UserDTO u = userManagement.getUserDetails(userID);
            
            //Check password
            if(!password.equals(u.getPassword())) return "failure";
            
            //Set userid to session scope attribute
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("userid", userID);
            
            return "success";
        }
        return "failure";
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/login.xhtml?faces-redirect=true";
    }

}

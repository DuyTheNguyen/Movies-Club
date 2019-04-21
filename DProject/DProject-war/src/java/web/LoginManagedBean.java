/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

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
        if (userManagement.hasUser(userID)) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("userid", userID);
            return "success";
        }
        return "failure";
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "logout";
    }

}

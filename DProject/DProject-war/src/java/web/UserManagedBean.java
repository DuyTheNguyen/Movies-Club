/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.UserDTO;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import session.UserManagementRemote;

/**
 *
 * @author 101036886
 */
@Named(value = "userManagedBean")
@RequestScoped
public class UserManagedBean {

    private  String userid;
    private  String name;
    private  String email;
    private  String password;
    private  String confirmPassword;
    private  String appGroup;
    private  String phone;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAppGroup() {
        return appGroup;
    }

    public void setAppGroup(String appGroup) {
        this.appGroup = appGroup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    private boolean isNull(String s) {
        return (s == null);
    }
    @EJB
    private UserManagementRemote userManagement;

    /**
     * Creates a new instance of UserManagedBean
     */
    public UserManagedBean() {
        userid = null;
        name = null;
        phone = null;
        email = null;
        password = null;
        confirmPassword = null;
        appGroup = null;
    }
    
     private String setUserDetails() {
        /*
        if (isNull(userid)) {
            return "debug";
        }
        */

        UserDTO u = userManagement.getUserDetails("00003");

        if (u == null) {
            // no such employee
            return "failure";
        } else {
            // found - set details for display
            this.userid = u.getUserid();
            this.name = u.getName();
            this.phone = u.getPhone();
            this.email = u.getEmail();
            this.password = u.getPassword();
            this.appGroup = u.getAppGroup();
            return "success";
        }
    }
    
    public String displayUser() {
        
        // check empId is null
        /*
        if (isNull(userid)) {
            return "debug";
        }*/
        

        return setUserDetails();
    }
    
}

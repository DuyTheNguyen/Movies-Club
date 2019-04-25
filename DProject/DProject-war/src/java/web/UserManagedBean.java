/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.UserDTO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import session.UserManagementRemote;

/**
 *
 * @author 101036886
 */
@Named(value = "userManagedBean")
@ConversationScoped
public class UserManagedBean implements Serializable {

    private String userid;
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String appGroup;
    private String phone;

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

    //Conversation
    @Inject
    private Conversation conversation;

    public void startConversation() {
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }

    //User manage bean
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

    private boolean setUserDetails() {
        /*
        if (isNull(userid)) {
            return "debug";
        }
         */

        //TODO: Change the hardcode ID
        UserDTO u = userManagement.getUserDetails("00003");

        if (u == null) {
            // no such employee
            return false;
        } else {
            // found - set details for display
            this.userid = u.getUserid();
            this.name = u.getName();
            this.phone = u.getPhone();
            this.email = u.getEmail();
            this.password = u.getPassword();
            this.appGroup = u.getAppGroup();
            return true;
        }
    }

    public String displayUserFor(String purpose) {

        // check empId is null
        /*
        if (isNull(userid)) {
            return "debug";
        }*/
        String result = "failure";
        if (purpose.equals("details")) {
            // note the startConversation of the conversation
            startConversation();
        }
        if (setUserDetails()) {
            result = "success_" + purpose;
        }
        return result;
    }

    public String updateUser() {
        // check userid is null

        if (isNull(userid)) {
            return "debug";
        }

        UserDTO userDTO = new UserDTO(userid, name, phone, email, password, appGroup);
        boolean result = userManagement.updateUserDetails(userDTO);

        // note the endConversation of the conversation
        endConversation();
        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }

}

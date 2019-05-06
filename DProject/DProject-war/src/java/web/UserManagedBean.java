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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
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
    private String newPassword;
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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
        userid = SessionUtils.getUserId();
        name = null;
        phone = null;
        email = null;
        password = null;
        newPassword = null;
        confirmPassword = null;
        appGroup = null;
    }

    private boolean setUserDetails() {
        if (isNull(userid)) {
            return false;
        }

        UserDTO u = userManagement.getUserDetails(userid);

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

    /**
     * ************************ Display user ************************
     */
    public String displayUserFor(String purpose) {

        // check userid is null
        if (isNull(userid)) {
            return "debug";
        }
        String result = "failure";
        if (!purpose.equals("display")) {
            // note the startConversation of the conversation
            startConversation();
        }
        if (setUserDetails()) {
            result = "success_" + purpose;
        }
        return result;
    }

    /**
     * ************************ Create user ************************
     */
    public String createUser(){
        // check userid is null
        if (isNull(userid)) {
            return "debug";
        }
        UserDTO userDTO = new UserDTO(userid, name, phone, email, newPassword, "USER");
        boolean result = userManagement.addUser(userDTO);
        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }
    /**
     * ************************ Delete user ************************
     */
    public String deleteUser(){
        // check userid is null
        if (isNull(userid)) {
            return "debug";
        }
        boolean result = userManagement.removeUser(userid);
        
        // note the endConversation of the conversationz
        endConversation();
        if (result) {
            return "success";
        } else {
            return "failure";
        }
    }
    
    /**
     * ************************ Change details ************************
     */
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
            EmailCenter.sendEmail(userDTO);
            return "success";
        } else {
            return "failure";
        }
    }

    /**
     * ************************ Change password ************************
     */
    public void validateNewPasswordPair(FacesContext context,
            UIComponent componentToValidate,
            Object newValue) throws ValidatorException {

        // get new password
        String newPwd = (String) newValue;

        // get confirm password
        UIInput cnfPwdComponent = (UIInput) componentToValidate.getAttributes().get("cnfpwd");
        String cnfPwd = (String) cnfPwdComponent.getSubmittedValue();

        System.out.println("new password : " + newPwd);
        System.out.println("confirm password : " + cnfPwd);

        if (!newPwd.equals(cnfPwd)) {
            FacesMessage message = new FacesMessage(
                    "New Password and Confirm New Password are not the same! They must be the same.");
            throw new ValidatorException(message);
        }
    }

    public String changeUserPassword() {
        // check userid is null
        if (isNull(userid)) {
            return "debug change password";
        }

        // newPassword and confirmPassword are the same - checked by the validator during input to JSF form
        boolean result = userManagement.updateUserPassword(userid, newPassword);

        System.out.println("result = " + result);
        // note the endConversation of the conversation
        endConversation();
        if (result) {
            EmailCenter.sendEmail(name);
            return "success";
        } else {
            return "failure";
        }
    }
}

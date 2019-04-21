/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure;

import java.io.Serializable;

/**
 *
 * @author 101036886
 */
public class User implements Serializable{
    private final String userid;
    private final String name;
    private final String email;
    private final String password;
    private final String appGroup;
    private final String phone;

    public String getUserid() {
        return userid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAppGroup() {
        return appGroup;
    }

    public String getPhone() {
        return phone;
    }

    public User(String userid, String name, String phone, String email, String password, String appGroup) {
        this.userid = userid;
        this.email = email;
        this.password = password;
        this.name = name;
        this.appGroup = appGroup;
        this.phone = phone;
    }
}

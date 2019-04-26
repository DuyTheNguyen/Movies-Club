/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.Classes;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author 101036886
 */
public class MyAuthenticator extends Authenticator {

    PasswordAuthentication mypa;

    public MyAuthenticator(String username, String password) {
        mypa = new PasswordAuthentication(username, password);
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return mypa;
    }
}

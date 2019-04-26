/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.UserDTO;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import web.Classes.MyAuthenticator;

/**
 *
 * @author 101036886
 */
public class EmailCenter {

    private static void send(String to, String subject, String body) {
        String smtpServer = "smtp.gmail.com";
        String from = "cos30041@gmail.com";
        String emailUser = from;
        String password = "Creatingsoftware";

        try {
            Properties props = System.getProperties();
            // -- Attaching to default Session, or we could start a new one --
            props.put("mail.smtp.host", smtpServer);
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.starttls.enable", true);
            // -- prepare a password authenticator --
            MyAuthenticator myPA = new MyAuthenticator(emailUser, password); // see MyAuthenticator class
            // get a session
            Session session = Session.getInstance(props, myPA);
            // -- Create a new message --
            Message msg = new MimeMessage(session);
            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            // -- Set the subject and body text --
            msg.setSubject(subject);
            msg.setText(body);
            // -- Set some other header information --
            msg.setHeader("X-Mailer", "Gmail");
            msg.setSentDate(new Date());
            // -- Send the message --
            Transport.send(msg);
            Transport.send(msg, emailUser, password);
            System.out.println("Message sent OK.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void sendEmail(UserDTO user) {
       
        String to = "duy2721995@gmail.com";
        String subject = "Update Details";
        String body = "Hi " + user.getName() + ",\nYour details has been changed, in case this is not done by you, please contact us immediately at xyz@swin.com!\nRegards,\nAdmin\n";
       

       send(to, subject, body);
    }

    public static void sendEmail(String name) {
       
        String to = "duy2721995@gmail.com";
        String subject = "Update Passowrd";
        String body = "Hi " + name + ",\nYour password has been changed, in case this is not done by you, please contact us immediately at xyz@swin.com!\nRegards,\nAdmin\n";
        
        send(to, subject, body);
       
    }
}

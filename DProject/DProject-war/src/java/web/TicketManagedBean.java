/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.TicketDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import session.TicketManagementRemote;

/**
 *
 * @author 101036886
 */
@Named(value = "ticketManagedBean")
@RequestScoped
public class TicketManagedBean implements Serializable {

   private String userid;
   
   private ArrayList<TicketDTO> ticketList;

    public String getUserid() {
        return userid;
    }

    public ArrayList<TicketDTO> getTicketList() {
        return ticketList;
    }
    
     @PostConstruct
    public void init() {
        userid = SessionUtils.getUserId();
        ticketList = getTickets(userid);
    }
   
   @EJB
   private TicketManagementRemote ticketManagement;
   
   public TicketManagedBean(){
       
   }
   
   public boolean isEmptyList(){
      return ticketList == null;
   }
   
   private ArrayList<TicketDTO> getTickets(String id){
        return ticketManagement.getTickets(id);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.ShowtimeDTO;
import entity.TicketDTO;
import entity.UserDTO;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import session.TicketManagementRemote;

/**
 *
 * @author 101036886
 */
@Named(value = "ticketManagedBean")
@RequestScoped
public class TicketManagedBean implements Serializable {

   private String userid;
   
   private String ticketId;
   
   private String quantity;
  
   private ShowtimeDTO showtimeDTO;
   
    private UserDTO userDTO;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public ShowtimeDTO getShowtimeDTO() {
        return showtimeDTO;
    }

    public void setShowtimeDTO(ShowtimeDTO showtimeDTO) {
        this.showtimeDTO = showtimeDTO;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
   
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
   
    private boolean isNull(String s) {
        return (s == null);
    }
   
   public boolean isEmptyList(){
      return ticketList == null;
   }
   
   private ArrayList<TicketDTO> getTickets(String id){
        return ticketManagement.getTickets(id);
   }
   
   //Remove ticket
   public String removeTicket() throws IOException{
       
       // check ticket id is null
        if (isNull(ticketId)) {
            return "debug";
        }
        boolean result = ticketManagement.removeTicket(ticketId);
        
        if (result) {
             ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
             ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
            return "success";
        } else {
            return "failure";
        }
   }
   
   public String displayTicket()
   {
        if (isNull(ticketId)) {
            return "debug";
        }
        TicketDTO t = ticketManagement.getTicketDetails(ticketId);
        
        if (t == null) {
            // no such showtime
            return "failure";
        } else {
            // found - set details for display
            this.ticketId = t.getTicketId();
            this.quantity = t.getQuantity();
            this.showtimeDTO = t.getShowtimeId();
            this.userDTO = t.getUserId();
            return "success";
        }
   }
}

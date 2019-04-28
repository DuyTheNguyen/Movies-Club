/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.TicketDTO;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author 101036886
 */
@Remote
public interface TicketManagementRemote {
    boolean hasShowtime(String ticketid);

    TicketDTO getTicketDetails(String ticketid);
    
    ArrayList<TicketDTO> getTickets(String userid);
    
    boolean addTicket(TicketDTO ticket);

    boolean updatTicketQuantity(String ticketid, String quantity);
    
    boolean removeTicket(String ticketid);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Tickettable;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author 101036886
 */
@Local
public interface TicketFacadeLocal {
    boolean hasShowtime(String ticketid);

    Tickettable find(String ticketid);
    
    ArrayList<Tickettable> getTickets(String userid);
    
    boolean addTicket(Tickettable ticket);

    boolean updatTicketQuantity(String ticketid, String quantity);
    
    boolean removeTicket(String ticketid);
}

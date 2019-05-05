/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.TicketDTO;
import entity.Tickettable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author 101036886
 */
@Stateful
public class ShoppingCartManagement implements ShoppingCartManagementRemote {
    @EJB
    private ShoppingCartFacadeLocal shoppingCartFacade;
    
    private ArrayList<TicketDTO> ticketCart; 
    
    @PostConstruct
    private void initializeBean(){
        ticketCart = new ArrayList<>();
    }
    
    
    
    @Override
    @PermitAll
    public String add(TicketDTO ticketDTO) {
        boolean result = false;
        try {
            //Already have ticket for this showtime
            for (TicketDTO ticket : ticketCart) {
                if ((ticket.getShowtimeId().getShowtimeId()).equals(ticketDTO.getShowtimeId().getShowtimeId())) {
                    Integer newV =  Integer.parseInt(ticketDTO.getQuantity());
                    ticket.setQuantity(Integer.toString(newV));
                    result = true;
                }
            }
            //New ticket
            if (!result) {
                ticketCart.add(ticketDTO);
                
                result = true;
               return ticketCart.get(0).getQuantity() + "-" + ticketCart.get(0).getTicketId();
            }
        } catch (Exception ex) {
        }
        return ticketDTO.getTicketId();
    }
    
    @Override
    @PermitAll
    public String remove(TicketDTO ticketDTO) {
        boolean result = false;
        try {
            //Already have ticket for this showtime
            for (TicketDTO ticket : ticketCart) {
                if ((ticket.getShowtimeId().getShowtimeId()).equals(ticketDTO.getShowtimeId().getShowtimeId())) {
                    Integer newV =  Integer.parseInt(ticketDTO.getQuantity());
                    ticket.setQuantity(Integer.toString(newV));
                    result = true;
                }
            }
            //New ticket
            if (!result) {
                ticketCart.add(ticketDTO);
                result = true;
               return ticketCart.get(0).getQuantity() + "-" + ticketCart.get(0).getTicketId();
            }
        } catch (Exception ex) {
        }
        return ticketDTO.getTicketId();
    }
    

    @Override
    @PermitAll
    public String checkOut() {
        // return shoppingCartFacade.checkOut();
      return ticketCart.get(0).getQuantity() + "-" + ticketCart.get(0).getTicketId();
    }
    
    @Remove
    @PermitAll
    public void remove() {
        ticketCart = null;
    }
}

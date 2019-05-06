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
    
    private ArrayList<Tickettable> ticketCart; 
    
    @PostConstruct
    private void initializeBean(){
        ticketCart = new ArrayList<>();
    }
    
    
    
    @Override
    @PermitAll
    public String add(TicketDTO ticketDTO) {
        boolean result = false;
        Tickettable tickettable = Utility.ticketDTO2Entity(ticketDTO);
        try {
            //Already have ticket for this showtime
            for (Tickettable ticket : ticketCart) {
                if ((ticket.getShowtimeid().getShowtimeid()).equals(tickettable.getShowtimeid().getShowtimeid())) {
                    Integer newV =  Integer.parseInt(ticketDTO.getQuantity());
                    ticket.setQuantity(Integer.toString(newV));
                    result = true;
                }
            }
            //New ticket
            if (!result) {
                ticketCart.add(tickettable);
            }
        } catch (Exception ex) {
            return "failure";
        }
        return "success";
    }
    
    @Override
    @PermitAll
    public String remove(TicketDTO ticketDTO) {
        boolean result = false;
        
        Tickettable tickettable = Utility.ticketDTO2Entity(ticketDTO);
        try {
            //Already have ticket for this showtime
            for (Tickettable ticket : ticketCart) {
                if ((ticket.getShowtimeid().getShowtimeid()).equals(tickettable.getShowtimeid().getShowtimeid())) {
                    Integer newV =  Integer.parseInt(ticketDTO.getQuantity());
                    ticket.setQuantity(Integer.toString(newV));
                    result = true;
                }
            }
            //New ticket
            if (!result) {
                ticketCart.add(tickettable);
            }
        } catch (Exception ex) {
            return "failure";
        }
        return "success";
    }
    

    @Override
    @PermitAll
    public String checkOut() {
      return shoppingCartFacade.checkOut(ticketCart);
    }
    
    @Remove
    @PermitAll
    public void remove() {
        ticketCart = null;
    }
}

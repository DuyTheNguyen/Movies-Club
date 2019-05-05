/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.TicketDTO;
import entity.Tickettable;
import java.util.ArrayList;
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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private ShoppingCartFacadeLocal shoppingCartFacade;
    
    private ArrayList<TicketDTO> cart;

    public ShoppingCartManagement() {
        cart = new ArrayList<>();
    }

    @Override
    @PermitAll
    public boolean add(TicketDTO ticketDTO) {
       if (ticketDTO == null) {
            // just in case
            return false;
        }

        // user not exist
        // convert to entity
        Tickettable ticket = Utility.ticketDTO2Entity(ticketDTO);
        // add one
        return shoppingCartFacade.add(ticket);
    }

    @Override
    @PermitAll
    public boolean checkOut() {
        return shoppingCartFacade.checkOut(); //To change body of generated methods, choose Tools | Templates.
    }

  
}

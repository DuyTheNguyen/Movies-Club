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
    @EJB
    private ShoppingCartFacadeLocal shoppingCartFacade;
    
    @Override
    @PermitAll
    public boolean add(TicketDTO ticketDTO) {
       if (ticketDTO == null) {
            // just in case
            return false;
        }

        // convert to entity
        Tickettable ticket = Utility.ticketDTO2Entity(ticketDTO);
        // add one
        return shoppingCartFacade.add(ticket);
    }
    
    @Override
    @PermitAll
    public ArrayList<TicketDTO> getCart() {
        try {
            ArrayList<Tickettable> alst = shoppingCartFacade.getCart();

            if (alst.isEmpty()) {
                //not found
                return null;
            } else {
                ArrayList<TicketDTO> alsDTO = new ArrayList<>(alst.size());
                for (Tickettable stt : alst) {
                    TicketDTO stDTO = Utility.ticketEntity2DTO(stt);
                    alsDTO.add(stDTO);
                }
                return alsDTO;
            }
        } catch (NullPointerException e) {
            throw e;
        }
    }

    @Override
    @PermitAll
    public String checkOut() {
        return shoppingCartFacade.checkOut();
    }
}

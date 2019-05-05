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
public interface ShoppingCartManagementRemote {
    public String add(TicketDTO ticketDTO);
    
    public String remove(TicketDTO ticketDTO);

   // ArrayList<TicketDTO> getCart();

    //public boolean addCartItem(TicketDTO ticketDTO);

    //public boolean deleteCartItem(String ticketId);

    //public boolean updateCartItem(TicketDTO ticketDTO);
    
    public String checkOut();
}

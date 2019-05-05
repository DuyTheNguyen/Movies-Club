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
public interface ShoppingCartFacadeLocal {

    public boolean add(Tickettable tickettable);

    ArrayList<Tickettable> getCart();

    //public boolean addCartItem(Tickettable tickettable);

   // public boolean deleteCartItem(String ticketId);

    //public boolean updateCartItem(Tickettable tickettable);
    
    public String checkOut();
}

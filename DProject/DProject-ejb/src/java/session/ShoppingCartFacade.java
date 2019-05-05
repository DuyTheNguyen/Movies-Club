/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Showtimetable;
import entity.Tickettable;
import entity.Usertable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author 101036886
 */
@Stateful
public class ShoppingCartFacade{

    @PersistenceContext(unitName = "DProject-ejbPU", type = PersistenceContextType.EXTENDED)
    private EntityManager em;
    
    private void create(Tickettable tickettable) {
        em.persist(tickettable);
    }

    private ArrayList<Tickettable> ticketCart;

    @PostConstruct
    private void initializeBean(){
        ticketCart = new ArrayList<>();
    }

    public boolean add(Tickettable tickettable) {
        boolean result = false;

        //Set the correct user and showtime table
        tickettable.setUserid(this.getUsertableFrom(tickettable.getUserid().getUserid()));
        tickettable.setShowtimeid(this.getShowtimetableFrom(tickettable.getShowtimeid().getShowtimeid()));

        try {
            //Already have ticket for this showtime
            for (Tickettable ticket : ticketCart) {
                if (ticket.getShowtimeid().getShowtimeid().equals(tickettable.getShowtimeid().getShowtimeid())) {
                    ticket.setQuantity(ticket.getQuantity() + tickettable.getQuantity());
                    result = true;
                }
            }
            //New ticket
            if (!result) {
                ticketCart.add(tickettable);
                result = true;
            }
        } catch (Exception ex) {
        }
        return result;
    }
    
    public ArrayList<Tickettable> getCart() {
        return ticketCart;
    }

    public String checkOut() {
        try {
            if(ticketCart.isEmpty()) return "empty list";
            for (Tickettable ticket : ticketCart) {
                create(ticket);
            }
            ticketCart.clear();
            return "success";
        } catch (Exception e) {
        }
        return "failure";
    }

    private Usertable getUsertableFrom(String userId) {  
        Usertable u = em.getReference(Usertable.class, userId);
        return u;
    }

    private Showtimetable getShowtimetableFrom(String showtimeId) {
        Showtimetable s = em.getReference(Showtimetable.class, showtimeId);
        return s;
    }

    @Remove
    public void remove() {
        ticketCart = null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

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

/**
 *
 * @author 101036886
 */
@Stateful
public class ShoppingCartFacade implements ShoppingCartFacadeLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "DProject-ejbPU")
    private EntityManager em;
    
     private void create(Tickettable tickettable) {
        em.persist(tickettable);
    }
    
    private ArrayList<Tickettable> ticketCart;
    
    @PostConstruct
    private void initializeBean(){
        ticketCart = new ArrayList<>();
    }
    
    @Override
    public boolean add(Tickettable tickettable) {
        boolean result = false;
        ticketCart.add(tickettable);
        try{
            //Already have ticket for this showtime
            for(Tickettable ticket : ticketCart ){
                if(ticket.getShowtimeid().getShowtimeid().equals(tickettable.getShowtimeid().getShowtimeid())){
                    ticket.setQuantity(ticket.getQuantity() + tickettable.getQuantity());
                    result = true;
                }
            }
            //New ticket
            if(!result){
                ticketCart.add(tickettable);
                result = true;
            }
        }catch(Exception ex){
        }
        return result;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean checkOut() {
        for(Tickettable tikcet : ticketCart){
            em.persist(ticketCart);
        }
        ticketCart.clear();
        return true;
    }
    
            
}

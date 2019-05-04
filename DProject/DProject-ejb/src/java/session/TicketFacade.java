/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Tickettable;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author 101036886
 */
@Stateless
public class TicketFacade implements TicketFacadeLocal {

    @PersistenceContext(unitName = "DProject-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(Tickettable ticket) {
        em.persist(ticket);
    }

    private void edit(Tickettable ticket) {
        em.merge(ticket);
    }

    private void remove(Tickettable ticket) {
        em.remove(em.merge(ticket));
    }

    private Tickettable find(Object id) {
        return em.find(Tickettable.class, id);
    }

    
    
    @Override
    public boolean hasShowtime(String ticketid) {
        return (find(ticketid) != null);
    }

    @Override
    public Tickettable find(String ticketid) {
       return em.find(Tickettable.class, ticketid);
    }

    @Override
    public ArrayList<Tickettable> getTickets(String userid) {
         try{
            //TypedQuery<Tickettable> tq = em.createNamedQuery("Tickettable.findByUserid", Tickettable.class);
            TypedQuery<Tickettable> tq = em.createQuery("SELECT t FROM Tickettable t WHERE t.userid.userid = :userid_id", Tickettable.class);
            tq.setParameter("userid_id", userid);
            ArrayList<Tickettable> al = new ArrayList<>(tq.getResultList().size());
            al.addAll(tq.getResultList());
            return al;
        }catch(NullPointerException e){
            throw e;
        }
    }

    @Override
    public boolean updatTicketQuantity(String ticketid, String ticketquantity) {
         // find the employee
        Tickettable t = find(ticketid);

        // check again - just to play it safe
        if (t == null) {
            return false;
        }

        // only need to update the password field
        t.setQuantity(ticketquantity);
        return true;
    }

    @Override
    public boolean removeTicket(String ticketid) {
         // find the employee
        Tickettable t = find(ticketid);
        
        // check again - just to play it safe
        if (t == null) {
            return false;
        }

        em.remove(t);
        return true;
    }

    @Override
    public boolean addTicket(Tickettable ticket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

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
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author 101036886
 */
@Stateless
public class TicketFacade implements TicketFacadeLocal, ShoppingCartFacadeLocal {

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
    public boolean hasTicket(String ticketid) {
        return (find(ticketid) != null);
    }

    @Override
    public Tickettable find(String ticketid) {
        return em.find(Tickettable.class, ticketid);
    }

    @Override
    public ArrayList<Tickettable> getTickets(String userid) {
        try {
            //TypedQuery<Tickettable> tq = em.createNamedQuery("Tickettable.findByUserid", Tickettable.class);
            TypedQuery<Tickettable> tq = em.createQuery("SELECT t FROM Tickettable t WHERE t.userid.userid = :userid_id", Tickettable.class);
            tq.setParameter("userid_id", userid);
            ArrayList<Tickettable> al = new ArrayList<>(tq.getResultList().size());
            al.addAll(tq.getResultList());
            return al;
        } catch (NullPointerException e) {
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

    @Override
    public String checkOut(ArrayList<Tickettable> tickettableList) {
        if (tickettableList.isEmpty()) {
            return "emptylist";
        }
        //return tickettableList.get(0).getTicketid();
        try {
            String result = "success";
            for (Tickettable t : tickettableList) {
                Usertable user = this.getUsertableFrom(t.getUserid().getUserid());
                Showtimetable showtime = this.getShowtimetableFrom(t.getShowtimeid().getShowtimeid());

                Tickettable n = new Tickettable();
                n.setTicketid(String.valueOf(gen()));
                n.setQuantity(t.getQuantity());
                n.setUserid(user);
                n.setShowtimeid(showtime);

                em.persist(n);

            }
            return result;
        } catch (ConstraintViolationException e) {
            String r = "";
            for (ConstraintViolation c : e.getConstraintViolations()) {
                r += c.toString();
            }
            return r;
        }
    }

    private int gen() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

    private Usertable getUsertableFrom(String userId) {
        Usertable u = em.getReference(Usertable.class, userId);
        return u;
    }

    private Showtimetable getShowtimetableFrom(String showtimeId) {
        Showtimetable s = em.getReference(Showtimetable.class, showtimeId);
        return s;
    }

}

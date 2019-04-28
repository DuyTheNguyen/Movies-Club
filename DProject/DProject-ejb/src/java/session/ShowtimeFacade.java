/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Showtimetable;
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
public class ShowtimeFacade implements ShowtimeFacadeLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "DProject-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    private Showtimetable find(Object id) {
    
        return em.find(Showtimetable.class, id);
    }
    
    
    /**
     *
     * @param id
     * @return a showtime
     */
    @Override
    public Showtimetable find(String id) {
        return em.find(Showtimetable.class, id);
    }

    
    /**
     * checks whether an showtime exist using showtimeid
     *
     * @param id 
     * @return true if exist, false otherwise
     */
    @Override
    public boolean hasShowtime(String id) {
        return (find(id) != null);
    }
    
    /**
     * @return a list of showtime
     */
    @Override
    public ArrayList<Showtimetable> getShowtimes(){
        try{
            TypedQuery<Showtimetable> tq = em.createNamedQuery("Showtimetable.findAll", Showtimetable.class);
        
            ArrayList<Showtimetable> al = new ArrayList<>(tq.getResultList().size());
            al.addAll(tq.getResultList());
            return al;
        }catch(NullPointerException e){
            throw e;
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entity.Usertable;

/**
 *
 * @author 101036886
 */
@Stateless
public class UserFacade implements UserFacadeLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "DProject-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    private void create(Usertable user) {
        em.persist(user);
    }

    private void edit(Usertable user) {
        em.merge(user);
    }

    private void remove(Usertable user) {
        em.remove(em.merge(user));
    }

    private Usertable find(Object id) {
        return em.find(Usertable.class, id);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Usertable find(String id) {
        return em.find(Usertable.class, id);
    }

    /**
     * checks whether an employee exist using empId
     *
     * @param id 
     * @return true if exist, false otherwise
     */
    @Override
    public boolean hasUser(String id) {
        return (find(id) != null);
    }

    /**
     * add an employee to the system
     *
     * @param user
     * @return true if addition is successful, false otherwise
     */
    @Override
    public boolean addUser(Usertable user) {
        // check again - just to play it safe
        Usertable u = find(user.getUserid());

        if (u != null) {
            // could not add one
            return false;
        }

        create(user);

        return true;
    }

    /**
     * update employee details without changing password
     *
     * @param user
     * @return true if update is successful, false otherwise
     */
    @Override
    public boolean updatUserDetails(Usertable user) {
        // find the employee, just in case
        Usertable u = find(user.getUserid());

        // check again - just to play it safe
        if (u == null) {
            return false;
        }

        // no need to update the primary key - empId
        edit(user);
        return true;
    }

    /**
     * update employee's password
     *
     * @param userId
     * @param password
     * @return true if update is successful, false otherwise
     */
    @Override
    public boolean updatePassword(String userId, String password) {
        // find the employee
        Usertable u = find(userId);

        // check again - just to play it safe
        if (u == null) {
            return false;
        }

        // only need to update the password field
        u.setPassword(password);
        return true;
    }

   

    /**
     * physically remove the employee record from database table
     *
     * this is only for lab purposes - never ever do this in real world
     * applications
     *
     * @param userId
     * @return true if successful, false otherwise
     */
    @Override
    public boolean removeUser(String userId) {
        // find the employee
        Usertable u = find(userId);

        // check again - just to play it safe
        if (u == null) {
            return false;
        }

        em.remove(u);
        return true;
    }
}

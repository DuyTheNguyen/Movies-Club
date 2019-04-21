/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Local;
import entity.Usertable;
/**
 *
 * @author 101036886
 */
@Local
public interface UserFacadeLocal {
    Usertable find(String id);

    boolean hasUser(String userId);
    
    boolean addUser(Usertable user);
    
    boolean updatUserDetails(Usertable user);
    
    boolean updatePassword(String userId, String password);

    boolean removeUser(String userId);
}

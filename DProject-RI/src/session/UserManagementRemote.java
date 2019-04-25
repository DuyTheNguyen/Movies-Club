/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.UserDTO;
import javax.ejb.Remote;

/**
 *
 * @author 101036886
 */
@Remote
public interface UserManagementRemote {
    boolean hasUser(String userid);
    
    boolean addUser(UserDTO userDTO);

    boolean updateUserDetails(UserDTO userDTO);

    boolean updateUserPassword(String userid, String newPassword);

    UserDTO getUserDetails(String userid);

    boolean removeUser(String userid);
}

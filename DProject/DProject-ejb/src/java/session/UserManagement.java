/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.UserDTO;
import entity.Usertable;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author 101036886
 */
@Stateless
public class UserManagement implements UserManagementRemote {

    @EJB
    private UserFacadeLocal userFacade;

    
    @Override
    @PermitAll
    public boolean hasUser(String userid) {
        return userFacade.hasUser(userid);
    }

    @Override
    @PermitAll
    public boolean addUser(UserDTO userDTO) {
        if (userDTO == null) {
            // just in case
            return false;
        }

        // check user exist?
        if (hasUser(userDTO.getUserid())) {
            // user exists, cannot add one
            return false;
        }

        // user not exist
        // convert to entity
        Usertable user = Utility.userDTO2Entity(userDTO);
        // add one
        return userFacade.addUser(user);
    }

    @Override
    @PermitAll
    public boolean updateUserDetails(UserDTO userDTO) {
        // check user exist?
        if (!hasUser(userDTO.getUserid())) {
            return false;
        }

        // user exist, update details
        // convert to entity class
        Usertable user = Utility.userDTO2Entity(userDTO);
        // update details
        return userFacade.updatUserDetails(user);
    }

    @Override
    @PermitAll
    public boolean updateUserPassword(String userid, String newPassword) {
        return userFacade.updatePassword(userid, newPassword);    
    }

    @Override
    @PermitAll
    public UserDTO getUserDetails(String userid) {
         // get the user
        Usertable user = userFacade.find(userid);

        if (user == null) {
            // not found - no such user
            return null;
        } else {
            // found - prepare details
            UserDTO userDTO = new UserDTO(user.getUserid(),
                    user.getName(), user.getPhone(),
                    user.getEmail(), user.getPassword(), user.getAppgroup()
                   );
                    
            return userDTO;
        }
    }

    @Override
    @PermitAll
    public boolean removeUser(String userid) {
        return userFacade.removeUser(userid);
    }

}

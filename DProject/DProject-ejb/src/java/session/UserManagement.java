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

    private Usertable employeeDTO2Entity(UserDTO userDTO) {
        if (userDTO == null) {
            // just in case
            return null;
        }

        String userid = userDTO.getUserid();
        String name = userDTO.getName();
        String phone = userDTO.getPhone();
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        String appGroup = userDTO.getAppGroup();

        Usertable usertable = new Usertable(userid, name, phone, email, password, appGroup);

        return usertable;
    }

    private UserDTO employeeEntity2DTO(Usertable user) {
        if (user == null) {
            // just in case
            return null;
        }

        UserDTO userDTO = new UserDTO(
                user.getUserid(),
                user.getName(),
                user.getPhone(),
                user.getEmail(),
                user.getPassword(),
                user.getAppgroup()
        );

        return userDTO;
    }

    @Override
    public boolean hasUser(String userid) {
        return userFacade.hasUser(userid);
    }

    @Override
    public boolean addUser(UserDTO userDTO) {
        if (userDTO == null) {
            // just in case
            return false;
        }

        // check employee exist?
        if (hasUser(userDTO.getUserid())) {
            // employee exists, cannot add one
            return false;
        }

        // employee not exist
        // convert to entity
        Usertable user = this.employeeDTO2Entity(userDTO);
        // add one
        return userFacade.addUser(user);
    }

    @Override
    public boolean updateUserDetails(UserDTO userDTO) {
        // check employee exist?
        if (!hasUser(userDTO.getUserid())) {
            return false;
        }

        // employee exist, update details
        // convert to entity class
        Usertable user = this.employeeDTO2Entity(userDTO);
        // update details
        return userFacade.updatUserDetails(user);
    }

    @Override
    public boolean updateEmployeePassword(String userid, String newPassword) {
        return userFacade.updatePassword(userid, newPassword);    
    }

    @Override
    public UserDTO getUserDetails(String userid) {
         // get the employee
        Usertable user = userFacade.find(userid);

        if (user == null) {
            // not found - no such employee
            return null;
        } else {
            // found - prepare details
            UserDTO userDTO = new UserDTO(user.getUserid(),
                    user.getName(), user.getPhone(),
                    user.getEmail(), user.getPassword(), user.getAppgroup());
            
            return userDTO;
        }
    }

    @Override
    public boolean removeUser(String userid) {
        return userFacade.removeUser(userid);
    }

}

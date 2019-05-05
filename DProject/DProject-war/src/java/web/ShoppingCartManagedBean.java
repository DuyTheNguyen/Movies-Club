/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.ShowtimeDTO;
import entity.TicketDTO;
import entity.UserDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import session.ShoppingCartManagementRemote;

/**
 *
 * @author 101036886
 */
@Named(value = "shoppingCartManagedBean")
@ViewScoped
public class ShoppingCartManagedBean implements Serializable{

    /**
     * Creates a new instance of ShoppingCartManagedBean
     */
    private ShowtimeDTO showtimeDTO;
    private UserDTO userDTO;
    private String showtimeId;
    private String userId;
    private Integer quantity;

     private ArrayList<TicketDTO> carts;

    public ArrayList<TicketDTO> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<TicketDTO> carts) {
        this.carts = carts;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
   

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(String showtimeId) {
        this.showtimeId = showtimeId;
    }

    public ShowtimeDTO getShowtimeDTO() {
        return showtimeDTO;
    }

    public void setShowtimeDTO(ShowtimeDTO showtimeDTO) {
        this.showtimeDTO = showtimeDTO;
    }
    
    @EJB
    private ShoppingCartManagementRemote shoppingCartManagement;
    
    public String addTicket(){
        
        this.quantity += 1;
        
        UserDTO u = new UserDTO(userId,"empty","empty","empty","empty","empty");
        ShowtimeDTO s = new ShowtimeDTO(showtimeId,"empty","empty","empty","empty","empty");
        
        TicketDTO t = new TicketDTO("111111",u,s,Integer.toString(this.quantity));
        //carts = shoppingCartManagement.getCart();
        return shoppingCartManagement.add(t);
        /*
        boolean result =  shoppingCartManagement.add(t);
        if(result){
            return "success";
        }
        return "failure";
        */
    }
    
    public String removeTicket(){
        
        this.quantity -= 1;
        
        //Cannot lower than 1
        if(this.quantity == 0) this.quantity = 1;
        
        UserDTO u = new UserDTO(userId,"empty","empty","empty","empty","empty");
        ShowtimeDTO s = new ShowtimeDTO(showtimeId,"empty","empty","empty","empty","empty");
        
        TicketDTO t = new TicketDTO("111111",u,s,Integer.toString(this.quantity));
        //carts = shoppingCartManagement.getCart();
        return shoppingCartManagement.remove(t);
        /*
        boolean result =  shoppingCartManagement.add(t);
        if(result){
            return "success";
        }
        return "failure";
        */
    }
    
    public String checkout(){
        return shoppingCartManagement.checkOut();
        /*
        boolean result = shoppingCartManagement.checkOut(); 
        if(result) return "success";
        return "failure";
*/
    }

    @PostConstruct
    public void init() {
        userId = SessionUtils.getUserId();
        showtimeId = SessionUtils.getShowtimeId();
       
        this.quantity = 1;
    }
    
    
    
    public ShoppingCartManagedBean() {
         
    }
    
}

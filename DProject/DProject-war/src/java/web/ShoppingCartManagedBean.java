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
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import session.ShoppingCartManagementRemote;

/**
 *
 * @author 101036886
 */
@Named(value = "shoppingCartManagedBean")
@ViewScoped
public class ShoppingCartManagedBean implements Serializable {

    
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

    @EJB
    private ShoppingCartManagementRemote shoppingCartManagement;

    public void addTicket() {

        this.quantity += 1;

        UserDTO u = new UserDTO(userId, "empty", "empty", "empty", "empty", "empty");
        ShowtimeDTO s = new ShowtimeDTO(showtimeId, "empty", "empty", "empty", "empty", "empty");

        TicketDTO t = new TicketDTO("", u, s, Integer.toString(this.quantity));
        shoppingCartManagement.add(t);
    }

    public void removeTicket() {

        this.quantity -= 1;

        //Cannot lower than 1
        if (this.quantity == 0) {
            this.quantity = 1;
        }

        UserDTO u = new UserDTO(userId, "empty", "empty", "empty", "empty", "empty");
        ShowtimeDTO s = new ShowtimeDTO(showtimeId, "empty", "empty", "empty", "empty", "empty");

        TicketDTO t = new TicketDTO("00009", u, s, Integer.toString(this.quantity));
        shoppingCartManagement.remove(t);

    }

    public String checkout() {
        //Case 1 item
        if (this.quantity == 1) {
            UserDTO u = new UserDTO(userId, "empty", "empty", "empty", "empty", "empty");
            ShowtimeDTO s = new ShowtimeDTO(showtimeId, "empty", "empty", "empty", "empty", "empty");

            TicketDTO t = new TicketDTO("", u, s, "1");
            shoppingCartManagement.add(t);
        }
        return shoppingCartManagement.checkOut();
    }

    @PostConstruct
    public void init() {
        userId = SessionUtils.getUserId();
        showtimeId = SessionUtils.getShowtimeId();

    }

    public ShoppingCartManagedBean() {
        this.quantity = 1;
    }

}

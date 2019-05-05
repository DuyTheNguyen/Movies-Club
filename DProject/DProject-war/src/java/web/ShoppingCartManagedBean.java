/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

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
    private String showtimeId;

    public String getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(String showtimeId) {
        this.showtimeId = showtimeId;
    }
    
    public ShoppingCartManagedBean() {
    }
    
}

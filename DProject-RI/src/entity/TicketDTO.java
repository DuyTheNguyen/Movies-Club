/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author 101036886
 */
public class TicketDTO implements Serializable {
    private final String ticketId;
    private final UserDTO userId;
    private final ShowtimeDTO showtimeId;
    private String quantity;

    public String getQuantity() {
        return quantity;
    }

    public String getTicketId() {
        return ticketId;
    }

    public UserDTO getUserId() {
        return userId;
    }

    public ShowtimeDTO getShowtimeId() {
        return showtimeId;
    }

    public void setQuantity(String quantity){
        this.quantity = quantity;
    }

    public TicketDTO(String ticketId, UserDTO userId, ShowtimeDTO showtimeId, String quantity) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.showtimeId = showtimeId;
        this.quantity = quantity;
    }
}

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
    private final String userId;
    private final String showtimeId;
    private final String quantity;

    public String getTicketId() {
        return ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public String getShowtimeId() {
        return showtimeId;
    }

    public String getQuantity() {
        return quantity;
    }

    public TicketDTO(String ticketId, String userId, String showtimeId, String quantity) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.showtimeId = showtimeId;
        this.quantity = quantity;
    }
}

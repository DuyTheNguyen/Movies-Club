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
public class ShowtimeDTO implements Serializable{
    private final String showtimeId;
    private final String date;
    private final String time;
    private final String room;
    private final String movieName;
    private final String movieDes;

    public String getShowtimeId() {
        return showtimeId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getRoom() {
        return room;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieDes() {
        return movieDes;
    }

    public ShowtimeDTO(String showtimeId, String date, String time, String room, String movieName, String movieDes) {
        this.showtimeId = showtimeId;
        this.date = date;
        this.time = time;
        this.room = room;
        this.movieName = movieName;
        this.movieDes = movieDes;
    }
}

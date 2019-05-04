/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import entity.ShowtimeDTO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import session.ShowtimeManagementRemote;

/**
 *
 * @author 101036886
 */
@Named(value = "showtimeManagedBean")
@RequestScoped
public class ShowtimeManagedBean implements Serializable {

    private String userid;

    private String showtimeId;
    private String date;
    private String time;
    private String room;
    private String movieName;
    private String movieDes;

    public String getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(String showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDes() {
        return movieDes;
    }

    public void setMovieDes(String movieDes) {
        this.movieDes = movieDes;
    }

    
    private boolean isNull(String s) {
        return (s == null);
    }

    private ArrayList<ShowtimeDTO> showtimeList;

    @PostConstruct
    public void init() {
        showtimeList = getShowtimes();
    }

    public ShowtimeManagedBean() {
        //Get userid from session
        userid = SessionUtils.getUserId();
    }

    public String getUserid() {
        return userid;
    }

    public ArrayList<ShowtimeDTO> getShowtimeList() {
        return showtimeList;
    }

    public String displayShowtime() {
        if (isNull(showtimeId)) {
            return "debug";
        }
        ShowtimeDTO s = showtimeManagement.getShowtimeDetails(showtimeId);

        if (s == null) {
            // no such showtime
            return "failure";
        } else {
            // found - set details for display
            this.showtimeId = s.getShowtimeId();
            this.date = s.getDate();
            this.room = s.getRoom();
            this.time = s.getTime();
            this.movieName = s.getMovieName();
            this.movieDes = s.getMovieDes();
            return "success";
        }
    }

    @EJB
    private ShowtimeManagementRemote showtimeManagement;

    private ArrayList<ShowtimeDTO> getShowtimes() {
        return showtimeManagement.getShowtimes();
    }
}

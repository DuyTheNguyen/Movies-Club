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
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import session.ShowtimeManagementRemote;

/**
 *
 * @author 101036886
 */
@Named(value = "showtimeManagedBean")
@ViewScoped
public class ShowtimeManagedBean implements Serializable {

    private String userid;

    private String showtimeId;
    private ShowtimeDTO showtimeDTO;

    public ShowtimeDTO getShowtimeDTO() {
        return showtimeDTO;
    }

    public void setShowtimeDTO(ShowtimeDTO showtimeDTO) {
        this.showtimeDTO = showtimeDTO;
    }

    public String getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(String showtimeId) {
        this.showtimeId = showtimeId;
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
            this.showtimeDTO = s;
            return "success";
        }
    }

    public String purchaseShowtimeTicket() {
        if (isNull(showtimeId)) {
            return "debug";
        }

        // Store showtime id
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("showtimeid", showtimeId);
        return "success";

    }

    @EJB
    private ShowtimeManagementRemote showtimeManagement;

    private ArrayList<ShowtimeDTO> getShowtimes() {
        return showtimeManagement.getShowtimes();
    }
}

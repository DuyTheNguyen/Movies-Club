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
    private ArrayList<ShowtimeDTO> showtimeList;
    
    @PostConstruct
    public void init() {
        showtimeList = getShowtimes();
    }
    
    public ShowtimeManagedBean() {
        userid = SessionUtils.getUserId();
    }
    
    public String getUserid() {
        return userid;
    }

    public ArrayList<ShowtimeDTO> getShowtimeList() {
        return showtimeList;
    }
   
    
    @EJB
    private ShowtimeManagementRemote showtimeManagement;
    
    private ArrayList<ShowtimeDTO> getShowtimes(){
        return showtimeManagement.getShowtimes();
    }
}

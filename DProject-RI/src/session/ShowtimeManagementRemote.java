/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ShowtimeDTO;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author 101036886
 */
@Remote
public interface ShowtimeManagementRemote {

    boolean hasShowtime(String showtimeid);

    ShowtimeDTO getShowtimeDetails(String showtimeid);

    ArrayList<ShowtimeDTO> getShowtimes();

    /*
    boolean addShowtime(Showtimetable showtime);
    
    boolean updatShowtimeDetails(Showtimetable showtime);
    
    boolean removeShowtime(String showtimeid);
     */
}

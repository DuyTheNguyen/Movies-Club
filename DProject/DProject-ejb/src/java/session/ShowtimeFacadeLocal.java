/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Showtimetable;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author 101036886
 */
@Local
public interface ShowtimeFacadeLocal {
    boolean hasShowtime(String showtimeid);

    Showtimetable find(String showtimeid);
    
    ArrayList<Showtimetable> getShowtimes();

    /*
    boolean addShowtime(Showtimetable showtime);
    
    boolean updatShowtimeDetails(Showtimetable showtime);
    
    boolean removeShowtime(String showtimeid);
    */  
}

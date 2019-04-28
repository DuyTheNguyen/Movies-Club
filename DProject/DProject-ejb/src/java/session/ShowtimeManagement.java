/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ShowtimeDTO;
import entity.Showtimetable;
import java.util.ArrayList;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author 101036886
 */
@Stateless
public class ShowtimeManagement implements ShowtimeManagementRemote {

    @EJB
    private ShowtimeFacadeLocal showtimeFacade;

    private Showtimetable showtimeDTO2Entity(ShowtimeDTO showtimeDTO) {
        if (showtimeDTO == null) {
            // just in case
            return null;
        }

        String showtimeid = showtimeDTO.getShowtimeId();
        String date = showtimeDTO.getDate();
        String time = showtimeDTO.getTime();
        String room = showtimeDTO.getRoom();
        String movieName = showtimeDTO.getMovieName();
        String movieDes = showtimeDTO.getMovieDes();
        Showtimetable showtimetable = new Showtimetable(showtimeid, date, time, room, movieName, movieDes);

        return showtimetable;
    }

    private ShowtimeDTO showtimeEntity2DTO(Showtimetable showtime) {
        if (showtime == null) {
            // just in case
            return null;
        }

        ShowtimeDTO showtimeDTO = new ShowtimeDTO(
                showtime.getShowtimeid(),
                showtime.getDate(),
                showtime.getTime(),
                showtime.getRoom(),
                showtime.getMoviename(),
                showtime.getMoviedescription()
        );

        return showtimeDTO;
    }

    @Override
    @PermitAll
    public boolean hasShowtime(String showtimeid) {
        return showtimeFacade.hasShowtime(showtimeid);
    }

    @Override
    @PermitAll
    public ShowtimeDTO getShowtimeDetails(String showtimeid) {
        // get the user
        Showtimetable showtimetable = showtimeFacade.find(showtimeid);

        if (showtimetable == null) {
            // not found - no such user
            return null;
        } else {
            // found - prepare details
            ShowtimeDTO showtimeDTO = new ShowtimeDTO(showtimetable.getShowtimeid(),
                    showtimetable.getDate(), showtimetable.getTime(), showtimetable.getRoom(),
                    showtimetable.getMoviename(), showtimetable.getMoviedescription());

            return showtimeDTO;
        }
    }

    @Override
    @PermitAll
    public ArrayList<ShowtimeDTO> getShowtimes() {
        try {
            ArrayList<Showtimetable> alst = showtimeFacade.getShowtimes();

            if (alst.isEmpty()) {
                //not found
                return null;
            } else {
                ArrayList<ShowtimeDTO> alsDTO = new ArrayList<>(alst.size());
                for (Showtimetable stt : alst) {
                    ShowtimeDTO stDTO = showtimeEntity2DTO(stt);
                    alsDTO.add(stDTO);
                }
                return alsDTO;
            }
        } catch (NullPointerException e) {
            throw e;
        }
    }

}

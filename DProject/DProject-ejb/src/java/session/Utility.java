/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.ShowtimeDTO;
import entity.Showtimetable;
import entity.TicketDTO;
import entity.Tickettable;
import entity.UserDTO;
import entity.Usertable;
import java.util.ArrayList;

/**
 *
 * @author 101036886
 */
public class Utility {

    public static ArrayList<Tickettable> listOfTicketDTO2Entity(ArrayList<TicketDTO> listOfTicketDTO) {
        if (listOfTicketDTO.isEmpty()) {
            return null;
        }
        
        ArrayList<Tickettable> altt = new ArrayList<>(listOfTicketDTO.size());
        
        for(TicketDTO tDTO : listOfTicketDTO){
            Tickettable tt = ticketDTO2Entity(tDTO);
            altt.add(tt);
        }

        return altt;
    }
    
     public static ArrayList<TicketDTO> listOfTicketEntity2DTO(ArrayList<Tickettable> listOfTicketEntity) {
        if (listOfTicketEntity.isEmpty()) {
            return null;
        }
        
        ArrayList<TicketDTO> altDTO = new ArrayList<>(listOfTicketEntity.size());
        
        for(Tickettable tt : listOfTicketEntity){
            TicketDTO tDTO = ticketEntity2DTO(tt);
            altDTO.add(tDTO);
        }

        return altDTO;
    }
     
    public static TicketDTO ticketEntity2DTO(Tickettable tickettable) {
        if (tickettable == null) {
            // just in case
            return null;
        }
        String ticketid = tickettable.getTicketid();
        UserDTO userid = userEntity2DTO(tickettable.getUserid());
        ShowtimeDTO showtimeid = showtimeEntity2DTO(tickettable.getShowtimeid());
        String quantity = tickettable.getQuantity();

        TicketDTO ticketDTO = new TicketDTO(ticketid, userid, showtimeid, quantity);

        return ticketDTO;
    }

    public static Tickettable ticketDTO2Entity(TicketDTO ticketDTO) {
        if (ticketDTO == null) {
            // just in case
            return null;
        }
        String ticketid = ticketDTO.getTicketId();
        Usertable userid = userDTO2Entity(ticketDTO.getUserId());
        Showtimetable showtimeid = showtimeDTO2Entity(ticketDTO.getShowtimeId());
        String quantity = ticketDTO.getQuantity();

        Tickettable tickettable = new Tickettable(ticketid, userid, showtimeid, quantity);

        return tickettable;
    }
    
    
    /****************************** USER ******************************************/
    public static Usertable userDTO2Entity(UserDTO userDTO) {
        if (userDTO == null) {
            // just in case
            return null;
        }

        String userid = userDTO.getUserid();
        String name = userDTO.getName();
        String phone = userDTO.getPhone();
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        String appGroup = userDTO.getAppGroup();

        Usertable usertable = new Usertable(userid, name, phone, email, password, appGroup);

        return usertable;
    }

    
    public static UserDTO userEntity2DTO(Usertable user) {
        if (user == null) {
            // just in case
            return null;
        }

        UserDTO userDTO = new UserDTO(
                user.getUserid(),
                user.getName(),
                user.getPhone(),
                user.getEmail(),
                user.getPassword(),
                user.getAppgroup()
               );

        return userDTO;
    }
    
    
     /****************************** SHOWTIME ******************************************/
    public static Showtimetable showtimeDTO2Entity(ShowtimeDTO showtimeDTO) {
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

    public static ShowtimeDTO showtimeEntity2DTO(Showtimetable showtime) {
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.TicketDTO;
import entity.Tickettable;
import java.util.ArrayList;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author 101036886
 */
@Stateless
public class TicketManagement implements TicketManagementRemote {

    @EJB
    private TicketFacadeLocal ticketFacade;
    
    @Override
    @PermitAll
    public boolean hasShowtime(String ticketid) {
        return ticketFacade.hasTicket(ticketid);
    }

    @Override
    @PermitAll
    public TicketDTO getTicketDetails(String ticketid) {
         // get the user
        Tickettable tickettable = ticketFacade.find(ticketid);

        if (tickettable == null) {
            // not found - no such user
            return null;
        } else {
            // found - prepare details
            TicketDTO ticketDTO = new TicketDTO(tickettable.getTicketid(),
                    Utility.userEntity2DTO(tickettable.getUserid()),  Utility.showtimeEntity2DTO(tickettable.getShowtimeid()), tickettable.getQuantity()
                   );

            return ticketDTO;
        }
    }

    @Override
    @PermitAll
    public ArrayList<TicketDTO> getTickets(String userid) {
         try {
            ArrayList<Tickettable> alst = ticketFacade.getTickets(userid);

            if (alst.isEmpty()) {
                //not found
                return null;
            } else {
                ArrayList<TicketDTO> alsDTO = new ArrayList<>(alst.size());
                for (Tickettable stt : alst) {
                    TicketDTO stDTO = Utility.ticketEntity2DTO(stt);
                    alsDTO.add(stDTO);
                }
                return alsDTO;
            }
        } catch (NullPointerException e) {
            throw e;
        }
    }

    @Override
    public boolean addTicket(TicketDTO ticket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updatTicketQuantity(String ticketid, String quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeTicket(String ticketid) {
        return ticketFacade.removeTicket(ticketid);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 101036886
 */
@Entity
@Table(name = "TICKETTABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tickettable.findAll", query = "SELECT t FROM Tickettable t")
    , @NamedQuery(name = "Tickettable.findByTicketid", query = "SELECT t FROM Tickettable t WHERE t.ticketid = :ticketid")
    , @NamedQuery(name = "Tickettable.findByQuantity", query = "SELECT t FROM Tickettable t WHERE t.quantity = :quantity")
    , @NamedQuery(name = "Tickettable.findByUserid", query = "SELECT t FROM Tickettable t WHERE t.userid = :userid")})
public class Tickettable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "TICKETID")
    private String ticketid;
    @Size(max = 3)
    @Column(name = "QUANTITY")
    private String quantity;
    @JoinColumn(name = "SHOWTIMEID", referencedColumnName = "SHOWTIMEID")
    @ManyToOne
    private Showtimetable showtimeid;
    @JoinColumn(name = "USERID", referencedColumnName = "USERID")
    @ManyToOne
    private Usertable userid;

    public Tickettable() {
    }

    public Tickettable(String ticketid) {
        this.ticketid = ticketid;
    }
    
     public Tickettable(String ticketid, Usertable userid, Showtimetable showtimeid, String quantity) {
        this.userid = userid;
        this.ticketid = ticketid;
        this.showtimeid = showtimeid;
        this.quantity = quantity;
    }

    public String getTicketid() {
        return ticketid;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Showtimetable getShowtimeid() {
        return showtimeid;
    }

    public void setShowtimeid(Showtimetable showtimeid) {
        this.showtimeid = showtimeid;
    }

    public Usertable getUserid() {
        return userid;
    }

    public void setUserid(Usertable userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketid != null ? ticketid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tickettable)) {
            return false;
        }
        Tickettable other = (Tickettable) object;
        if ((this.ticketid == null && other.ticketid != null) || (this.ticketid != null && !this.ticketid.equals(other.ticketid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tickettable[ ticketid=" + ticketid + " ]";
    }
    
}

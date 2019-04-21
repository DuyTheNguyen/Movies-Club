/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 101036886
 */
@Entity
@Table(name = "SHOWTIMETABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Showtimetable.findAll", query = "SELECT s FROM Showtimetable s")
    , @NamedQuery(name = "Showtimetable.findByShowtimeid", query = "SELECT s FROM Showtimetable s WHERE s.showtimeid = :showtimeid")
    , @NamedQuery(name = "Showtimetable.findByDate", query = "SELECT s FROM Showtimetable s WHERE s.date = :date")
    , @NamedQuery(name = "Showtimetable.findByTime", query = "SELECT s FROM Showtimetable s WHERE s.time = :time")
    , @NamedQuery(name = "Showtimetable.findByRoom", query = "SELECT s FROM Showtimetable s WHERE s.room = :room")
    , @NamedQuery(name = "Showtimetable.findByMoviename", query = "SELECT s FROM Showtimetable s WHERE s.moviename = :moviename")
    , @NamedQuery(name = "Showtimetable.findByMoviedescription", query = "SELECT s FROM Showtimetable s WHERE s.moviedescription = :moviedescription")})
public class Showtimetable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "SHOWTIMEID")
    private String showtimeid;
    @Size(max = 10)
    @Column(name = "DATE")
    private String date;
    @Size(max = 10)
    @Column(name = "TIME")
    private String time;
    @Size(max = 5)
    @Column(name = "ROOM")
    private String room;
    @Size(max = 255)
    @Column(name = "MOVIENAME")
    private String moviename;
    @Size(max = 255)
    @Column(name = "MOVIEDESCRIPTION")
    private String moviedescription;
    @OneToMany(mappedBy = "showtimeid")
    private Collection<Tickettable> tickettableCollection;

    public Showtimetable() {
    }

    public Showtimetable(String showtimeid) {
        this.showtimeid = showtimeid;
    }

    public String getShowtimeid() {
        return showtimeid;
    }

    public void setShowtimeid(String showtimeid) {
        this.showtimeid = showtimeid;
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

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getMoviedescription() {
        return moviedescription;
    }

    public void setMoviedescription(String moviedescription) {
        this.moviedescription = moviedescription;
    }

    @XmlTransient
    public Collection<Tickettable> getTickettableCollection() {
        return tickettableCollection;
    }

    public void setTickettableCollection(Collection<Tickettable> tickettableCollection) {
        this.tickettableCollection = tickettableCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (showtimeid != null ? showtimeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Showtimetable)) {
            return false;
        }
        Showtimetable other = (Showtimetable) object;
        if ((this.showtimeid == null && other.showtimeid != null) || (this.showtimeid != null && !this.showtimeid.equals(other.showtimeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Showtimetable[ showtimeid=" + showtimeid + " ]";
    }
    
}

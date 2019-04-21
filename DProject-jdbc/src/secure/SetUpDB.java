/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure;

import java.util.ArrayList;

/**
 *
 * @author 101036886
 */
public class SetUpDB {
      public static void main(String[] args) {
          /*
        UserDB userDB = new UserDB("UserTable");
        
       
            // make sure no name conflicts
        try {
            userDB.destroyDBTable();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        // create the database table
        System.out.println("Create an empty database table User");
        userDB.createDBTable();
        
        System.out.println("Add several static records in the database table");
        
        // prepare data
        User emp001 = new User("00001", "Adam", "1234567890", "adam@secure.com.au", "11111111", "ADMIN");
        User emp002 = new User("00002", "Bill", "2345678901", "bill@secure.com.au", "22222222", "USER");
        User emp003 = new User("00003", "Ceci", "3456789012", "ceci@secure.com.au", "33333333", "USER");
        User emp004 = new User("00004", "Dave", "4567890123", "dave@secure.com.au", "44444444", "USER");
        
        // prepare list
        ArrayList<User> empList = new ArrayList<>();
        empList.add(emp001);
        empList.add(emp002);
        empList.add(emp003);
        empList.add(emp004);
        
        // add data to db
        userDB.addRecords(empList);
        
        ShowtimeDB showtimeDB = new ShowtimeDB("ShowtimeTable");
        try {
            showtimeDB.destroyDBTable();
        } catch (Exception ex) {
            System.out.println(ex);
        }
      

        // create the database table
        System.out.println("\n\nCreate an empty database table Showitme");
        showtimeDB.createDBTable();
        
        System.out.println("Add several static records in the database table");
        
        // prepare data
        Showtime sh001 = new Showtime("00001", "10-04-2019", "10:00", "00001", "Iron Man", "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.");
        Showtime sh002 = new Showtime("00002", "20-04-2019", "14:00", "00002", "Captain America: Civir War", "Political involvement in the Avengers' affairs causes a rift between Captain America and Iron Man.");
        Showtime sh003 = new Showtime("00003", "30-04-2019", "18:00", "00003", "Doctor Strange", "While on a journey of physical and spiritual healing, a brilliant neurosurgeon is drawn into the world of the mystic arts.");
        Showtime sh004 = new Showtime("00004", "01-05-2019", "20:00", "00004", "Avenger: Endgame", "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to undo Thanos' actions and restore order to the universe.");
        
        // prepare list
        ArrayList<Showtime> shList = new ArrayList<>();
        shList.add(sh001);
        shList.add(sh002);
        shList.add(sh003);
        shList.add(sh004);
        
        // add data to db
        showtimeDB.addRecords(shList);
        */
        
        
        TicketDB ticketDB = new TicketDB("TicketTable");
        try {
            ticketDB.destroyDBTable();
        } catch (Exception ex) {
            System.out.println(ex);
        }
     
        // create the database table
        System.out.println("\n\nCreate an empty database table Ticket");
        ticketDB.createDBTable();
        
        System.out.println("Add several static records in the database table");
        
        // prepare data
        Ticket t001 = new Ticket("00001", "00002", "00003", "2");
        Ticket t002 = new Ticket("00002", "00003", "00004", "3");
       
        // prepare list
        ArrayList<Ticket> tList = new ArrayList<>();
        tList.add(t001);
        tList.add(t002);
        
        
        // add data to db
        ticketDB.addRecords(tList);
        
      }
}

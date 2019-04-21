/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static secure.BaseDB.DB_Table;

/**
 *
 * @author 101036886
 */
public class TicketDB extends BaseDB{
    
    public TicketDB(String tableName) {
        super(tableName);
    }
    
    
      /*
     * createDBTable
     *
     * @aim Use SQL commands to create the database table
     */
    public void createDBTable() {
        Connection cnnct = null;    // declare a connection object
        Statement stmnt = null;     // declare a statement object

        try {
            // get connection
            cnnct = getConnection();
            // get statement
            stmnt = cnnct.createStatement();

            /**
             * execute query to create a data table with the required fields:
             * EmpId, Name, Tel, Address, Bank_Account (for payroll), Salary,
             * Active (currently employed in the company)
             */
            stmnt.execute("CREATE TABLE " + DB_Table
                    + " (TicketId CHAR(5) CONSTRAINT " + DB_PK_Constraint + " PRIMARY KEY,"
                    + " UserId CHAR(5), "
                    + " ShowtimeId CHAR(5), "
                    + " Quantity CHAR(3),"
                    + " FOREIGN KEY (UserId) REFERENCES USERTABLE, "
                    + " FOREIGN KEY (ShowtimeId) REFERENCES SHOWTIMETABLE ) ");
        } catch (SQLException ex) {
            // do nothing
            System.out.println(ex);
        } catch (IOException ex) {
            // do nothing
             System.out.println(ex);
        } finally {
            // close Statement object
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                    // do nothing
                     System.out.println(e);
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    /**
                     * cnnct.close() throws a SQLException, but we cannot
                     * recover at this point
                     */
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    // do nothing
                    System.out.println(sqlEx);
                }
            }
        }
    }
    
    
    /**
     * addRecord()
     *
     * @aim Add a record into the database table
     */
    public void addRecords(ArrayList<Ticket> empList) {

        Connection cnnct = null;

        // create a PreparedStatement object
        PreparedStatement pStmnt = null;

        try {
            // get connection
            cnnct = getConnection();

            // precompiled query statement
            String preQueryStatement = "INSERT INTO " + DB_Table
                    + " VALUES (?, ?, ?, ?)";

            for (Ticket emp : empList) {

                // get statement
                pStmnt = cnnct.prepareStatement(preQueryStatement);

                // set individual parameters at corresponding positions
                pStmnt.setString(1, emp.getTicketId());
                pStmnt.setString(2, emp.getUserId());
                pStmnt.setString(3, emp.getShowtimeId());
                pStmnt.setString(4, emp.getQuantity());
               
                /*
                 * execute update query to add record into the data table
                 * with four fields: CustId, Name, Tel, Age
                 *
                 * will return number of records added
                 */
                int rowCount = pStmnt.executeUpdate();

                /*
                 * rowCount should be 1 because 1 record is added
                 *
                 * throws exception if not
                 */
                if (rowCount == 0) {
                    throw new SQLException("Cannot insert records!");
                }
            }
        } catch (SQLException ex) {
            // do nothing
             System.out.println(ex);
        } catch (IOException ex) {
            // do nothing
             System.out.println(ex);
        } finally {
            // close Prepared Statement object
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                    // do nothing
                    System.out.println(e);
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    // do nothing
                     System.out.println(sqlEx);
                }
            }
        }
    }
}

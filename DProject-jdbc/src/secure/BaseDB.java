/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author 101036886
 */
public class BaseDB {
    
    // Database parameters for connection - url, username, password
    static String url;
    static String username;
    static String password;
    static String DB_Table;
    static String DB_PK_Constraint;

    /**
     * constructor using default url, username and password
     * @param  tableName 
     */
    public BaseDB(String tableName) {
        // set default parameters for Derby and JavaDB
        url = "jdbc:derby://localhost/sun-appserv-samples;create=true";
        username = "APP";
        password = "APP";
        DB_Table = tableName;
        DB_PK_Constraint = "PK_" + DB_Table;
    }

    /**
     * getConnecion()
     *
     * @aim Get a connection to the database using the specified info
     */
    protected Connection getConnection()
            throws SQLException, IOException {
        // first, need to set the driver for connection
        // for Derby
        System.setProperty("jdbc.drivers",
                "org.apache.derby.jdbc.ClientDriver");

        // next is to get the connection
        return DriverManager.getConnection(url, username, password);
    }

   
    /**
     * destroyDBTable()
     *
     * @aim Remove the database table
     */
    public void destroyDBTable() {
        Connection cnnct = null;
        Statement stmnt = null;

        try {
            // get connection
            cnnct = getConnection();
            // get statement
            stmnt = cnnct.createStatement();

            // execute action query to destroy a data table
            stmnt.execute("DROP TABLE " + DB_Table);
        } catch (SQLException ex) {
            // do nothing
        } catch (IOException ex) {
            // do nothing
        } finally {
            // close Statement object
            if (stmnt != null) {
                try {
                    stmnt.close();
                } catch (SQLException e) {
                    // do nothing
                }
            }

            // close Connection object
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                    // do nothing
                }
            }
        }
    }
    
}

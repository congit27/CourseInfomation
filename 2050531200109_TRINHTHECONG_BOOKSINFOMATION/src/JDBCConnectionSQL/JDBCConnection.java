/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBCConnectionSQL;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author TGDD
 */
public class JDBCConnection {
    public static Connection getJDBCConnection() {
        final String user = "sa";
        final String password = "123456";
        final String url = "jdbc:sqlserver://localhost:1433;databaseName=BOOKS;user="+user+";password="+password;
        
        try {
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

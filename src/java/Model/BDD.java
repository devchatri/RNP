/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class BDD {
    private static final String URL = "jdbc:mysql://localhost/rnpjava";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection getConnection() throws SQLException {
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException ex) {
        // Gérer l'exception si le pilote n'est pas trouvé
        ex.printStackTrace();
        throw new SQLException("MySQL JDBC Driver not found", ex);
    }

    // Établir la connexion à la base de données
    return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

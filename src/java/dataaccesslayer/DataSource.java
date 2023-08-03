/*
Student Name: Andre Azevedo
Student Number: 041076086
Course & Section #: 23S_CST8288_020 
Declaration: This is my own original work and is free from Plagiarism.
*/

package dataaccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DataSource class provides a connection to a MySQL database using JDBC.
 * It manages the creation and retrieval of a database connection for the application.
 */
public class DataSource {

    private Connection connection = null;
    private final String url = "jdbc:mysql://localhost:3306/peertutor?useSSL=false";
    private String username = "root";
    private String password = "Loluol151721!";

    /**
     * Constructs a new DataSource object.
     */
    public DataSource() {
    }

    /**
     * Creates and returns a connection to the MySQL database.
     * If a connection already exists, it returns the existing connection instead of creating a new one.
     *
     * @return A Connection object representing the database connection.
     */
    public Connection createConnection() {
       
        try {
            if (connection != null) {
                System.out.println("Cannot create new connection, one exists already");
            } else {
                // Establish a new connection to the database using the specified URL, username, and password.
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException ex) {
            // Print the stack trace in case of any SQL exception.
            ex.printStackTrace();
        }
        return connection;
    }
}

package library.librarymangementsystem;

import java.sql.Connection;
import Database.DBConnection;
import UI.LoginForm; 

public class Librarymangementsystem {

    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            System.out.println("Database connection successful!");

            new LoginForm().setVisible(true);

        } else {
            System.out.println("Database connection failed!");
        }
    }
}

package lesson1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBC {
    public static void main(String[] args) throws SQLException {
        // MYSQL ---------------------------

        String urlConnectionMySQL = "jdbc:mysql://localhost/dio";
        // Try catch - connection with root
        Connection connMySQL = null;
        try {
            connMySQL = DriverManager.getConnection(urlConnectionMySQL, "root", "root");
            System.out.println("Success - Root MySQL connection");
        } catch (Exception e) {
            System.out.println("Fail - Root MySQL connection");
            e.printStackTrace();
        } finally {
            System.out.println("Closing - Root MySQL connection");
            connMySQL.close();
        }

        // Try with resources - connection with user
        try (Connection connMySQLUser = DriverManager.getConnection(urlConnectionMySQL, "dev", "dev123dev123")){
            System.out.println("Success - User MySQL connection");
        } catch (Exception e) {
            System.out.println("Fail - User MySQL connection");
            e.printStackTrace();
        }

        // POSTGRESQL ---------------------------

        String urlConnectionPostgreSQL = "jdbc:postgresql://localhost/mypgdb";
        // Try catch - connection with root
        Connection connPg = null;
        try {
            connPg = DriverManager.getConnection(urlConnectionPostgreSQL, "postgres", "postgres");
            System.out.println("Success - Root PostgreSQL connection");
        } catch (Exception e) {
            System.out.println("Fail - Root PostgreSQL connection");
            e.printStackTrace();
        } finally {
            System.out.println("Closing - Root PostgreSQL connection");
            connPg.close();
        }

        // Try with resources - connection with user
        try (Connection connPgUser = DriverManager.getConnection(urlConnectionPostgreSQL, "dev", "dev123dev123")){
            System.out.println("Success - User PostgreSQL connection");
        } catch (Exception e) {
            System.out.println("Fail - User PostgreSQL connection");
            e.printStackTrace();
        }
    }
}

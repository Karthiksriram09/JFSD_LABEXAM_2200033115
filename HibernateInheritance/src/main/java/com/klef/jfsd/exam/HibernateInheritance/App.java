package com.klef.jfsd.exam.HibernateInheritance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Hello world with actual database operations for inserting car data.
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Attempt to establish a database connection
        Connection connection = null;
        try {
            connection = connectToDatabase();
            if (connection != null) {
                createTable(connection);
            }

            // Input loop
            while (connection != null) {
                System.out.println("Enter car make, model, and year (or type 'exit' to finish):");
                System.out.print("Make: ");
                String make = scanner.nextLine();
                if ("exit".equalsIgnoreCase(make)) {
                    break;
                }

                System.out.print("Model: ");
                String model = scanner.nextLine();
                if ("exit".equalsIgnoreCase(model)) {
                    break;
                }

                System.out.print("Year: ");
                int year = scanner.nextInt();
                scanner.nextLine(); // consume newline left-over

                insertData(connection, make, model, year);
            }
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
            scanner.close();
        }

        System.out.println("\nData insertion complete.");
    }

    private static Connection connectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/labexam", "root", "klu12345");
    }

    private static void createTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String sqlCreate = "CREATE TABLE IF NOT EXISTS Car (id BIGINT AUTO_INCREMENT PRIMARY KEY, make VARCHAR(255), model VARCHAR(255), year INT);";
        statement.executeUpdate(sqlCreate);
        System.out.println("Table 'Car' ensured to be created.\n");
    }

    private static void insertData(Connection connection, String make, String model, int year) {
        try {
            Statement statement = connection.createStatement();
            String sqlInsert = String.format("INSERT INTO Car (make, model, year) VALUES ('%s', '%s', %d);", make, model, year);
            statement.executeUpdate(sqlInsert);
            System.out.println("Record inserted: " + make + " " + model + " " + year);
        } catch (SQLException e) {
            System.out.println("Failed to insert data: " + e.getMessage());
        }
    }
}

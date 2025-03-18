package banking;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost/customer";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12092001";

    public static void insertCustomer(Customer customer) {
        try (Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement st = con.createStatement()) {

            String sql = "INSERT INTO Customers (nid, names, age, phone_number, account_number) VALUES ('"
                    + customer.getNationalId() + "', '" + customer.getNames() + "', " + customer.age() + ", '"
                    + customer.phoneNumber() + "', '" + customer.accountNumber() + "')";

            int rowsAffected = st.executeUpdate(sql);
            System.out.println(rowsAffected > 0 ? "Data Inserted" : "Data not inserted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void findCustomerByNID(String nationalID) {
        try (Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Customers WHERE nid = '" + nationalID + "'")) {

            if (rs.next()) {
                System.out.println("Customer found");
                System.out.println("National ID: " + rs.getString("nid"));
                System.out.println("Names: " + rs.getString("names"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Phone Number: " + rs.getString("phone_number"));
                System.out.println("Account Number: " + rs.getString("account_number"));
            } else {
                System.out.println("No customer found with this national ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomer(String nationalID) {
        try (Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement st = con.createStatement()) {

            int rowsAffected = st.executeUpdate("DELETE FROM Customers WHERE nid = '" + nationalID + "'");
            System.out.println(rowsAffected > 0 ? "Customer deleted successfully!" : "Customer not found!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void findAllCustomers() {
        try (Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Customers")) {

            while (rs.next()) {
                System.out.println(rs.getString("nid") + " - " + rs.getString("names"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

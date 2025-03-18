package banking.services;

import banking.database.DatabaseConnection;
import banking.models.Customer;
import banking.utils.Validation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class CustomerServices {
    private Scanner sc = new Scanner(System.in);

    public void run() {
        boolean condition = true;

        while(true) {
            System.out.println("=====================");
            System.out.println("    BANKING SYSTEM   ");
            System.out.println("1. Register a customer");
            System.out.println("2. Find a customer by National ID");
            System.out.println("3. Exit");
            System.out.println("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    findCustomer();
                    break;
                case 3:
                    condition = false;
                    System.out.println("Thank you for using our banking system!");
                break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void registerCustomer() {
        System.out.println("Customer Registration");

        // validate national id
        String nationalID;
        do {
            System.out.println("Enter National ID(digits only): ");
            nationalID = sc.nextLine();
        } while (!InputValidator.isValidNumeric(nationlaID));

        // validate names
        do {
            System.out.println("Enter your names: ");
            names = sc.nextLine();
        } while (!InputValidator.isValidName(names));

        // valid age
        int age;
        do {
            System.out.println("Enter your age: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number");
                sc.next();
            }
            age = sc.nextInt();
            sc.nextLine();
        } while (age <= 0 || age > 120);

        // validate phone number
        String phoneNumber;
        do {
            System.out.print("Enter your phone number (10 digits): ");
            phoneNumber = sc.nextLine();
        } while (!InputValidator.isValidPhone(phoneNumber));

        // validate account number
        String accountNumber;
        do {
            System.out.println("Enter account number: ");
            accountNumber = sc.nextLine();
        } while (!InputValidator.isValidNumber(accountNumber)) {
            
            Customer customer = new Customer(nationalID, nationalID, age, phoneNumber, accountNumber);

            try {
                String sql = "INSERT INTO Customers (nid, names, age, phone_number, account_number) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, customer.getNationalID());
                pst.setString(2, customer.getNames());
                pst.setInt(3, customer.getAge());
                pst.setString(4, customer.getPhoneNumber());
                pst.setString(5, customer.getAccountNumber());

                int rows = pst.executeUpdate();
                if (rows > 0) {
                    System.out.println("Customer registered successfully!");
                } else {
                    System.out.println("Registration failed.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public static void findCustomerByNID(String nationalID) {
            try (Connection con = DriverManager.getConnection(db_url, username, passwd)){
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Customers WHERE nid = '"+ nationalID +"'");
                if (rs.next()) {
                    System.out.println("Customer found");
                    System.out.println("National id: " + rs.getString("nid"));
                    System.out.println("Customer name: " + rs.getString("names"));
                    System.out.println("Customer age: " + rs.getString("age"));
                    System.out.println("Customer phone number: " + rs.getString("phoneNumber"));
                    System.out.println("Customer account number: " + rs.getString("accountNumber"));
                } else {
                    System.out.println("No customer found with this national id.");
                }
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public static void findAllCustomers() {
            try {
                System.out.println(Connection con = DriverManager.getConnection(db_url, username, passwd));
                Statement st =con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * Customers");

                while (rs.next()) {
                    System.out.println(rs.getString("nid")  + "-" + rs.getString("names"));
                }

            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        }
    }
}

package banking.services;

import banking.database.DatabaseConnection;
import banking.models.Customer;
import banking.utils.Validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    }
}

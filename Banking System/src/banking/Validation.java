package banking;

import java.util.Scanner;

public class Validation {
    public static String getValidNationalID(Scanner sc) {
        while (true) {
            System.out.println("Enter the national ID(digits only): ");
            String nationalID = sc.nextLine();
            if (nationalID.matches("\\d+")) {
                return nationalID;
            } else {
                System.out.println("Invalid input!, try again");
            }
        }
    }

    public static String getValidName(Scanner sc) {
        while (true) {
            System.out.println("Enter your name: ");
            String names = sc.nextLine();
            if (names.matches("^[A-Za-z ]+$")) {
                return names;
            } else {
                System.out.println("Invalid name, try again and use only letters");
            }
        }
    }

    public static int getValidAge(Scanner sc) {
        while (true) {
            System.out.println("Enter your age: ");
            if (sc.hasNextInt()) {
                int age = sc.nextInt();
                sc.nextLine();
                if (age > 0 & age < 120) {
                    return age;
                }
            }
            System.out.println("Invalid input, please try again");
            sc.nextLine();

        }
    }

    public static String getValidPhoneNumber(Scanner sc) {
        while (true) {
            System.out.println("Enter your phone number: ");
            String phoneNumber = sc.nextLine();
            if (phoneNumber.matches("\\d{10}")) {
                return phoneNumber;
            } else {
                System.out.println("Invalid input, please try again with a valid 10 digit number");
            }
        }
    }

    public static String getValidAccountNumber(Scanner sc) {
        while (true) {
           System.out.println("Enter your account number: ");
           String accountNumber = sc.nextLine();
           if (accountNumber.matches("\\d+")) {
            return accountNumber;
           } else {
            System.out.println("Invalid input, try again with numbers ony");
           }
        }
    }
}

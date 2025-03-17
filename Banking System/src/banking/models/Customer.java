package banking.models;

import java.util.Scanner;

import banking.utils.Validation;

public class Customer {
    private String nationalID;
    private String names;
    private int age;
    private String phoneNumber;
    private String accountNumber;

    public Customer(String nationalID, String names, int age, String phoneNumber, String accountNumber) {
        this.nationalID = nationalID;
        this.names = names;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
    }
    public String getNationalId() {
        return nationalID;
    }

    public String getNames() {
        return names;
    }

    public int age() {
        return age;
    }

    public String phoneNumber() {
        return phoneNumber;
    }

    public String accountNumber() {
        return accountNumber;
    }

    public static Customer registrationCustomer(Scanner sc) {
        System.out.println("Customer Registration");
        System.out.println("------------------------------");

        String nationalID = Validation.getValidNationalID(sc);
        String names = Validation.getValidName(sc);
        int age = Validation.getValidAge(sc);
        String phoneNumber = Validation.getValidPhoneNumber(sc);
        String accountNumber = Validation.getValidAccountNumber(sc);
        
        return new Customer(nationalID, names, age, phoneNumber, accountNumber);
    }
    
}
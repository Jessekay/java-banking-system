package banking.models;

public class Customer {
    private String nationalID;
    private String names;
    private int age;
    private String phoneNumber;
    private String accountNumber;

    // Constructor
    public Customer(String nationalID, String names, int age, String phoneNumber, String accountNumber) {
        this.nationalID = nationalID;
        this.names = names;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
    }

    // Getters & Setters
    public String getNationalID() { return nationalID; }
    public void setNationalID(String nationalID) { this.nationalID = nationalID; }

    public String getNames() { return names; }
    public void setNames(String names) { this.names = names; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
}

package banking.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class BankingSystem {
    public static void main(String[] args) {
        // variable declaration
        Scanner sc = new Scanner(System.in);
        boolean condition = true;
        String option;
        String nationalID;
        String names;
        int age;
        String phoneNumber;
        String accountNumber;
        String db_url = "jdbc:mysql://localhost/customer";
        String username = "root";
        String passwd = "12092001";
        String answer;

        while (condition) {
            System.out.println("=====================");
            System.out.println("    BANKING SYSTEM   ");
            System.out.println("1. Register a customer");
            System.out.println("2. Update Customer Information");
            System.out.println("3. Find a customer By national ID");
            System.out.println("4. Delete a Customer");
            System.out.println("5. Find all Customers");
            System.out.println("0. Exit");
            System.out.println("----------------------");
            System.out.println("Choose: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Customer registration selected!");
                    System.out.println("--------------------------------");
                    System.out.print("Enter the National ID:");
                    nationalID = sc.next();
                    System.out.print("Enter YOUR NAMES:");
                    sc.nextLine();
                    names = sc.nextLine();
                    System.out.print("Enter your Age:");
                    age = sc.nextInt();
                    System.out.print("Enter your Phone Number:");
                    phoneNumber = sc.next();
                    System.out.print("Enter your Account Number:");
                    accountNumber = sc.next();
                    sc.nextLine();

                    try {
                        // Step 0: Surround with try and catch
                        // Step 1: Create / Establish connection
                        Connection con = DriverManager.getConnection(db_url, username, passwd);
                        // step2: Create Statement
                        Statement st = con.createStatement();
                        // step3: Execute the Statement
                        String sql = "INSERT INTO  Customers (nid, names,age, phone_number,account_number)values('"
                                + nationalID + "','" + names + "'," + age + ",'" + phoneNumber + "','" + accountNumber
                                + "')";
                        int rowsAffected = st.executeUpdate(sql);
                        if (rowsAffected > 0) {
                            System.out.println("Data Inserted");
                        } else {
                            System.out.println("Data not inserted");
                        }
                        con.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    System.out.println("Do you want to continue(yes/no): ");
                    answer = sc.next();
                    if (answer.equalsIgnoreCase("yes")) {
                        condition = true;
                    } else if (answer.equalsIgnoreCase("no")) {
                        System.out.println("Thank you for using our system!");
                        condition = false;
                        System.exit(0);
                    } else {
                        System.out.println("invalid choice");
                        condition = true;
                    }
                    break;
                case 2:
                    System.out.println("Option 2 Selected!");
                    System.out.println("Update");
                    System.out.println("1. Update national ID");
                    System.out.println("2. Update name");
                    System.out.println("3. Update age");
                    System.out.println("4. Update phone number");
                    System.out.println("5. Update account number");

                    int updateChoice = sc.nextInt();
                    switch (updateChoice) {
                        case 1:
                            System.out.println("Enter your name: ");
                            sc.nextLine();
                            names = sc.nextLine();
                            System.out.println("Enter your new national ID:");
                            int newNid = sc.nextInt();

                            try {
                                // create and establish a connection
                                Connection con = DriverManager.getConnection(db_url, username, passwd);
                                // create a statement
                                Statement st = con.createStatement();
                                // execute the statement
                                String sql = "UPDATE Customers SET nid = '" + newNid + "' WHERE names = '" + names
                                        + "'";
                                int idUpdateRowsAffected = st.executeUpdate(sql);
                                if (idUpdateRowsAffected > 0) {
                                    System.out.println("National ID updated successfully!");
                                } else {
                                    System.out.println("National ID not updated!");
                                }
                                con.close();

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        //     System.out.println("Do you want to continue(yes/no): ");
                        //     answer = sc.next();
                        //     if (answer.equalsIgnoreCase("yes")) {
                        //         condition = true;
                        //     } else if (answer.equalsIgnoreCase("no")) {
                        //         System.out.println("Thank you for using our system!");
                        //         condition = false;
                        //         System.exit(0);
                        //     } else {
                        //         System.out.println("invalid choice");
                        //         condition = true;
                        //     }
                        break;
                        case 2:
                            System.out.println("Enter national ID: ");
                            nationalID = sc.next();
                            System.out.print("Enter your new name: ");
                            sc.nextLine();
                            String newNames = sc.nextLine();

                            try {
                                // create and establish a connection
                                Connection con = DriverManager.getConnection(db_url, username, passwd);
                                // create statement
                                Statement st = con.createStatement();
                                // execute query
                                String sql = "UPDATE Customers SET names = '" + newNames + "' WHERE nid = '"
                                        + nationalID + "'";
                                int namesUpdateRowsAffected = st.executeUpdate(sql);
                                if (namesUpdateRowsAffected > 0) {
                                    System.out.println("Names updated successfully!");
                                } else {
                                    System.out.println("Names not updated");
                                }
                                con.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                            // System.out.println("Do you want to continue(yes/no): ");
                            // answer = sc.next();
                            // if (answer.equalsIgnoreCase("yes")) {
                            //     condition = true;
                            // } else if (answer.equalsIgnoreCase("no")) {
                            //     System.out.println("Thank you for using our system!");
                            //     condition = false;
                            //     System.exit(0);
                            // } else {
                            //     System.out.println("invalid choice");
                            //     condition = true;
                            // }
                        break;
                        case 3:
                            System.out.println("Enter your names");
                            sc.nextLine();
                            names = sc.nextLine();
                            System.out.println("Enter your new age");
                            while (!sc.hasNextInt()) {
                                System.out.println("Invalid input, please enter valid number");
                                sc.next();
                            }
                            int newAge = sc.nextInt();
                            
                            try {
                                // create and establish a connection
                                Connection con = DriverManager.getConnection(db_url, username, passwd);
                                // create statement
                                Statement st = con.createStatement();
                                // execute query
                                String sql = "UPDATE Customers SET age = '"+ newAge +"' WHERE names = '"+ names +"'";
                                int ageRowsAffected = st.executeUpdate(sql);
                                if (ageRowsAffected > 0) {
                                    System.out.println("Age updated successfully!");
                                } else {
                                    System.out.println("Age not updated!");
                                }
                                con.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                            // System.out.println("Do you want to continue(yes/no): ");
                            // answer = sc.next();
                            // if (answer.equalsIgnoreCase("yes")){
                            //     condition = true;
                            // } else if (answer.equalsIgnoreCase("no")) {
                            //     System.out.println("Thank you for using our system!");
                            //     condition = false;
                            //     System.exit(0);
                            // } else {
                            //     System.out.println("invalid choice");
                            //     condition = true;
                            // }
                            break;
                            case 4:
                            System.out.println("Enter your national ID: ");
                            sc.nextLine();
                            nationalID = sc.nextLine();
                            System.out.println("Enter your new phone number: ");

                            while (!sc.hasNextLine()) {
                                System.out.println("Invalid input, please try again");
                                // sc.nextLine();
                            }
                            String newPhoneNumber = sc.next();
                            try {
                                // create and establish a network
                                Connection con = DriverManager.getConnection(db_url, username, passwd);
                                // create statement
                                Statement st = con.createStatement();
                                // execute the query
                                String sql = "UPDATE Customers SET phone_number = '"+ newPhoneNumber +"' WHERE nid = '"+ nationalID +"'";
                                int phoneRowsAffected = st.executeUpdate(sql);
                                if (phoneRowsAffected > 0) {
                                    System.out.println("Phone number updated successfully!");
                                } else {
                                    System.out.println("Sorry, phone number not updated!");
                                }
                                con.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            break;
                    }
                case 0:
                    System.out.println("Thank you for using the system");
                    condition = false;
                    System.out.println("Enter yes to continue or No to quit");
                    option = sc.next();
                    if (option.equalsIgnoreCase("Yes")) {
                        condition = true;
                    } else if (option.equalsIgnoreCase("No")) {
                        condition = false;
                    } else {
                        System.out.println("Wrong Answer");
                    }
                    break;
                case 5:
                    System.out.println("Option 5 selected!");
                    System.out.println("Enter yes to continue or No to quit");
                    option = sc.next();
                    if (option.equalsIgnoreCase("Yes")) {
                        condition = true;
                    } else if (option.equalsIgnoreCase("No")) {
                        condition = false;
                    } else {
                        System.out.println("Wrong Answer");
                        condition = false;
                    }
                    break;
                default:
                    System.out.println("Wrong choice!!!");
                    System.out.println("Enter yes to continue or No to quit");
                    option = sc.next();
                    if (option.equalsIgnoreCase("Yes")) {
                        condition = true;
                    } else if (option.equalsIgnoreCase("No")) {
                        condition = false;
                    } else {
                        System.out.println("Wrong Answer");
                        condition = false;
                    }
            }
        }
    }
}

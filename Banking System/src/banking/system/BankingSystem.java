package banking.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BankingSystem {
    public static void main(String[] args) {
        // variable declaration
        Scanner sc = new Scanner(System.in);
        boolean condition = true;
        String option;
        String nationalID = null;
        String names = null;
        int age = 0;
        String phoneNumber = null;
        String accountNumber = null;
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
                    // validate national ID to always be digits only
                    while (true) {
                        System.out.print("Enter the National ID(numbers only):");
                        nationalID = sc.next();
                        if (nationalID.matches("\\d+")) {
                            break;
                        } else {
                            System.out.println("Invalid input! National should contain digits only.");
                        }
                    }
                //    validate user to only enter alphabets for names
                    while (true) {
                        System.out.print("Enter your names: ");
                        sc.nextLine();
                        names = sc.nextLine();
                        if (names.matches("^[A-Za-z ]+$")) {
                            break;
                        } else {
                            System.out.println("Invalid name!, Please use only letters and spaces");
                        }
                    }
                    
                    // validate age to always be integer and reasonable
                    while (true) {
                        System.out.print("Enter your Age:");
                        if (sc.hasNextInt()) {
                           age = sc.nextInt();
                           if (age > 0 & age < 120) {
                            break;
                           } else {
                            System.out.println("Invalid age! Please enter a valid age (1-120).");
                           }
                        } else {
                            System.out.println("Inavlid input! Please enter a valid age.");
                            sc.next();
                        }
                    }
                    // validatet phone number to be always digits and to not exceed 10 digits
                    while(true) {
                        System.out.print("Enter your Phone Number(10 digits):");
                        phoneNumber = sc.next();
                        if (phoneNumber.matches("\\d+{10}")) {
                            break;
                        } else {
                            System.out.println("Invalid phone number!, Enter a valid 10 digit number");
                        }
                    }

                    // validate acc number to only be numbers
                    while (true) {
                        System.out.print("Enter your Account Number:");
                        accountNumber = sc.next();
                        if (accountNumber.matches("\\d+")) {
                            break;
                        } else {
                            System.out.println("Invalid account number!, Please enter a valid account number");
                        }
                    }
                    
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
                    boolean updateCondition = true;

                    while (updateCondition) {
                    System.out.println("Option 2 Selected!");
                    System.out.println("Update");
                    System.out.println("1. Update national ID");
                    System.out.println("2. Update name");
                    System.out.println("3. Update age");
                    System.out.println("4. Update phone number");
                    System.out.println("5. Update account number");
                    System.out.println("0. Go back to main menu");

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
                            case 5:
                            System.out.println("Enter your national ID: ");
                            sc.nextLine();
                            nationalID = sc.nextLine();
                            System.out.println("Enter your new account number: ");
                            while(!sc.hasNextLine()) {
                                System.out.println("Invalid input, try again");
                            }
                            String newAccNumber = sc.nextLine();

                            try {
                                // create and establish a connection
                                Connection con = DriverManager.getConnection(db_url, username, passwd);
                                // create statement
                                Statement st = con.createStatement();
                                // execute query
                                String sql = "UPDATE Customers SET account_number = '"+ newAccNumber +"' WHERE nid = '"+ nationalID +"'";
                                int accNumberRowsAffected = st.executeUpdate(sql);
                                if (accNumberRowsAffected > 0) {
                                    System.out.println("Account number has been successfully updated!");
                                } else {
                                    System.out.println("Sorry, account number not updated!");
                                }
                                con.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            break;
                         case 0:
                               updateCondition = false;
                               break;
                               
                         default:
                               System.out.println("Invalid choice, please select a valid choice");      
                    
                        }
                        
                        if (updateChoice != 0) {
                            System.out.println("Do you want to continue updating? (yes/no)");
                            String continueUpdating = sc.next();
                            if (!continueUpdating.equalsIgnoreCase("yes")) {
                                updateCondition = false;
                            }
                        }
                    }

                    break;
                case 3: 
                     System.out.println("Option 3 selected");
                     System.out.println("Find customer using national ID");
                     sc.nextLine();
                     System.out.println("Enter the national ID of the customer: ");
                     nationalID = sc.nextLine();
                     
                     try {
                        // create and establish a connection
                        Connection con = DriverManager.getConnection(db_url, username, passwd);
                        // create statement 
                        Statement st = con.createStatement();
                        // excute the query
                        String sql = "SELECT * FROM Customers WHERE nid = '"+ nationalID +"'" ;
                        ResultSet rs = st.executeQuery(sql);

                        if (rs.next()) {
                            System.out.println("Customer found");
                            System.out.println("National ID: " + rs.getString("nid"));
                            System.out.println("Names: " + rs.getString("names"));
                            System.out.println("Age: " + rs.getString("age"));
                            System.out.println("Phone number: " + rs.getString("phone_number"));
                            System.out.println("Account number: " + rs.getString("account_number"));
                        } else {
                            System.out.println("No customer found with this national ID");
                        }
                        con.close();
                     } catch (Exception ex) {
                        ex.printStackTrace();
                     }
                     break;
                case 4:
                     System.out.println("Option 4 selected");
                     System.out.println("Delete customer");
                     sc.nextLine();
                     System.out.println("Enter the id of the customer you want delete: ");
                     nationalID = sc.nextLine();
                     
                     try {
                        Connection con = DriverManager.getConnection(db_url, username, passwd);
                        Statement st = con.createStatement();

                        String sql = "DELETE FROM Customers WHERE nid = '"+ nationalID +"'";
                        int deleteRowsAffected = st.executeUpdate(sql);
                        if (deleteRowsAffected > 0) {
                            System.out.println("Customer deleted successfully!");
                        } else {
                            System.out.println("Sorry, customer not deleted!");
                        }
                        con.close();
                     } catch (Exception ex) {
                        ex.printStackTrace();
                     }
                     break;
                case 5: 
                     System.out.println("Option 5 selected");
                     System.out.println("Find all customers");
                     sc.nextLine();
                     try {
                        Connection con = DriverManager.getConnection(db_url, username, passwd);
                        Statement st = con.createStatement();

                        String query = "SELECT * FROM Customers";
                        ResultSet rs = st.executeQuery(query);

                        System.out.println("\nLIST OF ALL CUSTOMERS");
                        System.out.println("=====================");

                        boolean hasData = false;

                        while(rs.next()){
                            hasData = true;
                            System.out.println("National ID: " + rs.getString("nid"));
                            System.out.println("Names: " + rs.getString("names"));
                            System.out.println("Age: " + rs.getString("age"));
                            System.out.println("Phone number: " + rs.getString("phone_number"));
                            System.out.println("Account number: " + rs.getString("account_number"));
                            System.out.println("------------------------");
                        }

                        if (!hasData) {
                            System.out.println("No customers found in the database");
                        }
                        rs.close();
                        st.close();
                        con.close();
                     } catch (Exception ex) {
                        ex.printStackTrace();
                     } 
                     break;   
                case 6:
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
                case 0:
                    System.out.println("Option 0 selected!");
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

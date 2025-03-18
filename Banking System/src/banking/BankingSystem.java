package banking;

import java.util.Scanner;

public class BankingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean condition = true;
        String option;

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
            System.out.print("Choose: "); 

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    Customer customer = Customer.registerCustomer(sc);
                    if (customer != null) {
                        DatabaseManager.insertCustomer(customer);
                    }
                    break;
                case 2: 
                    DatabaseManager.updateCustomer(sc);    
                    break;
                case 3:
                    System.out.println("Enter the national ID of the customer: ");
                    String nid = sc.nextLine();
                    DatabaseManager.findCustomerByNID(nid);
                    break;    
                case 4: 
                    System.out.println("Enter the national id of the customer to delete: ");
                    String deleteID = sc.nextLine();
                    DatabaseManager.deleteCustomer(deleteID);
                    break;
                case 5:
                    DatabaseManager.findAllCustomers();
                    break;
                case 0:
                    System.out.println("Thank you for using our system!");
                    condition = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");                 
            }
        }
        sc.close();
    }
}


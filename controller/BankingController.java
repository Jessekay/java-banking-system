import java.util.List;

public class BankingController {
    private CustomerDAO dao;
    private BankingView view;

    public BankingController() {
        this.dao = new CustomerDAO();
        this.view = new BankingView(this);
    }

    public void run() {
        view.showMainMenu();
    }

    public void registerCustomer(String nationalID, String names, int age, String phoneNumber, String accountNumber) {
        Customer newCustomer = new Customer(nationalID, names, age, phoneNumber, accountNumber);
        if(dao.addCustomer(newCustomer)) {
            view.displaymessage("Customer registered successfully!");
        } else {
            view.displaymessage("Failed to register.");
        }
    }
    public void findCustomerByNationalID(String nationalID) {
        Customer cutomer = dao.getCustomerByNationalID(nationalID);
        if(customer != null) {
            view.displaymessage(customer);
        } else {
            view.displaymessage("No customer found with the provided nationa ID.");
        }
    }

    public void updateCustomerInformation(String nationalId, int updateChoice, String newValue) {
        Customer existingCustomer = dao.getCustomerByNationalId(nationalId);
        if(existingCustomer == null) {
            view.displaymessage("Customer with national ID " + nationalId + "is not found");
            return;
        }

        boolean updated = false;
        switch (updateChoice) {
            case 1: 
                if (newValue.matches("\\d+")) { 
                    updated = dao.updateCustomerField(nationalID, "nid", newValue); 
                } else {
                    view.displayMessage("Invalid national ID format. Please use only digits."); 
                    return; 
                }
                break;
            case 2:
                if (newValue.matches("^[A-Za-z ]+$")) { 
                    updated = dao.updateCustomerField(nationalID, "names", newValue); 
                } else {
                    view.displayMessage("Invalid name format. Please use only letters and spaces."); 
                    return; 
                }
                break;
            case 3: 
                if (newValue.matches("\\d+") && Integer.parseInt(newValue) >= 18 && Integer.parseInt(newValue) < 120) { 
                    updated = dao.updateCustomerField(nationalID, "age", newValue); 
                } else {
                    view.displayMessage("Invalid age format. Please enter a valid age (18-120)."); 
                    return; 
                }
                break;
            case 4: 
                if (newValue.matches("\\d{10}")) { 
                    updated = dao.updateCustomerField(nationalID, "phone_number", newValue); 
                } else {
                    view.displayMessage("Invalid phone number format (10 digits required)."); 
                    return; 
                }
                break;
            case 5: 
                if (newValue.matches("\\d+")) { 
                    updated = dao.updateCustomerField(nationalID, "account_number", newValue); 
                } else {
                    view.displayMessage("Invalid account number format."); 
                    return;
                }
                break;
            default:
                view.displayMessage("Invalid update option."); 
                return;
        }
        if(updated) {
            view.displayMessage("Update done successfully!");
        } else {
            view.displayMessage("Failed to update");
        }
    }

    public void deleteCustomer(String nationalID) {
        if(dao.deleteCustomer(nationalID)) {
            view.displayMessage("Customer deleted!");
        } else {
            view.displayMessage("Failed to delete customer");
        }
    }

    public void findAllCustomers() {
        List<Customer> allCustomers = dao.getAllCustomers();
        if (allCustomers.isEmpty()) {
            view.displayMessage("No customers in the database");
        } else {
            view.displayAllCustomers(allCustomers);
        }
    }
}

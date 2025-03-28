import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAO {
    private String db_url = "jdbc:mysql://localhost:3306/customer";
    private String username = "root";
    private String passwd = "12092001";
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(db_url, username, passwd);
    }

    public int addCustomer(Customer customerObj) {
        String sql = "INSERT INTO customers (nid, names, age, phone_number, account_number) VALES (?,?,?,?,?)";
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, customerObj.getNationalID());
            pst.setString(2, customerObj.getNames());
            pst.setString(3, customerObj.getAge());
            pst.setString(4, customerObj.getPhoneNumber());
            pst.setString(5, customerObj.getAccountNumber());

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
            
        } catch (Exception e) {
             e.printStackTrace();
             return 0;
        }
    }

    public Customer getCustomerByNationalID(String nationalID) {
        String sql = "SELECT * FROM customers WHERE nid = ?";
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setString(1, nationalID);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return new CustomerDAO(
                    rs.getString("nid"),
                    rs.getString("names"),
                    rs.getString("age"),
                    rs.getString("phone_number"),
                    rs.getString("account_number")
                );
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateCustomerField(String nationalID, String fieldName, String newValue) {
        String sql = "UPDATE customers SET " + fieldName + "= ? WHERE nid = ?";
        try (Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement(sql)){

            pst.setString(1, newValue);
            pst.setString(2, nationalID);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCustomer(String nationalID) {
        String sql = "DELETE * FROM customers WHERE nid = ?";
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nationalID);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;    
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Customer> gtAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection con = getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

           while (rs.next()) {
            customers.add(new Customer(
                rs.getString("nid"),
                rs.getString("names"),
                rs.getString("age"),
                rs.getString("phone_number"),
                rs.getString("account_number")
            ));
           }     
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }
}

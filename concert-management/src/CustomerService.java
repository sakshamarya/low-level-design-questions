import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerService {
    List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        if (customer != null && !customers.contains(customer)) {
            customers.add(customer);
        } else {
            System.out.println("Customer already exists or is null.");
        }
    }

    public Customer getCustomerById(UUID customerId) {
        Customer customerFind = customers.stream()
                .filter(customer -> customer.getCustomerId().equals(customerId))
                .findFirst()
                .orElse(null);

        if (customerFind == null) {
            System.out.println("Customer not found.");
        }

        return customerFind;
    }

    public void updateCustomerThroughMobile(UUID customerId, String message){
        Customer customer = getCustomerById(customerId);
        System.out.println("Sent message to customer: " + customer.getCustomerName() + " - " + message + " mobile number: "  + customer.getMob());
    }

    public void updateCustomerThroughEmail(UUID customerId, String message){
        Customer customer = getCustomerById(customerId);
        System.out.println("Sent message to customer: " + customer.getCustomerName() + " - " + message + " email: "  + customer.getEmail());
    }
}

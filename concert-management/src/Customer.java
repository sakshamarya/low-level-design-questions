import java.util.UUID;

public class Customer {
    private UUID customerId;
    private String customerName;

    private String mob;

    private String email;

    public UUID getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMob() {
        return mob;
    }

    public String getEmail() {
        return email;
    }

    public Customer(String customerName, String mob, String email) {
        this.customerId = UUID.randomUUID();
        this.customerName = customerName;
        this.mob = mob;
        this.email = email;
        System.out.println("Customer created: " + customerName + ", ID: " + customerId);
    }
}

import java.util.UUID;

public interface NotificationSystem {
    public void notifyCustomers(String message);
    public void addCustomer(UUID customer);
    public void removeCustomer(UUID customerId);
}

import java.util.List;
import java.util.UUID;

public class EmailNotification implements NotificationSystem{

    List<UUID> emailSubscribers;
    @Override
    public void notifyCustomers(String message) {
        for(UUID customerId : emailSubscribers) {
            System.out.println("Sending email notification to customer ID: " + customerId + " with message: " + message);
            TicketBookingApp.getInstance().getCustomerService().updateCustomerThroughEmail(customerId, message);
        }
    }

    @Override
    public void addCustomer(UUID customer) {
        emailSubscribers.add(customer);
    }

    @Override
    public void removeCustomer(UUID customerId) {
        emailSubscribers.remove(customerId);
    }
}

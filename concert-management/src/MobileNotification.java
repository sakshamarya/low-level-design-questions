import java.util.List;
import java.util.UUID;

public class MobileNotification implements NotificationSystem{

    List<UUID> mobileSubscribers;
    @Override
    public void notifyCustomers(String message) {
        for(UUID customerId : mobileSubscribers) {
            System.out.println("Sending mobile notification to customer ID: " + customerId + " with message: " + message);
            TicketBookingApp.getInstance().getCustomerService().updateCustomerThroughMobile(customerId, message);
        }
    }

    @Override
    public void addCustomer(UUID customer) {
        mobileSubscribers.add(customer);
    }

    @Override
    public void removeCustomer(UUID customerId) {
        mobileSubscribers.remove(customerId);
    }
}

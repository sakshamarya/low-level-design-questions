import java.util.HashMap;

public class NotificationFactory {
    HashMap<String, NotificationSystem> notificationSystems;

    NotificationFactory(){
        notificationSystems = new HashMap<>();
        notificationSystems.put("email", new EmailNotification());
        notificationSystems.put("mobile", new MobileNotification());
    }

    public NotificationSystem getNotificationSystem(String type) {
        return notificationSystems.get(type);
    }
}

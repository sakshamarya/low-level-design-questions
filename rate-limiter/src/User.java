import java.util.concurrent.atomic.AtomicLong;

public class User {
    private static AtomicLong userId = new AtomicLong(0);
    private String userName;
    private Subscription subscription;

    public User(String userName, Subscription subscription) {
        userId = new AtomicLong(userId.incrementAndGet());
        this.userName = userName;
        this.subscription = subscription;
    }

    public String getUserName() {
        return userName;
    }

    public Subscription getSubscription() {
        return subscription;
    }

}

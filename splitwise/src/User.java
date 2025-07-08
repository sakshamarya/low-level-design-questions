import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicLong;

public class User {
    private AtomicLong userId = new AtomicLong(0);
    private String userName;
    private String email;
    private String phoneNumber;
    private Timestamp createdAt;

    User(String userName, String email, String phoneNumber){
        this.userId = new AtomicLong(userId.incrementAndGet());
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

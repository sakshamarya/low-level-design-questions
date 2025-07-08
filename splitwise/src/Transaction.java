import java.util.concurrent.atomic.AtomicLong;

public class Transaction {
    private AtomicLong transactionId = new AtomicLong(0);
    private User fromUser;
    private User toUser;
    private int amount;

    public Transaction(User fromUser, User toUser, int amount) {
        this.transactionId = new AtomicLong(transactionId.incrementAndGet());
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
    }

    public User getFromUser() {
        return fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public int getAmount() {
        return amount;
    }
}

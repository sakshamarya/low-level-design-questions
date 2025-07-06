import java.util.concurrent.atomic.AtomicLong;

public class Request {
    private AtomicLong requestId = new AtomicLong(0);
    private User sentBy;
    private long timestamp;
    private String requestMessage;

    public Request(User sentBy, String requestMessage) {
        this.requestId = new AtomicLong(requestId.incrementAndGet());
        this.sentBy = sentBy;
        this.timestamp = System.currentTimeMillis();
        this.requestMessage = requestMessage;
    }

    public Request(User sentBy, String requestMessage, long timestamp) {
        this.requestId = new AtomicLong(requestId.incrementAndGet());
        this.sentBy = sentBy;
        this.timestamp = timestamp;
        this.requestMessage = requestMessage;
    }

    public AtomicLong getRequestId() {
        return requestId;
    }

    public User getSentBy() {
        return sentBy;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getRequestMessage() {
        return requestMessage;
    }
}

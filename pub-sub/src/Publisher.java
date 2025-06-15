import java.util.UUID;

public class Publisher {
    private UUID publisherId;
    private String publisherName;
    public Publisher(String name) {
        this.publisherId = UUID.randomUUID();
        this.publisherName = name;
    }
    public void publishMessage(String message, Topic topic){
        topic.publishMessage(message, this);
    }

    public String getPublisherName() {
        return this.publisherName;
    }
}

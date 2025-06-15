import java.util.UUID;

public class Publisher {
    private UUID publisherId;
    private String publisherName;
    public Publisher(String name) {
        this.publisherId = UUID.randomUUID();
        this.publisherName = name;
    }
    public void publishMessage(String message, Topic topic){
        if(topic.isValidPublisher(this)) {
            topic.publishMessage(message);
        } else {
            System.out.println("Publisher " + publisherName + " is not valid for topic " + topic.getTopicName());
        }
    }

    public String getPublisherName() {
        return this.publisherName;
    }
}

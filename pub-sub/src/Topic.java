import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Topic {
    private String topicName;
    private UUID topicId;
    private List<Message> messages;
    private AtomicInteger offset;
    private List<Subscriber> subscribers;
    private List<Publisher> publishers;

    public Topic(String name){
        this.topicId = UUID.randomUUID();
        this.offset = new AtomicInteger(0);
        this.topicName = name;
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
        this.publishers = new ArrayList<>();
    }

    public void notifySubscribers(Message message){
        for(Subscriber subscriber : subscribers) {
            subscriber.notify(message, this);
        }
    }

    public void publishMessage(String message){
        AppExecutor.getExecutorService().submit(() ->{
            Message newMessage = new Message(message, offset.getAndIncrement());
            messages.add(newMessage);
            System.out.println("Message pushed to topic " + topicName + ": " + newMessage.getContent() + " at offset: " + newMessage.getOffset());
            notifySubscribers(newMessage);
        });
    }

    public boolean isValidPublisher(Publisher publisher) {
        return publishers.contains(publisher);
    }

    public String getTopicName(){
        return this.topicName;
    }
    public void subscribe(Subscriber subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
            System.out.println("Subscriber " + subscriber.getSubscriberName() + " subscribed to topic " + topicName);
        } else {
            System.out.println("Subscriber " + subscriber.getSubscriberName() + " is already subscribed to topic " + topicName);
        }
    }

    public void addPublisher(Publisher publisher) {
        if (!publishers.contains(publisher)) {
            publishers.add(publisher);
            System.out.println("Publisher " + publisher.getPublisherName() + " added to topic " + topicName);
        } else {
            System.out.println("Publisher " + publisher.getPublisherName() + " is already added to topic " + topicName);
        }
    }
}

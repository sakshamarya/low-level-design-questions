import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Subscriber {
    private UUID subscriberId;
    private String subscriberName;
    private int offset;
    Lock lock = new ReentrantLock(true);

    public Subscriber(String name) {
        this.subscriberId = UUID.randomUUID();
        this.subscriberName = name;
        this.offset = 0; // Initialize offset to 0
    }

    public void notify(Message message, Topic topic) {
        // Logic to handle the notification from the publisher
        try{
            if(lock.tryLock(10, TimeUnit.SECONDS)){
                this.offset++;
                System.out.println("Subscriber " + subscriberName + " received message: " + message.getContent() + " from topic: " + topic.getTopicName());
            }else{
                System.out.println("Cannot occupy lock, please try again later.");
            }
        } catch(Exception ex){
            System.out.println("Error while trying to lock: " + ex.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public String getSubscriberName() {
        return this.subscriberName;
    }
}

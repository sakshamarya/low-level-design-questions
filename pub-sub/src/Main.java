import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Topic topic1 = new Topic("Delhi");
        Topic topic2 = new Topic("Mumbai");

        Publisher publisher1 = new Publisher("Alan");
        Publisher publisher2 = new Publisher("Bob");
        Subscriber subscriber1 = new Subscriber("Charlie");
        Subscriber subscriber2 = new Subscriber("David");
        Subscriber subscriber3 = new Subscriber("Eve");

        topic1.addPublisher(publisher1);
        topic1.addPublisher(publisher2);
        topic2.addPublisher(publisher2);

        topic1.subscribe(subscriber1);
        topic1.subscribe(subscriber2);
        topic2.subscribe(subscriber2);
        topic2.subscribe(subscriber3);

        publisher1.publishMessage("Delhi is sunny today", topic1);
        publisher1.publishMessage("Delhi is rainy today", topic1);
        publisher1.publishMessage("Delhi is cloudy today", topic2);
        publisher2.publishMessage("Mumbai is cloudy today", topic2);
        publisher2.publishMessage("Mumbai is sunny today", topic2);
        publisher2.publishMessage("Mumbai is rainy today", topic2);

        AppExecutor.getExecutorService().shutdown();
    }
}
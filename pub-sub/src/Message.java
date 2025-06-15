import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Message {
    private UUID messageId;
    private String content;
    private int offset;

    public Message(String content, int offset) {
        this.messageId = UUID.randomUUID();
        this.content = content;
        this.offset = offset;
    }
    public String getContent(){
        return this.content;
    }
    public int getOffset(){
        return this.offset;
    }

}

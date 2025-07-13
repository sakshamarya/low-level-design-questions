import java.util.Objects;

public class UserPair {
    User from;
    User to;

    public UserPair(User from, User to){
        this.from = from;
        this.to = to;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "UserPair{" +
                "from=" + from.getUserName() +
                ", to=" + to.getUserName() +
                '}';
    }
}

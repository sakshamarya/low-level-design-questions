import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Over {
    private AtomicLong overId = new AtomicLong(1);
    private List<Ball> balls;
    private Player bowler;

    Over(Player bowler) {
        this.overId = new AtomicLong(overId.getAndIncrement());
        this.balls = new ArrayList<>();
        this.bowler = bowler;
    }

    public Player getBowler(){
        return bowler;
    }
    public List<Ball> getBalls() {
        return balls;
    }

    public int getBallCount() {
        return (int) balls.stream().flatMap(ball -> ball.getBallEvents().stream()).filter(BallEvent::isBallCounted).count();
    }

    public void addBall(Ball ball){
        if(this.getBallCount()>=6){
            System.out.println("Over cannot have more than 6 balls");
        }
        balls.add(ball);
    }

}

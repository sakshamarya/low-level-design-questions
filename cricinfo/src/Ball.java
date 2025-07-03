import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Ball {
    private AtomicLong ballId = new AtomicLong(1);
    List<BallEvent> ballEvents;
    private Player batsman;
    private Player thirdPlayer; // Optional, can be used for run-outs or other events

    Ball(List<BallEvent> ballEvents, Player batsman, Player thirdPlayer) {
        this.ballId = new AtomicLong(ballId.getAndIncrement());
        this.ballEvents = ballEvents;
        this.batsman = batsman;
        this.thirdPlayer = thirdPlayer;
    }

    public List<BallEvent> getBallEvents() {
        return ballEvents;
    }

    public int getRunsScored() {
        return ballEvents.stream().mapToInt(BallEvent::getRuns).sum();
    }

    public int getExtrasRuns() {
        return ballEvents.stream().filter(BallEvent::isExtras).mapToInt(BallEvent::getRuns).sum();
    }

    public int getNonExtrasRuns(){
        return getRunsScored() - getExtrasRuns();
    }

    public Player getBatsman() {
        return batsman;
    }
    public Player getThirdPlayer() {
        return thirdPlayer;
    }

}

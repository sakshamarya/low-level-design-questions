import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Inning {
    private AtomicLong inningId = new AtomicLong(1);
    private String inningName;
    private Team battingTeam;
    private Team bowlingTeam;
    List<Over> overs;
    private int numberOfOvers;
    ScoreCard battingScoreCard = new BattingScoreCard();
    ScoreCard bowlingScoreCard = new BowlingScoreCard();

    Inning(String inningName, Team battingTeam, Team bowlingTeam, int numberOfOvers) {
        this.inningName = inningName;
        this.inningId = new AtomicLong(inningId.getAndIncrement());
        this.battingTeam = battingTeam;
        this.bowlingTeam = bowlingTeam;
        this.numberOfOvers = numberOfOvers;
        this.overs = new ArrayList<>();
    }

    public String getInningName() {
        return inningName;
    }

    public Team getBattingTeam() {
        return battingTeam;
    }

    public Team getBowlingTeam() {
        return bowlingTeam;
    }

    public List<Over> getOvers() {
        return overs;
    }

    public int getNumberOfOvers() {
        return numberOfOvers;
    }

    public void printScoreCard(){
        System.out.println("Inning: " + inningName);
        battingScoreCard.printScoreCard(overs, battingTeam);
        bowlingScoreCard.printScoreCard(overs, bowlingTeam);
    }

    public void addOver(Player bowler) {
        if (overs.size() < numberOfOvers) {
            Over over = new Over(bowler);
            overs.add(over);
        } else {
            System.out.println("Inning cannot have more than " + numberOfOvers + " overs.");
        }
    }

    public Over getLiveOver() {
        if (overs.size() == 0) {
            System.out.println("No overs available in the inning.");
            return null;
        }
        return overs.get(overs.size() - 1);
    }

}

import java.util.HashMap;
import java.util.List;

public class BowlingScoreCard implements ScoreCard{
    public void printScoreCard(List<Over> overs, Team bowlingTeam){
        System.out.println("Bowling Team: " + bowlingTeam.getTeamName());
        HashMap<Player, Integer> oversBowled = new HashMap<>();
        HashMap<Player, Integer> runsConceded = new HashMap<>();
        HashMap<Player, Integer> wicketsTaken = new HashMap<>();
        for(Over over : overs) {
            Player bowler = over.getBowler();
            int updatedOvers = oversBowled.getOrDefault(bowler, 0)+1;
            oversBowled.put(bowler, updatedOvers);
            int runs = runsConceded.getOrDefault(bowler, 0);
            for(Ball ball : over.getBalls()) {
                runs += ball.getRunsScored();
                if(ball.getBallEvents().stream().anyMatch(BallEvent::isBatsmanOut)) {
                    int currentWickets = wicketsTaken.getOrDefault(bowler, 0);
                    wicketsTaken.put(bowler, currentWickets + 1);
                }
            }
            runsConceded.put(bowler, runs);
        }

        for(Player bowler : oversBowled.keySet()) {
            int oversCount = oversBowled.get(bowler);
            int runs = runsConceded.getOrDefault(bowler, 0);
            int wickets = wicketsTaken.getOrDefault(bowler, 0);
            System.out.println("Bowler: " + bowler.getPlayerName() +
                               ", Overs: " + oversCount +
                               ", Runs Conceded: " + runs +
                               ", Wickets: " + wickets);
        }
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BattingScoreCard implements ScoreCard{
    public void printScoreCard(List<Over> overs, Team battingTeam){
        System.out.println("Batting Team: "+ battingTeam.getTeamName());
        HashMap<Player, Integer> playerScores = new HashMap<>();
        HashMap<Player, Integer> playerBallsPlayed = new HashMap<>();
        HashMap<Player, Integer> fours = new HashMap<>();
        HashMap<Player, Integer> sixes = new HashMap<>();
        HashMap<Player, String> playerOutEvent = new HashMap<>();
        List<Player> battingOrder = new ArrayList<>();


        for(Over over: overs){
            for(Ball ball: over.getBalls()){
                Player batsman = ball.getBatsman();
                int nonExtras = ball.getNonExtrasRuns();

                if(!battingOrder.contains(batsman)){
                    battingOrder.add(batsman);
                }

                int currentRuns = playerScores.getOrDefault(batsman, 0);
                playerScores.put(batsman, currentRuns + nonExtras);

                int currentBalls = playerBallsPlayed.getOrDefault(batsman, 0);
                playerBallsPlayed.put(batsman, currentBalls + 1);

                if(ball.getBallEvents().stream().anyMatch(BallEvent::isBatsmanFour)){
                    int currentFours = fours.getOrDefault(batsman, 0);
                    fours.put(batsman, currentFours + 1);
                }
                if(ball.getBallEvents().stream().anyMatch(BallEvent::isBatsmanSix)){
                    int currentSixes = sixes.getOrDefault(batsman, 0);
                    sixes.put(batsman, currentSixes + 1);
                }

                ball.getBallEvents().stream().filter(BallEvent::isBatsmanOut)
                        .findAny()
                        .ifPresent(batsmanOutEvent -> playerOutEvent.put(batsman, batsmanOutEvent.getType() + " by: " + over.getBowler().getPlayerName() + ", fielder: " + ball.getThirdPlayer().getPlayerName()));
            }
        }

        for(Player player : battingOrder) {
            int runs = playerScores.getOrDefault(player, 0);
            int balls = playerBallsPlayed.getOrDefault(player, 0);
            int fourCount = fours.getOrDefault(player, 0);
            int sixCount = sixes.getOrDefault(player, 0);

            System.out.println("Player: " + player.getPlayerName() +
                               ", Runs: " + runs +
                               ", Balls: " + balls +
                               ", Fours: " + fourCount +
                               ", Sixes: " + sixCount);
            if(playerOutEvent.containsKey(player)) {
                System.out.println("Out: " + playerOutEvent.get(player));
            } else {
                System.out.println("Status: Not Out");
            }
        }
    }
}

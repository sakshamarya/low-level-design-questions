import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Match {
    private AtomicLong matchId = new AtomicLong(1);
    private MatchType matchType;
    private String matchName;
    private String venue;
    private Date matchDate;
    private Team team1;
    private Team team2;
    private MatchStatus status;
    private String result;
    private Date startTime;
    private List<Inning> innings;

    Match(MatchType matchType, String matchName, String venue, Date matchDate, Team team1, Team team2, MatchStatus status, String result, Date startTime) {
        this.matchId = new AtomicLong(matchId.getAndIncrement());
        this.matchType = matchType;
        this.matchName = matchName;
        this.venue = venue;
        this.matchDate = matchDate;
        this.team1 = team1;
        this.team2 = team2;
        this.status = status;
        this.result = result;
        this.startTime = startTime;
        this.innings = new ArrayList<>();
    }

    public void addInning(Team battingTeam, Team bowlingTeam) {
        if(battingTeam!=team1 && battingTeam!=team2){
            System.out.println("Batting team must be either " + team1.getTeamName() + " or " + team2.getTeamName());
            return;
        }
        if(bowlingTeam!=team1 && bowlingTeam!=team2){
            System.out.println("Bowling team must be either " + team1.getTeamName() + " or " + team2.getTeamName());
            return;
        }
        if (innings.size() < 2) {
            Inning inning = new Inning("Inning " + (innings.size() + 1), team1, team2, matchType.getOvers());
            innings.add(inning);
        } else {
            System.out.println("Match already has two innings.");
        }
    }

    public Inning getLiveInning(){
        if(innings.size()==0){
            System.out.println("No innings available in the match.");
            return null;
        }
        return innings.get(innings.size() - 1);
    }
}

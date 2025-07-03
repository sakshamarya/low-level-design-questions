import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Team {
    private AtomicInteger teamId = new AtomicInteger(1);
    private String teamName;
    private List<Player> players;
    private String owner;
    private String coach;
    private String homeGround;
    private String captain;

    Team(String teamName, List<Player> players, String owner, String coach, String homeGround, String captain) {
        this.teamId = new AtomicInteger(teamId.getAndIncrement());
        this.teamName = teamName;
        this.players = players;
        this.owner = owner;
        this.coach = coach;
        this.homeGround = homeGround;
        this.captain = captain;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getOwner() {
        return owner;
    }

    public String getCoach() {
        return coach;
    }

    public String getHomeGround() {
        return homeGround;
    }

    public String getCaptain() {
        return captain;
    }
}

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class Player {

    private AtomicLong playerId = new AtomicLong(1);
    private String playerName;
    private String nationality;
    private Date dateOfBirth;
    private PlayerRole role;

    Player(String playerName, String nationality, Date dateOfBirth, PlayerRole role) {
        this.playerId = new AtomicLong(playerId.getAndIncrement());
        this.playerName = playerName;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public String getPlayerName() {
        return playerName;
    }
}

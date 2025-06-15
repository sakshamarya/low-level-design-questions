import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Concert {
    private UUID concertId;
    private String concertName;
    private String concertDescription;
    private String artist;
    private Date concertDate;
    private List<UUID> seatIds;
    private String venue;

    public Concert(String concertName, String concertDescription, String artist, Date concertDate, List<UUID> seatIds, String venue) {
        this.concertId = UUID.randomUUID();
        this.concertName = concertName;
        this.concertDescription = concertDescription;
        this.artist = artist;
        this.concertDate = concertDate;
        this.seatIds = seatIds;
        this.venue = venue;
    }

    public UUID getConcertId() {
        return concertId;
    }

    public String getConcertName() {
        return concertName;
    }

    public String getConcertDescription() {
        return concertDescription;
    }

    public String getArtist() {
        return artist;
    }

    public Date getConcertDate() {
        return concertDate;
    }

    public List<UUID> getSeats() {
        return seatIds;
    }

    public String getVenue() {
        return venue;
    }

}

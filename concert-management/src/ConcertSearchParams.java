import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ConcertSearchParams {

    private UUID concertId;
    private List<String> artist;
    private List<String> venue;
    private Date date;

    public static class Builder {
        private UUID concertId;
        private List<String> artist;
        private List<String> venue;
        private Date date;

        public Builder concertId(UUID concertId) {
            this.concertId = concertId;
            return this;
        }

        public Builder artist(List<String> artist) {
            this.artist = artist;
            return this;
        }

        public Builder venue(List<String> venue) {
            this.venue = venue;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public ConcertSearchParams build() {
            ConcertSearchParams concertSearchParams = new ConcertSearchParams();
            concertSearchParams.concertId = this.concertId;
            concertSearchParams.artist = this.artist;
            concertSearchParams.venue = this.venue;
            concertSearchParams.date = this.date;
            return concertSearchParams;
        }


    }

    public UUID getConcertId() {
        return concertId;
    }

    public List<String> getArtist() {
        return artist;
    }

    public void setArtist(List<String> artist) {
        this.artist = artist;
    }

    public List<String> getVenue() {
        return venue;
    }

    public void setVenue(List<String> venue) {
        this.venue = venue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

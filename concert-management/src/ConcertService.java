import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConcertService {
    private static List<Concert> concerts;

    public ConcertService(){
        concerts = new ArrayList<>();
    };


    public List<Concert> getConcerts(ConcertSearchParams concertSearchParams) {

        if(concertSearchParams == null){
            return concerts;
        }
        return concerts.stream().filter(concert -> {
            return  concert.getConcertId().equals(concertSearchParams.getConcertId())
                    || (Objects.nonNull(concertSearchParams.getArtist()) && concertSearchParams.getArtist().contains(concert.getArtist()))
                    || (Objects.nonNull(concertSearchParams.getVenue()) && concertSearchParams.getVenue().contains(concert.getVenue()))
                    || concert.getConcertDate().equals(concertSearchParams.getDate());
        }).collect(Collectors.toList());
    }

    public void printConcerts(ConcertSearchParams concertSearchParams) {
        List<Concert> filteredConcerts = getConcerts(concertSearchParams);
        if(filteredConcerts.isEmpty()) {
            System.out.println("No concerts found.");
            return;
        }
        filteredConcerts.forEach(concert -> {
            System.out.println("Concert ID: " + concert.getConcertId());
            System.out.println("Name: " + concert.getConcertName());
            System.out.println("Description: " + concert.getConcertDescription());
            System.out.println("Artist: " + concert.getArtist());
            System.out.println("Date: " + concert.getConcertDate());
            System.out.println("Venue: " + concert.getVenue());
            System.out.println("Seats: " + concert.getSeats().size());
            concert.getSeats().forEach(seatId -> {
                Seat seat = TicketBookingApp.getInstance().getSeatService().getSeatById(seatId);
                System.out.println("  Seat ID: " + seat.getSeatId());
                System.out.println("  Type: " + seat.getSeatType());
                System.out.println("  Features: " + String.join(", ", seat.getFeatures()));
                System.out.println("  Booked By: " + (seat.getBookedBy() != null ? seat.getBookedBy().getCustomerName() : "Available"));
            });
            System.out.println("--------------------------------------------------");
        });
    }

    public void addConcert(Concert concert) {
        if(concert == null || concerts.contains(concert)) {
            System.out.println("Concert already exists or is null.");
            return;
        }

        System.out.println("Adding concert: " + concert.getConcertName());
        concerts.add(concert);
    }


}

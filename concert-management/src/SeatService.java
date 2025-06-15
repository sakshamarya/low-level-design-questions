import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class SeatService {
    List<Seat> seats = new ArrayList<>();

    public void addSeat(Seat seat) {
        if (seat != null && !seats.contains(seat)) {
            seats.add(seat);
        } else {
            System.out.println("Seat already exists or is null.");
        }
    }

    public Seat getSeatById(UUID seatId) {
        Seat seatFind = seats.stream()
                .filter(seat -> seat.getSeatId().equals(seatId))
                .findFirst()
                .orElse(null);

        if (seatFind == null) {
            System.out.println("Seat not found.");
        }

        return seatFind;
    }

    public Boolean isSeatAvailable(UUID seatId) {
        Seat seat = getSeatById(seatId);
        return Objects.isNull(seat.getBookedBy());
    }

    public void emptySeat(UUID seatId) {
        Seat seat = getSeatById(seatId);
        if (seat != null) {
            seat.setBookedBy(null);
            System.out.println("Seat " + seatId + " is now empty.");
        } else {
            System.out.println("Seat not found.");
        }
    }
}

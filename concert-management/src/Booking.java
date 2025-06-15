import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Booking {
    private UUID bookingId;
    private UUID concertId;
    private UUID customerId;
    private UUID seatId;
    private BooingStatus bookingStatus;

    public Booking(UUID bookingId, UUID concertId, UUID customerId, UUID seatId, BooingStatus bookingStatus) {
        this.bookingId = UUID.randomUUID();
        this.concertId = concertId;
        this.customerId = customerId;
        this.seatId = seatId;
        this.bookingStatus = bookingStatus;
    }
    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingStatus(BooingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public UUID getConcertId() {
        return concertId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getSeatId() {
        return seatId;
    }

    public BooingStatus getBookingStatus() {
        return bookingStatus;
    }


}

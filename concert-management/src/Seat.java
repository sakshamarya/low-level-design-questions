import java.util.*;

public class Seat {
    private UUID seatId;
    private SeatType seatType;
    private List<String> features;
    private Customer bookedBy;

    public void setBookedBy(Customer bookedBy) {
        this.bookedBy = bookedBy;
    }

    private static HashMap<SeatType, List<String>> seatFeatures = new HashMap<>();

    public UUID getSeatId() {
        return seatId;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public List<String> getFeatures() {
        return features;
    }

    public Customer getBookedBy() {
        return bookedBy;
    }

    public static HashMap<SeatType, List<String>> getSeatFeatures() {
        return seatFeatures;
    }

    static {
        seatFeatures.put(SeatType.STANDARD, Arrays.asList("Basic seating", "Standard view"));
        seatFeatures.put(SeatType.GOLD, Arrays.asList("Comfortable seating", "Enhanced view", "Refreshments included"));
        seatFeatures.put(SeatType.PLATINUM, Arrays.asList("Luxury seating", "Best view", "Complimentary drinks and snacks", "Exclusive lounge access"));
    }

    Seat(SeatType seatType){
        this.seatId = UUID.randomUUID();
        this.seatType = seatType;
        this.features = seatFeatures.get(seatType);
    }

}

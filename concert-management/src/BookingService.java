import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class BookingService {

    private static List<Booking> bookings;
    private final Lock lock = new ReentrantLock(true);

    BookingService(){
        bookings = new ArrayList<>();
    }

    public void createBooking(Concert concert, Customer customer, UUID seatId) {

        UUID id = UUID.randomUUID();

        if(!TicketBookingApp.getInstance().getConcertService().getConcerts(null).contains(concert)) {
            System.out.println("Concert not found.");
            bookings.add(new Booking(id, concert.getConcertId(), customer.getCustomerId(), seatId, BooingStatus.FAILED));
        }

        if(!TicketBookingApp.getInstance().getSeatService().isSeatAvailable(seatId)) {
            System.out.println("Seat not found.");
            bookings.add(new Booking(id, concert.getConcertId(), customer.getCustomerId(), seatId, BooingStatus.FAILED));
        }

        try {
            if(lock.tryLock(10000, TimeUnit.MILLISECONDS)){
                TicketBookingApp.getInstance().getSeatService().getSeatById(seatId).setBookedBy(customer);
                bookings.add(new Booking(id, concert.getConcertId(), customer.getCustomerId(), seatId, BooingStatus.CONFIRMED));
                System.out.println("Booking created successfully with ID: " + id);
                TicketBookingApp.getInstance().getNotificationFactory().getNotificationSystem("mobile").addCustomer(customer.getCustomerId());

            } else {
                System.out.println("Could not acquire lock for booking.");
                bookings.add(new Booking(id, concert.getConcertId(), customer.getCustomerId(), seatId, BooingStatus.FAILED));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void cancelBooking(UUID bookingID){
        Booking booking = bookings.stream().filter(b -> b.getBookingId().equals(bookingID)).findFirst().orElse(null);
        if(!bookings.contains(booking)) {
            System.out.println("Booking not found.");
            return;
        }

        try {
            if(lock.tryLock(10000, TimeUnit.MILLISECONDS)){
                // unoccupy the seat
                TicketBookingApp.getInstance().getSeatService().emptySeat(booking.getSeatId());
                // Cancel the booking
                booking.setBookingStatus(BooingStatus.CANCELLED);
                System.out.println("Booking with ID: " + bookingID + " has been cancelled successfully.");
                TicketBookingApp.getInstance().getNotificationFactory().getNotificationSystem("mobile").addCustomer(booking.getCustomerId());
            }
            else{
                System.out.println("Could not acquire lock for cancelling booking.");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }


    public void printBookings(UUID customerId) {
        if(bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        System.out.println("List of Bookings:");
        bookings.stream().filter(booking -> {return booking.getCustomerId().equals(customerId);}).collect(Collectors.toList()).forEach(booking -> {
            System.out.println("Booking ID: " + booking.getBookingId());
            System.out.println("Concert ID: " + booking.getConcertId());
            System.out.println("Customer ID: " + booking.getCustomerId());
            System.out.println("Seat ID: " + booking.getSeatId());
            System.out.println("Status: " + booking.getBookingStatus());
            System.out.println("--------------------------------------------------");
        });
    }
}

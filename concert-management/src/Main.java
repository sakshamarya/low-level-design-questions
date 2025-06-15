import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Concert Management System!");

        Seat seat1 = new Seat(SeatType.GOLD);
        Seat seat2 = new Seat(SeatType.GOLD);
        Seat seat3 = new Seat(SeatType.GOLD);
        Seat seat4 = new Seat(SeatType.PLATINUM);
        Seat seat5 = new Seat(SeatType.PLATINUM);
        Seat seat6 = new Seat(SeatType.PLATINUM);
        Seat seat7 = new Seat(SeatType.PLATINUM);
        Seat seat8 = new Seat(SeatType.STANDARD);
        Seat seat9 = new Seat(SeatType.STANDARD);

        TicketBookingApp.getInstance().getSeatService().addSeat(seat1);
        TicketBookingApp.getInstance().getSeatService().addSeat(seat2);
        TicketBookingApp.getInstance().getSeatService().addSeat(seat3);
        TicketBookingApp.getInstance().getSeatService().addSeat(seat4);
        TicketBookingApp.getInstance().getSeatService().addSeat(seat5);
        TicketBookingApp.getInstance().getSeatService().addSeat(seat6);
        TicketBookingApp.getInstance().getSeatService().addSeat(seat7);
        TicketBookingApp.getInstance().getSeatService().addSeat(seat8);
        TicketBookingApp.getInstance().getSeatService().addSeat(seat9);

        Concert concert1 = new Concert(
                "Rock Night",
                "An electrifying night of rock music",
                "The Rock Band",
                new Date(),
                Arrays.asList(seat1.getSeatId(), seat4.getSeatId(), seat8.getSeatId()),
                "Stadium A"
        );

        Concert concert2 = new Concert(
                "Jazz Evening",
                "A smooth evening of jazz tunes",
                "Jazz Ensemble",
                new Date(),
                Arrays.asList(seat2.getSeatId(), seat5.getSeatId(), seat9.getSeatId()),
                "Concert Hall B"
        );

        Concert concert3 = new Concert(
                "Classical Symphony",
                "An evening of classical symphonies",
                "Symphony Orchestra",
                new Date(),
                Arrays.asList(seat3.getSeatId(), seat6.getSeatId(), seat7.getSeatId()),
                "Opera House C"
        );

        TicketBookingApp.getInstance().getConcertService().addConcert(concert1);
        TicketBookingApp.getInstance().getConcertService().addConcert(concert2);
        TicketBookingApp.getInstance().getConcertService().addConcert(concert3);

        Customer customer1 = new Customer("Ram", "ram@abc.com", "1234567890");
        TicketBookingApp.getInstance().getCustomerService().addCustomer(customer1);

        System.out.println("Please select an option:");

        int optionSelected;

        do {
            System.out.println("1. Search Concerts");
            System.out.println("2. Book Concert");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");

            optionSelected = scanner.nextInt();
            UUID concertId;
            UUID seatId;
            UUID customerId = customer1.getCustomerId();
            UUID bookingId;

            switch (optionSelected) {
                case 1:
                    System.out.println("Searching Concerts...");
                    // Call the method to search concerts
                    TicketBookingApp.getInstance().getConcertService().printConcerts(null);
                    break;
                case 2:
                    System.out.println("Booking Concert...");
                    // Call the method to book a concert
                    System.out.println("Enter Concert ID and SeatID to book:");
                    concertId = UUID.fromString(scanner.next());
                    seatId = UUID.fromString(scanner.next());
//                    System.out.println("Enter customer Id:");
//                    customerId = UUID.fromString(scanner.next());

                    Concert concert = TicketBookingApp.getInstance().getConcertService().getConcerts(new ConcertSearchParams.Builder().concertId(concertId).build()).stream().findFirst().orElse(null);
                    TicketBookingApp.getInstance().getBookingService().createBooking(concert, TicketBookingApp.getInstance().getCustomerService().getCustomerById(customerId), seatId);
                    break;
                case 3:
                    System.out.println("Cancelling Booking...");
                    // Call the method to cancel a booking

                    System.out.println("Enter booking id:");
                    bookingId = UUID.fromString(scanner.next());
                    TicketBookingApp.getInstance().getBookingService().cancelBooking(bookingId);

                    break;
                case 4:
                    System.out.println("Viewing Bookings...");
                    // Call the method to view bookings
                    TicketBookingApp.getInstance().getBookingService().printBookings(customerId);
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (optionSelected != 5);
    }
}
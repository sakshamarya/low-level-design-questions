public class TicketBookingApp {
    private static TicketBookingApp instance = null;
    private final ConcertService concertService;
    private final BookingService bookingService;
    private final CustomerService customerService;
    private final SeatService seatService;
    private final NotificationFactory notificationFactory;

    private TicketBookingApp(){
        concertService = new ConcertService();
        bookingService = new BookingService();
        customerService = new CustomerService();
        seatService = new SeatService();
        notificationFactory = new NotificationFactory();
    };

    public static TicketBookingApp getInstance() {
        if (instance == null) {
            synchronized (TicketBookingApp.class) {
                if (instance == null) {
                    instance = new TicketBookingApp();
                }
            }
        }
        return instance;
    }

    public ConcertService getConcertService() {
        return concertService;
    }

    public BookingService getBookingService() {
        return bookingService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }
    public SeatService getSeatService() {
        return seatService;
    }
    public NotificationFactory getNotificationFactory() {
        return notificationFactory;
    }
}

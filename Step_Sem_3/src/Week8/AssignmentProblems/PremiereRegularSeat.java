import java.util.*;

interface PremiereSeat {
    String getId();
    double getPrice();
}

class PremiereRegularSeat implements PremiereSeat {
    private String id;

    public PremiereRegularSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 150;
    }
}

class PremierePremiumSeat implements PremiereSeat {
    private String id;

    public PremierePremiumSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 250;
    }
}

class PremiereReclinerSeat implements PremiereSeat {
    private String id;

    public PremiereReclinerSeat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return 400;
    }
}

class PremiereCustomer {
    private String name;

    public PremiereCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class PremiereShow {
    private String time;
    private boolean started;
    private Set<String> bookedSeats = new HashSet<>();

    public PremiereShow(String time) {
        this.time = time;
    }

    public boolean isBooked(String id) {
        return bookedSeats.contains(id);
    }

    public boolean bookSeat(String id) {
        return bookedSeats.add(id);
    }

    public void releaseSeat(String id) {
        bookedSeats.remove(id);
    }

    public void startShow() {
        started = true;
    }

    public boolean hasStarted() {
        return started;
    }
}

class PremiereBooking {
    private PremiereCustomer customer;
    private PremiereShow show;
    private List<PremiereSeat> seats;
    private boolean cancelled;

    public PremiereBooking(PremiereCustomer customer,
                           PremiereShow show,
                           List<PremiereSeat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = seats;
    }

    public double getTotal() {
        double total = 0;

        for (PremiereSeat seat : seats)
            total += seat.getPrice();

        return total;
    }

    public void cancel() {
        if (show.hasStarted()) {
            System.out.println("Cannot cancel: show has already started.");
            return;
        }

        if (cancelled)
            return;

        for (PremiereSeat seat : seats)
            show.releaseSeat(seat.getId());

        cancelled = true;

        System.out.println(customer.getName() +
                "'s booking cancelled.");
        System.out.println("Seats " + getSeatNames() +
                " released.");
    }

    private String getSeatNames() {
        StringBuilder s = new StringBuilder();

        for (PremiereSeat seat : seats) {
            if (s.length() > 0)
                s.append(", ");

            s.append(seat.getId());
        }

        return s.toString();
    }
}

public class CampusPremiereTicketCounter {

    static PremiereBooking book(PremiereCustomer customer,
                                PremiereShow show,
                                PremiereSeat... seats) {

        if (seats.length > 6) {
            System.out.println("Maximum 6 seats allowed.");
            return null;
        }

        for (PremiereSeat seat : seats) {
            if (show.isBooked(seat.getId())) {
                System.out.println("Seat " + seat.getId() +
                        " is already booked for this show.");
                return null;
            }
        }

        List<PremiereSeat> list = Arrays.asList(seats);

        for (PremiereSeat seat : seats)
            show.bookSeat(seat.getId());

        PremiereBooking booking =
                new PremiereBooking(customer, show, list);

        System.out.print("Booking confirmed for " +
                customer.getName() + ": ");

        for (int i = 0; i < seats.length; i++) {
            if (i > 0)
                System.out.print(", ");

            System.out.print(seats[i].getId());
        }

        System.out.printf(". Total: ₹%.2f%n",
                booking.getTotal());

        return booking;
    }

    public static void main(String[] args) {

        PremiereShow show = new PremiereShow("7 PM");

        PremiereCustomer asha =
                new PremiereCustomer("Asha");

        PremiereCustomer ravi =
                new PremiereCustomer("Ravi");

        PremiereCustomer neha =
                new PremiereCustomer("Neha");

        PremiereSeat a1 =
                new PremiereRegularSeat("A1");

        PremiereSeat a2 =
                new PremiereRegularSeat("A2");

        PremiereSeat f5 =
                new PremierePremiumSeat("F5");

        PremiereSeat r1 =
                new PremiereReclinerSeat("R1");

        PremiereBooking b1 =
                book(asha, show, a1, a2, f5);

        book(ravi, show, a2);

        book(ravi, show, r1);

        b1.cancel();

        book(neha, show, a2);
    }
}

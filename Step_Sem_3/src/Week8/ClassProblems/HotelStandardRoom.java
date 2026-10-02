import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

abstract class HotelRoom {
    protected String roomNumber;
    protected List<HotelReservation> reservations = new ArrayList<>();

    public HotelRoom(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public abstract double calculatePrice(long nights);

    public boolean isAvailable(LocalDate start, LocalDate end) {
        for (HotelReservation r : reservations) {
            if (r.isActive() &&
                    start.isBefore(r.getEndDate()) &&
                    end.isAfter(r.getStartDate()))
                return false;
        }
        return true;
    }

    public void addReservation(HotelReservation r) {
        reservations.add(r);
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}

class HotelStandardRoom extends HotelRoom {
    public HotelStandardRoom(String roomNumber) {
        super(roomNumber);
    }

    public double calculatePrice(long nights) {
        return nights * 100;
    }
}

class HotelDeluxeRoom extends HotelRoom {
    public HotelDeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    public double calculatePrice(long nights) {
        return nights * 150;
    }
}

class HotelSuite extends HotelRoom {
    public HotelSuite(String roomNumber) {
        super(roomNumber);
    }

    public double calculatePrice(long nights) {
        return nights * 250;
    }
}

class HotelCustomer {
    private String name;

    public HotelCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class HotelReservation {
    private HotelCustomer customer;
    private HotelRoom room;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate cancellationDeadline;
    private boolean active;

    public HotelReservation(HotelCustomer customer, HotelRoom room,
                            LocalDate startDate, LocalDate endDate,
                            LocalDate cancellationDeadline) {
        this.customer = customer;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cancellationDeadline = cancellationDeadline;
        active = true;
    }

    public double getPrice() {
        long nights = startDate.until(endDate).getDays();
        return room.calculatePrice(nights);
    }

    public boolean cancel(LocalDate today) {
        if (!active || today.isAfter(cancellationDeadline))
            return false;

        active = false;
        return true;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getCustomerName() {
        return customer.getName();
    }

    public String getRoomNumber() {
        return room.getRoomNumber();
    }
}

public class HotelBookingSystem {

    static boolean checkAvailability(HotelRoom room,
                                     LocalDate start,
                                     LocalDate end) {
        boolean available = room.isAvailable(start, end);

        System.out.println(room.getClass().getSimpleName() + " " +
                room.getRoomNumber() +
                (available ? " is available" : " is not available") +
                " from " + start + " to " + end);

        return available;
    }

    static HotelReservation reserve(HotelCustomer customer,
                                    HotelRoom room,
                                    LocalDate start,
                                    LocalDate end,
                                    LocalDate deadline) {
        if (!room.isAvailable(start, end)) {
            System.out.println(room.getRoomNumber() +
                    " is not available.");
            return null;
        }

        HotelReservation r =
                new HotelReservation(customer, room, start, end, deadline);

        room.addReservation(r);

        System.out.println("Reservation confirmed for " +
                customer.getName() + ", " +
                room.getRoomNumber() + " (" +
                start + "-" + end + ").");

        System.out.println("Price: $" + r.getPrice());

        return r;
    }

    static void cancelReservation(HotelReservation r, LocalDate today) {
        if (r.cancel(today))
            System.out.println("Reservation for " +
                    r.getCustomerName() + ", " +
                    r.getRoomNumber() +
                    " cancelled successfully.");
        else
            System.out.println("Cancellation deadline has passed.");
    }
}
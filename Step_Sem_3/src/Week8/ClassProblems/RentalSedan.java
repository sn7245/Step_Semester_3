abstract class RentalVehicle {
    protected String id;
    protected boolean available = true;

    public RentalVehicle(String id) {
        this.id = id;
    }

    public abstract double calculateCharge(int days);

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class RentalSedan extends RentalVehicle {
    public RentalSedan(String id) {
        super(id);
    }

    public double calculateCharge(int days) {
        return days * 50;
    }
}

class RentalSUV extends RentalVehicle {
    public RentalSUV(String id) {
        super(id);
    }

    public double calculateCharge(int days) {
        return days * 80;
    }
}

class RentalTruck extends RentalVehicle {
    public RentalTruck(String id) {
        super(id);
    }

    public double calculateCharge(int days) {
        return days * 100;
    }
}

class RentalCustomer {
    private String name;

    public RentalCustomer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class VehicleRental {
    private RentalVehicle vehicle;
    private RentalCustomer customer;
    private int days;
    private boolean active;

    public VehicleRental(RentalVehicle vehicle, RentalCustomer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        active = true;
        vehicle.setAvailable(false);
    }

    public double getCharge() {
        return vehicle.calculateCharge(days);
    }

    public void returnVehicle() {
        active = false;
        vehicle.setAvailable(true);
    }

    public RentalVehicle getVehicle() {
        return vehicle;
    }

    public RentalCustomer getCustomer() {
        return customer;
    }

    public boolean isActive() {
        return active;
    }
}

public class VehicleRentalSystem {

    static VehicleRental rentVehicle(RentalVehicle vehicle,
                                     RentalCustomer customer,
                                     int days) {
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getId() + " is currently unavailable.");
            return null;
        }

        VehicleRental rental =
                new VehicleRental(vehicle, customer, days);

        System.out.println(vehicle.getId() +
                " rented successfully by " + customer.getName() + ".");
        System.out.println("Rental charge: $" +
                rental.getCharge());

        return rental;
    }

    static void returnVehicle(VehicleRental rental) {
        rental.returnVehicle();

        System.out.println(rental.getVehicle().getId() +
                " returned by " +
                rental.getCustomer().getName() + ".");
    }
}
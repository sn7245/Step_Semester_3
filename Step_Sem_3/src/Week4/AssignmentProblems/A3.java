class ParkingTicket {
    String vehicleNo;
    double ratePerMinute;

    ParkingTicket(String vehicleNo, double ratePerMinute) {
        this.vehicleNo = vehicleNo;
        this.ratePerMinute = ratePerMinute;
    }

    final double calculateFine(int overstayMinutes) {
        return overstayMinutes * ratePerMinute;
    }

    final void printReceipt(int overstayMinutes) {

        if (overstayMinutes > 0) {

            double fine = calculateFine(overstayMinutes);

            System.out.println(
                    vehicleNo + " - Fine: Rs " + fine
            );

        } else {

            System.out.println(
                    vehicleNo +
                            " - No fine, within allotted time"
            );
        }
    }
}

public class A3 {
    public static void main(String[] args) {

        String[] vehicleNos = {
                "TN09AB1234",
                "TN22CD5678",
                "TN09EF9012",
                "TN10GH3456"
        };

        double[] rates = {
                2,
                2,
                3,
                2
        };

        int[] overstayMinutes = {
                15,
                0,
                -5,
                8
        };

        ParkingTicket[] tickets =
                new ParkingTicket[vehicleNos.length];

        for (int i = 0; i < vehicleNos.length; i++) {

            tickets[i] =
                    new ParkingTicket(
                            vehicleNos[i],
                            rates[i]
                    );
        }

        for (int i = 0; i < tickets.length; i++) {

            tickets[i].printReceipt(
                    overstayMinutes[i]
            );
        }
    }
}
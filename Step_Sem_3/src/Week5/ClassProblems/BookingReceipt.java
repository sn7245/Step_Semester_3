class BookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        this.seatNumbers = seatNumbers.clone();
    }

    public String[] getSeatNumbers() {
        return seatNumbers.clone();
    }

    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] seats = seatNumbers.clone();
        seats[index] = newSeat;
        return new BookingReceipt(bookingId, seats);
    }
}

class GroupBookingReceipt extends BookingReceipt {
    private final int groupSize;

    public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }
}

public class NightlySettlement {

    static String processNightlySettlement(BookingReceipt[] receipts) {
        int processed = 0, skipped = 0, group = 0, individual = 0;

        for (BookingReceipt r : receipts) {
            if (r == null) {
                skipped++;
            } else {
                processed++;

                if (r instanceof GroupBookingReceipt)
                    group++;
                else
                    individual++;
            }
        }

        return processed + " processed | " + skipped +
                " null skipped | " + group + " group | " +
                individual + " individual";
    }
}
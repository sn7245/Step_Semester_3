final class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    public String[] getBookIds() {
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] copy = bookIds.clone();
        copy[index] = newId;
        return new LoanReceipt(memberId, copy);
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}

public class CirculationLedger {
    static String branchCode;

    static {
        branchCode = "PT-001";
    }

    static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int skipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt r : receipts) {
            if (r == null) {
                skipped++;
            } else {
                processed++;

                if (r instanceof ReferenceOnlyLoanReceipt)
                    referenceOnly++;
                else
                    regular++;
            }
        }

        return processed + " processed | " + skipped +
                " null skipped | " + referenceOnly +
                " reference-only | " + regular + " regular";
    }
}
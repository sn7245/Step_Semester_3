class FineMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    private int[] fineHistory = new int[10];
    private int fineCount;

    public FineMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4)
            throw new IllegalArgumentException();

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit)
            booksBorrowed++;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    protected void chargeFine(int amount) {
        if (fineCount < fineHistory.length)
            fineHistory[fineCount++] = amount;
    }

    public int[] getFineHistory() {
        int[] copy = new int[fineCount];
        System.arraycopy(fineHistory, 0, copy, 0, fineCount);
        return copy;
    }

    public int getTotalFine() {
        int total = 0;

        for (int i = 0; i < fineCount; i++)
            total += fineHistory[i];

        return total;
    }
}

class FineStudentMember extends FineMember {
    private String course;

    public FineStudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }
}

public class FineLedger {
}
class AuditMember {
    private static int membersEnrolled = 0;

    protected final String memberNumber;
    protected int borrowLimit;
    protected int booksBorrowed;

    public AuditMember(int borrowLimit) {
        membersEnrolled++;
        memberNumber = "LIB-" + (100 + membersEnrolled);
        this.borrowLimit = borrowLimit;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit)
            booksBorrowed++;
    }

    public void borrowBook(String genre) {
        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4)
            return false;

        return code.charAt(0) == 'R' &&
                Character.isDigit(code.charAt(1)) &&
                Character.isDigit(code.charAt(2)) &&
                Character.isUpperCase(code.charAt(3));
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }
}

class AuditFacultyMember extends AuditMember {
    private String department;

    public AuditFacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }
}

public class MembershipAudit {

    static String processNightlyAudit(AuditMember[] members) {
        int processed = 0, skipped = 0;
        int faculty = 0, regular = 0;

        for (AuditMember member : members) {
            if (member == null) {
                skipped++;
            } else {
                processed++;

                if (member instanceof AuditFacultyMember)
                    faculty++;
                else
                    regular++;
            }
        }

        return processed + " processed | " + skipped +
                " null skipped | " + faculty +
                " faculty | " + regular + " regular";
    }
}
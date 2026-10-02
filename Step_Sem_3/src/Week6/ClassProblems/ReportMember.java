class ReportMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public ReportMember(String memberId, int borrowLimit) {
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

    public String displayInfo() {
        return "General | Books: " + booksBorrowed;
    }
}

class ReportStudentMember extends ReportMember {
    protected String course;

    public ReportStudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    public String displayInfo() {
        return "Student | Course: " + course +
                " | Books: " + booksBorrowed;
    }
}

public class WeeklyCirculationReport {

    static String batchPrint(ReportMember[] members) {
        StringBuilder sb = new StringBuilder();

        for (ReportMember member : members) {
            sb.append(member.displayInfo());

            if (member instanceof ReportStudentMember) {
                ReportStudentMember s = (ReportStudentMember) member;
                sb.append(" [Course via downcast: ")
                        .append(s.course)
                        .append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }
}
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

interface PortalAssignment {
    String getTitle();
    int getMaxMarks();
    double applyPenalty(double marks, long lateDays);
}

class PortalCodingAssignment implements PortalAssignment {
    private String title;
    private int maxMarks;
    private LocalDate dueDate;

    public PortalCodingAssignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public double applyPenalty(double marks, long lateDays) {
        return marks * Math.pow(0.90, lateDays);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}

class PortalWrittenAssignment implements PortalAssignment {
    private String title;
    private int maxMarks;
    private LocalDate dueDate;

    public PortalWrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public double applyPenalty(double marks, long lateDays) {
        return marks * Math.pow(0.80, lateDays);
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}

class PortalStudent {
    private String name;

    public PortalStudent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class PortalSubmission {
    private PortalStudent student;
    private PortalAssignment assignment;
    private LocalDate submissionDate;
    private String status;
    private double finalMarks;

    public PortalSubmission(PortalStudent student,
                            PortalAssignment assignment,
                            LocalDate submissionDate) {

        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        status = "Submitted";

        long lateDays = getLateDays();

        if (lateDays == 0)
            System.out.println(student.getName() +
                    "'s submission for '" + assignment.getTitle() +
                    "' received (on time). Status: Submitted.");
        else
            System.out.println(student.getName() +
                    "'s submission for '" + assignment.getTitle() +
                    "' received (" + lateDays +
                    " days late). Status: Submitted.");
    }

    private long getLateDays() {
        LocalDate dueDate;

        if (assignment instanceof PortalCodingAssignment)
            dueDate = ((PortalCodingAssignment) assignment).getDueDate();
        else
            dueDate = ((PortalWrittenAssignment) assignment).getDueDate();

        return Math.max(0,
                ChronoUnit.DAYS.between(dueDate, submissionDate));
    }

    public void grade(double awardedMarks) {
        if (!status.equals("Submitted")) {
            System.out.println("Cannot grade this submission.");
            return;
        }

        long lateDays = getLateDays();
        finalMarks = assignment.applyPenalty(awardedMarks, lateDays);
        status = "Graded";

        if (lateDays == 0) {
            System.out.printf("%s graded: %.0f/%d.%n",
                    student.getName(),
                    finalMarks,
                    assignment.getMaxMarks());
        } else {
            double penalty = 100 * (1 - finalMarks / awardedMarks);

            System.out.printf("%s graded: %.0f/%d after %.0f%% late penalty.%n",
                    student.getName(),
                    finalMarks,
                    assignment.getMaxMarks(),
                    penalty);
        }

        System.out.println("Status: Graded.");
    }

    public void resubmit(LocalDate date) {
        if (status.equals("Graded")) {
            System.out.println("Cannot resubmit: '" +
                    assignment.getTitle() +
                    "' has already been graded.");
        }
    }
}

public class AssignmentSubmissionPortal {

    public static void main(String[] args) {

        PortalStudent asha = new PortalStudent("Asha");
        PortalStudent ravi = new PortalStudent("Ravi");

        PortalAssignment coding =
                new PortalCodingAssignment(
                        "Linked List Lab",
                        50,
                        LocalDate.of(2026, 3, 10));

        PortalAssignment written =
                new PortalWrittenAssignment(
                        "Design Essay",
                        50,
                        LocalDate.of(2026, 3, 12));

        PortalSubmission s1 =
                new PortalSubmission(
                        asha,
                        coding,
                        LocalDate.of(2026, 3, 10));

        PortalSubmission s2 =
                new PortalSubmission(
                        ravi,
                        written,
                        LocalDate.of(2026, 3, 14));

        s1.grade(45);
        s2.grade(40);

        s1.resubmit(LocalDate.of(2026, 3, 11));
    }
}
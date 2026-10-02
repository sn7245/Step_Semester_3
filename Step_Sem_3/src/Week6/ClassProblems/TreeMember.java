class TreeMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public TreeMember(String memberId, int borrowLimit) {
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

    public void displayInfo() {
        System.out.println("General Member | Books Borrowed: " + booksBorrowed);
    }
}

class TreeStudentMember extends TreeMember {
    protected String course;

    public TreeStudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    public void displayInfo() {
        System.out.println("Student Member | Course: " + course +
                " | Books Borrowed: " + booksBorrowed);
    }
}

class TreeHonorsStudentMember extends TreeStudentMember {
    private int bonusLimit;

    public TreeHonorsStudentMember(String memberId, int borrowLimit,
                                   String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public void displayInfo() {
        System.out.println("Honors Student Member | Course: " + course +
                " | Bonus Limit: " + bonusLimit +
                " | Books Borrowed: " + booksBorrowed);
    }
}

class TreeFacultyMember extends TreeMember {
    private String department;

    public TreeFacultyMember(int borrowLimit, String department) {
        super("FAC1", borrowLimit);
        this.department = department;
    }

    public TreeFacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    @Override
    public void displayInfo() {
        System.out.println("Faculty Member | Department: " + department +
                " | Books Borrowed: " + booksBorrowed);
    }
}

public class MembershipTree {

    static String classifyGeneration(TreeMember member) {
        if (member instanceof TreeHonorsStudentMember)
            return "Multilevel descendant (3 generations deep)";

        if (member instanceof TreeFacultyMember)
            return "Hierarchical sibling (independent branch)";

        return "Base or direct student member";
    }

    static int getTotalBooksBorrowed(TreeMember[] members) {
        int total = 0;

        for (TreeMember member : members)
            total += member.getBooksBorrowed();

        return total;
    }
}
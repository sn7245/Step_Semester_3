interface CampusNotificationChannel {
    void send(String student, String notice);
}

class CampusEmailChannel implements CampusNotificationChannel {
    public void send(String student, String notice) {
        System.out.println("[Email → " + student + "] " + notice);
    }
}

class CampusSmsChannel implements CampusNotificationChannel {
    public void send(String student, String notice) {
        System.out.println("[SMS → " + student + "] " + notice);
    }
}

class CampusAppChannel implements CampusNotificationChannel {
    public void send(String student, String notice) {
        System.out.println("[App → " + student + "] " + notice);
    }
}

class CampusStudent {
    private String name;
    private String department;
    private List<CampusNotificationChannel> channels = new ArrayList<>();

    public CampusStudent(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public void addChannel(CampusNotificationChannel channel) {
        channels.add(channel);
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public List<CampusNotificationChannel> getChannels() {
        return channels;
    }
}

class CampusNotice {
    private String title;
    private List<String> departments;

    public CampusNotice(String title, String... departments) {
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException(
                    "Notice title is required.");

        if (departments.length == 0)
            throw new IllegalArgumentException(
                    "At least one target department is required.");

        this.title = title;
        this.departments = Arrays.asList(departments);
    }

    public String getTitle() {
        return title;
    }

    public List<String> getDepartments() {
        return departments;
    }
}

class CampusNoticeBoard {
    private List<CampusStudent> students = new ArrayList<>();

    public void addStudent(CampusStudent student) {
        students.add(student);
    }

    public void post(CampusNotice notice) {
        System.out.println("Notice '" + notice.getTitle() +
                "' posted to " +
                String.join(", ", notice.getDepartments()) + ".");

        for (CampusStudent student : students) {

            if (notice.getDepartments()
                    .contains(student.getDepartment())) {

                for (CampusNotificationChannel channel :
                        student.getChannels()) {

                    channel.send(
                            student.getName(),
                            notice.getTitle());
                }
            }
        }
    }
}

public class CampusNoticeBroadcaster {

    public static void main(String[] args) {

        CampusNoticeBoard board =
                new CampusNoticeBoard();

        CampusStudent asha =
                new CampusStudent("Asha", "CSE");

        CampusStudent ravi =
                new CampusStudent("Ravi", "ECE");

        asha.addChannel(new CampusEmailChannel());
        asha.addChannel(new CampusAppChannel());

        ravi.addChannel(new CampusSmsChannel());

        board.addStudent(asha);
        board.addStudent(ravi);

        board.post(new CampusNotice(
                "Lab Closed Tomorrow", "CSE"));

        board.post(new CampusNotice(
                "Fee Deadline Extended",
                "CSE", "ECE"));

        try {
            board.post(new CampusNotice(
                    "Sports Day"));
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Cannot post notice: " + e.getMessage());
        }
    }
}
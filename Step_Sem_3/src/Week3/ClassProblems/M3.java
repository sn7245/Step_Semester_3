class Course {
    String code;
    String title;
    int credits;
    int labCredits;

    Course(String code, String title, int credits, int labCredits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }

    Course(String code, String title, int credits) {
        this(code, title, credits, 0);
    }

    int totalCredits() {
        return credits + labCredits;
    }
}

public class M3 {
    public static void main(String[] args) {

        Course course1 = new Course(
                "21CSC201J",
                "Data Structures",
                4
        );

        Course course2 = new Course(
                "21CSC205L",
                "DS Lab",
                3,
                1
        );

        System.out.println(
                course1.code + " total credits: " + course1.totalCredits()
        );

        System.out.println(
                course2.code + " total credits: " + course2.totalCredits()
        );
    }
}
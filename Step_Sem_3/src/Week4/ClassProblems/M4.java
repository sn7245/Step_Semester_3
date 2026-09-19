class Student {
    static String collegeName;
    static String academicYear;

    String studentName;

    static {
        collegeName = "SRM Institute of Science and Technology";
        academicYear = "2026-27";

        System.out.println("College info loaded");
    }

    Student(String studentName) {
        this.studentName = studentName;
    }

    void printStudent() {
        System.out.println(
                "Student record created: " + studentName
        );
    }
}

public class M4 {
    public static void main(String[] args) {

        String[] names = {
                "Ravi",
                "Meera",
                "Karthik",
                "Divya",
                "Anitha"
        };

        for (String name : names) {

            Student student = new Student(name);

            student.printStudent();
        }
    }
}
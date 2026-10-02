abstract class ExamQuestion {
    protected int id;
    protected String text;
    protected int marks;

    public ExamQuestion(int id, String text, int marks) {
        this.id = id;
        this.text = text;
        this.marks = marks;
    }

    public abstract boolean evaluate(String answer);

    public int getId() {
        return id;
    }

    public int getMarks() {
        return marks;
    }
}

class ExamMCQ extends ExamQuestion {
    private String correctAnswer;

    public ExamMCQ(int id, String text, int marks, String correctAnswer) {
        super(id, text, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class ExamTrueFalse extends ExamQuestion {
    private boolean correctAnswer;

    public ExamTrueFalse(int id, String text, int marks, boolean correctAnswer) {
        super(id, text, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}

class ExamShortAnswer extends ExamQuestion {
    private String correctAnswer;

    public ExamShortAnswer(int id, String text, int marks, String correctAnswer) {
        super(id, text, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class ExamStudent {
    private String name;

    public ExamStudent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class OnlineExam {
    private String name;
    private ExamQuestion[] questions;

    public OnlineExam(String name, ExamQuestion[] questions) {
        this.name = name;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public ExamQuestion[] getQuestions() {
        return questions;
    }
}

class ExamAttempt {
    private ExamStudent student;
    private OnlineExam exam;
    private String[] answers;
    private boolean submitted;

    public ExamAttempt(ExamStudent student, OnlineExam exam) {
        this.student = student;
        this.exam = exam;
        answers = new String[exam.getQuestions().length];
    }

    public void recordAnswer(int questionIndex, String answer) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }

        answers[questionIndex] = answer;
        System.out.println("Answer recorded for Question " +
                exam.getQuestions()[questionIndex].getId() + ".");
    }

    public void submit() {
        if (submitted)
            return;

        submitted = true;
        System.out.println(exam.getName() + " submitted by " +
                student.getName() + ".");

        calculateResult();
    }

    private void calculateResult() {
        int total = 0;
        int maximum = 0;

        for (int i = 0; i < answers.length; i++) {
            ExamQuestion q = exam.getQuestions()[i];
            maximum += q.getMarks();

            if (q.evaluate(answers[i])) {
                total += q.getMarks();
                System.out.println("Result: Question " + q.getId() +
                        ": Correct (" + q.getMarks() + " points)");
            } else {
                System.out.println("Result: Question " + q.getId() +
                        ": Incorrect (0 points)");
            }
        }

        System.out.println("Total score: " + total + "/" + maximum);
    }
}

public class OnlineExaminationSystem {
    static ExamAttempt startExam(ExamStudent student, OnlineExam exam) {
        System.out.println(exam.getName() +
                " started by " + student.getName() + ".");
        return new ExamAttempt(student, exam);
    }
}
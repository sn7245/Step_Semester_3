interface LaundryWashType {
    String getName();
    int getDuration();
    double getCharge();
}

class LaundryQuickWash implements LaundryWashType {
    public String getName() {
        return "Quick";
    }

    public int getDuration() {
        return 30;
    }

    public double getCharge() {
        return 20;
    }
}

class LaundryNormalWash implements LaundryWashType {
    public String getName() {
        return "Normal";
    }

    public int getDuration() {
        return 45;
    }

    public double getCharge() {
        return 30;
    }
}

class LaundryHeavyWash implements LaundryWashType {
    public String getName() {
        return "Heavy";
    }

    public int getDuration() {
        return 60;
    }

    public double getCharge() {
        return 45;
    }
}

class LaundryStudent {
    private String name;

    public LaundryStudent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class LaundryWashingMachine {
    private String id;
    private boolean busy;

    public LaundryWashingMachine(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isBusy() {
        return busy;
    }

    void startMachine() {
        busy = true;
    }

    void completeMachine() {
        busy = false;
    }
}

class LaundryWashCycle {
    private LaundryStudent student;
    private LaundryWashingMachine machine;
    private LaundryWashType washType;

    public LaundryWashCycle(LaundryStudent student,
                            LaundryWashingMachine machine,
                            LaundryWashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public void start() {
        machine.startMachine();

        System.out.printf("%s wash started on %s for %s (%d min).%n",
                washType.getName(),
                machine.getId(),
                student.getName(),
                washType.getDuration());

        System.out.printf("Charge: ₹%.2f%n", washType.getCharge());
    }

    public void complete() {
        machine.completeMachine();
        System.out.println(machine.getId() + " cycle completed.");
        System.out.println(machine.getId() + " is now free.");
    }
}

public class HostelLaundryQueue {

    static LaundryWashCycle startWash(LaundryStudent student,
                                      LaundryWashingMachine machine,
                                      LaundryWashType type) {

        if (machine.isBusy()) {
            System.out.println(machine.getId() + " is currently busy.");
            return null;
        }

        LaundryWashCycle cycle =
                new LaundryWashCycle(student, machine, type);

        cycle.start();
        return cycle;
    }

    public static void main(String[] args) {

        LaundryStudent asha = new LaundryStudent("Asha");
        LaundryStudent ravi = new LaundryStudent("Ravi");
        LaundryStudent neha = new LaundryStudent("Neha");

        LaundryWashingMachine m1 =
                new LaundryWashingMachine("M1");

        LaundryWashingMachine m2 =
                new LaundryWashingMachine("M2");

        LaundryWashCycle c1 =
                startWash(asha, m1, new LaundryQuickWash());

        startWash(ravi, m1, new LaundryHeavyWash());

        LaundryWashCycle c2 =
                startWash(ravi, m2, new LaundryHeavyWash());

        c1.complete();

        startWash(neha, m1, new LaundryNormalWash());
    }
}
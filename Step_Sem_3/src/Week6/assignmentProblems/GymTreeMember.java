class GymTreeMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymTreeMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().length() < 4)
            throw new IllegalArgumentException();

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    public void attendSession() {
        sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public void displayInfo() {
        System.out.println("Standard Member | Sessions: " + sessionsAttended);
    }
}

class GymTreePremiumMember extends GymTreeMember {
    protected String trainerName;

    public GymTreePremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    public void displayInfo() {
        System.out.println("Premium Member | Trainer: " + trainerName +
                " | Sessions: " + sessionsAttended);
    }
}

class GymTreeEliteMember extends GymTreePremiumMember {
    private String lockerNumber;

    public GymTreeEliteMember(String memberId, int monthlyFee,
                              String trainerName, String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    @Override
    public void displayInfo() {
        System.out.println("Elite Member | Trainer: " + trainerName +
                " | Locker: " + lockerNumber +
                " | Sessions: " + sessionsAttended);
    }
}

class GymTreeGroupClassMember extends GymTreeMember {
    private String className;

    public GymTreeGroupClassMember(String memberId, int monthlyFee,
                                   String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    @Override
    public void displayInfo() {
        System.out.println("Group Class Member | Class: " + className +
                " | Sessions: " + sessionsAttended);
    }
}

public class GymMembershipTree {

    static String classifyGeneration(GymTreeMember member) {
        if (member instanceof GymTreeEliteMember)
            return "Multilevel descendant (3 generations deep)";

        if (member instanceof GymTreeGroupClassMember)
            return "Hierarchical sibling (independent branch)";

        return "Base or premium member";
    }

    static int getTotalSessionsAttended(GymTreeMember[] members) {
        int total = 0;

        for (GymTreeMember member : members)
            total += member.getSessionsAttended();

        return total;
    }
}
class AnnounceGymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public AnnounceGymMember(String memberId, int monthlyFee) {
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

    public String displayInfo() {
        return "Standard | Sessions: " + sessionsAttended;
    }
}

class AnnouncePremiumMember extends AnnounceGymMember {
    private String trainerName;

    public AnnouncePremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium | Trainer: " + trainerName +
                " | Sessions: " + sessionsAttended;
    }

    public String getTrainerName() {
        return trainerName;
    }
}

public class MonthlyAttendanceAnnouncer {

    static String batchPrint(AnnounceGymMember[] members) {
        StringBuilder sb = new StringBuilder();

        for (AnnounceGymMember member : members) {
            sb.append(member.displayInfo());

            if (member instanceof AnnouncePremiumMember) {
                AnnouncePremiumMember p = (AnnouncePremiumMember) member;
                sb.append(" [Trainer via downcast: ")
                        .append(p.getTrainerName())
                        .append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }
}
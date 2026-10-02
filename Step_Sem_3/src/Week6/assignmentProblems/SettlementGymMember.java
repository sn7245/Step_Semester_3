class SettlementGymMember {
    private static int membersEnrolled = 0;

    private final String membershipNumber;
    protected int monthlyFee;
    private int feesPaid;

    public SettlementGymMember(int monthlyFee) {
        membersEnrolled++;
        membershipNumber = "GYM-" + (2000 + membersEnrolled);
        this.monthlyFee = monthlyFee;
    }

    public void payFee(int amount) {
        feesPaid += amount;
    }

    public void payFee(int amount, String mode) {
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4)
            return false;

        return code.charAt(0) == 'G' &&
                Character.isDigit(code.charAt(1)) &&
                Character.isDigit(code.charAt(2)) &&
                Character.isUpperCase(code.charAt(3));
    }

    public static int getMembersEnrolled() {
        return membersEnrolled;
    }
}

class SettlementGroupClassMember extends SettlementGymMember {
    private String className;

    public SettlementGroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }
}

public class WeeklyCheckInSettlement {

    static String processWeeklyCheckIn(SettlementGymMember[] members) {
        int processed = 0, skipped = 0;
        int group = 0, individual = 0;

        for (SettlementGymMember member : members) {
            if (member == null) {
                skipped++;
            } else {
                processed++;

                if (member instanceof SettlementGroupClassMember)
                    group++;
                else
                    individual++;
            }
        }

        return processed + " processed | " + skipped +
                " null skipped | " + group +
                " group | " + individual + " individual";
    }
}
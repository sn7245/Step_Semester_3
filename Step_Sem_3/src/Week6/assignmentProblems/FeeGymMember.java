class FeeGymMember {
    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    private int[] lateFeeHistory = new int[10];
    private int feeCount;

    public FeeGymMember(String memberId, int monthlyFee) {
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

    protected void chargeLateFee(int amount) {
        if (feeCount < lateFeeHistory.length)
            lateFeeHistory[feeCount++] = amount;
    }

    public int[] getLateFeeHistory() {
        int[] copy = new int[feeCount];
        System.arraycopy(lateFeeHistory, 0, copy, 0, feeCount);
        return copy;
    }

    public int getTotalLateFees() {
        int total = 0;

        for (int i = 0; i < feeCount; i++)
            total += lateFeeHistory[i];

        return total;
    }
}

class FeePremiumMember extends FeeGymMember {

    public FeePremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
    }

    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }
}

public class PremiumLoyaltyLedger {
}
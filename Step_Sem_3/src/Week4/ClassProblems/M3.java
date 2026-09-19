class Account {
    String regNo;
    double totalFee;

    Account(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    final double calculateLateFee(int daysLate) {
        if (daysLate <= 0) {
            return 0;
        }

        return daysLate * 500;
    }

    final void printSummary(int daysLate) {

        if (daysLate <= 0) {
            System.out.println(regNo + " - On time, no late fee");
        } else {
            double fee = calculateLateFee(daysLate);

            System.out.println(
                    regNo + " | Total Fee: Rs " +
                            totalFee + " | Late Fee: Rs " + fee
            );
        }
    }
}

public class M3 {
    public static void main(String[] args) {

        String[] regNos = {"RA001", "RA002", "RA003", "RA004"};

        double[] totalFees = {
                200000,
                150000,
                180000,
                220000
        };

        int[] daysLate = {10, 0, -2, 5};

        Account[] accounts = new Account[regNos.length];

        for (int i = 0; i < regNos.length; i++) {
            accounts[i] =
                    new Account(regNos[i], totalFees[i]);
        }

        for (int i = 0; i < accounts.length; i++) {
            accounts[i].printSummary(daysLate[i]);
        }
    }
}
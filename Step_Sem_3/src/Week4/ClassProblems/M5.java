class FeeAccount {

    void pay(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends FeeAccount {

    void payWithProcessingFee(double amount) {
        double total = amount * 1.02;

        System.out.println(
                "Paid in two installments (hostel account)"
        );
    }
}

public class M5 {

    static void processPayment(
            FeeAccount account,
            double amount) {

        if (account instanceof HostelFeeAccount) {

            HostelFeeAccount hostel =
                    (HostelFeeAccount) account;

            hostel.payWithProcessingFee(amount);

        } else {

            account.pay(amount);
        }
    }

    public static void main(String[] args) {

        FeeAccount[] accounts = {
                new HostelFeeAccount(),
                new HostelFeeAccount(),
                new FeeAccount(),
                new FeeAccount()
        };

        int hostelCount = 0;
        int dayScholarCount = 0;

        for (FeeAccount account : accounts) {

            processPayment(account, 60000);

            if (account instanceof HostelFeeAccount) {
                hostelCount++;
            } else {
                dayScholarCount++;
            }
        }

        System.out.println(
                "Hostel accounts processed: " + hostelCount
                        + " | Day-scholar accounts processed: "
                        + dayScholarCount
        );
    }
}
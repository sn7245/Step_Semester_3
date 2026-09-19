class Payment {

    void pay(double amount) {
        System.out.println(
                "Paid (cash): Rs " + amount
        );
    }
}

class CardPayment extends Payment {

    void payWithProcessingFee(double amount) {

        double total = amount * 1.02;

        System.out.println(
                "Charged (card, incl. fee): Rs " + total
        );
    }
}

public class A5 {

    static void processTransaction(
            Payment payment,
            double amount) {

        if (payment instanceof CardPayment) {

            CardPayment card =
                    (CardPayment) payment;

            card.payWithProcessingFee(amount);

        } else {

            payment.pay(amount);
        }
    }

    public static void main(String[] args) {

        Payment[] payments = {
                new CardPayment(),
                new Payment(),
                new CardPayment(),
                new Payment(),
                new CardPayment()
        };

        double[] amounts = {
                100,
                50,
                200,
                75,
                120
        };

        double totalCollected = 0;

        for (int i = 0; i < payments.length; i++) {

            processTransaction(
                    payments[i],
                    amounts[i]
            );

            if (payments[i] instanceof CardPayment) {
                totalCollected += amounts[i] * 1.02;
            } else {
                totalCollected += amounts[i];
            }
        }

        System.out.println(
                "Total Collected: Rs " +
                        totalCollected
        );
    }
}
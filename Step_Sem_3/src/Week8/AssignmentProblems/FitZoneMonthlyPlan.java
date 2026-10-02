interface FitZonePlan {
    String getName();
    double calculateFee();
}

class FitZoneMonthlyPlan implements FitZonePlan {
    public String getName() {
        return "Monthly";
    }

    public double calculateFee() {
        return 1000;
    }
}

class FitZoneQuarterlyPlan implements FitZonePlan {
    public String getName() {
        return "Quarterly";
    }

    public double calculateFee() {
        return 1000 * 3 * 0.90;
    }
}

class FitZoneAnnualPlan implements FitZonePlan {
    public String getName() {
        return "Annual";
    }

    public double calculateFee() {
        return 1000 * 12 * 0.75;
    }
}

class FitZoneMember {
    private String name;

    public FitZoneMember(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class FitZoneMembership {
    private FitZoneMember member;
    private FitZonePlan plan;
    private String status;

    public FitZoneMembership(FitZoneMember member,
                             FitZonePlan plan) {
        this.member = member;
        this.plan = plan;
        status = "Active";

        System.out.printf("%s membership created for %s.%n",
                plan.getName(), member.getName());

        System.out.printf("Fee: ₹%.2f%n", plan.calculateFee());
        System.out.println("Status: Active.");
    }

    public void checkIn() {
        if (status.equals("Active"))
            System.out.println(member.getName() +
                    " checked in successfully.");
        else
            System.out.println("Check-in denied: " +
                    member.getName() +
                    "'s membership is " + status + ".");
    }

    public void freeze() {
        if (status.equals("Expired")) {
            System.out.println("Cannot freeze an Expired membership.");
            return;
        }

        if (status.equals("Frozen")) {
            System.out.println("Membership is already Frozen.");
            return;
        }

        status = "Frozen";

        System.out.println(member.getName() +
                "'s membership frozen.");
        System.out.println("Status: Frozen.");
    }

    public void unfreeze() {
        if (status.equals("Expired")) {
            System.out.println("Cannot unfreeze an Expired membership.");
            return;
        }

        if (status.equals("Active")) {
            System.out.println("Membership is already Active.");
            return;
        }

        status = "Active";

        System.out.println(member.getName() +
                "'s membership unfrozen.");
        System.out.println("Status: Active.");
    }

    public void expire() {
        if (status.equals("Expired"))
            return;

        status = "Expired";

        System.out.println(member.getName() +
                "'s membership expired.");
        System.out.println("Status: Expired.");
    }
}

public class FitZoneMembershipDesk {

    static FitZoneMembership buyMembership(
            FitZoneMember member,
            FitZonePlan plan) {

        return new FitZoneMembership(member, plan);
    }

    public static void main(String[] args) {

        FitZoneMember asha =
                new FitZoneMember("Asha");

        FitZoneMember ravi =
                new FitZoneMember("Ravi");

        FitZoneMembership ashaMembership =
                buyMembership(
                        asha,
                        new FitZoneQuarterlyPlan());

        FitZoneMembership raviMembership =
                buyMembership(
                        ravi,
                        new FitZoneMonthlyPlan());

        ashaMembership.checkIn();

        ashaMembership.freeze();

        ashaMembership.checkIn();

        raviMembership.expire();

        raviMembership.freeze();
    }
}
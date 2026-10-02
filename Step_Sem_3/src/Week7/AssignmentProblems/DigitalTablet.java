abstract class DigitalClassroomDevice {
    public abstract String operate();
}

interface DigitalChargeable {
    String charge();
    String charge(int minutes);
}

class DigitalTablet extends DigitalClassroomDevice implements DigitalChargeable {
    private String assetTag;

    public DigitalTablet(String assetTag) {
        this.assetTag = assetTag;
    }

    @Override
    public String operate() {
        return "Tablet " + assetTag + " displaying lesson";
    }

    @Override
    public String charge() {
        return assetTag + " charging";
    }

    @Override
    public String charge(int minutes) {
        return assetTag + " charging for " + minutes + " minutes";
    }
}

public class DigitalClassroomSetup {
}
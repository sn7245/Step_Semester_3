abstract class CourierDeliveryNote {
    public abstract String confirmDelivery();

    public String confirmDelivery(String signature) {
        return confirmDelivery() + ", signed by " + signature;
    }
}

class CourierParcelNote extends CourierDeliveryNote {
    private String trackingId;

    public CourierParcelNote(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String confirmDelivery() {
        return "Parcel " + trackingId + " delivered";
    }
}

class CourierLetterNote extends CourierDeliveryNote {
    private String trackingId;

    public CourierLetterNote(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String confirmDelivery() {
        return "Letter " + trackingId + " delivered";
    }
}

public class PackageDropOffLog {

    static void logAll(CourierDeliveryNote[] notes) {
        for (CourierDeliveryNote note : notes)
            System.out.println(note.confirmDelivery());
    }
}
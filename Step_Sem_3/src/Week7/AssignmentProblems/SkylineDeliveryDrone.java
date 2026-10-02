abstract class SkylineDrone {
    protected String id;

    public SkylineDrone(String id) {
        this.id = id;
    }

    public abstract String fly();
}

interface SkylineTrackable {
    String getLocation();
}

class SkylineDeliveryDrone extends SkylineDrone implements SkylineTrackable {
    public SkylineDeliveryDrone(String id) {
        super(id);
    }

    @Override
    public String fly() {
        return id + " delivering package";
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}

class SkylineScoutDrone extends SkylineDrone {
    public SkylineScoutDrone(String id) {
        super(id);
    }

    @Override
    public String fly() {
        return id + " scouting area";
    }
}

class SkylineGroundRobot implements SkylineTrackable {
    private String id;

    public SkylineGroundRobot(String id) {
        this.id = id;
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}

public class SkylineDeliveryFleet {
    static String getLocationIfTrackable(Object o) {
        if (o instanceof SkylineTrackable) {
            SkylineTrackable t = (SkylineTrackable) o;
            return t.getLocation();
        }
        return "Tracking not available";
    }
}
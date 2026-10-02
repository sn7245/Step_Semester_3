interface MorningRingable {
    String ring();
}

class MorningAlarmClock implements MorningRingable {
    private String time;

    public MorningAlarmClock(String time) {
        this.time = time;
    }

    @Override
    public String ring() {
        return "Alarm ringing for " + time;
    }
}

class MorningDoorbell implements MorningRingable {
    private String location;

    public MorningDoorbell(String location) {
        this.location = location;
    }

    @Override
    public String ring() {
        return "Doorbell ringing at " + location;
    }
}

public class MorningWakeUpCircuit {

    static void ringAll(MorningRingable[] devices) {
        for (MorningRingable device : devices)
            System.out.println(device.ring());
    }
}
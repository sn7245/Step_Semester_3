abstract class KitchenBaseTool {
    private int speedLevel;

    public abstract String prepare();

    public int getSpeedLevel() {
        return speedLevel;
    }

    public void setSpeedLevel(int speedLevel) {
        if (speedLevel >= 1 && speedLevel <= 5)
            this.speedLevel = speedLevel;
    }
}

interface KitchenWashable {
    String clean();
}

class KitchenBlender extends KitchenBaseTool implements KitchenWashable {

    @Override
    public String prepare() {
        return "Blending at speed " + getSpeedLevel();
    }

    @Override
    public String clean() {
        return "Blender rinsed and dried";
    }
}

public class SmartKitchenAssistant {
}
abstract class ToyBase {
    private static int count = 0;
    private final String toyId;

    public ToyBase() {
        count++;
        toyId = "TOY-" + (1000 + count);
    }

    public abstract String makeSound();

    public String getToyId() {
        return toyId;
    }
}

class ToyBoxCar extends ToyBase {
    private String name;

    public ToyBoxCar(String name) {
        this.name = name;
    }

    @Override
    public String makeSound() {
        return name + ": Vroom vroom!";
    }
}

class ToyBoxRobot extends ToyBase {
    private String name;

    public ToyBoxRobot(String name) {
        this.name = name;
    }

    @Override
    public String makeSound() {
        return name + ": Beep boop!";
    }
}

public class TalkingToyBox {
}
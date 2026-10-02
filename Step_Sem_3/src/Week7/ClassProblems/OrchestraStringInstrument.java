abstract class OrchestraInstrument {
    public OrchestraInstrument() {
    }

    public abstract String play();
}

class OrchestraStringInstrument extends OrchestraInstrument {
    public OrchestraStringInstrument() {
        super();
    }

    @Override
    public String play() {
        return "Strumming the strings";
    }
}

class OrchestraViolin extends OrchestraStringInstrument {
    public OrchestraViolin() {
        super();
    }

    @Override
    public String play() {
        return super.play() + ", with a bow drawn across four strings";
    }
}

public class OrchestraWarmUpRoutine {
}
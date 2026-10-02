abstract class BackyardGardenTool {
    public BackyardGardenTool() {
    }

    public abstract String use();
}

class BackyardCuttingTool extends BackyardGardenTool {
    public BackyardCuttingTool() {
        super();
    }

    @Override
    public String use() {
        return super.use() + ", blade sharpened first";
    }
}

class BackyardPruner extends BackyardCuttingTool {
    public BackyardPruner() {
        super();
    }

    @Override
    public String use() {
        return super.use() + ", then trimming branches precisely";
    }
}

public class BackyardToolshedRoutine {
}
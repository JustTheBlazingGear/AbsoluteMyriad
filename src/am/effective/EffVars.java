package am.effective;

public class EffVars {
    public static EffMenuRenderer menuRenderer;

    public EffVars() {
    }

    public static void load() {
    }

    static {
        menuRenderer = new EffMenuRenderer();
    }
}


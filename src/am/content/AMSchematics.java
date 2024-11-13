package am.content;

import java.io.IOException;
import mindustry.Vars;
import mindustry.game.Schematic;
import mindustry.game.Schematics;

public class AMSchematics {
    public static Schematic coreBoiler;

    public AMSchematics() {
    }

    public static void load() {
        coreBoiler = loadSchem("core-boiler");
    }

    private static Schematic loadSchem(String name) {
        Schematic s;
        try {
            s = Schematics.read(Vars.tree.get("schematics/" + name + ".msch"));
        } catch (IOException var3) {
            IOException e = var3;
            s = null;
            e.printStackTrace();
        }

        return s;
    }

    private static Schematic loadBase64(String b64) {
        return Schematics.readBase64(b64);
    }
}

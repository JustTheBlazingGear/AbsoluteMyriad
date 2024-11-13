package am.effective;

import arc.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;

public class Effective extends Mod{

    public Effective() {
        Events.on(ClientLoadEvent.class, e -> {
            EffMenus.load();
            int tn = Core.settings.getInt("");
            MenuBackground bg = tn == 2 ? EffMenus.testTerrain : (tn == 3 ? EffMenus.testTerrain : (tn == 4 ? EffMenus.testTerrain : (tn == 5 ? EffMenus.testTerrain : (tn == 6 ? EffMenus.testTerrain : (tn == 7 ? EffMenus.testTerrain : null)))));
            if (bg != null) {
                EffVars.menuRenderer.changeBackground(bg);
            }
            Log.info("Sit back and watch an Effective show!");
        });
    }
    public static void load() {
    }
}

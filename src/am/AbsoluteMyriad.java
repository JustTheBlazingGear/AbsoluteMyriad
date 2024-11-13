package am;

import am.content.*;
import am.effective.EffMenus;
import am.effective.EffVars;
import am.effective.Effective;
import am.effective.MenuBackground;
import am.ui.AMStyles;
import am.ui.AMUI;
import arc.*;
import arc.scene.Element;
import arc.scene.Group;
import arc.util.*;
import mindustry.Vars;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class AbsoluteMyriad extends Mod{

    public AbsoluteMyriad() {
        Events.on(ClientLoadEvent.class, e -> {
            EffMenus.load();
            Element menu = ((Element)Reflect.get(Vars.ui.menufrag, "container")).parent.parent;
            Group menuCont = menu.parent;
            menuCont.addChildBefore(menu, new Element() {
                public void draw() {
                    EffVars.menuRenderer.render();
                }
            });
            int tn = Core.settings.getInt("");
            MenuBackground bg = tn == 2 ? EffMenus.testTerrain : (tn == 3 ? EffMenus.testTerrain : (tn == 4 ? EffMenus.testTerrain : (tn == 5 ? EffMenus.testTerrain : (tn == 6 ? EffMenus.testTerrain : (tn == 7 ? EffMenus.testTerrain : null)))));
            if (bg != null) {
                EffVars.menuRenderer.changeBackground(bg);
            }
            Log.info("Sit back and watch an Effective show!");
        });
        Events.on(ClientLoadEvent.class, e -> {
            AMStyles.load();
            AMUI.load();
            //Difficulties.load();
            Effective.load();
            Log.info("Absolute Myriad has been downloaded. Prepare thyself.");
            BaseDialog dialog = new BaseDialog("");
            dialog.cont.image(Core.atlas.find("am-banner")).row();
            dialog.cont.add("@am-startup-window").row();
            dialog.cont.table((t) -> {
                t.button("Ok", dialog::hide).size(100f, 50f);
                t.button(Core.bundle.get("dont-show-this-again"), () -> {
                    Core.settings.put("@am-startup-window-setting", false);
                    dialog.hide();
                }).size(250.0F, 50.0F).pad(10.0F);
            });
            dialog.show();
        });
    }
    public void loadContent() {
        AMTeams.load();
        AMStatusEffects.load();
        AMLiquids.load();
        AMItems.load();
        AMWeathers.load();
        AMUnitTypes.load();
        AMUnitParts.load();
        AMBlocks.load();
        AMSchematics.load();
        AMPlanets.load();
        AsteroidTechTree.load();
        VoltarTechTree.load();

        Events.on(ClientLoadEvent.class, a -> {
        });
    }
}
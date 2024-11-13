package am.ui;

import am.ui.dialogs.DifficultySelectDialog;
import arc.Core;
import arc.scene.ui.layout.Table;
import mindustry.Vars;
import mindustry.gen.Icon;

import java.util.Objects;

public class AMUI{
    public static DifficultySelectDialog DifficultySelection;

    public AMUI() {
    }

    public static void load() {
        DifficultySelection = new DifficultySelectDialog("@am-difficulty-select");

        Vars.ui.settings.addCategory(Core.bundle.get("am-settings"), Icon.eyeSmall, t -> {
            t.checkPref("@am-startup-window-setting", true);
        });

        //Vars.ui.planet.fill(t -> {
        //    t.button("@am-difficulty", Icon.units, DifficultySelection::show).size(210, 64);
        //});

        //Vars.ui.research.buttons.button("@am-difficulty", Icon.units, DifficultySelection::show).size(210, 64);
    }
}
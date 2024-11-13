package am.ui.dialogs;

import am.AMVars;
import am.ui.AMStyles;
import arc.Core;
import arc.scene.style.TextureRegionDrawable;
import mindustry.Vars;
import mindustry.content.Planets;
import mindustry.gen.Icon;
import mindustry.ui.Styles;
import mindustry.ui.dialogs.BaseDialog;

import static am.AMVars.SerpuloDifficulty;

public class DifficultySelectDialog extends BaseDialog {

    public DifficultySelectDialog(String title) {
        super(title);

        if (Vars.ui.planet.state.planet == Planets.serpulo) {
        }

        this.cont.table((t) -> {
            t.add("@am-difficulty-select1").top().row();
            t.add("@am-difficulty-select2").top().row();
            t.add("@am-difficulty-select3").top().row();
        }).top().row();

        this.row();

        this.cont.table((t) -> {
            t.button(new TextureRegionDrawable(Core.atlas.find("am-crux-easy")), AMStyles.difficulties, () -> {
                Core.settings.put("@am-serpulo-difficulty", 1);
            }).size(200, 400).center().padRight(50);
            t.button(new TextureRegionDrawable(Core.atlas.find("am-crux-normal")), AMStyles.difficulties, () -> {
                Core.settings.put("@am-serpulo-difficulty", 2);
            }).size(200, 400).center().padRight(50);
            t.button(new TextureRegionDrawable(Core.atlas.find("am-crux-hard")), AMStyles.SerpuloHard, () -> {
                Core.settings.put("@am-serpulo-difficulty", 3);
            }).size(200, 400).center().padRight(50);
            t.button(new TextureRegionDrawable(Core.atlas.find("am-crux-extreme")), AMStyles.SerpuloExtreme, () -> {
                Core.settings.put("@am-serpulo-difficulty", 4);
            }).size(200, 400).center().padRight(50);
            t.button(new TextureRegionDrawable(Core.atlas.find("am-crux-eradication")), AMStyles.SerpuloEradication, () -> {
                Core.settings.put("@am-serpulo-difficulty", 5);
            }).size(200, 400).center().row();
        }).center().row();

        this.row();

        this.cont.table((t) -> {
            t.label(() -> {
                return SerpuloDifficulty == 0 ? "@am-difficulty-unselected" : "@am-difficulty-title" + SerpuloDifficulty;
            }).labelAlign(1).style(Styles.outlineLabel).wrap().center().pad(10).row();
            t.label(() -> {
                return SerpuloDifficulty == 0 ? "" : "@am-serpulo-difficulty" + SerpuloDifficulty;
            });
        }).bottom().row();

        this.buttons.button("@sectors.select", Icon.ok, this::hide).center().size(210F, 64F).padTop(50F);
    }
}
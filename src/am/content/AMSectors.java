package am.content;

import mindustry.gen.Icon;
import mindustry.type.ItemStack;
import mindustry.type.SectorPreset;

import static mindustry.content.Planets.gier;
import static mindustry.content.Planets.serpulo;

public class AMSectors {
    public static SectorPreset asteroidRuins;

    public AMSectors() {
    }

    public static void load() {
        asteroidRuins = new SectorPreset("asteroid-ruins", gier, 0) {
            {
                this.researchRequirements();
                this.alwaysUnlocked = true;
                this.difficulty = 8.0F;
                this.uiIcon = Icon.commandRally.getRegion();
            }
        };
    }
}

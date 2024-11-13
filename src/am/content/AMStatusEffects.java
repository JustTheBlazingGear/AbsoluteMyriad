package am.content;

import am.graphics.AMPal;
import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.game.Team;
import mindustry.graphics.Pal;
import mindustry.type.StatusEffect;

public class AMStatusEffects {
    public static StatusEffect sulfurCovered;
    public static StatusEffect reinforced;
    public static StatusEffect enhanced;
    public static StatusEffect prismatic;
    public static StatusEffect watcher;
    public static StatusEffect warden;
    public static StatusEffect colossus;
    public static StatusEffect eradicator;

    public static void load() {
        sulfurCovered = new StatusEffect("sulfur-covered") {
            {
                this.color = Color.valueOf("f2d583");
                this.speedMultiplier = 0.9F;
                this.effect = AMFx.sulfurCovered;
                this.init(() -> {
                    this.affinity(StatusEffects.melting, (unit, result, time) -> {
                        result.set(StatusEffects.melting, result.time + time);
                    });
                    this.affinity(StatusEffects.burning, (unit, result, time) -> {
                        result.set(StatusEffects.burning, result.time + time);
                    });
                });
            }
        };
        warden = new StatusEffect("warden") {
            {
                this.color = Team.crux.color;
                this.permanent = true;
                this.damageMultiplier = 1.5F;
                this.healthMultiplier = 1.75F;
            }
            public boolean isHidden() {
                return false;
            }
        };
        reinforced = new StatusEffect("reinforced") {
            {
                this.color = Pal.lightOrange;
                this.outline = true;
                this.permanent = true;
                this.healthMultiplier = 1.5F;
                this.damageMultiplier = 1.5F;
                this.reloadMultiplier = 1.5F;
                this.speedMultiplier = 1.1F;
            }
            public boolean isHidden() {
                return false;
            }
        };
        enhanced = new StatusEffect("enhanced") {
            {
                this.color = AMPal.enhanced;
                this.outline = true;
                this.permanent = true;
                this.healthMultiplier = 2F;
                this.damageMultiplier = 2F;
                this.reloadMultiplier = 2F;
                this.speedMultiplier = 1.2F;
            }
            public boolean isHidden() {
                return false;
            }
        };
        prismatic = new StatusEffect("prismatic") {
            {
                this.color = AMPal.prismatic;
                this.outline = true;
                this.permanent = true;
                this.healthMultiplier = 3F;
                this.damageMultiplier = 3F;
                this.reloadMultiplier = 3F;
                this.speedMultiplier = 1.3F;
            }
            public boolean isHidden() {
                return false;
            }
        };
    }
}

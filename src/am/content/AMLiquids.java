package am.content;

import arc.graphics.Color;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.type.CellLiquid;
import mindustry.type.Liquid;

public class AMLiquids {
    public static Liquid vulcanicWater;
    public static Liquid carbonGas;
    public static Liquid sulfuricAcid;
    public static Liquid scarletine;

    public AMLiquids() {
    }

    public static void load() {
        vulcanicWater = new Liquid("vulcanic-water", Color.valueOf("f98155")) {
            {
                this.temperature = 0.9F;
                this.viscosity = 1F;
                this.effect = StatusEffects.burning;
                this.lightColor = Color.valueOf("f0511d").a(0.4F);
            }
        };
        carbonGas = new Liquid("carbon-gas", Color.valueOf("595a61")) {
            {
                this.gas = true;
            }
        };
        sulfuricAcid = new Liquid("sulfuric-acid", Color.valueOf("cccccc")) {
            {
                this.heatCapacity = 0.9F;
                this.temperature = 0.5F;
                this.viscosity = 0.5F;
                this.effect = StatusEffects.corroded;
                this.lightColor = Color.valueOf("cccccc").a(0.1F);
            }
        };
        scarletine = new Liquid("scarletine", Color.valueOf("f25149")) {
            {
                this.flammability = 0.4F;
                this.viscosity = 0.7F;
            }
        };
    }
}

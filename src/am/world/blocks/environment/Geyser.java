package am.world.blocks.environment;

import am.content.AMFx;
import am.graphics.AMPal;
import am.world.meta.AMAttribute;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.math.geom.Point2;
import arc.util.Time;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.entities.Effect;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

public class Geyser extends Floor {
    public static final Point2[] offsets = new Point2[]{new Point2(0, 0), new Point2(1, 0), new Point2(1, 1), new Point2(0, 1)};
    public Block parent;
    public Effect effect;
    public Color effectColor;
    public float effectSpacing;

    public Geyser(String name) {
        super(name);
        this.parent = Blocks.air;
        this.effect = AMFx.geyser;
        this.effectColor = AMPal.geyser1;
        this.effectSpacing = 300.0F;
        this.variants = 2;
        this.attributes.set(AMAttribute.geyserWater, 1.0F);
    }

    public void drawBase(Tile tile) {
        this.parent.drawBase(tile);
        if (this.checkAdjacent(tile)) {
            Mathf.rand.setSeed((long)tile.pos());
            Draw.rect(this.variantRegions[Mathf.randomSeed((long)tile.pos(), 0, Math.max(0, this.variantRegions.length - 1))], tile.worldx() - 4.0F, tile.worldy() - 5.0F);
        }

    }

    public boolean updateRender(Tile tile) {
        return this.checkAdjacent(tile);
    }

    public void renderUpdate(Floor.UpdateRenderState state) {
        if (state.tile.block() == Blocks.air && (state.data += Time.delta) >= this.effectSpacing) {
            this.effect.at((float)(state.tile.x * 8 - 4), (float)(state.tile.y * 8 - 4), this.effectColor);
            state.data = 0.0F;
        }

    }

    public boolean checkAdjacent(Tile tile) {
        Point2[] var2 = offsets;
        int var3 = var2.length;

        for(int var4 = 1; var4 < var3; ++var4) {
            Point2 point = var2[var4];
            Tile other = Vars.world.tile(tile.x + point.x, tile.y + point.y);
            if (other == null || other.floor() != this) {
                return false;
            }
        }

        return true;
    }

    static {
        Point2[] var0 = offsets;
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            Point2 p = var0[var2];
            p.sub(1, 1);
        }

    }
}

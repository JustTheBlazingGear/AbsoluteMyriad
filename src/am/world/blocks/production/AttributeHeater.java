package am.world.blocks.production;

import arc.Core;
import arc.func.Prov;

import java.util.Objects;
import mindustry.game.Team;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.Tile;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.Stat;

public class AttributeHeater extends HeatProducer{
    public Attribute attribute;
    public float baseEfficiency;
    public float boostScale;
    public float maxBoost;
    public float minEfficiency;
    public float displayEfficiencyScale;
    public boolean displayEfficiency;
    public boolean scaleLiquidConsumption;
    public boolean floating;

    public AttributeHeater(String name) {
        super(name);
        this.attribute = Attribute.heat;
        this.baseEfficiency = 0F;
        this.boostScale = 1.0F;
        this.maxBoost = 10.0F;
        this.minEfficiency = 0F;
        this.displayEfficiencyScale = 1.0F;
        this.displayEfficiency = true;
        this.scaleLiquidConsumption = false;
        this.floating = true;
    }

    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        if (this.displayEfficiency) {
            this.drawPlaceText(Core.bundle.format("bar.efficiency", new Object[]{(int)((this.baseEfficiency + Math.min(this.maxBoost, this.boostScale * this.sumAttribute(this.attribute, x, y))) * 100.0F)}), x, y, valid);
        }
    }

    public float getDisplayedHeatOutput() {
        return this.heatOutput / this.displayEfficiencyScale;
    }

    public void setBars() {
        super.setBars();
        if (this.displayEfficiency) {
            this.addBar("efficiency", (entity) -> {
                Prov var10002 = () -> {
                    return Core.bundle.format("bar.efficiency", new Object[]{(int)(entity.efficiency() * 100.0F * this.displayEfficiencyScale)});
                };
                Prov var10003 = () -> {
                    return Pal.lightOrange;
                };
                Objects.requireNonNull(entity);
                return new Bar(var10002, var10003, entity::efficiency);
            });
        }
    }

    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        return this.baseEfficiency + tile.getLinkedTilesAs(this, tempTiles).sumf((other) -> {
            return other.floor().attributes.get(this.attribute);
        }) >= this.minEfficiency;
    }

    public void setStats() {
        super.setStats();
        this.stats.add(this.baseEfficiency <= 1.0E-4F ? Stat.tiles : Stat.affinities, this.attribute, this.floating, this.boostScale * (float)this.size * (float)this.size, !this.displayEfficiency);
    }

    public class AttributeHeaterBuild extends HeatProducer.HeatProducerBuild {
        public float attrsum;

        public AttributeHeaterBuild() {
            super();
        }

        public float getProgressIncrease(float base) {
            return super.getProgressIncrease(base) * this.efficiencyMultiplier();
        }

        public float efficiencyMultiplier() {
            return AttributeHeater.this.baseEfficiency + Math.min(AttributeHeater.this.maxBoost, AttributeHeater.this.boostScale * this.heat) + AttributeHeater.this.attribute.env();
        }

        public float efficiencyScale() {
            return AttributeHeater.this.scaleLiquidConsumption ? this.efficiencyMultiplier() : super.efficiencyScale();
        }

        public void pickedUp() {
            this.attrsum = 0.0F;
            this.warmup = 0.0F;
        }

        public void onProximityUpdate() {
            super.onProximityUpdate();
            this.heat = AttributeHeater.this.sumAttribute(AttributeHeater.this.attribute, this.tile.x, this.tile.y);
        }
        public float heat() {
            return this.heat * (AttributeHeater.this.baseEfficiency + Math.min(AttributeHeater.this.maxBoost, AttributeHeater.this.boostScale * this.attrsum) + AttributeHeater.this.attribute.env());
        }
    }
}

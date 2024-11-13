package am.content;

import am.world.blocks.environment.Geyser;
import am.world.blocks.environment.GeyserLarge;
import am.world.blocks.production.AttributeHeater;
import am.world.meta.AMAttribute;
import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import arc.math.Mathf;
import arc.struct.Seq;
import mindustry.content.*;
import mindustry.ctype.ContentType;
import mindustry.entities.Effect;
import mindustry.entities.UnitSorts;
import mindustry.entities.effect.RadialEffect;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.Sounds;
import mindustry.graphics.Drawf;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.type.PayloadStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.Turret;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.environment.StaticWall;
import mindustry.world.blocks.heat.HeatConductor;
import mindustry.world.blocks.logic.MessageBlock;
import mindustry.world.blocks.payloads.Constructor;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.units.UnitAssembler;
import mindustry.world.draw.*;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.BuildVisibility;

import static mindustry.content.Items.silicon;

public class AMBlocks {
    public static Block asteroidsIcon;

    public static Block oilSynthesizer;
    public static Block tungstenSeparator;

    public static Block sulfurFloor;
    public static Block sulfurWall;
    public static Block sulfurGeyser;
    public static Block sulfurGeyserLarge;
    public static Block ash;
    public static Block ashWall;
    public static Block gabbro;
    public static Block gabbroWall;
    public static Block basaltWall;

    public static Block basaltDeltaHuge;

    public static Block oxidizedCopperOre;
    public static Block zincOre;
    public static Block silverBasalt;
    public static Block platinumOre;
    public static Block goldBasalt;

    public static Block endOfEverything;

    public static Block encasedDrill;
    public static Block geyserCollector;

    public static Block encasedConveyor;
    public static Block encasedJunction;
    public static Block encasedRouter;
    public static Block encasedOverflowGate;
    public static Block encasedUnderflowGate;

    public static Block encasedNode;
    public static Block hydroThermalTurbine;

    public static Block encasedCopperWall;
    public static Block encasedCopperWallLarge;

    public static Block siliconFurnace;
    public static Block geothermalHeater;
    public static Block heatDuct;
    public static Block litterBurner;

    public static Block basicAssembler;
    public static Block moduleConstructor;

    public static Block encasedMessage;

    public static Block coreBoiler;
    public static Block juniorStorageModule;
    public static Block juniorCommandingModule;
    public static Block juniorBuildingModule;
    public static Block corePalace;
    public static Block coreInferno;

    public static void load() {
        asteroidsIcon = new Wall("asteroids-icon") {
            {
                this.requirements(Category.effect, BuildVisibility.editorOnly, ItemStack.with(new Object[0]));
                this.isHidden();
            }
        };
        oilSynthesizer = new AttributeCrafter("oil-synthesizer") {
            {
                this.requirements(Category.production, ItemStack.with(new Object[]{Items.copper, 150, Items.graphite, 175, Items.lead, 115, Items.tungsten, 75, Items.silicon, 75}));
                this.researchCost = ItemStack.with(new Object[]{Items.copper, 150, Items.graphite, 175, Items.lead, 115, Items.tungsten, 75, Items.silicon, 75});
                this.outputLiquid = new LiquidStack(Liquids.oil, 0.25F);
                this.updateEffect = Fx.pulverize;
                this.updateEffectChance = 0.05F;
                this.size = 3;
                this.liquidCapacity = 30.0F;
                this.attribute = AMAttribute.carbon;
                this.baseEfficiency = 0.0F;
                this.craftTime = 60.0F;
                this.consumeItem(Items.graphite);
                this.consumePower(3.0F);
                this.consumeLiquid(Liquids.water, 0.15F);
                this.drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.oil), new DrawParticles() {
                    {
                        this.color = Color.valueOf("313135");
                        this.alpha = 0.5F;
                        this.particleSize = 3.0F;
                        this.particles = 10;
                        this.particleRad = 9.0F;
                        this.particleLife = 200.0F;
                        this.reverse = true;
                        this.particleSizeInterp = Interp.one;
                    }
                });
            }
        };
        tungstenSeparator = new GenericCrafter("tungsten-separator") {
            {
                this.requirements(Category.crafting, ItemStack.with(new Object[]{Items.lead, 120, Items.beryllium, 80, Items.titanium, 100, Items.silicon, 150}));
                this.researchCost = ItemStack.with(new Object[]{Items.lead, 120, Items.beryllium, 80, Items.titanium, 100, Items.silicon, 150});
                this.outputItem = new ItemStack(Items.tungsten, 2);
                this.hasPower = true;
                this.craftTime = 60.0F;
                this.size = 3;
                this.itemCapacity = 20;
                this.consumePower(4.0F);
                this.consumeItem(Items.scrap, 4);
                this.consumeLiquid(Liquids.slag, 0.15F);
                this.consumeLiquid(Liquids.cryofluid, 0.2F);
                this.drawer = new DrawMulti(new DrawBlock[]{new DrawRegion("-bottom"), new DrawLiquidTile(Liquids.slag), new DrawRegion("-spinner", 3.0F, true), new DrawDefault()});
            }
        };
        sulfurFloor = new Floor("sulfur-floor") {
            {
                this.itemDrop = AMItems.sulfur;
                this.playerUnmineable = true;
                this.variants = 3;
            }
        };
        sulfurWall = new StaticWall("sulfur-wall") {
            {
                AMBlocks.sulfurFloor.asFloor().wall = this;
            }
        };
        sulfurGeyser = new Geyser("sulfur-geyser") {
            {
                this.parent = this.blendGroup = AMBlocks.sulfurFloor;
            }
        };
        sulfurGeyserLarge = new GeyserLarge("sulfur-geyser-large") {
            {
                this.parent = this.blendGroup = AMBlocks.sulfurFloor;
            }
        };
        ash = new Floor("ash") {
            {
                this.variants = 3;
            }
        };
        ashWall = new StaticWall("ash-wall") {
            {
                AMBlocks.ash.asFloor().wall = this;
            }
        };
        gabbro = new Floor("gabbro") {
            {
                this.variants = 4;
            }
        };
        gabbroWall = new StaticWall("gabbro-wall") {
            {
                AMBlocks.gabbro.asFloor().wall = this;
            }
        };
        basaltWall = new StaticWall("basalt-wall") {
            {
                Blocks.basalt.asFloor().wall = this;
            }
        };
        basaltDeltaHuge = new Wall("basalt-delta-huge") {
            {
                this.requirements(Category.defense, BuildVisibility.editorOnly, ItemStack.with(new Object[0]));
                this.customShadow = true;
                this.drawTeamOverlay = true;
                this.scaledHealth = 1000;
                this.size = 3;
                this.isHidden();
                this.instantDeconstruct = false;
                this.destructible = true;
            }
        };
        oxidizedCopperOre = new OreBlock("ore-oxidized-copper", AMItems.oxidizedCopper) {
            {
                this.variants = 3;
            }
        };
        zincOre = new OreBlock("ore-zinc", AMItems.zinc) {
            {
                this.variants = 3;
            }
        };
        silverBasalt = new StaticWall("silver-basalt") {
            {
                this.attributes.set(AMAttribute.silver, 1.0F);
            }
        };
        platinumOre = new OreBlock("ore-platinum", AMItems.platinum) {
            {
                this.variants = 3;
            }
        };
        goldBasalt = new StaticWall("gold-basalt") {
            {
                this.attributes.set(AMAttribute.gold, 1.0F);
            }
        };
        endOfEverything = new ItemTurret("end-of-everything") {
            {
                this.armor = 60.0F;
                this.size = 16;
                this.outlineRadius = 7;
                this.range = 4000.0F;
                this.heatColor = Color.valueOf("FF0000");
                this.unitSort = UnitSorts.strongest;
                this.buildCostMultiplier *= 2.0F;
                this.canOverdrive = true;
                /*
                this.drawer = new DrawTurret() {
                    {
                        this.parts.add(new RegionPart("-side") {
                            {
                                this.under = this.mirror = true;
                                this.layerOffset = -0.1F;
                                this.moveX = 6.0F;
                                this.progress = DrawPart.PartProgress.smoothReload.inv().curve(Interp.pow3Out);
                            }
                        }, new RegionPart("-side-down") {
                            {
                                this.mirror = true;
                                this.layerOffset = -0.5F;
                                this.moveX = 10.0F;
                                this.moveY = 45.0F;
                                this.y = 10.0F;
                                this.progress = PartProgress.smoothReload.inv().curve(Interp.pow3Out);
                            }
                        }, new RegionPart("-side-down") {
                            {
                                this.mirror = true;
                                this.layerOffset = -0.35F;
                                this.moveX = -9.0F;
                                this.moveY = 7.0F;
                                this.y = -2.0F;
                                this.x = 8.0F;
                                this.progress = PartProgress.smoothReload.inv().curve(Interp.pow3Out);
                            }
                        }, new RegionPart("-side-down") {
                            {
                                this.under = this.mirror = true;
                                this.layerOffset = -0.2F;
                                this.moveY = -33.0F;
                                this.y = -33.0F;
                                this.x = 14.0F;
                                this.progress = PartProgress.smoothReload.inv().curve(Interp.pow3Out);
                            }
                        });
                        this.parts.add(new ArcCharge() {
                            {
                                this.progress = PartProgress.smoothReload.inv().curve(Interp.pow5Out);
                                this.color = NHColor.darkEnrColor;
                                this.chargeY = (t) -> {
                                    return -35.0F;
                                };
                                this.shootY = (t) -> {
                                    return 90.0F * this.curve.apply(1.0F - t.smoothReload);
                                };
                            }
                        });
                    }
                };
                */
                this.shoot = new ShootPattern();
                this.inaccuracy = 0.0F;
                this.ammoPerShot = 40;
                this.coolantMultiplier = 0.8F;
                this.rotateSpeed = 0.25F;
                float chargeCircleFrontRad = 12.0F;
                this.shootEffect = new Effect(120.0F, 2000.0F, (e) -> {
                    float scl = 1.0F;
                    if (e.data instanceof Float) {
                        scl *= (Float)e.data;
                    }

                    Draw.color(this.heatColor, Color.white, e.fout() * 0.25F);
                    float rand = Mathf.randomSeed((long)e.id, 60.0F);
                    float extend = Mathf.curve(e.fin(Interp.pow10Out), 0.075F, 1.0F) * scl;
                    float rot = e.fout(Interp.pow10In);
                    int[] var7 = Mathf.signs;
                    int var8 = var7.length;
                    /*
                    int var9;
                    int i;
                    for(var9 = 0; var9 < var8; ++var9) {
                        i = var7[var9];
                        DrawFunc.tri(e.x, e.y, chargeCircleFrontRad * 1.2F * e.foutpowdown() * scl, 200.0F + 500.0F * extend, e.rotation + (90.0F + rand) * rot + (float)(90 * i) - 45.0F);
                    }

                    var7 = Mathf.signs;
                    var8 = var7.length;

                    for(var9 = 0; var9 < var8; ++var9) {
                        i = var7[var9];
                        DrawFunc.tri(e.x, e.y, chargeCircleFrontRad * 1.2F * e.foutpowdown() * scl, 200.0F + 500.0F * extend, e.rotation + (90.0F + rand) * rot + (float)(90 * i) + 45.0F);
                    }
                    */
                });
                this.smokeEffect = new Effect(50.0F, (e) -> {
                    Draw.color(this.heatColor);
                    Lines.stroke(e.fout() * 5.0F);
                    Lines.circle(e.x, e.y, e.fin() * 300.0F);
                    Lines.stroke(e.fout() * 3.0F);
                    Lines.circle(e.x, e.y, e.fin() * 180.0F);
                    Lines.stroke(e.fout() * 3.2F);
                    /*Angles.randLenVectors((long)e.id, 30, 18.0F + 80.0F * e.fin(), (x, y) -> {
                        Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 14.0F + 5.0F);
                    });*/
                    Draw.color(Color.white);
                    Drawf.light(e.x, e.y, e.fout() * 120.0F, this.heatColor, 0.7F);
                });
                this.recoil = 18.0F;
                this.shake = 240.0F;
                this.shootSound = Sounds.laserblast;
                this.health = 1200000;
                this.shootCone = 5.0F;
                this.maxAmmo = 80;
                this.consumePowerCond(8000.0F, Turret.TurretBuild::isActive);
                this.reload = 1800.0F;
                this.ammo(new Object[]{Items.copper, Bullets.fireball});
                this.requirements(Category.turret, BuildVisibility.shown, ItemStack.with(new Object[]{Items.copper, 1}));
            }
        };
        encasedDrill = new Drill("encased-drill") {
            {
                this.requirements(Category.production, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 25, AMItems.zinc, 10}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 10, AMItems.zinc, 5});
                this.drillTime = 350.0F;
                this.size = 3;
                this.hasPower = true;
                this.tier = 2;
                this.updateEffect = Fx.pulverizeMedium;
                this.drillEffect = Fx.mineBig;
                this.consumePower(0.4F);
                this.consumeLiquid(Liquids.water, 0.05F).boost();
            }
        };
        geyserCollector = new SolidPump("geyser-collector") {
                {
                    this.requirements(Category.production, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 15, AMItems.zinc, 5}));
                    this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 10, AMItems.zinc, 5});
                    this.result = AMLiquids.vulcanicWater;
                    this.hasPower = false;
                    this.baseEfficiency = 0F;
                    this.squareSprite = false;
                    this.pumpAmount = 0.2F;
                    this.size = 2;
                    this.liquidCapacity = 30.0F;
                    this.rotateSpeed = 1.2F;
                    this.attribute = AMAttribute.geyserWater;
                    this.placeEffect = Fx.turbinegenerate;
                    this.updateEffectChance = 0.2F;
                    this.ambientSound = Sounds.hum;
                    this.ambientSoundVolume = 0.06F;
                }
            };
        encasedConveyor = new StackConveyor("encased-conveyor") {
            {
                this.requirements(Category.distribution, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 1, AMItems.zinc, 1}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 5, AMItems.zinc, 5});
                this.health = 40;
                this.itemCapacity = 5;
                this.size = 1;
                this.speed = 0.0333334F;
                this.outputRouter = false;
            }
        };
        encasedJunction = new Junction("encased-junction") {
            {
                this.description = Core.bundle.getOrNull("block.junction.description");
                this.requirements(Category.distribution, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 2, AMItems.zinc, 1}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 10, AMItems.zinc, 5});
                this.speed = 30.0F;
                this.capacity = 10;
                this.health = 40;
            }
        };
        encasedRouter = new StackRouter("encased-router") {
            {
                this.requirements(Category.distribution, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 2, AMItems.zinc, 1}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 10, AMItems.zinc, 5});
                this.speed = 40.0F;
                this.health = 40;
                this.regionRotated1 = 1;
                this.solid = false;
            }
        };
        encasedOverflowGate = new OverflowGate("encased-overflow-gate") {
            {
                this.description = Core.bundle.getOrNull("block.overflow-gate.description");
                this.requirements(Category.distribution, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 2, AMItems.zinc, 1}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 10, AMItems.zinc, 5});
            }
        };
        encasedUnderflowGate = new OverflowGate("encased-underflow-gate") {
            {
                this.description = Core.bundle.getOrNull("block.underflow-gate.description");
                this.requirements(Category.distribution, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 2, AMItems.zinc, 1}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 10, AMItems.zinc, 5});
                this.invert = true;
            }
        };
        encasedNode = new BeamNode("encased-node") {
            {
                this.requirements(Category.power, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 5, AMItems.zinc, 5}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 10, AMItems.zinc, 5});
                this.consumesPower = this.outputsPower = true;
                this.health = 90;
                this.range = 7;
                this.fogRadius = 1;
                this.consumePowerBuffered(250.0F);
            }
        };
        hydroThermalTurbine = new HeaterGenerator("hydro-thermal-turbine") {
            {
                this.requirements(Category.power, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 25, AMItems.zinc, 10}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 10, AMItems.zinc, 5});
                this.size = 2;
                this.heatOutput = 3;
                this.liquidCapacity = 40.0F;
                this.consumeLiquid(AMLiquids.vulcanicWater, 0.1F);
                this.powerProduction = 2.0F;
                this.ambientSound = Sounds.hum;
                this.ambientSoundVolume = 0.04F;
                this.placeEffect = new RadialEffect(Fx.smoke, 4, 90.0F, 3.5F);
            }
        };
        encasedCopperWall = new Wall("encased-copper-wall") {
            {
                this.description = Core.bundle.getOrNull("block.copper-wall.description");
                this.requirements(Category.defense, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 6, AMItems.zinc, 2}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 20, AMItems.zinc, 10});
                this.health = 300;
            }
        };
        encasedCopperWallLarge = new Wall("encased-copper-wall-large") {
            {
                this.description = Core.bundle.getOrNull("block.copper-wall.description");
                this.requirements(Category.defense, ItemStack.mult(AMBlocks.encasedCopperWall.requirements, 4.0F));
                this.researchCost = ItemStack.mult(AMBlocks.encasedCopperWall.requirements, 4.0F);
                this.scaledHealth = 300;
                this.size = 2;
            }
        };
        siliconFurnace = new HeatCrafter("silicon-furnace") {
            {
                this.requirements(Category.crafting, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 20, AMItems.zinc, 10});
                this.craftEffect = Fx.smeltsmoke;
                this.outputItem = new ItemStack(Items.silicon, 5);
                this.craftTime = 120.0F;
                this.heatRequirement = 10.0F;
                this.maxEfficiency = 2.0F;
                this.size = 3;
                this.hasPower = true;
                this.hasLiquids = false;
                this.itemCapacity = 20;
                this.drawer = new DrawMulti(new DrawBlock[]{new DrawDefault(), new DrawFlame(Color.valueOf("ffef99"))});
                this.ambientSound = Sounds.smelter;
                this.ambientSoundVolume = 0.07F;
                this.consumeItems(ItemStack.with(new Object[]{AMItems.sulfur, 5, AMItems.quartz, 8}));
                this.consumePower(4.0F);
            }
        };
        geothermalHeater = new AttributeHeater("geothermal-heater") {
            {
                this.requirements(Category.crafting, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 40, AMItems.zinc, 10, Items.silicon, 15}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 100, AMItems.zinc, 50, Items.silicon, 75});
                this.baseEfficiency = 0F;
                this.drawer = new DrawMulti(new DrawBlock[]{new DrawDefault(), new DrawHeatOutput()});
                this.rotateDraw = false;
                this.size = 2;
                this.heatOutput = 2.0F;
                this.regionRotated1 = 1;
                this.ambientSound = Sounds.hum;
                this.itemCapacity = 0;
            }
        };
        heatDuct = new HeatConductor("heat-duct") {
            {
                this.requirements(Category.crafting, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 5, AMItems.zinc, 2}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 5, AMItems.zinc, 5});
                this.group = BlockGroup.heat;
                this.size = 1;
                this.drawer = new DrawMulti(new DrawBlock[]{new DrawDefault(), new DrawHeatOutput(), new DrawHeatInput("-heat")});
                this.regionRotated1 = 1;
            }
        };
        litterBurner = new Incinerator("litter-burner") {
            {
                this.requirements(Category.crafting, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 5, AMItems.zinc, 2}));
                this.health = 90;
                this.consumePower(0.5F);
            }
        };
        basicAssembler = new UnitAssembler("basic-assembler") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 80, AMItems.zinc, 50, silicon, 100}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 80, AMItems.zinc, 50, silicon, 100});
                this.regionSuffix = "-dark";
                this.size = 3;
                this.plans.add(new UnitAssembler.AssemblerUnitPlan(AMUnitTypes.voidSlayerMachineGun, 900.0F, PayloadStack.list(new Object[]{AMUnitParts.basicPlating, 1})));
                this.areaSize = 5;
                this.consumePower(3.0F);
            }
        };
        moduleConstructor = new Constructor("module-constructor") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 60, AMItems.zinc, 30, silicon, 60}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 60, AMItems.zinc, 30, silicon, 60});
                this.regionSuffix = "-dark";
                this.hasPower = true;
                this.buildSpeed = 0.6F;
                this.consumePower(2.0F);
                this.size = 3;
                this.filter = Seq.with(new Block[]{AMUnitParts.basicPlating});
            }
        };
        encasedMessage = new MessageBlock("encased-message") {
            {
                this.description = Core.bundle.getOrNull("block.message.description");
                this.requirements(Category.logic, ItemStack.with(new Object[]{AMItems.zinc, 10, AMItems.silver, 5}));
                this.health = 120;
            }
        };
        coreBoiler = new CoreBlock("core-boiler") {
            {
                this.requirements(Category.effect, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 500, AMItems.zinc, 300, silicon, 400}));
                this.unitType = AMUnitTypes.imp;
                //this.moduleManagerLimit = 1;
                this.isFirstTier = true;
                this.solid = false;
                this.health = 400;
                this.armor = 2.0F;
                this.itemCapacity = 500;
                this.unitCapModifier = 2;
                this.size = 2;
                this.requiresCoreZone = true;
                this.squareSprite = false;
                this.alwaysUnlocked = true;
            }
        };
        /* How I expect the module code to look like
        juniorStorageModule = new StorageCoreModule("junior-storage-module") {
            {
                this.requirements(Category.effect, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 100, AMItems.zinc, 50, silicon, 100}));
                this.researchCost = ItemStack.mult(AMBlocks.juniorStorageModule.requirements, 4.0F);
                this.unitType = AMUnits.chisel;
                this.size = 2;
                this.solid = false;
                this.health = 1200;
                this.armor = 4.0F;
                this.itemCapacity = 150;
                this.squareSprite = false;
            }
        };
        juniorCommandingModule = new CommandingCoreModule("junior-commanding-module") {
            {
                this.requirements(Category.effect, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 100, AMItems.zinc, 50, silicon, 100}));
                this.unitType = AMUnits.healer;
                this.size = 2;
                this.solid = false;
                this.health = 1200;
                this.armor = 4.0F;
                this.unitCapModifier = 4;
                this.squareSprite = false;
            }
        };
        juniorBuildingModule = new BuildingCoreModule("junior-building-module") {
            {
                this.requirements(Category.effect, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 100, AMItems.zinc, 50, silicon, 100}));
                this.unitType = AMUnits.builder;
                this.size = 2;
                this.solid = false;
                this.health = 1200;
                this.armor = 4.0F;
                this.squareSprite = false;
            }
        };*/
        corePalace = new CoreBlock("core-palace") {
            {
                this.requirements(Category.effect, ItemStack.with(new Object[]{silicon, 1000, AMItems.brass, 750, AMItems.platinum, 500}));
                this.unitType = AMUnitTypes.imp;
                //this.moduleManagerLimit = 2;
                this.solid = false;
                this.health = 900;
                this.armor = 4.0F;
                this.itemCapacity = 1000;
                this.unitCapModifier = 4;
                this.size = 3;
                this.requiresCoreZone = true;
                this.squareSprite = false;
            }
        };
        coreInferno = new CoreBlock("core-inferno") {
            {
                this.requirements(Category.effect, ItemStack.with(new Object[]{silicon, 2000, AMItems.brass, 1500, AMItems.platinum, 1000}));
                this.unitType = AMUnitTypes.imp;
                //this.moduleManagerLimit = 3;
                this.solid = false;
                this.health = 1600;
                this.armor = 8.0F;
                this.itemCapacity = 1500;
                this.unitCapModifier = 8;
                this.size = 4;
                this.requiresCoreZone = true;
                this.squareSprite = false;
            }
        };
    }
}
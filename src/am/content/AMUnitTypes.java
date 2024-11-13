package am.content;

import am.graphics.AMPal;
import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.part.HaloPart;
import mindustry.gen.MechUnit;
import mindustry.gen.Sounds;
import mindustry.gen.UnitEntity;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import am.type.VoltarUnitType;
import mindustry.type.weapons.BuildWeapon;
import mindustry.type.weapons.RepairBeamWeapon;

public class AMUnitTypes {
    public static UnitType imp;
    public static UnitType voidSlayerMachineGun;

    public AMUnitTypes() {
    }

    public static void load() {
        imp = new VoltarUnitType("imp") {
            {
                this.constructor = MechUnit::create;
                this.coreUnitDock = true;
                this.mechFrontSway = 0.1F;
                this.mechSideSway = 0.3F;
                this.flying = false;
                this.canBoost = false;
                this.range = 60.0F;
                this.faceTarget = true;
                this.mineWalls = true;
                this.mineFloor = true;
                this.mineHardnessScaling = false;
                this.mineSpeed = 5.0F;
                this.mineTier = 2;
                this.buildSpeed = 0.5F;
                this.speed = 1F;
                this.rotateSpeed = 5.0F;
                this.itemCapacity = 50;
                this.health = 300.0F;
                this.armor = 4.0F;
                this.hitSize = 8.0F;
                this.fogRadius = 20F;
                this.rotateToBuilding = false;
                this.buildRange = 150F;
                this.mineRange = 100F;
                this.buildBeamOffset = 2F;
                this.drawBuildBeam = false;
                this.weapons.add(new Weapon("am-imp-cannon") {
                    {
                        this.reload = 15F;
                        this.mirror = true;
                        this.top = false;
                        this.x = 7F;
                        this.y = 0F;
                        this.alternate = true;
                        this.shootSound = Sounds.artillery;
                        this.ejectEffect = Fx.casing1;
                        this.recoil = 0.7F;
                        this.bullet = new BasicBulletType() {
                            {
                                this.damage = 20.0F;
                                this.speed = 3.0F;
                                this.lifetime = 50F;
                                this.width = this.height = 12.0F;
                            }
                        };
                    }
                });
                this.weapons.add(new BuildWeapon("am-imp-build-weapon") {
                    {
                        this.mirror = false;
                        this.top = true;
                        this.x = 4F;
                        this.y = -2F;
                        this.rotate = true;
                        this.rotateSpeed = 9;
                        this.targetInterval = 1;
                        this.targetSwitchInterval = 1;
                    }
                });
                this.weapons.add(new RepairBeamWeapon("build-weapon") {
                    {
                        this.mirror = false;
                        this.top = true;
                        this.rotate = true;
                        this.rotateSpeed = 9;
                        this.x = -4F;
                        this.y = -2F;
                        this.shootY = 4F;
                        this.beamWidth = 0.5F;
                        this.repairSpeed = 0.5F;
                        this.targetInterval = 1;
                        this.targetSwitchInterval = 1;
                        this.reload = 20;
                        this.targetUnits = false;
                        this.targetBuildings = true;
                        this.controllable = false;
                        this.laserColor = Color.valueOf("ffd37f");
                        this.healColor = Color.valueOf("ffd37f");
                        this.bullet = new BasicBulletType() {
                            {
                                this.maxRange = 80F;
                            }
                        };
                    }
                });
            }
        };
        voidSlayerMachineGun = new UnitType("void-slayer-machinegun") {
            {
                this.outlineColor = Color.valueOf("2a2933");
                this.hitSize = 32.0F;
                this.health = 100000.0F;
                this.speed = 10F;
                this.rotateSpeed = 10F;
                this.drag = this.accel = 0.04F;
                this.flying = true;
                this.constructor = UnitEntity::create;
                this.itemCapacity = 0;
                this.engineColor = Color.valueOf("bf92f9");
                this.engineOffset = 22.0F;
                this.engineSize = 8.0F;
                this.trailLength = 10;
                this.fogRadius = 80F;
                this.trailColor = Color.valueOf("bf92f9");
                this.weapons.add(new Weapon("am-void-slayer-machinegun-weapon") {
                    {
                        this.reload = 3.0F;
                        this.mirror = true;
                        this.top = false;
                        this.x = this.y = 0F;
                        this.alternate = true;
                        this.shootSound = Sounds.shootBig;
                        this.ejectEffect = Fx.casing3;
                        this.recoil = 0.7F;
                        this.shootY = 4;
                        this.shootX = 2;
                        this.bullet = new BasicBulletType() {
                            {
                                this.damage = 250.0F;
                                this.lifetime = 30.0F;
                                this.speed = 16.0F;
                                this.inaccuracy = 1F;
                                this.width = this.height = 10F;
                                this.keepVelocity = false;
                            }
                        };
                    }
                });
                this.parts.add(new HaloPart() {
                    {
                        this.shapes = 2;
                        this.layer = 101.0F;
                        this.x = 0.0F;
                        this.y = 0.0F;
                        this.color = AMPal.voidLight;
                        this.hollow = true;
                        this.sides = 4;
                        this.stroke = 0.0F;
                        this.strokeTo = 5F;
                        this.radiusTo = 60.0F;
                        this.rotateSpeed = 5.2F;
                        this.progress = PartProgress.life;
                    }
                });
            }
        };
    }
}
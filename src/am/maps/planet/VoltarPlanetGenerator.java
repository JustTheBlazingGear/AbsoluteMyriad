package am.maps.planet;

import am.content.AMBlocks;
import am.content.AMSchematics;
import arc.graphics.Color;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.math.geom.Vec2;
import arc.math.geom.Vec3;
import arc.util.Tmp;
import arc.util.noise.Ridged;
import arc.util.noise.Simplex;
import java.util.Iterator;
import mindustry.Vars;
import mindustry.ai.Astar;
import mindustry.content.Blocks;
import mindustry.content.Loadouts;
import mindustry.game.Schematics;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.type.Sector;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.TileGen;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.SteamVent;
import mindustry.world.blocks.environment.TallBlock;
import mindustry.world.meta.Attribute;

public class VoltarPlanetGenerator extends PlanetGenerator {
    public float heightScl = 0.9F;
    public float octaves = 8.0F;
    public float persistence = 0.7F;
    public float heightPow = 3.0F;
    public float heightMult = 1.6F;
    public static float arkThresh = 0.28F;
    public static float arkScl = 0.83F;
    public static int arkSeed = 7;
    public static int arkOct = 2;
    public static float liqThresh = 0.64F;
    public static float liqScl = 87.0F;
    public static float redThresh = 3.1F;
    public static float noArkThresh = 0.3F;
    public static int crystalSeed = 8;
    public static int crystalOct = 2;
    public static float crystalScl = 0.9F;
    public static float crystalMag = 0.3F;
    public static float airThresh = 0.13F;
    public static float airScl = 14.0F;
    Block[] terrain;

    public VoltarPlanetGenerator() {
        this.terrain = new Block[]{Blocks.dacite, Blocks.basalt, AMBlocks.ash, AMBlocks.sulfurFloor, AMBlocks.gabbro};
        this.baseSeed = 2;
        this.defaultLoadout = AMSchematics.coreBoiler;
    }

    public void generateSector(Sector sector) {
    }

    public float getHeight(Vec3 position) {
        return Mathf.pow(this.rawHeight(position), this.heightPow) * this.heightMult;
    }

    public Color getColor(Vec3 position) {
        Block block = this.getBlock(position);
        if (block == Blocks.crystallineStone) {
            block = Blocks.crystalFloor;
        }

        return Tmp.c1.set(block.mapColor).a(1.0F - block.albedo);
    }

    public float getSizeScl() {
        return 2568.0F;
    }

    float rawHeight(Vec3 position) {
        return Simplex.noise3d(this.seed, (double)this.octaves, (double)this.persistence, (double)(1.0F / this.heightScl), (double)(10.0F + position.x), (double)(10.0F + position.y), (double)(10.0F + position.z));
    }

    float rawTemp(Vec3 position) {
        return position.dst(0.0F, 0.0F, 1.0F) * 2.2F - Simplex.noise3d(this.seed, 8.0, 0.5400000214576721, 1.399999976158142, (double)(10.0F + position.x), (double)(10.0F + position.y), (double)(10.0F + position.z)) * 2.9F;
    }

    Block getBlock(Vec3 position) {
        float ice = this.rawTemp(position);
        Tmp.v32.set(position);
        float height = this.rawHeight(position);
        Tmp.v31.set(position);
        height *= 1.2F;
        height = Mathf.clamp(height);
        Block result = this.terrain[Mathf.clamp((int)(height * (float)this.terrain.length), 0, this.terrain.length - 1)];
        if ((double)ice < 0.3 + (double)(Math.abs(Ridged.noise3d(this.seed + crystalSeed, (double)(position.x + 4.0F), (double)(position.y + 8.0F), (double)(position.z + 1.0F), crystalOct, crystalScl)) * crystalMag)) {
            return Blocks.crystallineStone;
        } else if (!((double)ice < 0.6) || result != Blocks.rhyolite && result != Blocks.yellowStone && result != Blocks.regolith) {
            position = Tmp.v32;
            if (ice < redThresh - noArkThresh && Ridged.noise3d(this.seed + arkSeed, (double)(position.x + 2.0F), (double)(position.y + 8.0F), (double)(position.z + 1.0F), arkOct, arkScl) > arkThresh) {
                result = Blocks.beryllicStone;
            }

            if (ice > redThresh) {
                result = Blocks.redStone;
            } else if (ice > redThresh - 0.4F) {
                result = Blocks.regolith;
            }

            return result;
        } else {
            return Blocks.carbonStone;
        }
    }

    public void genTile(Vec3 position, TileGen tile) {
        tile.floor = this.getBlock(position);
        if (tile.floor == Blocks.rhyolite && this.rand.chance(0.01)) {
            tile.floor = Blocks.rhyoliteCrater;
        }

        tile.block = tile.floor.asFloor().wall;
        if (Ridged.noise3d(this.seed + 1, (double)position.x, (double)position.y, (double)position.z, 2, airScl) > airThresh) {
            tile.block = Blocks.air;
        }

        if ((double)Ridged.noise3d(this.seed + 2, (double)position.x, (double)(position.y + 4.0F), (double)position.z, 3, 6.0F) > 0.6) {
            tile.floor = Blocks.carbonStone;
        }

    }

    protected void generate() {
        float temp = this.rawTemp(this.sector.tile.v);
        if ((double)temp > 0.7) {
            this.pass((xx, yx) -> {
                if (this.floor != Blocks.redIce) {
                    float noise = this.noise((float)(xx + 782), (float)yx, 7.0, 0.800000011920929, 280.0, 1.0);
                    if (noise > 0.62F) {
                        if (noise > 0.635F) {
                            this.floor = Blocks.slag;
                        } else {
                            this.floor = Blocks.yellowStone;
                        }

                        this.ore = Blocks.air;
                    }

                    if (noise > 0.55F && this.floor == Blocks.beryllicStone) {
                        this.floor = Blocks.yellowStone;
                    }
                }

            });
        }

        this.cells(4);
        this.pass((xx, yx) -> {
            if (this.floor == Blocks.regolith && this.noise((float)xx, (float)yx, 3.0, 0.4000000059604645, 13.0, 1.0) > 0.59F) {
                this.block = Blocks.regolithWall;
            }

        });
        float length = (float)this.width / 2.6F;
        Vec2 trns = Tmp.v1.trns(this.rand.random(360.0F), length);
        int spawnX = (int)(trns.x + (float)this.width / 2.0F);
        int spawnY = (int)(trns.y + (float)this.height / 2.0F);
        int endX = (int)(-trns.x + (float)this.width / 2.0F);
        int endY = (int)(-trns.y + (float)this.height / 2.0F);
        float maxd = Mathf.dst((float)this.width / 2.0F, (float)this.height / 2.0F);
        this.erase(spawnX, spawnY, 15);
        this.brush(this.pathfind(spawnX, spawnY, endX, endY, (tilex) -> {
            return (tilex.solid() ? 300.0F : 0.0F) + maxd - tilex.dst((float)this.width / 2.0F, (float)this.height / 2.0F) / 10.0F;
        }, Astar.manhattan), 9);
        this.erase(endX, endY, 15);
        this.pass((xx, yx) -> {
            if (this.floor == Blocks.beryllicStone) {
                if (Math.abs(this.noise((float)xx, (float)yx + 500.0F, 5.0, 0.6000000238418579, 40.0, 1.0) - 0.5F) < 0.09F) {
                    this.floor = Blocks.arkyicStone;
                }

                if (!this.nearWall(xx, yx)) {
                    float noise = this.noise((float)(xx + 300), (float)yx - (float)xx * 1.6F + 100.0F, 4.0, 0.800000011920929, (double)liqScl, 1.0);
                    if (noise > liqThresh) {
                        this.floor = Blocks.arkyciteFloor;
                    }

                }
            }
        });
        this.median(2, 0.6, Blocks.arkyciteFloor);
        this.blend(Blocks.arkyciteFloor, Blocks.arkyicStone, 4.0F);
        this.blend(Blocks.slag, Blocks.yellowStonePlates, 4.0F);
        this.distort(10.0F, 12.0F);
        this.distort(5.0F, 7.0F);
        this.median(2, 0.6, Blocks.arkyciteFloor);
        this.median(3, 0.6, Blocks.slag);
        this.pass((xx, yx) -> {
            if (this.noise((float)xx, (float)(yx + 600 + xx), 5.0, 0.8600000143051147, 60.0, 1.0) < 0.41F && this.floor == Blocks.rhyolite) {
                this.floor = Blocks.roughRhyolite;
            }

            if (this.floor == Blocks.slag && Mathf.within((float)xx, (float)yx, (float)spawnX, (float)spawnY, 30.0F + this.noise((float)xx, (float)yx, 2.0, 0.800000011920929, 9.0, 15.0))) {
                this.floor = Blocks.yellowStonePlates;
            }

            if ((this.floor == Blocks.arkyciteFloor || this.floor == Blocks.arkyicStone) && this.block.isStatic()) {
                this.block = Blocks.arkyicWall;
            }

            float max = 0.0F;
            Point2[] var6 = Geometry.d8;
            int var7 = var6.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Point2 p = var6[var8];
                max = Math.max(max, Vars.world.getDarkness(xx + p.x, yx + p.y));
            }

            if (max > 0.0F) {
                this.block = this.floor.asFloor().wall;
                if (this.block == Blocks.air) {
                    this.block = Blocks.yellowStoneWall;
                }
            }

            if (this.floor == Blocks.yellowStonePlates && this.noise((float)(xx + 78 + yx), (float)yx, 3.0, 0.800000011920929, 6.0, 1.0) > 0.44F) {
                this.floor = Blocks.yellowStone;
            }

            if (this.floor == Blocks.redStone && this.noise((float)(xx + 78 - yx), (float)yx, 4.0, 0.7300000190734863, 19.0, 1.0) > 0.63F) {
                this.floor = Blocks.denseRedStone;
            }

        });
        this.inverseFloodFill(this.tiles.getn(spawnX, spawnY));
        this.blend(Blocks.redStoneWall, Blocks.denseRedStone, 4.0F);
        this.erase(endX, endY, 6);
        this.tiles.getn(endX, endY).setOverlay(Blocks.spawn);
        this.pass((xx, yx) -> {
            if (this.block != Blocks.air) {
                if (this.nearAir(xx, yx)) {
                    if (this.block == Blocks.carbonWall && this.noise((float)(xx + 78), (float)yx, 4.0, 0.699999988079071, 33.0, 1.0) > 0.52F) {
                        this.block = Blocks.graphiticWall;
                    } else if (this.block != Blocks.carbonWall && this.noise((float)(xx + 782), (float)yx, 4.0, 0.800000011920929, 38.0, 1.0) > 0.665F) {
                        this.ore = Blocks.wallOreBeryllium;
                    }
                }
            } else if (!this.nearWall(xx, yx)) {
                if (this.noise((float)(xx + 150), (float)(yx + xx * 2 + 100), 4.0, 0.800000011920929, 55.0, 1.0) > 0.76F) {
                    this.ore = AMBlocks.oxidizedCopperOre;
                }

                if (this.noise((float)(xx + 999), (float)(yx + 600 - xx), 4.0, 0.6299999952316284, 45.0, 1.0) < 0.27F) {
                    this.ore = AMBlocks.zincOre;
                }
            }

            if (this.noise((float)(xx + 999), (float)(yx + 600 - xx), 5.0, 0.800000011920929, 45.0, 1.0) < 0.44F && this.floor == Blocks.crystallineStone) {
                this.floor = Blocks.crystalFloor;
            }

            if (this.block == Blocks.air && (this.floor == Blocks.crystallineStone || this.floor == Blocks.crystalFloor) && this.rand.chance(0.09) && this.nearWall(xx, yx) && !this.near(xx, yx, 4, Blocks.crystalCluster) && !this.near(xx, yx, 4, Blocks.vibrantCrystalCluster)) {
                this.block = this.floor == Blocks.crystalFloor ? Blocks.vibrantCrystalCluster : Blocks.crystalCluster;
                this.ore = Blocks.air;
            }

            if (this.block == Blocks.arkyicWall && this.rand.chance(0.23) && this.nearAir(xx, yx) && !this.near(xx, yx, 3, Blocks.crystalOrbs)) {
                this.block = Blocks.crystalOrbs;
                this.ore = Blocks.air;
            }

            if (this.block == Blocks.regolithWall && this.rand.chance(0.3) && this.nearAir(xx, yx) && !this.near(xx, yx, 3, Blocks.crystalBlocks)) {
                this.block = Blocks.crystalBlocks;
                this.ore = Blocks.air;
            }

        });
        this.pass((xx, yx) -> {
            if (this.ore.asFloor().wallOre || this.block.itemDrop != null || this.block == Blocks.air && this.ore != Blocks.air) {
                this.removeWall(xx, yx, 3, (b) -> {
                    return b instanceof TallBlock;
                });
            }

        });
        this.trimDark();
        int minVents = this.rand.random(6, 9);
        int ventCount = 0;
        Iterator var11 = this.tiles.iterator();

        while(true) {
            label218:
            while(true) {
                Tile tile;
                Floor floor;
                int x;
                int y;
                do {
                    do {
                        if (!var11.hasNext()) {
                            int iterations = 0;
                            int maxIterations = 5;

                            Iterator var30;
                            label185:
                            while(ventCount < minVents && iterations++ < maxIterations) {
                                var30 = this.tiles.iterator();

                                while(true) {
                                    label152:
                                    while(true) {
                                        do {
                                            do {
                                                do {
                                                    do {
                                                        if (!var30.hasNext()) {
                                                            continue label185;
                                                        }

                                                        tile = (Tile)var30.next();
                                                    } while(!this.rand.chance(1.8E-4 * (double)(1 + iterations)));
                                                } while(Mathf.within((float)tile.x, (float)tile.y, (float)spawnX, (float)spawnY, 5.0F));
                                            } while(tile.floor() == Blocks.crystallineStone);
                                        } while(tile.floor() == Blocks.crystalFloor);

                                        int radius = 1;

                                        for(x = -radius; x <= radius; ++x) {
                                            for(y = -radius; y <= radius; ++y) {
                                                Tile other = this.tiles.get(x + tile.x, y + tile.y);
                                                if (other == null || other.block().solid || other.floor().attributes.get(Attribute.steam) != 0.0F || other.floor() == Blocks.slag || other.floor() == Blocks.arkyciteFloor) {
                                                    continue label152;
                                                }
                                            }
                                        }

                                        Block firstFloor = Blocks.rhyolite;
                                        Block secondFloor = Blocks.rhyoliteCrater;
                                        Block vent = Blocks.rhyoliteVent;
                                        int xDir = 1;
                                        if (tile.floor() != Blocks.beryllicStone && tile.floor() != Blocks.arkyicStone) {
                                            if (tile.floor() != Blocks.yellowStone && tile.floor() != Blocks.yellowStonePlates && tile.floor() != Blocks.regolith) {
                                                if (tile.floor() != Blocks.redStone && tile.floor() != Blocks.denseRedStone) {
                                                    if (tile.floor() == Blocks.carbonStone) {
                                                        firstFloor = secondFloor = Blocks.carbonStone;
                                                        vent = Blocks.carbonVent;
                                                    }
                                                } else {
                                                    firstFloor = Blocks.denseRedStone;
                                                    secondFloor = Blocks.redStone;
                                                    vent = Blocks.redStoneVent;
                                                    xDir = -1;
                                                }
                                            } else {
                                                firstFloor = Blocks.yellowStone;
                                                secondFloor = Blocks.yellowStonePlates;
                                                vent = Blocks.yellowStoneVent;
                                            }
                                        } else {
                                            firstFloor = secondFloor = Blocks.arkyicStone;
                                            vent = Blocks.arkyicVent;
                                        }

                                        ++ventCount;
                                        Point2[] var20 = SteamVent.offsets;
                                        int crad2 = var20.length;

                                        int cx;
                                        for(cx = 0; cx < crad2; ++cx) {
                                            Point2 pos = var20[cx];
                                            Tile other = this.tiles.get(pos.x + tile.x + 1, pos.y + tile.y + 1);
                                            other.setFloor(vent.asFloor());
                                        }

                                        int crad = this.rand.random(6, 14);
                                        crad2 = crad * crad;

                                        for(cx = -crad; cx <= crad; ++cx) {
                                            for(int cy = -crad; cy <= crad; ++cy) {
                                                int rx = cx + tile.x;
                                                int ry = cy + tile.y;
                                                float rcy = (float)cy + (float)cx * 0.9F;
                                                if ((float)(cx * cx) + rcy * rcy <= (float)crad2 - this.noise((float)rx, (float)ry + (float)rx * 2.0F * (float)xDir, 2.0, 0.699999988079071, 8.0, (double)((float)crad2 * 1.1F))) {
                                                    Tile dest = this.tiles.get(rx, ry);
                                                    if (dest != null && dest.floor().attributes.get(Attribute.steam) == 0.0F && dest.floor() != Blocks.roughRhyolite && dest.floor() != Blocks.arkyciteFloor && dest.floor() != Blocks.slag) {
                                                        dest.setFloor(this.rand.chance(0.08) ? secondFloor.asFloor() : firstFloor.asFloor());
                                                        if (dest.block().isStatic()) {
                                                            dest.setBlock(firstFloor.asFloor().wall);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            var30 = this.tiles.iterator();

                            while(var30.hasNext()) {
                                tile = (Tile)var30.next();
                                if (tile.overlay().needsSurface && !tile.floor().hasSurface()) {
                                    tile.setOverlay(Blocks.air);
                                }
                            }

                            this.decoration(0.017F);
                            Vars.state.rules.env = this.sector.planet.defaultEnv;
                            Vars.state.rules.placeRangeCheck = true;
                            Schematics.placeLaunchLoadout(spawnX, spawnY);
                            Vars.state.rules.waves = true;
                            Vars.state.rules.showSpawns = true;
                            return;
                        }

                        tile = (Tile)var11.next();
                        floor = tile.floor();
                    } while(floor != Blocks.rhyolite && floor != Blocks.roughRhyolite);
                } while(!this.rand.chance(0.002));

                int radius = 2;

                for(int x1 = -radius; x1 <= radius; ++x) {
                    for(x = -radius; x <= radius; ++x) {
                        Tile other = this.tiles.get(x + tile.x, x + tile.y);
                        if (other == null || other.floor() != Blocks.rhyolite && other.floor() != Blocks.roughRhyolite || other.block().solid) {
                            continue label218;
                        }
                    }
                }

                ++ventCount;
                Point2[] var32 = SteamVent.offsets;
                x = var32.length;

                for(y = 0; y < x; ++y) {
                    Point2 pos = var32[y];
                    Tile other = this.tiles.get(pos.x + tile.x + 1, pos.y + tile.y + 1);
                    other.setFloor(Blocks.rhyoliteVent.asFloor());
                }
            }
        }
    }
}

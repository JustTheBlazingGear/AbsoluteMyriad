package am.world.blocks.power;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.struct.Seq;
import java.util.Arrays;
import mindustry.Vars;
import mindustry.core.Renderer;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.input.Placement;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.power.BeamNode;
import mindustry.world.meta.BlockStatus;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.world.blocks.power.PowerGraph;
import mindustry.world.blocks.power.PowerBlock;
import mindustry.world.blocks.power.PowerNode;

public class DiagonalBeamNode extends PowerBlock {
    public int range = 5;
    public TextureRegion laser;
    public TextureRegion laserEnd;
    public Color laserColor1;
    public Color laserColor2;
    public float pulseScl;
    public float pulseMag;
    public float laserWidth;

    public DiagonalBeamNode(String name) {
        super(name);
        this.laserColor1 = Color.white;
        this.laserColor2 = Color.valueOf("ffd9c2");
        this.pulseScl = 7.0F;
        this.pulseMag = 0.05F;
        this.laserWidth = 0.4F;
        this.consumesPower = this.outputsPower = false;
        this.drawDisabled = false;
        this.envEnabled |= 2;
        this.allowDiagonal = false;
        this.underBullets = true;
        this.priority = -1.0F;
    }

    public void setBars() {
        super.setBars();
        this.addBar("power", PowerNode.makePowerBalance());
        this.addBar("batteries", PowerNode.makeBatteryBalance());
    }

    public void setStats() {
        super.setStats();
        this.stats.add(Stat.powerRange, (float)this.range, StatUnit.blocks);
    }

    public void init() {
        super.init();
        this.updateClipRadius((float)((this.range + 1) * 8));
    }

    public void drawPlace(int x, int y, int rotation, boolean valid) {
        for(int i = 0; i < 4; ++i) {
            int maxLen = this.range + this.size / 2;
            Building dest = null;
            Point2 dir = Geometry.d4[i];
            int dx = dir.x;
            int dy = dir.y;
            int offset = this.size / 2;

            for(int j = 1 + offset; j <= this.range + offset; ++j) {
                Building other = Vars.world.build(x + j * dir.x, y + j * dir.y);
                if (other != null && other.isInsulated()) {
                    break;
                }

                if (other != null && other.block.hasPower && other.team == Vars.player.team() && !(other.block instanceof PowerNode)) {
                    maxLen = j;
                    dest = other;
                    break;
                }
            }

            Drawf.dashLine(Pal.placing, (float)(x * 8) + (float)dx * ((float)(8 * this.size) / 2.0F + 2.0F), (float)(y * 8) + (float)dy * ((float)(8 * this.size) / 2.0F + 2.0F), (float)(x * 8 + dx * maxLen * 8), (float)(y * 8 + dy * maxLen * 8));
            if (dest != null) {
                Drawf.square(dest.x, dest.y, (float)(dest.block.size * 8) / 2.0F + 2.5F, 0.0F);
            }
        }

    }

    public void changePlacementPath(Seq<Point2> points, int rotation, boolean diagonal) {
        if (!diagonal) {
            Placement.calculateNodes(points, this, rotation, (point, other) -> {
                return Math.max(Math.abs(point.x - other.x), Math.abs(point.y - other.y)) <= this.range;
            });
        }

    }

    public class DiagonalBeamNodeBuild extends Building {
        public Building[] links = new Building[4];
        public Tile[] dests = new Tile[4];
        public int lastChange = -2;

        public DiagonalBeamNodeBuild() {
        }

        public void updateTile() {
            if (this.lastChange != Vars.world.tileChanges) {
                this.lastChange = Vars.world.tileChanges;
                this.updateDirections();
            }

        }

        public BlockStatus status() {
            float balance = this.power.graph.getPowerBalance();
            if (balance > 0.0F) {
                return BlockStatus.active;
            } else {
                return balance < 0.0F && this.power.graph.getLastPowerStored() > 0.0F ? BlockStatus.noOutput : BlockStatus.noInput;
            }
        }

        public void draw() {
            super.draw();
            if (!Mathf.zero(Renderer.laserOpacity)) {
                Draw.z(70.0F);
                Draw.color(DiagonalBeamNode.this.laserColor1, DiagonalBeamNode.this.laserColor2, (1.0F - this.power.graph.getSatisfaction()) * 0.86F + Mathf.absin(3.0F, 0.1F));
                Draw.alpha(Renderer.laserOpacity);
                float w = DiagonalBeamNode.this.laserWidth + Mathf.absin(DiagonalBeamNode.this.pulseScl, DiagonalBeamNode.this.pulseMag);

                for(int i = 0; i < 4; ++i) {
                    if (this.dests[i] != null && this.links[i].wasVisible) {
                        Block var4 = this.links[i].block;
                        if (var4 instanceof DiagonalBeamNode) {
                            DiagonalBeamNode node = (DiagonalBeamNode)var4;
                            if ((this.links[i].tileX() == this.tileX() || this.links[i].tileY() == this.tileY()) && (this.links[i].id <= this.id || DiagonalBeamNode.this.range < node.range) && DiagonalBeamNode.this.range <= node.range) {
                                continue;
                            }
                        }

                        int dst = Math.max(Math.abs(this.dests[i].x - this.tile.x), Math.abs(this.dests[i].y - this.tile.y));
                        if (dst > 1 + DiagonalBeamNode.this.size / 2) {
                            Point2 point = Geometry.d4[i];
                            float poff = 4.0F;
                            Drawf.laser(DiagonalBeamNode.this.laser, DiagonalBeamNode.this.laserEnd, this.x + poff * (float) DiagonalBeamNode.this.size * (float)point.x, this.y + poff * (float) DiagonalBeamNode.this.size * (float)point.y, this.dests[i].worldx() - poff * (float)point.x, this.dests[i].worldy() - poff * (float)point.y, w);
                        }
                    }
                }

                Draw.reset();
            }
        }

        public void pickedUp() {
            Arrays.fill(this.links, (Object)null);
            Arrays.fill(this.dests, (Object)null);
        }

        public void updateDirections() {
            for(int i = 0; i < 4; ++i) {
                Building prev = this.links[i];
                Point2 dir = Geometry.d4[i];
                this.links[i] = null;
                this.dests[i] = null;
                int offset = DiagonalBeamNode.this.size / 2;

                for(int j = 1 + offset; j <= DiagonalBeamNode.this.range + offset; ++j) {
                    Building other = Vars.world.build(this.tile.x + j * dir.x, this.tile.y + j * dir.y);
                    if (other != null && other.isInsulated()) {
                        break;
                    }

                    if (other != null && other.block.hasPower && other.block.connectedPower && other.team == this.team && !(other.block instanceof PowerNode)) {
                        this.links[i] = other;
                        this.dests[i] = Vars.world.tile(this.tile.x + j * dir.x, this.tile.y + j * dir.y);
                        break;
                    }
                }

                Building next = this.links[i];
                if (next != prev) {
                    if (prev != null && prev.isAdded()) {
                        prev.power.links.removeValue(this.pos());
                        this.power.links.removeValue(prev.pos());
                        PowerGraph newgraph = new PowerGraph();
                        newgraph.reflow(this);
                        if (prev.power.graph != newgraph) {
                            PowerGraph og = new PowerGraph();
                            og.reflow(prev);
                        }
                    }

                    if (next != null) {
                        this.power.links.addUnique(next.pos());
                        next.power.links.addUnique(this.pos());
                        this.power.graph.addGraph(next.power.graph);
                    }
                }
            }

        }
    }
}

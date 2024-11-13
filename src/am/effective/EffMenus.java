package am.effective;

import arc.math.Mathf;
import arc.struct.Seq;
import arc.util.noise.Simplex;
import mindustry.content.Blocks;
import mindustry.game.Team;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.Tiles;

public class EffMenus {
    public static MenuBackground background;
    public static MenuBackground testTerrain;

    public EffMenus() {
    }

    public static void load() {
        testTerrain = new TerrainMenuBackground() {
            public void generate(Tiles tiles) {
                Seq<Block> ores = Seq.with(new Block[]{Blocks.oreCopper});
                int offset = Mathf.floor((float)(Math.random() * 100000.0));
                int s2 = offset + 1;
                int s3 = offset + 2;
                Block[][] blocks = new Block[][]{{Blocks.stone, Blocks.stoneWall}};
                Block[][] blocks2 = new Block[][]{{Blocks.stone, Blocks.stoneWall}};
                Block[] selected = blocks[Mathf.floor((float)(Math.random() * (double)blocks.length))];
                Block[] selected2 = blocks2[Mathf.floor((float)(Math.random() * (double)blocks2.length))];
                Block ore1 = (Block)ores.get(Mathf.floor((float)(Math.random() * (double)ores.size)));
                ores.remove(ore1);
                Block ore2 = (Block)ores.get(Mathf.floor((float)(Math.random() * (double)ores.size)));
                double tr1 = (double)Mathf.random(0.65F, 0.85F);
                double tr2 = (double)Mathf.random(0.65F, 0.85F);
                Block floord = selected[0];
                Block walld = selected[1];
                Block floord2 = selected2[0];
                Block walld2 = selected2[1];

                for(int x = 0; x < this.width; ++x) {
                    for(int y = 0; y < this.height; ++y) {
                        Block floor = floord;
                        Block ore = Blocks.air;
                        Block wall = Blocks.air;
                        if ((double)Simplex.noise2d(offset, 3.0, 0.5, 0.05, (double)x, (double)y) > 0.5) {
                            wall = walld;
                        }

                        if ((double)Simplex.noise2d(s3, 3.0, 0.5, 0.05, (double)x, (double)y) > 0.5) {
                            floor = floord2;
                            if (wall != Blocks.air) {
                                wall = walld2;
                            }
                        }

                        if ((double)Simplex.noise2d(s2, 3.0, 0.3, 0.03333333333333333, (double)x, (double)y) > tr1) {
                            ore = ore1;
                        }

                        if ((double)Simplex.noise2d(s2, 2.0, 0.2, 0.06666666666666667, (double)x, (double)(y + 99999)) > tr2) {
                            ore = ore2;
                        }

                        this.setTile(x, y, floor, wall, ore, tiles);
                    }
                }

                Tile center = tiles.get(tiles.width / 2, tiles.height / 2);
                center.setBlock(Blocks.coreShard, Team.sharded);
            }
        };
    }
    public void changeBackground(MenuBackground background) {
        this.background = background;
        if (background != null) {
            background.generateWorld();
        }

    }

    public void render() {
        if (this.background != null) {
            this.background.render();
        }

    }
}


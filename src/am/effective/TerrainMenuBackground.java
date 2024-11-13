package am.effective;

import arc.Core;
import arc.graphics.Camera;
import arc.graphics.Color;
import arc.graphics.Texture;
import arc.graphics.g2d.Batch;
import arc.graphics.g2d.CacheBatch;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.SpriteCache;
import arc.graphics.gl.FrameBuffer;
import arc.math.Mat;
import arc.math.Mathf;
import arc.scene.ui.layout.Scl;
import java.util.Iterator;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.game.Team;
import mindustry.world.Block;
import mindustry.world.CachedTile;
import mindustry.world.Tile;
import mindustry.world.Tiles;

public class TerrainMenuBackground extends MenuBackground {
    protected final int width;
    protected final int height;
    protected int seed;
    private FrameBuffer shadows;
    private CacheBatch batch;
    private int cacheFloor;
    private int cacheWall;
    private int cacheBuild;
    private final Camera camera;
    private final Mat mat;

    public TerrainMenuBackground() {
        this.width = !Vars.mobile ? 100 : 60;
        this.height = !Vars.mobile ? 50 : 40;
        this.camera = new Camera();
        this.mat = new Mat();
    }

    public void generateWorld() {
        this.seed = Mathf.rand.nextInt();
        Vars.world.beginMapLoad();
        Vars.world.tiles = new Tiles(this.width, this.height);
        this.generate(Vars.world.tiles);
        Vars.world.endMapLoad();
        this.cache();
    }

    private void cache() {
        this.shadows = new FrameBuffer(this.width, this.height);
        Draw.proj().setOrtho(0.0F, 0.0F, (float)this.shadows.getWidth(), (float)this.shadows.getHeight());
        this.shadows.begin(Color.clear);
        Draw.color(Color.black);
        Iterator var1 = Vars.world.tiles.iterator();

        while(var1.hasNext()) {
            Tile tile = (Tile)var1.next();
            if (tile.block() != Blocks.air) {
                Fill.rect((float)tile.x + 0.5F, (float)tile.y + 0.5F, 1.0F, 1.0F);
            }
        }

        Draw.color();
        this.shadows.end();
        Batch prev = Core.batch;
        Core.batch = this.batch = new CacheBatch(new SpriteCache(this.width * this.height * 6, false));
        this.batch.beginCache();
        Iterator var5 = Vars.world.tiles.iterator();

        Tile tile;
        while(var5.hasNext()) {
            tile = (Tile)var5.next();
            tile.floor().drawBase(tile);
        }

        var5 = Vars.world.tiles.iterator();

        while(var5.hasNext()) {
            tile = (Tile)var5.next();
            tile.overlay().drawBase(tile);
        }

        this.cacheFloor = this.batch.endCache();
        this.batch.beginCache();
        var5 = Vars.world.tiles.iterator();

        while(var5.hasNext()) {
            tile = (Tile)var5.next();
            tile.block().drawBase(tile);
        }

        this.cacheWall = this.batch.endCache();
        this.batch.beginCache();
        var5 = Vars.world.tiles.iterator();

        while(var5.hasNext()) {
            tile = (Tile)var5.next();
            if (tile.block().hasBuilding()) {
                tile.build.init(tile.build.tile(), Team.sharded, true, 0).draw();
            }
        }

        this.cacheBuild = this.batch.endCache();
        Core.batch = prev;
    }

    protected void setTile(int x, int y, Block floor, Block block, Block overlay, Tiles tiles) {
        CachedTile tile;
        tiles.set(x, y, tile = new CachedTile());
        tile.x = (short)x;
        tile.y = (short)y;
        ((Tile)tile).setFloor(floor.asFloor());
        ((Tile)tile).setBlock(block);
        ((Tile)tile).setOverlay(overlay);
    }

    public void render() {
        float scaling = Math.max(Scl.scl(4.0F), Math.max((float)Core.graphics.getWidth() / (((float)this.width - 1.0F) * 8.0F), (float)Core.graphics.getHeight() / (((float)this.height - 1.0F) * 8.0F)));
        this.camera.position.set((float)(this.width * 8) / 2.0F, (float)(this.height * 8) / 2.0F);
        this.camera.resize((float)Core.graphics.getWidth() / scaling, (float)Core.graphics.getHeight() / scaling);
        this.mat.set(Draw.proj());
        Draw.flush();
        Draw.proj(this.camera);
        this.batch.setProjection(this.camera.mat);
        this.batch.beginDraw();
        this.batch.drawCache(this.cacheFloor);
        this.batch.endDraw();
        Draw.color();
        Draw.rect(Draw.wrap((Texture)this.shadows.getTexture()), (float)(this.width * 8) / 2.0F - 4.0F, (float)(this.height * 8) / 2.0F - 4.0F, (float)(this.width * 8), (float)(-this.height * 8));
        Draw.flush();
        this.batch.beginDraw();
        this.batch.drawCache(this.cacheWall);
        this.batch.endDraw();
        this.batch.beginDraw();
        this.batch.drawCache(this.cacheBuild);
        this.batch.endDraw();
        Draw.proj(this.mat);
        Draw.color(0.0F, 0.0F, 0.0F, 0.3F);
        Fill.crect(0.0F, 0.0F, (float)Core.graphics.getWidth(), (float)Core.graphics.getHeight());
        Draw.color();
    }
}


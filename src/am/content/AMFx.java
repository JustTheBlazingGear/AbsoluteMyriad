package am.content;

import am.graphics.AMPal;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Angles;
import arc.math.Rand;
import arc.math.geom.Vec2;
import mindustry.entities.Effect;

public class AMFx {
    public static final Rand rand = new Rand();
    public static final Vec2 v = new Vec2();
    public static final Effect none = new Effect(0.0F, 0.0F, (e) -> {
    });
    public static final Effect geyser = (new Effect(180.0F, (e) -> {
        Draw.color(e.color, AMPal.geyser1, e.fin());
        Draw.alpha(e.fslope() * 0.6F);
        float length = 2.0F + e.finpow() * 10.0F;
        rand.setSeed((long)e.id);

        for(int i = 0; i < rand.random(20, 25); ++i) {
            v.trns(rand.random(360.0F), rand.random(length));
            Fill.circle(e.x + v.x, e.y + v.y, rand.random(0.3F, 1F) + e.fslope() * 2F);
        }

    })).layer(79.0F);
    public static final Effect geyserLarge = (new Effect(240.0F, (e) -> {
        Draw.color(e.color, AMPal.geyser1, e.fin());
        Draw.alpha(e.fslope() * 0.6F);
        float length = 8.0F + e.finpow() * 10.0F;
        rand.setSeed((long)e.id);

        for(int i = 0; i < rand.random(60, 75); ++i) {
            v.trns(rand.random(360.0F), rand.random(length));
            Fill.circle(e.x + v.x, e.y + v.y, rand.random(1F, 2.4F) + e.fslope() * 2F);
        }
    })).layer(79.0F);
    public static final Effect sulfurCovered = new Effect(120.0F, (e) -> {
        Draw.color(Color.valueOf("f2d583"));
        Angles.randLenVectors((long)e.id, 2, 1.0F + e.fin() * 2.0F, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout());
        });
    });
}


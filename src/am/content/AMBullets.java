package am.content;

import arc.graphics.Color;
import arc.math.Interp;
import mindustry.content.Bullets;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Sounds;

public class AMBullets {
    public static BulletType endOfEverything;

    public static void load() {
        /*
        endOfEverything = new BasicBulletType(10.0F, 1000.0F) {
            {
                this.drawSize = 1200.0F;
                this.width = this.height = this.shrinkX = this.shrinkY = 0.0F;
                this.collides = false;
                this.despawnHit = false;
                this.collidesAir = this.collidesGround = this.collidesTiles = true;
                this.splashDamage = 4000.0F;
                /*
                this.velocityBegin = 6.0F;
                this.velocityIncrease = -5.9F;
                this.accelerateEnd = 0.75F;
                this.accelerateBegin = 0.1F;
                this.accelInterp = Interp.pow2;

                this.trailInterp = Interp.pow10Out;
                this.despawnSound = Sounds.plasmaboom;
                this.hitSound = Sounds.explosionbig;
                this.hitShake = 60.0F;
                this.despawnShake = 100.0F;
                this.lightning = 12;
                this.lightningDamage = 2000.0F;
                this.lightningLength = 50;
                this.lightningLengthRand = 80;
                this.status = StatusEffects.sapped;
                this.statusDuration = 1200.0F;
                this.fragBullets = 1;
                this.fragBullet = Bullets.fireball;
                this.fragVelocityMin = 0.4F;
                this.fragVelocityMax = 0.6F;
                this.fragLifeMin = 0.5F;
                this.fragLifeMax = 0.7F;
                this.trailWidth = 12.0F;
                this.trailLength = 120;
                this.ammoMultiplier = 1.0F;
                this.hittable = false;
                this.scaleLife = true;
                this.splashDamageRadius = 400.0F;
                this.hitColor = this.lightColor = this.lightningColor = this.trailColor = Color.valueOf("FF0000");
                Effect effect = NHFx.crossBlast(this.hitColor, 420.0F, 45.0F);
                effect.lifetime += 180.0F;
                this.despawnEffect = NHFx.circleOut(this.hitColor, this.splashDamageRadius);
                this.hitEffect = new OptionalMultiEffect(new Effect[]{NHFx.blast(this.hitColor, 200.0F), (new Effect(180.0F, 600.0F, (e) -> {
                    float rad = 120.0F;
                    float f = (e.fin(Interp.pow10Out) + 8.0F) / 9.0F * Mathf.curve(Interp.slowFast.apply(e.fout(0.75F)), 0.0F, 0.85F);
                    Draw.alpha(0.9F * e.foutpowdown());
                    Draw.color(Color.white, e.color, e.fin() + 0.6F);
                    Fill.circle(e.x, e.y, rad * f);
                    e.scaled(45.0F, (i) -> {
                        Lines.stroke(7.0F * i.fout());
                        Lines.circle(i.x, i.y, rad * 3.0F * i.finpowdown());
                        Lines.circle(i.x, i.y, rad * 2.0F * i.finpowdown());
                    });
                    Draw.color(Color.white);
                    Fill.circle(e.x, e.y, rad * f * 0.75F);
                    Drawf.light(e.x, e.y, rad * f * 2.0F, Draw.getColor(), 0.7F);
                })).layer(110.001F), effect, new Effect(260.0F, 460.0F, (e) -> {
                    Draw.blend(Blending.additive);
                    Draw.z(114.2F);
                    float radius = e.fin(Interp.pow3Out) * 230.0F;
                    Fill.light(e.x, e.y, Lines.circleVertices(radius), radius, Color.clear, Tmp.c1.set(NHColor.darkEnrColor).a(e.fout(Interp.pow10Out)));
                    Draw.blend();
                })});
            }

            public void draw(Bullet b) {
                super.draw(b);
                Draw.color(NHColor.darkEnrColor, Color.white, b.fout() * 0.25F);
                float rand = Mathf.randomSeed((long)b.id, 60.0F);
                float extend = Mathf.curve(b.fin(Interp.pow10Out), 0.075F, 1.0F);
                float rot = b.fout(Interp.pow10In);
                float chargeCircleFrontRad = 20.0F;
                float width = chargeCircleFrontRad * 1.2F;
                Fill.circle(b.x, b.y, width * (b.fout() + 4.0F) / 3.5F);
                float rotAngle = b.fdata;
                int[] var8 = Mathf.signs;
                int var9 = var8.length;

                int var10;
                int i;
                for(var10 = 0; var10 < var9; ++var10) {
                    i = var8[var10];
                    DrawFunc.tri(b.x, b.y, width * b.foutpowdown(), 200.0F + 570.0F * extend, rotAngle + (float)(90 * i) - 45.0F);
                }

                var8 = Mathf.signs;
                var9 = var8.length;

                for(var10 = 0; var10 < var9; ++var10) {
                    i = var8[var10];
                    DrawFunc.tri(b.x, b.y, width * b.foutpowdown(), 200.0F + 570.0F * extend, rotAngle + (float)(90 * i) + 45.0F);
                }

                float cameraFin;
                if (NHSetting.enableDetails()) {
                    cameraFin = (1.0F + 2.0F * DrawFunc.cameraDstScl(b.x, b.y, Vars.mobile ? 200.0F : 320.0F)) / 3.0F;
                    float triWidth = b.fout() * chargeCircleFrontRad * cameraFin;
                    int[] var16 = Mathf.signs;
                    i = var16.length;

                    for(int var12 = 0; var12 < i; ++var12) {
                        int ix = var16[var12];
                        Fill.tri(b.x, b.y + triWidth, b.x, b.y - triWidth, b.x + (float)ix * cameraFin * chargeCircleFrontRad * (23.0F + Mathf.absin(10.0F, 0.75F)) * (b.fout() * 1.25F + 1.0F), b.y);
                    }
                }

                cameraFin = this.splashDamageRadius * b.fin(Interp.pow5Out) * Interp.circleOut.apply(b.fout(0.15F));
                Lines.stroke(8.0F * b.fin(Interp.pow2Out));
                Lines.circle(b.x, b.y, cameraFin);
                Draw.color(Color.white);
                Fill.circle(b.x, b.y, width * (b.fout() + 4.0F) / 5.5F);
                Drawf.light(b.x, b.y, cameraFin, this.hitColor, 0.5F);
            }

            public void init(Bullet b) {
                super.init(b);
                b.fdata = Mathf.randomSeed((long)b.id, 90.0F);
            }

            public void update(Bullet b) {
                super.update(b);
                b.fdata += b.vel.len() / 3.0F;
            }

            public void despawned(Bullet b) {
                super.despawned(b);
                float rad = 120.0F;
                float spacing = 3.0F;
                Angles.randLenVectors((long)b.id, 8, this.splashDamageRadius / 1.25F, (x, y) -> {
                    float nowX = b.x + x;
                    float nowY = b.y + y;
                    Vec2 vec2 = new Vec2(nowX, nowY);
                    Team team = b.team;
                    float mul = b.damageMultiplier();
                    Time.run(Mathf.random(6.0F, 24.0F) + Mathf.sqrt(x * x + y * y) / this.splashDamageRadius * 3.0F, () -> {
                        if (Mathf.chanceDelta(0.4000000059604645)) {
                            this.hitSound.at(vec2.x, vec2.y, this.hitSoundPitch, this.hitSoundVolume);
                        }

                        this.despawnSound.at(vec2);
                        Effect.shake(this.hitShake, this.hitShake, vec2);

                        int i;
                        for(i = 0; i < this.lightning / 2; ++i) {
                            Lightning.create(team, this.lightningColor, this.lightningDamage, vec2.x, vec2.y, Mathf.random(360.0F), this.lightningLength + Mathf.random(this.lightningLengthRand));
                        }

                        this.hitEffect.at(vec2.x, vec2.y, 0.0F, this.hitColor);
                        this.hitSound.at(vec2.x, vec2.y, this.hitSoundPitch, this.hitSoundVolume);
                        if (this.fragBullet != null) {
                            for(i = 0; i < this.fragBullets; ++i) {
                                this.fragBullet.create((Entityc)team.cores().firstOpt(), team, vec2.x, vec2.y, (float)Mathf.random(360), Mathf.random(this.fragVelocityMin, this.fragVelocityMax), Mathf.random(this.fragLifeMin, this.fragLifeMax));
                            }
                        }

                        if (this.splashDamageRadius > 0.0F && !b.absorbed) {
                            Damage.damage(team, vec2.x, vec2.y, this.splashDamageRadius, this.splashDamage * mul, this.collidesAir, this.collidesGround);
                            if (this.status != StatusEffects.none) {
                                Damage.status(team, vec2.x, vec2.y, this.splashDamageRadius, this.status, this.statusDuration, this.collidesAir, this.collidesGround);
                            }
                        }

                    });
                });
            }
        };
         */
    }
}

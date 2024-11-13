package am.content;

import arc.graphics.Color;
import mindustry.gen.Sounds;
import mindustry.type.Weather;
import mindustry.type.weather.ParticleWeather;
import mindustry.type.weather.RainWeather;
import mindustry.world.meta.Attribute;

public class AMWeathers {
    public static Weather sulfurstorm;
    public static Weather darkAshstorm;
    public static Weather grayAshstorm;

    public AMWeathers() {
    }

    public static void load() {
        darkAshstorm = new ParticleWeather("dark-ashstorm") {
            {
                this.color = this.noiseColor = Color.valueOf("0a0a0a");
                this.particleRegion = "circle-shadow";
                this.drawNoise = true;
                this.useWindVector = true;
                this.sizeMax = 600F;
                this.sizeMin = 200F;
                this.minAlpha = 0.15F;
                this.maxAlpha = 0.3F;
                this.density = 50000.0F;
                this.baseSpeed = 24F;
                this.attrs.set(Attribute.light, -0.2F);
                this.opacityMultiplier = 0.2F;
                this.sound = Sounds.windhowl;
                this.soundVol = 0.7F;
                this.duration = 900.0F;
            }
        };
        grayAshstorm = new ParticleWeather("gray-ashstorm") {
            {
                this.color = this.noiseColor = Color.valueOf("5b5b5b");
                this.particleRegion = "circle-shadow";
                this.drawNoise = true;
                this.useWindVector = true;
                this.sizeMax = 600F;
                this.sizeMin = 200F;
                this.minAlpha = 0.15F;
                this.maxAlpha = 0.3F;
                this.density = 50000.0F;
                this.baseSpeed = 24F;
                this.attrs.set(Attribute.light, -0.1F);
                this.opacityMultiplier = 0.2F;
                this.sound = Sounds.windhowl;
                this.soundVol = 0.7F;
                this.duration = 900.0F;
            }
        };
        sulfurstorm = new ParticleWeather("sulfurstorm") {
            {
                this.color = this.noiseColor = Color.valueOf("f2d583");
                this.particleRegion = "circle-small";
                this.drawNoise = true;
                this.useWindVector = true;
                this.sizeMax = 4F;
                this.sizeMin = 2F;
                this.minAlpha = 0.4F;
                this.maxAlpha = 0.8F;
                this.density = 4000.0F;
                this.baseSpeed = 12F;
                this.attrs.set(Attribute.light, -0.15F);
                this.status = AMStatusEffects.sulfurCovered;
                this.opacityMultiplier = 0.4F;
                this.sound = Sounds.wind2;
                this.soundVol = 0.7F;
                this.duration = 900.0F;
            }
        };
    }
}

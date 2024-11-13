package am.content;

import am.maps.planet.VoltarPlanetGenerator;
import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.content.Planets;
import mindustry.game.Team;
import mindustry.graphics.g3d.*;
import mindustry.graphics.g3d.PlanetGrid.Ptile;
import mindustry.maps.planet.AsteroidGenerator;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.type.Planet;
import mindustry.type.Sector;

import java.util.Iterator;

public class AMPlanets {
    public static Planet sol2;
    public static Planet vermelha;
    public static Planet azul;
    public static Planet voltar;
    public static Planet wavia;
    public static Planet waviaChrus;
    public static Planet hexanBase;
    public static Planet vestorMilitaryBase;
    public static Planet voidBase1;
    public static Planet voidBase2;
    public static Planet abyssPortal;
    public static Planet wastul;
    public static Planet wastulTrentas;
    public static Planet calamidus;
    public static Planet argonus;
    public static Planet argonusFeron;
    public static Planet argonusKorrab;
    public static Planet argonusLavier;
    public static Seq<Planet> amPlanets = new Seq();

    public AMPlanets() {
    }

    public static void load() {
        Planets.gier.startSector = 0;
        Planets.gier.defaultCore = Blocks.coreAcropolis;
        Planets.gier.alwaysUnlocked = true;
        Planets.gier.accessible = true;
        Planets.gier.unlockedOnLand.add(AMBlocks.asteroidsIcon);
        Planets.gier.itemWhitelist = AMItems.asteroidItems;
        Planets.gier.allowWaveSimulation = false;
        Planets.gier.prebuildBase = false;
        Planets.gier.ruleSetter = (r) -> {
            r.waveTeam = Team.malis;
            r.placeRangeCheck = false;
            r.showSpawns = true;
            r.fog = false;
            r.lighting = false;
            r.coreDestroyClear = true;
            r.onlyDepositCore = false;
            r.borderDarkness = false;
        };

        sol2 = new Planet("sol2", (Planet)null, 0F) {
            {
                this.solarSystem = this;
                this.accessible = false;
            }
        };
        vermelha = new Planet("vermelha", sol2, 4F) {
            {
                this.orbitRadius = 10F;
                this.orbitSpacing = 0F;
                this.bloom = true;
                this.hasAtmosphere = false;
                this.drawOrbit = true;
                this.meshLoader = () -> {
                    return new SunMesh(this, 5, 5.0, 0.3, 1.7, 1.2, 1.0, 1.1F, new Color[]{Color.valueOf("ffba8c"), Color.valueOf("fa946f"), Color.valueOf("ed7b69"), Color.valueOf("db4d4d")});
                };
                this.accessible = false;
                this.generator = new AsteroidGenerator();
                this.sectors.add(new Sector(this, Ptile.empty));
                this.tidalLock = true;
                this.orbitTime = 600;
            }
        };
        azul = new Planet("azul", sol2, 3F) {
            {
                this.orbitSpacing = 0.5F;
                this.orbitRadius = 15F;
                this.bloom = true;
                this.hasAtmosphere = false;
                this.atmosphereColor = Color.valueOf("92ebf2bb");
                this.drawOrbit = true;
                this.meshLoader = () -> {
                    return new SunMesh(this, 4, 5.0, 0.3, 1.2, 1.2, 1.0, 1.1F, new Color[]{Color.valueOf("ffffff"), Color.valueOf("ccffff"), Color.valueOf("92ebf2"), Color.valueOf("5ccfe6")});
                };
                this.accessible = false;
                this.generator = new AsteroidGenerator();
                this.sectors.add(new Sector(this, Ptile.empty));
                this.tidalLock = true;
                this.orbitTime = 600;
            }
        };
        voltar = new Planet("voltar", sol2, 1.35F, 2) {
            {
                this.clipRadius = 5F;
                this.orbitSpacing = 0.15F;
                this.orbitRadius = 30F;
                this.rotateTime = 3600;
                this.orbitTime = 300;
                this.defaultCore = AMBlocks.coreBoiler;
                this.atmosphereColor = Color.valueOf("ffc48b27");
                this.hasAtmosphere = true;
                this.atmosphereRadOut = 0.3F;
                this.atmosphereRadIn = -0.01F;
                this.bloom = false;
                this.iconColor = Color.valueOf("d4855e");
                this.allowLaunchLoadout = false;
                this.allowLaunchSchematics = false;
                this.allowLaunchToNumbered = true;
                this.allowSectorInvasion = false;
                this.allowWaveSimulation = false;
                this.startSector = 0;
                this.generator = new VoltarPlanetGenerator() {
                    {
                        this.defaultLoadout = AMSchematics.coreBoiler;
                    }
                };
                this.sectors.add(new Sector(this, Ptile.empty));
                this.minZoom = 0.2F;
                this.camRadius = 0.4F;
                this.accessible = true;
                this.alwaysUnlocked = true;
                this.updateLighting = false;
                this.prebuildBase = true;
                this.launchCandidates.add(AMPlanets.voltar);
                this.solarSystem = AMPlanets.sol2;
                this.itemWhitelist = AMItems.voltarItems;
                this.meshLoader = () -> {
                    return new MultiMesh(new GenericMesh[]{new SunMesh(this, 5, 5.0, 0.4, 1.2, 1.2, 1.0, 1F, new Color[]{Color.valueOf("ffffa3"), Color.valueOf("ffcd66"), Color.valueOf("e28654")}),

                    new NoiseMesh(this, 422, 5, 1.156F, 4, 0.8F, 0.66F, 1.5F, Color.valueOf("f2d583"), Color.valueOf("6a6a6a"), 4, 1F, 1.8F, 0.5F),
                    new NoiseMesh(this, 422, 5, 1.206F, 4, 0.8F, 0.66F, 1.1F, Color.valueOf("5c463b"), Color.valueOf("515151"), 4, 1F, 1.8F, 0.5F),

                    new NoiseMesh(this, 423, 5, 1.156F, 4, 0.8F, 0.66F, 1.5F, Color.valueOf("9c9ea4"), Color.valueOf("5c463b"), 4, 1F, 1.8F, 0.5F),
                    new NoiseMesh(this, 423, 5, 1.206F, 4, 0.8F, 0.66F, 1.1F, Color.valueOf("5c463b"), Color.valueOf("515151"), 4, 1F, 1.8F, 0.5F),
                    });
                };
                this.cloudMeshLoader = () -> {
                    return new HexSkyMesh(this, 7, 1.1F, 0.09F, 5, Color.valueOf("dcdbc26d"), 2, 0.5F, 1.0F, 0.45F);
                };
                this.ruleSetter = (r) -> {
                    r.loadout = ItemStack.list(new Object[0]);
                    r.fog = true;
                    r.defaultTeam = Team.sharded;
                    r.waveTeam = AMTeams.blaze;
                    r.enemyCoreBuildRadius = 300.0F;
                    r.coreCapture = false;
                    r.hideBannedBlocks = true;
                    r.showSpawns = true;
                    r.coreDestroyClear = true;
                    r.onlyDepositCore = false;
                    r.placeRangeCheck = true;
                };
                this.unlockedOnLand.add(AMBlocks.coreBoiler);
            }
        };
        wavia = new Planet("wavia", sol2, 0.8F, 1) {
            {
                this.clipRadius = 5F;
                this.orbitSpacing = 0.7F;
                this.orbitRadius = 50F;
                this.orbitTime = 1200;
                this.defaultCore = Blocks.coreNucleus;
                this.atmosphereColor = Color.valueOf("88888827");
                this.hasAtmosphere = true;
                this.atmosphereRadOut = 0.3F;
                this.atmosphereRadIn = -0.01F;
                this.bloom = false;
                this.iconColor = Color.valueOf("888888");
                this.allowLaunchLoadout = false;
                this.allowLaunchSchematics = false;
                this.allowLaunchToNumbered = true;
                this.allowSectorInvasion = false;
                this.allowWaveSimulation = false;
                this.startSector = 0;
                this.generator = new SerpuloPlanetGenerator() {
                };
                this.sectors.add(new Sector(this, Ptile.empty));
                this.minZoom = 0.3F;
                this.camRadius = 0.6F;
                this.accessible = false;
                this.alwaysUnlocked = false;
                this.updateLighting = false;
                this.launchCandidates.add(AMPlanets.wavia);
                this.solarSystem = AMPlanets.sol2;
                this.tidalLock = true;
                this.meshLoader = () -> {
                    return new MultiMesh(new GenericMesh[]{new SunMesh(this, 5, 5.0, 0.4, 1.2, 1.2, 1.0, 1F, new Color[]{Color.valueOf("ffffa3"), Color.valueOf("ffcd66"), Color.valueOf("e28654")}),

                    });
                };
                this.cloudMeshLoader = () -> {
                    return new HexSkyMesh(this, 7, 1.1F, 0.09F, 5, Color.valueOf("dcdbc26d"), 2, 0.5F, 1.0F, 0.45F);
                };
                this.ruleSetter = (r) -> {
                    r.loadout = ItemStack.list(new Object[0]);
                    r.fog = false;
                    r.defaultTeam = Team.sharded;
                    r.waveTeam = Team.neoplastic;
                    r.enemyCoreBuildRadius = 300.0F;
                    r.coreCapture = false;
                    r.hideBannedBlocks = true;
                    r.showSpawns = true;
                    r.coreDestroyClear = true;
                    r.onlyDepositCore = false;
                };
            }
        };
        waviaChrus = new Planet("wavia-chrus", wavia, 0.3F, 1) {
            {
                this.clipRadius = 5F;
                this.orbitSpacing = 0.1F;
                this.orbitRadius = 12F;
                this.orbitTime = 450;
                this.defaultCore = Blocks.coreNucleus;
                this.atmosphereColor = Color.valueOf("ffc48b27");
                this.hasAtmosphere = true;
                this.atmosphereRadOut = 0.3F;
                this.atmosphereRadIn = -0.01F;
                this.bloom = false;
                this.iconColor = Color.valueOf("888888");
                this.allowLaunchLoadout = false;
                this.allowLaunchSchematics = false;
                this.allowLaunchToNumbered = true;
                this.allowSectorInvasion = false;
                this.allowWaveSimulation = false;
                this.startSector = 0;
                this.generator = new AsteroidGenerator() {
                };
                this.sectors.add(new Sector(this, Ptile.empty));
                this.minZoom = 0.3F;
                this.camRadius = 0.6F;
                this.accessible = false;
                this.alwaysUnlocked = false;
                this.updateLighting = false;
                this.launchCandidates.add(AMPlanets.wavia);
                this.solarSystem = AMPlanets.sol2;
                this.meshLoader = () -> {
                    return new MultiMesh(new GenericMesh[]{new SunMesh(this, 5, 5.0, 0.4, 1.2, 1.2, 1.0, 1F, new Color[]{Color.valueOf("ffffa3"), Color.valueOf("ffcd66"), Color.valueOf("e28654")}),

                    });
                };
                this.cloudMeshLoader = () -> {
                    return new HexSkyMesh(this, 7, 1.1F, 0.09F, 5, Color.valueOf("dcdbc26d"), 2, 0.5F, 1.0F, 0.45F);
                };
                this.ruleSetter = (r) -> {
                    r.loadout = ItemStack.list(new Object[0]);
                    r.fog = false;
                    r.defaultTeam = Team.sharded;
                    r.waveTeam = Team.neoplastic;
                    r.enemyCoreBuildRadius = 300.0F;
                    r.coreCapture = false;
                    r.hideBannedBlocks = true;
                    r.showSpawns = true;
                    r.coreDestroyClear = true;
                    r.onlyDepositCore = false;
                };
            }
        };
        hexanBase = new Planet("hexan-base", sol2, 1F) {
            {
                this.clipRadius = 5F;
                this.orbitSpacing = 0.3F;
                this.orbitRadius = 75F;
                this.orbitTime = 300;
                this.defaultCore = Blocks.coreNucleus;
                this.atmosphereColor = Color.valueOf("ffc48b27");
                this.hasAtmosphere = true;
                this.atmosphereRadOut = 0.3F;
                this.atmosphereRadIn = -0.01F;
                this.bloom = false;
                this.iconColor = Color.valueOf("888888");
                this.allowLaunchLoadout = false;
                this.allowLaunchSchematics = false;
                this.allowLaunchToNumbered = true;
                this.allowSectorInvasion = false;
                this.allowWaveSimulation = false;
                this.startSector = 0;
                this.generator = new AsteroidGenerator() {
                };
                this.sectors.add(new Sector(this, Ptile.empty));
                this.minZoom = 0.3F;
                this.camRadius = 0.6F;
                this.accessible = false;
                this.alwaysUnlocked = false;
                this.updateLighting = false;
                this.launchCandidates.add(AMPlanets.wavia);
                this.solarSystem = AMPlanets.sol2;
                this.meshLoader = () -> {
                    return new MultiMesh(new GenericMesh[]{new SunMesh(this, 5, 5.0, 0.4, 1.2, 1.2, 1.0, 1F, new Color[]{Color.valueOf("ffffa3"), Color.valueOf("ffcd66"), Color.valueOf("e28654")}),

                    });
                };
                this.cloudMeshLoader = () -> {
                    return new HexSkyMesh(this, 7, 1.1F, 0.09F, 5, Color.valueOf("dcdbc26d"), 2, 0.5F, 1.0F, 0.45F);
                };
                this.ruleSetter = (r) -> {
                    r.loadout = ItemStack.list(new Object[0]);
                    r.fog = false;
                    r.defaultTeam = Team.sharded;
                    r.waveTeam = Team.neoplastic;
                    r.enemyCoreBuildRadius = 300.0F;
                    r.coreCapture = false;
                    r.hideBannedBlocks = true;
                    r.showSpawns = true;
                    r.coreDestroyClear = true;
                    r.onlyDepositCore = false;
                };
            }
        };
        vestorMilitaryBase = new Planet("vestor-military-base", sol2, 1F) {
            {
                this.clipRadius = 5F;
                this.orbitSpacing = 0.45F;
                this.orbitRadius = 75F;
                this.orbitTime = 300;
                this.defaultCore = Blocks.coreNucleus;
                this.atmosphereColor = Color.valueOf("ffc48b27");
                this.hasAtmosphere = true;
                this.atmosphereRadOut = 0.3F;
                this.atmosphereRadIn = -0.01F;
                this.bloom = false;
                this.iconColor = Color.valueOf("888888");
                this.allowLaunchLoadout = false;
                this.allowLaunchSchematics = false;
                this.allowLaunchToNumbered = true;
                this.allowSectorInvasion = false;
                this.allowWaveSimulation = false;
                this.startSector = 0;
                this.generator = new AsteroidGenerator() {
                };
                this.sectors.add(new Sector(this, Ptile.empty));
                this.minZoom = 0.3F;
                this.camRadius = 0.6F;
                this.accessible = false;
                this.alwaysUnlocked = false;
                this.updateLighting = false;
                this.launchCandidates.add(AMPlanets.wavia);
                this.solarSystem = AMPlanets.sol2;
                this.meshLoader = () -> {
                    return new MultiMesh(new GenericMesh[]{new SunMesh(this, 5, 5.0, 0.4, 1.2, 1.2, 1.0, 1F, new Color[]{Color.valueOf("ffffa3"), Color.valueOf("ffcd66"), Color.valueOf("e28654")}),

                    });
                };
                this.cloudMeshLoader = () -> {
                    return new HexSkyMesh(this, 7, 1.1F, 0.09F, 5, Color.valueOf("dcdbc26d"), 2, 0.5F, 1.0F, 0.45F);
                };
                this.ruleSetter = (r) -> {
                    r.loadout = ItemStack.list(new Object[0]);
                    r.fog = false;
                    r.defaultTeam = Team.sharded;
                    r.waveTeam = Team.neoplastic;
                    r.enemyCoreBuildRadius = 300.0F;
                    r.coreCapture = false;
                    r.hideBannedBlocks = true;
                    r.showSpawns = true;
                    r.coreDestroyClear = true;
                    r.onlyDepositCore = false;
                };
            }
        };
        voidBase1 = new Planet("void-base1", sol2, 1F) {
            {
                this.clipRadius = 5F;
                this.orbitSpacing = 0.6F;
                this.orbitRadius = 75F;
                this.orbitTime = 300;
                this.defaultCore = Blocks.coreNucleus;
                this.atmosphereColor = Color.valueOf("ffc48b27");
                this.hasAtmosphere = true;
                this.atmosphereRadOut = 0.3F;
                this.atmosphereRadIn = -0.01F;
                this.bloom = false;
                this.iconColor = Color.valueOf("888888");
                this.allowLaunchLoadout = false;
                this.allowLaunchSchematics = false;
                this.allowLaunchToNumbered = true;
                this.allowSectorInvasion = false;
                this.allowWaveSimulation = false;
                this.startSector = 0;
                this.generator = new AsteroidGenerator() {
                };
                this.sectors.add(new Sector(this, Ptile.empty));
                this.minZoom = 0.3F;
                this.camRadius = 0.6F;
                this.accessible = false;
                this.alwaysUnlocked = false;
                this.updateLighting = false;
                this.launchCandidates.add(AMPlanets.wavia);
                this.solarSystem = AMPlanets.sol2;
                this.meshLoader = () -> {
                    return new MultiMesh(new GenericMesh[]{new SunMesh(this, 5, 5.0, 0.4, 1.2, 1.2, 1.0, 1F, new Color[]{Color.valueOf("ffffa3"), Color.valueOf("ffcd66"), Color.valueOf("e28654")}),

                    });
                };
                this.cloudMeshLoader = () -> {
                    return new HexSkyMesh(this, 7, 1.1F, 0.09F, 5, Color.valueOf("dcdbc26d"), 2, 0.5F, 1.0F, 0.45F);
                };
                this.ruleSetter = (r) -> {
                    r.loadout = ItemStack.list(new Object[0]);
                    r.fog = false;
                    r.defaultTeam = Team.sharded;
                    r.waveTeam = Team.neoplastic;
                    r.enemyCoreBuildRadius = 300.0F;
                    r.coreCapture = false;
                    r.hideBannedBlocks = true;
                    r.showSpawns = true;
                    r.coreDestroyClear = true;
                    r.onlyDepositCore = false;
                };
            }
        };
        voidBase2 = new Planet("void-base2", sol2, 1F) {
            {
                this.clipRadius = 5F;
                this.orbitSpacing = 0.9F;
                this.orbitRadius = 75F;
                this.orbitTime = 300;
                this.defaultCore = Blocks.coreNucleus;
                this.atmosphereColor = Color.valueOf("ffc48b27");
                this.hasAtmosphere = true;
                this.atmosphereRadOut = 0.3F;
                this.atmosphereRadIn = -0.01F;
                this.bloom = false;
                this.iconColor = Color.valueOf("888888");
                this.allowLaunchLoadout = false;
                this.allowLaunchSchematics = false;
                this.allowLaunchToNumbered = true;
                this.allowSectorInvasion = false;
                this.allowWaveSimulation = false;
                this.startSector = 0;
                this.generator = new AsteroidGenerator() {
                };
                this.sectors.add(new Sector(this, Ptile.empty));
                this.minZoom = 0.3F;
                this.camRadius = 0.6F;
                this.accessible = false;
                this.alwaysUnlocked = false;
                this.updateLighting = false;
                this.launchCandidates.add(AMPlanets.wavia);
                this.solarSystem = AMPlanets.sol2;
                this.meshLoader = () -> {
                    return new MultiMesh(new GenericMesh[]{new SunMesh(this, 5, 5.0, 0.4, 1.2, 1.2, 1.0, 1F, new Color[]{Color.valueOf("ffffa3"), Color.valueOf("ffcd66"), Color.valueOf("e28654")}),

                    });
                };
                this.cloudMeshLoader = () -> {
                    return new HexSkyMesh(this, 7, 1.1F, 0.09F, 5, Color.valueOf("dcdbc26d"), 2, 0.5F, 1.0F, 0.45F);
                };
                this.ruleSetter = (r) -> {
                    r.loadout = ItemStack.list(new Object[0]);
                    r.fog = false;
                    r.defaultTeam = Team.sharded;
                    r.waveTeam = Team.neoplastic;
                    r.enemyCoreBuildRadius = 300.0F;
                    r.coreCapture = false;
                    r.hideBannedBlocks = true;
                    r.showSpawns = true;
                    r.coreDestroyClear = true;
                    r.onlyDepositCore = false;
                };
            }
        };
        abyssPortal = new Planet("abyss-portal", sol2, 1F) {
            {
                this.clipRadius = 5F;
                this.orbitSpacing = 0.75F;
                this.orbitRadius = 75F;
                this.orbitTime = 300;
                this.defaultCore = Blocks.coreNucleus;
                this.atmosphereColor = Color.valueOf("ffc48b27");
                this.hasAtmosphere = true;
                this.atmosphereRadOut = 0.3F;
                this.atmosphereRadIn = -0.01F;
                this.bloom = false;
                this.iconColor = Color.valueOf("888888");
                this.allowLaunchLoadout = false;
                this.allowLaunchSchematics = false;
                this.allowLaunchToNumbered = true;
                this.allowSectorInvasion = false;
                this.allowWaveSimulation = false;
                this.startSector = 0;
                this.generator = new AsteroidGenerator() {
                };
                this.sectors.add(new Sector(this, Ptile.empty));
                this.minZoom = 0.3F;
                this.camRadius = 0.6F;
                this.accessible = false;
                this.alwaysUnlocked = false;
                this.updateLighting = false;
                this.launchCandidates.add(AMPlanets.wavia);
                this.solarSystem = AMPlanets.sol2;
                this.meshLoader = () -> {
                    return new MultiMesh(new GenericMesh[]{new SunMesh(this, 5, 5.0, 0.4, 1.2, 1.2, 1.0, 1F, new Color[]{Color.valueOf("ffffa3"), Color.valueOf("ffcd66"), Color.valueOf("e28654")}),

                    });
                };
                this.cloudMeshLoader = () -> {
                    return new HexSkyMesh(this, 7, 1.1F, 0.09F, 5, Color.valueOf("dcdbc26d"), 2, 0.5F, 1.0F, 0.45F);
                };
                this.ruleSetter = (r) -> {
                    r.loadout = ItemStack.list(new Object[0]);
                    r.fog = false;
                    r.defaultTeam = Team.sharded;
                    r.waveTeam = Team.neoplastic;
                    r.enemyCoreBuildRadius = 300.0F;
                    r.coreCapture = false;
                    r.hideBannedBlocks = true;
                    r.showSpawns = true;
                    r.coreDestroyClear = true;
                    r.onlyDepositCore = false;
                };
            }
        };
        wastul = new Planet("wastul", sol2, 0.65F, 1) {
            {
                this.clipRadius = 5F;
                this.orbitSpacing = 0.7F;
                this.orbitRadius = 95F;
                this.orbitTime = 300;
                this.defaultCore = Blocks.coreNucleus;
                this.atmosphereColor = Color.valueOf("ffc48b27");
                this.hasAtmosphere = true;
                this.atmosphereRadOut = 0.3F;
                this.atmosphereRadIn = -0.01F;
                this.bloom = false;
                this.iconColor = Color.valueOf("888888");
                this.allowLaunchLoadout = false;
                this.allowLaunchSchematics = false;
                this.allowLaunchToNumbered = true;
                this.allowSectorInvasion = false;
                this.allowWaveSimulation = false;
                this.startSector = 0;
                this.generator = new AsteroidGenerator() {
                };
                this.sectors.add(new Sector(this, Ptile.empty));
                this.minZoom = 0.3F;
                this.camRadius = 0.6F;
                this.accessible = false;
                this.alwaysUnlocked = false;
                this.updateLighting = false;
                this.launchCandidates.add(AMPlanets.wavia);
                this.solarSystem = AMPlanets.sol2;
                this.meshLoader = () -> {
                    return new MultiMesh(new GenericMesh[]{new SunMesh(this, 5, 5.0, 0.4, 1.2, 1.2, 1.0, 1F, new Color[]{Color.valueOf("ffffa3"), Color.valueOf("ffcd66"), Color.valueOf("e28654")}),

                    });
                };
                this.cloudMeshLoader = () -> {
                    return new HexSkyMesh(this, 7, 1.1F, 0.09F, 5, Color.valueOf("dcdbc26d"), 2, 0.5F, 1.0F, 0.45F);
                };
                this.ruleSetter = (r) -> {
                    r.loadout = ItemStack.list(new Object[0]);
                    r.fog = false;
                    r.defaultTeam = Team.sharded;
                    r.waveTeam = Team.neoplastic;
                    r.enemyCoreBuildRadius = 300.0F;
                    r.coreCapture = false;
                    r.hideBannedBlocks = true;
                    r.showSpawns = true;
                    r.coreDestroyClear = true;
                    r.onlyDepositCore = false;
                };
            }
        };
        calamidus = new Planet("calamidus", sol2, 0.8F, 1) {
            {
                this.clipRadius = 5F;
                this.orbitSpacing = 0.7F;
                this.orbitRadius = 110F;
                this.orbitTime = 300;
                this.defaultCore = Blocks.coreNucleus;
                this.atmosphereColor = Color.valueOf("ffc48b27");
                this.hasAtmosphere = true;
                this.atmosphereRadOut = 0.3F;
                this.atmosphereRadIn = -0.01F;
                this.bloom = false;
                this.iconColor = Color.valueOf("888888");
                this.allowLaunchLoadout = false;
                this.allowLaunchSchematics = false;
                this.allowLaunchToNumbered = true;
                this.allowSectorInvasion = false;
                this.allowWaveSimulation = false;
                this.startSector = 0;
                this.generator = new AsteroidGenerator() {
                };
                this.sectors.add(new Sector(this, Ptile.empty));
                this.minZoom = 0.3F;
                this.camRadius = 0.6F;
                this.accessible = false;
                this.alwaysUnlocked = false;
                this.updateLighting = false;
                this.launchCandidates.add(AMPlanets.wavia);
                this.solarSystem = AMPlanets.sol2;
                this.meshLoader = () -> {
                    return new MultiMesh(new GenericMesh[]{new SunMesh(this, 5, 5.0, 0.4, 1.2, 1.2, 1.0, 1F, new Color[]{Color.valueOf("ffffa3"), Color.valueOf("ffcd66"), Color.valueOf("e28654")}),

                    });
                };
                this.cloudMeshLoader = () -> {
                    return new HexSkyMesh(this, 7, 1.1F, 0.09F, 5, Color.valueOf("dcdbc26d"), 2, 0.5F, 1.0F, 0.45F);
                };
                this.ruleSetter = (r) -> {
                    r.loadout = ItemStack.list(new Object[0]);
                    r.fog = false;
                    r.defaultTeam = Team.sharded;
                    r.waveTeam = Team.neoplastic;
                    r.enemyCoreBuildRadius = 300.0F;
                    r.coreCapture = false;
                    r.hideBannedBlocks = true;
                    r.showSpawns = true;
                    r.coreDestroyClear = true;
                    r.onlyDepositCore = false;
                };
            }
        };
        wavia.visible = false;
        waviaChrus.visible = false;
        hexanBase.visible = false;
        vestorMilitaryBase.visible = false;
        voidBase1.visible = false;
        voidBase2.visible = false;
        abyssPortal.visible = false;
        wastul.visible = false;
        calamidus.visible = false;

        Planets.serpulo.hiddenItems.addAll(AMItems.voltarItems).removeAll(Items.serpuloItems);
        Planets.erekir.hiddenItems.addAll(AMItems.voltarItems).removeAll(Items.erekirItems);

        amPlanets.addAll(new Planet[]{voltar, wavia});

        //for (Planet planet : Vars.content.planets()) {
        //    if (planet != AMPlanets.amPlanets) {
        //        planet.hiddenItems.addAll(AMItems.voltarItems);
        //    }
        //}
    }
}
package am;

import am.content.AMUnitTypes;
import arc.Core;
import arc.Events;
import arc.struct.Seq;
import arc.util.Log;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.game.SpawnGroup;

public class Difficulties{

    public Difficulties() {
    }

    public static void load() {

        Log.info("SerpuloDifficulty value = " + AMVars.SerpuloDifficulty);

        Events.run(EventType.Trigger.newGame, () -> {
            if (Core.settings.getInt("@am-serpulo-difficulty") == 1) {
                Log.info("USELESS");
                Vars.state.rules.teams.get(Vars.state.rules.waveTeam).unitHealthMultiplier *= 0.8F;
                Vars.state.rules.teams.get(Vars.state.rules.waveTeam).unitDamageMultiplier *= 0.8F;
                Vars.state.rules.teams.get(Vars.state.rules.waveTeam).blockHealthMultiplier *= 0.8F;
                Vars.state.rules.teams.get(Vars.state.rules.waveTeam).blockDamageMultiplier *= 0.8F;
            } else if (Core.settings.getInt("@am-serpulo-difficulty") == 3) {
                Log.info("WEAK");
                Vars.state.rules.waveSpacing *= 0.9F;
                Vars.state.rules.teams.get(Vars.state.rules.waveTeam).blockHealthMultiplier *= 1.5F;
                Vars.state.rules.teams.get(Vars.state.rules.waveTeam).blockDamageMultiplier *= 1.5F;
                Vars.state.rules.spawns.each((spawn) -> {
                    spawn.unitAmount *= 2;
                    spawn.unitScaling *= 2;
                    spawn.max *= 2;
                });
            } else if (Core.settings.getInt("@am-serpulo-difficulty") == 4) {
                Log.info("PREPARE THYSELF!!!!!!");
                Vars.state.rules.waveSpacing *= 0.8F;
                Vars.state.rules.teams.get(Vars.state.rules.waveTeam).blockHealthMultiplier *= 2F;
                Vars.state.rules.teams.get(Vars.state.rules.waveTeam).blockDamageMultiplier *= 2F;
                Vars.state.rules.spawns.each((spawn) -> {
                    spawn.unitAmount *= 3;
                    spawn.unitScaling *= 3;
                    spawn.max *= 3;
                });
            } else if (Core.settings.getInt("@am-serpulo-difficulty") == 5) {
                Log.info("THY END IS NOW!!!!!!!!!!!!!!!!!!!");
                Vars.state.rules.waveSpacing *= 0.8F;
                Vars.state.rules.teams.get(Vars.state.rules.waveTeam).blockHealthMultiplier *= 2.5F;
                Vars.state.rules.teams.get(Vars.state.rules.waveTeam).blockDamageMultiplier *= 2.5F;
                Seq<SpawnGroup> Spawns;
                Spawns = Vars.state.rules.spawns.copy();
                Vars.state.rules.spawns.each((spawn) -> {
                    spawn.type = AMUnitTypes.imp;
                });
                    /*
                    if (spawn.type == UnitTypes.scepter) {
                        spawn.type = UnitTypes.reign;
                    } else if (spawn.type == UnitTypes.fortress) {
                        spawn.type = UnitTypes.scepter;
                    } else if (spawn.type == UnitTypes.mace) {
                        spawn.type = UnitTypes.fortress;
                    } else if (spawn.type == UnitTypes.dagger) {
                        spawn.type = UnitTypes.mace;
                    } else if (spawn.type == UnitTypes.vela) {
                        spawn.type = UnitTypes.corvus;
                    } else if (spawn.type == UnitTypes.quasar) {
                        spawn.type = UnitTypes.vela;
                    } else if (spawn.type == UnitTypes.pulsar) {
                        spawn.type = UnitTypes.quasar;
                    } else if (spawn.type == UnitTypes.nova) {
                        spawn.type = UnitTypes.pulsar;
                    } else if (spawn.type == UnitTypes.arkyid) {
                        spawn.type = UnitTypes.toxopid;
                    } else if (spawn.type == UnitTypes.spiroct) {
                        spawn.type = UnitTypes.arkyid;
                    } else if (spawn.type == UnitTypes.atrax) {
                        spawn.type = UnitTypes.spiroct;
                    } else if (spawn.type == UnitTypes.crawler) {
                        spawn.type = UnitTypes.atrax;

                    } else if (spawn.type == UnitTypes.antumbra) {
                        spawn.type = UnitTypes.eclipse;
                    } else if (spawn.type == UnitTypes.zenith) {
                        spawn.type = UnitTypes.antumbra;
                    } else if (spawn.type == UnitTypes.horizon) {
                        spawn.type = UnitTypes.zenith;
                    } else if (spawn.type == UnitTypes.flare) {
                        spawn.type = UnitTypes.horizon;
                    } else if (spawn.type == UnitTypes.quad) {
                        spawn.type = UnitTypes.oct;
                    } else if (spawn.type == UnitTypes.mega) {
                        spawn.type = UnitTypes.quad;
                    } else if (spawn.type == UnitTypes.poly) {
                        spawn.type = UnitTypes.mega;
                    } else if (spawn.type == UnitTypes.mono) {
                        spawn.type = UnitTypes.poly;

                    } else if (spawn.type == UnitTypes.sei) {
                        spawn.type = UnitTypes.omura;
                    } else if (spawn.type == UnitTypes.bryde) {
                        spawn.type = UnitTypes.sei;
                    } else if (spawn.type == UnitTypes.minke) {
                        spawn.type = UnitTypes.bryde;
                    } else if (spawn.type == UnitTypes.risso) {
                        spawn.type = UnitTypes.minke;
                    } else if (spawn.type == UnitTypes.aegires) {
                        spawn.type = UnitTypes.navanax;
                    } else if (spawn.type == UnitTypes.cyerce) {
                        spawn.type = UnitTypes.aegires;
                    } else if (spawn.type == UnitTypes.oxynoe) {
                        spawn.type = UnitTypes.cyerce;
                    } else if (spawn.type == UnitTypes.retusa) {
                        spawn.type = UnitTypes.oxynoe;
                    } else {
                        if (spawn.effect != StatusEffects.boss) {
                            spawn.effect = StatusEffects.overclock;
                        }
                    }
                });
                */
                Vars.state.rules.spawns.add(Spawns);
                Vars.state.rules.spawns.add(Spawns);
            }
        });
    }
}

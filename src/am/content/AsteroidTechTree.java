package am.content;

import arc.struct.Seq;
import mindustry.content.Planets;
import mindustry.content.SectorPresets;
import mindustry.content.TechTree;
import mindustry.game.Objectives;

public class AsteroidTechTree {
    public AsteroidTechTree() {
    }

    public static void load() {
        Planets.gier.techTree = AMPlanets.hexanBase.techTree = AMPlanets.vestorMilitaryBase.techTree = AMPlanets.voidBase1.techTree = AMPlanets.voidBase2.techTree = TechTree.nodeRoot("@am-asteroid-tech-tree", AMBlocks.asteroidsIcon, true,  () -> {
            TechTree.node(AMBlocks.tungstenSeparator, () -> {
                TechTree.node(AMBlocks.oilSynthesizer, () -> {
                });
            });/*
            TechTree.node(AMSectors.asteroidRuins, () -> {
            });*/
        });
    }
}
//Seq.with(new Objectives.Objective[]{new Objectives.SectorComplete(SectorPresets.planetaryTerminal), new Objectives.SectorComplete(SectorPresets.karst)}),
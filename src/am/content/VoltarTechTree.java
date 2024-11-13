package am.content;

import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.TechTree;

public class VoltarTechTree {
    public VoltarTechTree() {
    }

    public static void load() {
        AMPlanets.voltar.techTree = TechTree.nodeRoot("@planet.am-voltar.name", AMBlocks.coreBoiler, true, () -> {
            TechTree.nodeProduce(AMItems.oxidizedCopper, () -> {
                TechTree.nodeProduce(AMItems.zinc, () -> {
                    TechTree.nodeProduce(AMLiquids.vulcanicWater, () -> {
                        TechTree.nodeProduce(AMLiquids.carbonGas, () -> {
                            TechTree.nodeProduce(Liquids.slag, () -> {
                            });
                            TechTree.nodeProduce(AMLiquids.scarletine, () -> {
                                TechTree.nodeProduce(Liquids.nitrogen, () -> {
                                });
                            });
                        });
                        TechTree.nodeProduce(Liquids.water, () -> {
                            TechTree.nodeProduce(AMLiquids.sulfuricAcid, () -> {
                            });
                        });
                    });
                    TechTree.nodeProduce(AMItems.sulfur, () -> {
                        TechTree.nodeProduce(AMItems.quartz, () -> {
                            TechTree.nodeProduce(Items.silicon, () -> {
                                TechTree.nodeProduce(AMItems.brass, () -> {
                                });
                            });
                        });
                    });
                    TechTree.nodeProduce(AMItems.silver, () -> {
                        TechTree.nodeProduce(AMItems.platinum, () -> {
                            TechTree.nodeProduce(AMItems.iridium, () -> {
                                TechTree.nodeProduce(AMItems.heavyAlloy, () -> {
                                });
                                TechTree.nodeProduce(AMItems.techAlloy, () -> {
                                });
                            });
                            TechTree.nodeProduce(AMItems.gold, () -> {
                            });
                        });
                    });
                });
            });
            TechTree.node(AMBlocks.encasedConveyor, () -> {
                TechTree.node(AMBlocks.encasedJunction, () -> {
                    TechTree.node(AMBlocks.encasedRouter, () -> {
                        TechTree.node(AMBlocks.encasedOverflowGate, () -> {
                            TechTree.node(AMBlocks.encasedUnderflowGate, () -> {
                            });
                        });
                    });
                });
            });
            TechTree.node(AMBlocks.encasedCopperWall, () -> {
                TechTree.node(AMBlocks.encasedCopperWallLarge, () -> {
                });
            });
            TechTree.node(AMBlocks.geyserCollector, () -> {
                TechTree.node(AMBlocks.encasedDrill, () -> {
                });
                TechTree.node(AMBlocks.hydroThermalTurbine, () -> {
                    TechTree.node(AMBlocks.encasedNode, () -> {
                    });
                });
                TechTree.node(AMBlocks.heatDuct, () -> {
                    TechTree.node(AMBlocks.siliconFurnace, () -> {
                        TechTree.node(AMBlocks.geothermalHeater, () -> {
                        });
                        TechTree.node(AMBlocks.litterBurner, () -> {
                        });
                    });
                });
            });
            TechTree.node(AMBlocks.basicAssembler, () -> {
                TechTree.node(AMBlocks.moduleConstructor, () -> {
                    TechTree.node(AMUnitParts.basicPlating, () -> {
                    });
                });
            });
            TechTree.node(AMBlocks.corePalace, () -> {
                TechTree.node(AMBlocks.coreInferno, () -> {
                });
            });
            TechTree.node(AMBlocks.encasedMessage, () -> {
            });
        });
    }
}
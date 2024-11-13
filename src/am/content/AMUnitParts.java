package am.content;

import am.world.UnitPart;
import mindustry.type.Category;
import mindustry.type.ItemStack;

public class AMUnitParts {
    public static UnitPart smallMechLegs;
    public static UnitPart largeMechLegs;
    public static UnitPart hugeMechLegs;
    public static UnitPart smallTankThreads;
    public static UnitPart largeTankThreads;
    public static UnitPart hugeTankThreads;
    public static UnitPart smallMechanicalParts;
    public static UnitPart largeMechanicalParts;
    public static UnitPart hugeMechanicalParts;
    public static UnitPart basicPlating;
    public static UnitPart advancedPlating;
    public static UnitPart superiorPlating;
    public static UnitPart basicPowerCapacitor;
    public static UnitPart advancedPowerCapacitor;
    public static UnitPart superiorPowerCapacitor;
    public static UnitPart basicDischarger;
    public static UnitPart advancedDischarger;
    public static UnitPart superiorDischarger;

    public static UnitPart abyssCore;
    public static UnitPart abyssChamber;
    public static UnitPart violenceCore;
    public static UnitPart violenceDischarger;

    public static void load() {
        smallMechLegs = new UnitPart("small-mech-legs") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 400;
                this.size = 2;
            }
        };
        largeMechLegs = new UnitPart("large-mech-legs") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 900;
                this.size = 3;
            }
        };
        hugeMechLegs = new UnitPart("huge-mech-legs") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 1600;
                this.size = 4;
            }
        };
        smallTankThreads = new UnitPart("small-tank-threads") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 400;
                this.size = 2;
            }
        };
        largeTankThreads = new UnitPart("large-tank-threads") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 900;
                this.size = 3;
            }
        };
        hugeTankThreads = new UnitPart("huge-tank-threads") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 1600;
                this.size = 4;
            }
        };
        smallMechanicalParts = new UnitPart("small-mechanical-parts") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 400;
                this.size = 2;
            }
        };
        largeMechanicalParts = new UnitPart("large-mechanical-parts") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 900;
                this.size = 3;
            }
        };
        hugeMechanicalParts = new UnitPart("huge-mechanical-parts") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 1600;
                this.size = 4;
            }
        };
        basicPlating = new UnitPart("basic-plating") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 1200;
                this.armor = 4.0F;
                this.size = 2;
            }
        };
        advancedPlating = new UnitPart("advanced-plating") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 2700;
                this.armor = 8.0F;
                this.size = 3;
            }
        };
        superiorPlating = new UnitPart("superior-plating") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 4800;
                this.armor = 12.0F;
                this.size = 4;
            }
        };
        basicPowerCapacitor = new UnitPart("basic-power-capacitor") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 400;
                this.armor = 2.0F;
                this.size = 2;
            }
        };
        advancedPowerCapacitor = new UnitPart("advanced-power-capacitor") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 900;
                this.armor = 2.0F;
                this.size = 3;
            }
        };
        superiorPowerCapacitor = new UnitPart("superior-power-capacitor") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 1600;
                this.armor = 2.0F;
                this.size = 4;
            }
        };
        basicDischarger = new UnitPart("basic-discharger") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 400;
                this.armor = 2.0F;
                this.size = 2;
            }
        };
        advancedDischarger = new UnitPart("advanced-discharger") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 900;
                this.armor = 2.0F;
                this.size = 3;
            }
        };
        superiorDischarger = new UnitPart("superior-discharger") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25}));
                this.researchCost = ItemStack.with(new Object[]{AMItems.oxidizedCopper, 50, AMItems.zinc, 25});
                this.health = 1600;
                this.armor = 2.0F;
                this.size = 4;
            }
        };
        abyssCore = new UnitPart("abyss-core") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[0]));
                this.scaledHealth = 1000;
                this.armor = 32.0F;
                this.size = 5;
            }
        };
        abyssChamber = new UnitPart("abyss-chamber") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[0]));
                this.scaledHealth = 1200;
                this.armor = 40.0F;
                this.size = 5;
            }
        };
        violenceCore = new UnitPart("violence-core") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[0]));
                this.scaledHealth = 1000;
                this.armor = 32.0F;
                this.size = 5;
            }
        };
        violenceDischarger = new UnitPart("violence-discharger") {
            {
                this.requirements(Category.units, ItemStack.with(new Object[0]));
                this.scaledHealth = 1200;
                this.armor = 40.0F;
                this.size = 5;
            }
        };
    }
}

package am.content;

import arc.Core;
import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.type.Item;

import static mindustry.content.Items.erekirItems;
import static mindustry.content.Items.serpuloItems;

public class AMItems {
    public static Item oxidizedCopper;
    public static Item zinc;
    public static Item silver;
    public static Item quartz;
    public static Item sulfur;
    public static Item brass;
    public static Item platinum;
    public static Item gold;
    public static Item iridium;
    public static Item heavyAlloy;
    public static Item techAlloy;
    public static Seq<Item> asteroidItems = new Seq();
    public static Seq<Item> voltarItems = new Seq();
    public static Seq<Item> voltarOnlyItems = new Seq();

    public static void load() {
        oxidizedCopper = new Item("oxidized-copper", Color.valueOf("c8a475")) {
            {
                this.hardness = 1;
                this.cost = 0.5F;
            }
        };
        zinc = new Item("zinc", Color.valueOf("a3b4a7")) {
            {
                this.hardness = 2;
                this.cost = 0.75F;
            }
        };
        silver = new Item("silver", Color.valueOf("dddddd")) {
            {
                this.cost = 1.25F;
                this.charge = 0.1F;
                this.hardness = 3;
            }
        };
        quartz = new Item("quartz", Color.valueOf("f2d583")) {
            {
                this.description = Core.bundle.getOrNull("item.sand.description");
                this.hardness = 2;
                this.buildable = false;
            }
        };
        sulfur = new Item("sulfur", Color.valueOf("f2d583")) {
            {
                this.flammability = 0.25F;
                this.buildable = false;
            }
        };
        brass = new Item("brass", Color.valueOf("bc976a")) {
            {
                this.cost = 1F;
            }
        };
        platinum = new Item("platinum", Color.valueOf("b0b3bf")) {
            {
                this.cost = 1.25F;
                this.hardness = 3;
            }
        };
        gold = new Item("gold", Color.valueOf("edd37f")) {
            {
                this.charge = 0.2F;
                this.cost = 1.5F;
                this.hardness = 3;
            }
        };
        iridium = new Item("iridium", Color.valueOf("bccee3")) {
            {
                this.charge = 0.2F;
                this.cost = 1.4F;
                this.hardness = 4;
            }
        };
        heavyAlloy = new Item("heavy-alloy", Color.valueOf("9799b8")) {
            {
                this.description = Core.bundle.getOrNull("item.surge-alloy.description");
                this.cost = 1.5F;
            }
        };
        techAlloy = new Item("tech-alloy", Color.valueOf("878eaa")) {
            {
                this.description = Core.bundle.getOrNull("item.phase-fabric.description");
                this.charge = 0.5F;
                this.cost = 1.5F;
            }
        };
        asteroidItems.addAll(erekirItems).addAll(serpuloItems);
        voltarItems.addAll(new Item[]{oxidizedCopper, zinc, silver, quartz, Items.silicon, brass, platinum, gold, Items.blastCompound, iridium, heavyAlloy, techAlloy});
        voltarOnlyItems.addAll(new Item[]{oxidizedCopper, zinc, silver, quartz, brass, platinum, gold, iridium, heavyAlloy, techAlloy});
    }
}

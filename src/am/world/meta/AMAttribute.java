package am.world.meta;

import mindustry.content.Blocks;
import mindustry.world.meta.Attribute;

import static mindustry.world.meta.Attribute.add;

public class AMAttribute {
    public static final Attribute carbon = add("carbon");
    public static final Attribute geyserWater = add("geyser-water");
    public static final Attribute silver = add("silver");
    public static final Attribute gold = add("gold");
    public final int id;
    public final String name;

    public String toString() {
        return this.name;
    }

    AMAttribute(int id, String name) {
        this.id = id;
        this.name = name;

        Blocks.carbonStone.attributes.set(AMAttribute.carbon, 1.0F);
    }
}
package am.world;

import arc.Core;
import mindustry.ctype.ContentType;
import mindustry.world.Block;

public class UnitPart extends Block {
    public UnitPart(String name) {
        super(name);
        this.localizedName = Core.bundle.get("unitpart." + this.name + ".name", this.name);
        this.description = Core.bundle.getOrNull("unitpart." + this.name + ".description");
        this.details = Core.bundle.getOrNull("unitpart." + this.name + ".details");
        this.customShadow = true;
        this.placeablePlayer = false;
        this.solid = true;
        this.getContentType();
    }
    /*
    public ContentType getContentType() {
        return ContentType.effect_UNUSED;
    }
     */
}

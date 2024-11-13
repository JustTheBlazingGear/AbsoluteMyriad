package am.type;

import am.content.AMItems;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.ammo.ItemAmmoType;

public class VoltarUnitType extends UnitType {
    public VoltarUnitType(String name) {
        super(name);
        this.outlineColor = Pal.darkOutline;
        this.ammoType = new ItemAmmoType(AMItems.oxidizedCopper);
    }
}


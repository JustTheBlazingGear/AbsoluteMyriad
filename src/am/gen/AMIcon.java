package am.gen;

import arc.scene.style.TextureRegionDrawable;
import arc.struct.ObjectMap;
import mindustry.ui.Fonts;

public class AMIcon {
    public static final ObjectMap<String, TextureRegionDrawable> icons = new ObjectMap();
    public static TextureRegionDrawable cruxEasy;
    public static TextureRegionDrawable cruxNormal;
    public static TextureRegionDrawable cruxHard;
    public static TextureRegionDrawable cruxExtreme;
    public static TextureRegionDrawable cruxEradication;

    public AMIcon() {
    }

    public static void load() {
        cruxEasy = Fonts.getGlyph(Fonts.icon, '\uf000');
        icons.put("cruxEasy", cruxEasy);
        cruxNormal = Fonts.getGlyph(Fonts.icon, '\uf001');
        icons.put("cruxNormal", cruxNormal);
        cruxHard = Fonts.getGlyph(Fonts.icon, '\uf002');
        icons.put("cruxHard", cruxHard);
        cruxExtreme = Fonts.getGlyph(Fonts.icon, '\uf003');
        icons.put("cruxExtreme", cruxExtreme);
        cruxEradication = Fonts.getGlyph(Fonts.icon, '\uf004');
        icons.put("cruxEradication", cruxEradication);
    }
}

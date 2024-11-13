package am.ctype;

import am.world.UnitPart;
import arc.util.Nullable;
import mindustry.ctype.Content;

public enum AMContentType {

    unitPart(UnitPart.class);

    public static final AMContentType[] all = values();
    @Nullable
    public final Class<? extends Content> contentClass;

    private AMContentType(Class contentClass) {
        this.contentClass = contentClass;
    }
}

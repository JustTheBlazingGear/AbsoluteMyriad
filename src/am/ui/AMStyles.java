package am.ui;

import am.graphics.AMPal;
import arc.Core;
import arc.graphics.Color;
import arc.scene.style.Drawable;
import arc.scene.style.TextureRegionDrawable;
import arc.scene.ui.Button;
import arc.scene.ui.ImageButton;
import mindustry.gen.Tex;
import mindustry.graphics.Pal;
import mindustry.ui.Styles;

import static mindustry.gen.Tex.*;

public class AMStyles {
    public static Drawable invis;
    public static Drawable accent;
    public static ImageButton.ImageButtonStyle difficulties;
    public static ImageButton.ImageButtonStyle SerpuloHard;
    public static ImageButton.ImageButtonStyle SerpuloExtreme;
    public static ImageButton.ImageButtonStyle SerpuloEradication;

    public AMStyles() {
    }

    public static void load() {
        final TextureRegionDrawable whiteui = (TextureRegionDrawable)Tex.whiteui;
        invis = whiteui.tint(1F, 1F, 1F, 0F);
        accent = whiteui.tint(1.0F, 0.82F, 0.49F, 1.0F);
        difficulties = new ImageButton.ImageButtonStyle() {
            {
                this.up = Tex.pane;
                this.over = Styles.flatDown;
                this.down = accent;
                this.imageUpColor = Color.gray;  //still
                this.imageOverColor = Color.white;  //hover
                this.imageDownColor = Color.lightGray;  //click
            }
        };
        SerpuloHard = new ImageButton.ImageButtonStyle() {
            {
                this.up = Core.atlas.drawable("am-crux-hard-icon");;
                this.over = Styles.flatDown;
                this.down = accent;
                this.imageUpColor = AMPal.invis;
                this.imageOverColor = Color.white;
                this.imageDownColor = Color.lightGray;
            }
        };
        SerpuloExtreme = new ImageButton.ImageButtonStyle() {
            {
                this.up = Core.atlas.drawable("am-crux-extreme-icon");;
                this.over = Styles.flatDown;
                this.down = accent;
                this.imageUpColor = AMPal.invis;
                this.imageOverColor = Color.white;
                this.imageDownColor = Color.lightGray;
            }
        };
        SerpuloEradication = new ImageButton.ImageButtonStyle() {
            {
                this.up = Core.atlas.drawable("am-crux-eradication-icon");;
                this.over = Styles.flatDown;
                this.down = accent;
                this.imageUpColor = AMPal.invis;
                this.imageOverColor = Color.white;
                this.imageDownColor = Color.lightGray;
            }
        };
    }
}

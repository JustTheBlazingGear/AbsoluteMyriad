package am.content;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Font;
import arc.graphics.g2d.TextureRegion;
import arc.math.geom.Vec2;
import arc.struct.ObjectIntMap;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import arc.util.Reflect;
import arc.util.Scaling;
import mindustry.game.Team;
import mindustry.ui.Fonts;

public class AMTeams {
    public static Team hexan;
    public static Team vestor;
    public static Team blaze;

    public static void load() {
        hexan = newTeam(71, "hexan", Color.valueOf("54d67d"));
        vestor = newTeam(72, "vestor", Color.valueOf("a5c0dc"));
        blaze = newTeam(73, "blaze", Color.valueOf("fab46f"));
    }

    private static Team newTeam(int id, String name, Color color) {
        Team team = Team.get(id);
        team.name = name;
        team.color.set(color);
        team.palette[0] = color;
        team.palette[1] = color.cpy().mul(0.75F);
        team.palette[2] = color.cpy().mul(0.5F);

        for(int i = 0; i < 3; ++i) {
            team.palettei[i] = team.palette[i].rgba();
        }
        return team;
    }
}

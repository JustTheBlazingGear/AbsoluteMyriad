package am.ui.dialogs;
/*
import arc.Core;
import arc.graphics.Color;
import arc.scene.ui.Button;
import arc.scene.ui.ScrollPane;
import arc.scene.ui.layout.Table;
import arc.struct.Array;
import java.util.Iterator;
import mindustry.Vars;
import mindustry.game.Objective;
import mindustry.game.Objectives;
import mindustry.game.Rules;
import mindustry.gen.Icon;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.type.Zone;
import mindustry.ui.Cicon;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.ui.dialogs.LoadoutDialog;

public class ZoneInfoDialog extends BaseDialog {
    private LoadoutDialog loadout = new LoadoutDialog();

    public ZoneInfoDialog() {
        super("");
        this.titleTable.remove();
        this.addCloseButton();
    }

    public void show(Zone zone) {
        this.setup(zone);
        this.show();
    }

    private void setup(Zone zone) {
        this.cont.clear();
        Table iteminfo = new Table();
        Runnable rebuildItems = () -> {
            int i = 0;
            iteminfo.clear();
            if (zone.unlocked()) {
                Iterator var3 = zone.getLaunchCost().iterator();

                while(var3.hasNext()) {
                    ItemStack stack = (ItemStack)var3.next();
                    if (stack.amount != 0) {
                        if (i++ % 2 == 0) {
                            iteminfo.row();
                        }

                        iteminfo.addImage(stack.item.icon(Cicon.small)).size(24.0F).padRight(1.0F);
                        iteminfo.add(stack.amount + "").color(Color.lightGray).padRight(5.0F);
                    }
                }

            }
        };
        rebuildItems.run();
        this.cont.pane((cont) -> {
            if (zone.locked()) {
                cont.addImage(Icon.lock);
                cont.row();
                cont.add("$locked").padBottom(6.0F);
                cont.row();
                cont.table((req) -> {
                    req.defaults().left();
                    Array<Objective> zones = zone.requirements.select((o) -> {
                        return !(o instanceof Objectives.Unlock);
                    });
                    if (!zones.isEmpty()) {
                        req.table((r) -> {
                            r.add("$complete").colspan(2).left();
                            r.row();
                            Iterator var2 = zones.iterator();

                            while(var2.hasNext()) {
                                Objective o = (Objective)var2.next();
                                r.addImage(Icon.terrain).padRight(4.0F);
                                r.add(o.display()).color(Color.lightGray);
                                r.addImage(o.complete() ? Icon.ok : Icon.cancel, o.complete() ? Color.lightGray : Color.scarlet).padLeft(3.0F);
                                r.row();
                            }

                        });
                    }

                    req.row();
                    Array<Objectives.Unlock> blocks = zone.requirements.select((o) -> {
                        return o instanceof Objectives.Unlock;
                    }).as(Objectives.Unlock.class);
                    if (!blocks.isEmpty()) {
                        req.table((r) -> {
                            r.add("$research.list").colspan(2).left();
                            r.row();
                            Iterator var2 = blocks.iterator();

                            while(var2.hasNext()) {
                                Objectives.Unlock blocko = (Objectives.Unlock)var2.next();
                                r.addImage(blocko.block.icon(Cicon.small)).size(24.0F).padRight(5.0F);
                                r.add(blocko.block.localizedName).color(Color.lightGray).left();
                                r.addImage(blocko.block.unlocked() ? Icon.ok : Icon.cancel, blocko.block.unlocked() ? Color.lightGray : Color.scarlet).padLeft(3.0F);
                                r.row();
                            }

                        }).padTop(10.0F);
                    }

                }).growX();
            } else {
                cont.add(zone.localizedName).color(Pal.accent).growX().center();
                cont.row();
                cont.addImage().color(Pal.accent).height(3.0F).pad(6.0F).growX();
                cont.row();
                cont.table((desc) -> {
                    desc.left().defaults().left().width(Core.graphics.isPortrait() ? 350.0F : 500.0F);
                    ((ScrollPane)desc.pane((t) -> {
                        t.marginRight(12.0F).add(zone.description).wrap().growX();
                    }).fillX().maxHeight(Vars.mobile ? 300.0F : 450.0F).pad(2.0F).padBottom(8.0F).get()).setScrollingDisabled(true, false);
                    desc.row();
                    desc.table((t) -> {
                        t.left();
                        t.add("$zone.resources").padRight(6.0F);
                        if (zone.resources.size > 0) {
                            t.table((r) -> {
                                t.left();
                                int i = 0;
                                Iterator var4 = zone.resources.iterator();

                                while(var4.hasNext()) {
                                    Item item = (Item)var4.next();
                                    r.addImage(item.icon(Cicon.small)).size(24.0F);
                                    ++i;
                                    if (i % 4 == 0) {
                                        r.row();
                                    }
                                }

                            });
                        } else {
                            t.add("$none");
                        }

                    });
                    Rules rules = zone.getRules();
                    desc.row();
                    desc.add(Core.bundle.format("zone.objective", new Object[]{Core.bundle.get(!rules.attackMode ? "zone.objective.survival" : "zone.objective.attack")}));
                    if (zone.bestWave() > 0) {
                        desc.row();
                        desc.add(Core.bundle.format("bestwave", new Object[]{zone.bestWave()}));
                    }

                });
                cont.row();
            }

            cont.marginRight(12.0F);
        });
        this.cont.row();
        this.cont.addButton(zone.canConfigure() ? "$configure" : Core.bundle.format("configure.locked", new Object[]{zone.configureObjective.display()}), () -> {
            this.loadout.show(zone.loadout.findCore().itemCapacity, zone.getStartingItems(), zone::resetStartingItems, zone::updateLaunchCost, rebuildItems);
        }).fillX().pad(3.0F).disabled((b) -> {
            return !zone.canConfigure();
        });
        this.cont.row();
        Button button = (Button)this.cont.addButton(zone.locked() ? "$uncover" : "$launch", () -> {
            if (!Vars.data.isUnlocked(zone)) {
                Sounds.unlock.play();
                Vars.data.unlockContent(zone);
                Vars.ui.deploy.setup();
                this.setup(zone);
            } else {
                Vars.ui.deploy.hide();
                Vars.data.removeItems(zone.getLaunchCost());
                this.hide();
                Vars.control.playZone(zone);
            }

        }).minWidth(200.0F).margin(13.0F).padTop(5.0F).disabled((b) -> {
            return zone.locked() ? !zone.canUnlock() : !Vars.data.hasItems(zone.getLaunchCost());
        }).uniformY().get();
        button.row();
        button.add(iteminfo);
    }
}
 */
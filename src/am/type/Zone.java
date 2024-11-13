package am.type;
/*
import arc.Core;
import arc.Events;
import arc.func.Cons;
import arc.graphics.g2d.TextureRegion;
import arc.scene.ui.layout.Table;
import arc.struct.Array;
import arc.util.ArcAnnotate.NonNull;
import java.util.Iterator;
import mindustry.Vars;
import mindustry.content.Loadouts;
import mindustry.ctype.ContentType;
import mindustry.ctype.UnlockableContent;
import mindustry.game.EventType;
import mindustry.game.Objective;
import mindustry.game.Objectives;
import mindustry.game.Rules;
import mindustry.game.Schematic;
import mindustry.maps.generators.Generator;
import mindustry.maps.generators.MapGenerator;

public class Zone extends UnlockableContent {
    @NonNull
    public Generator generator;
    @NonNull
    public Objective configureObjective;
    public Array<Objective> requirements;
    public Array<Item> resources;
    public Cons<Rules> rules;
    public boolean alwaysUnlocked;
    public int conditionWave;
    public int launchPeriod;
    public Schematic loadout;
    public TextureRegion preview;
    protected Array<ItemStack> baseLaunchCost;
    protected Array<ItemStack> startingItems;
    protected Array<ItemStack> launchCost;
    private Array<ItemStack> defaultStartingItems;

    public Zone(String name, Generator generator) {
        super(name);
        this.configureObjective = new Objectives.ZoneWave(this, 15);
        this.requirements = new Array();
        this.resources = new Array();
        this.rules = (rules) -> {
        };
        this.conditionWave = Integer.MAX_VALUE;
        this.launchPeriod = 10;
        this.loadout = Loadouts.basicShard;
        this.baseLaunchCost = new Array();
        this.startingItems = new Array();
        this.defaultStartingItems = new Array();
        this.generator = generator;
    }

    public Zone(String name) {
        this(name, new MapGenerator(name));
    }

    public void load() {
        this.preview = Core.atlas.find("zone-" + this.name, Core.atlas.find(this.name + "-zone"));
    }

    public Rules getRules() {
        if (this.generator instanceof MapGenerator) {
            return ((MapGenerator)this.generator).getMap().rules();
        } else {
            Rules rules = new Rules();
            this.rules.get(rules);
            return rules;
        }
    }

    public boolean isLaunchWave(int wave) {
        return this.metCondition() && wave % this.launchPeriod == 0;
    }

    public boolean canUnlock() {
        return Vars.data.isUnlocked(this) || !this.requirements.contains((r) -> {
            return !r.complete();
        });
    }

    public Array<ItemStack> getLaunchCost() {
        if (this.launchCost == null) {
            this.updateLaunchCost();
        }

        return this.launchCost;
    }

    public Array<ItemStack> getStartingItems() {
        return this.startingItems;
    }

    public void resetStartingItems() {
        this.startingItems.clear();
        this.defaultStartingItems.each((stack) -> {
            this.startingItems.add(new ItemStack(stack.item, stack.amount));
        });
    }

    public boolean hasLaunched() {
        return Core.settings.getBool(this.name + "-launched", false);
    }

    public void setLaunched() {
        this.updateObjectives(() -> {
            Core.settings.put(this.name + "-launched", true);
            Vars.data.modified();
        });
    }

    public void updateWave(int wave) {
        int value = Core.settings.getInt(this.name + "-wave", 0);
        if (value < wave) {
            this.updateObjectives(() -> {
                Core.settings.put(this.name + "-wave", wave);
                Vars.data.modified();
            });
        }

    }

    public void updateObjectives(Runnable closure) {
        Array<Objectives.ZoneObjective> incomplete = Vars.content.zones().map((z) -> {
            return z.requirements;
        }).flatten().select((o) -> {
            return o.zone() == this && !o.complete();
        }).as(Objectives.ZoneObjective.class);
        boolean wasConfig = this.configureObjective.complete();
        closure.run();
        Iterator var4 = incomplete.iterator();

        while(var4.hasNext()) {
            Objectives.ZoneObjective objective = (Objectives.ZoneObjective)var4.next();
            if (objective.complete()) {
                Events.fire(new EventType.ZoneRequireCompleteEvent(objective.zone, (Zone)Vars.content.zones().find((z) -> {
                    return z.requirements.contains(objective);
                }), objective));
            }
        }

        if (!wasConfig && this.configureObjective.complete()) {
            Events.fire(new EventType.ZoneConfigureCompleteEvent(this));
        }

    }

    public int bestWave() {
        return Core.settings.getInt(this.name + "-wave", 0);
    }

    public boolean isLaunchMet() {
        return this.bestWave() >= this.conditionWave;
    }

    public void updateLaunchCost() {
        Array<ItemStack> stacks = new Array();
        Cons<ItemStack> adder = (stackx) -> {
            Iterator var2 = stacks.iterator();

            ItemStack other;
            do {
                if (!var2.hasNext()) {
                    stacks.add(new ItemStack(stackx.item, stackx.amount));
                    return;
                }

                other = (ItemStack)var2.next();
            } while(other.item != stackx.item);

            other.amount += stackx.amount;
        };
        Iterator var3 = this.baseLaunchCost.iterator();

        ItemStack stack;
        while(var3.hasNext()) {
            stack = (ItemStack)var3.next();
            adder.get(stack);
        }

        var3 = this.startingItems.iterator();

        while(var3.hasNext()) {
            stack = (ItemStack)var3.next();
            adder.get(stack);
        }

        var3 = stacks.iterator();

        while(var3.hasNext()) {
            stack = (ItemStack)var3.next();
            if (stack.amount < 0) {
                stack.amount = 0;
            }
        }

        stacks.sort();
        this.launchCost = stacks;
        Core.settings.putObject(this.name + "-starting-items", this.startingItems);
        Vars.data.modified();
    }

    public boolean metCondition() {
        return Vars.state.wave >= this.conditionWave && !Vars.state.rules.attackMode;
    }

    public boolean canConfigure() {
        return this.configureObjective.complete();
    }

    public void init() {
        if (this.generator instanceof MapGenerator && this.minfo.mod != null) {
            ((MapGenerator)this.generator).removePrefix(this.minfo.mod.name);
        }

        this.generator.init(this.loadout);
        this.resources.sort();
        Iterator var1 = this.startingItems.iterator();

        while(var1.hasNext()) {
            ItemStack stack = (ItemStack)var1.next();
            this.defaultStartingItems.add(new ItemStack(stack.item, stack.amount));
        }

        Array<ItemStack> arr = (Array)Core.settings.getObject(this.name + "-starting-items", Array.class, () -> {
            return null;
        });
        if (arr != null) {
            this.startingItems = arr;
        }

    }

    public boolean alwaysUnlocked() {
        return this.alwaysUnlocked;
    }

    public boolean isHidden() {
        return true;
    }

    public void displayInfo(Table table) {
    }

    public ContentType getContentType() {
        return ContentType.zone;
    }
}
 */
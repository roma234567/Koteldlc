package Kotel.core.module;

import Kotel.modules.combat.KillAura;
import Kotel.modules.misc.*;
import Kotel.modules.movement.*;
import Kotel.modules.player.*;
import Kotel.modules.visual.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public void registerDefaults() {
        if (!modules.isEmpty()) return;
        register(new KillAura());
        register(new Speed()); register(new Fly()); register(new NoFall()); register(new NoSlow()); register(new Strafe());
        register(new Jesus()); register(new LongJump()); register(new ElytraGlide()); register(new SafeWalk()); register(new InventoryMove()); register(new Sprint());
        register(new ESP()); register(new TargetHUD()); register(new HUD()); register(new Fullbright()); register(new Chams()); register(new HandChams());
        register(new Particles()); register(new JumpCircle()); register(new KillEffect()); register(new HitEffect()); register(new BlockESP()); register(new ChinaHat());
        register(new CrossHair()); register(new SwingAnimation()); register(new ViewModel()); register(new CameraSettings()); register(new AspectRatio()); register(new FreeCam());
        register(new NoRender()); register(new BetterMinecraft()); register(new SeeInvisible()); register(new AuctionHelper()); register(new ProjectilePrediction()); register(new TargetESP()); register(new Arrows());
        register(new AutoAccept()); register(new AutoAuth()); register(new AutoRespawn()); register(new AutoResell()); register(new AutoJoin()); register(new AutoDuels());
        register(new Disabler()); register(new NameProtect()); register(new AntiCheatDetector()); register(new InventoryCleaner());
        register(new AutoArmor()); register(new AutoTool()); register(new NoSlowBreak());
        modules.sort(Comparator.comparing(Module::category).thenComparing(Module::name));
    }

    public void register(Module module) { modules.add(module); }
    public List<Module> modules() { return Collections.unmodifiableList(modules); }
    public Optional<Module> byName(String name) { return modules.stream().filter(module -> module.name().equalsIgnoreCase(name)).findFirst(); }
    public List<Module> byCategory(ModuleCategory category) { return modules.stream().filter(module -> module.category() == category).toList(); }
}

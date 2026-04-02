package com.koteldlc.client.module;

import com.koteldlc.client.config.modules.BypassProfileRepository;
import com.koteldlc.client.module.impl.SimpleToggleModule;
import com.koteldlc.client.module.impl.combat.AttackAuraModule;
import com.koteldlc.client.module.impl.combat.AutoTotemModule;
import com.koteldlc.client.module.impl.movement.FlyModule;
import com.koteldlc.client.module.impl.movement.SpeedModule;
import com.koteldlc.client.module.modules.combat.*;
import com.koteldlc.client.module.modules.movement.NoFall;
import com.koteldlc.client.module.modules.movement.Sprint;
import com.koteldlc.client.module.modules.movement.Strafe;
import com.koteldlc.client.module.modules.visual.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();
    private final BypassProfileRepository bypassProfiles = new BypassProfileRepository();

    public void registerDefaults() {
        // Combat (как на скринах)
        register(new AttackAuraModule(bypassProfiles));
        register(new AutoTotemModule());
        register(new AutoSwap());
        register(new TriggerBot());
        register(new Hitboxes());
        register(new NoFriendDamage());
        register(new NoSlotChange());
        register(new NoVelocity());
        register(new PacketCriticals());
        register(new WTap());
        register(new WebTrap());

        // Movement
        register(new SpeedModule(bypassProfiles));
        register(new FlyModule(bypassProfiles));
        register(new Sprint());
        register(new Strafe());
        register(new NoFall());
        register(new SimpleToggleModule("No Web", "Убирает замедление паутины", Category.MOVEMENT));
        register(new SimpleToggleModule("Phase", "Проход сквозь блоки", Category.MOVEMENT));
        register(new SimpleToggleModule("Target Strafe", "Стрейф вокруг цели", Category.MOVEMENT));

        // Visuals + Theme
        register(new ThemeEditor());
        register(new ESP());
        register(new JumpCircles());
        register(new Trail());
        register(new TargetHUD());
        register(new TargetESP());
        register(new HurtTimeESP());
        register(new InvulnerableESP());
        register(new SneakingESP());
        register(new GlidingESP());
        register(new SleepingESP());
        register(new SimpleToggleModule("Interface", "Настройки интерфейса", Category.RENDER));
        register(new SimpleToggleModule("Particles", "Настройки частиц", Category.RENDER));
        register(new SimpleToggleModule("Tracers", "Линии к игрокам", Category.RENDER));

        // Player / Misc из скрина
        register(new SimpleToggleModule("Auto Tool", "Автовыбор инструмента", Category.PLAYER));
        register(new SimpleToggleModule("Blink", "Отложенная отправка пакетов", Category.PLAYER));
        register(new SimpleToggleModule("Fast Break", "Быстрое ломание", Category.PLAYER));
        register(new SimpleToggleModule("No Interact", "Блок лишних интеракций", Category.PLAYER));

        register(new SimpleToggleModule("Potion Tracker", "Трекер эффектов", Category.MISC));
        register(new SimpleToggleModule("Streamer Mode", "Скрытие чувствительных данных", Category.MISC));
        register(new SimpleToggleModule("Voice Chat", "Интеграция голосового чата", Category.MISC));

        // HUD
        register(new InGameTimeHUD());
        register(new RealTimeHUD());
        register(new SessionTimeHUD());
        register(new ServerTimeHUD());

        // Backward compatibility module from previous PR
        register(new AIKillAura());
    }

    public void register(Module module) { modules.add(module); }

    public List<Module> getModules() { return Collections.unmodifiableList(modules); }

    public Module getByName(String name) {
        return modules.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    private static final class AutoSwap extends SimpleToggleModule {
        private AutoSwap() { super("Auto Swap", "Автосвап предметов", Category.COMBAT); }
    }
    private static final class NoFriendDamage extends SimpleToggleModule {
        private NoFriendDamage() { super("No Friend Damage", "Не бить друзей", Category.COMBAT); }
    }
    private static final class NoSlotChange extends SimpleToggleModule {
        private NoSlotChange() { super("No Slot Change", "Не менять слот", Category.COMBAT); }
    }
    private static final class NoVelocity extends SimpleToggleModule {
        private NoVelocity() { super("No Velocity", "Отключить откидывание", Category.COMBAT); }
    }
    private static final class PacketCriticals extends SimpleToggleModule {
        private PacketCriticals() { super("Packet Criticals", "Криты через пакеты", Category.COMBAT); }
    }
    private static final class WTap extends SimpleToggleModule {
        private WTap() { super("WTap", "Авто WTap", Category.COMBAT); }
    }
    private static final class WebTrap extends SimpleToggleModule {
        private WebTrap() { super("Web Trap", "Ловушка паутиной", Category.COMBAT); }
    }
}

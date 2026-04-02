package com.koteldlc.client.module;

import com.koteldlc.client.config.modules.BypassProfileRepository;
import com.koteldlc.client.module.impl.SimpleToggleModule;
import com.koteldlc.client.module.impl.combat.AttackAuraModule;
import com.koteldlc.client.module.impl.combat.AimAssistModule;
import com.koteldlc.client.module.impl.combat.AutoTotemModule;
import com.koteldlc.client.module.impl.combat.SilentHitboxesModule;
import com.koteldlc.client.module.impl.movement.FlyModule;
import com.koteldlc.client.module.impl.movement.SpeedModule;
import com.koteldlc.client.module.impl.visual.ESPModule;
import com.koteldlc.client.module.modules.combat.AIKillAura;
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
        register(new TriggerBot());
        register(new Hitboxes());
        register(new JumpCircles());
        register(new Trail());
        register(new ESP());
        register(new TargetHUD());
        register(new KillAura());
        register(new AIKillAura());
        register(new TeleportAura());
        register(new Speed());
        register(new NoFall());
        register(new Sprint());
        register(new Strafe());
        register(new NoFall());
        registerBatch(Category.MOVEMENT,
                "No Web", "Phase", "Target Strafe", "Timer", "Water Leave", "Water Speed", "No Slow",
                "Wall Climb", "Air Jump", "Auto Jump", "Elytra Target", "Movement Helper"
        );

        register(new ThemeEditor());

        // ESP Settings
        register(new HurtTimeESP());
        register(new InvulnerableESP());
        register(new SneakingESP());
        register(new GlidingESP());
        register(new SleepingESP());
        registerBatch(Category.RENDER,
                "Hands", "Interface", "Item Physics", "Particles", "Prediction", "Removals", "See Invisibles",
                "Shulker Preview", "Sword Animations", "Tags", "Third Person", "Tracers", "Trajectory", "View Model",
                "Ambience", "Armor Durability View", "Arrows", "Crosshair", "Entity ESP", "Full Bright"
        );

        registerBatch(Category.PLAYER,
                "Auto Tool", "Blink", "Click Pearl", "Fast Break", "Fast Exp", "Instant Respawn",
                "Item Release", "Item Scroller", "Items Cooldown", "No Entity Trace", "No Interact", "Nuker",
                "Auto Armor", "Auto Eat", "Auto Potion", "Auto Respawn"
        );

        registerBatch(Category.MISC,
                "No Server Rotation", "Open Walls", "Party Point", "Potion Tracker", "Really World Helper",
                "SRPSpoofer", "Scoreboard Health", "Streamer Mode", "Tape Mouse", "Toggle Sounds", "Use Tracker",
                "Voice Chat", "Air Place", "Auction Helper", "Auto Accept", "Auto Auth", "Chat Helper", "Discord Activity"
        );

        register(new InGameTimeHUD());
        register(new RealTimeHUD());
        register(new SessionTimeHUD());
        register(new ServerTimeHUD());

        register(new AIKillAura());
    }

    private void registerBatch(Category category, String... names) {
        for (String name : names) {
            register(new SimpleToggleModule(name, name + " module", category));
        }
    }

    public void register(Module module) { modules.add(module); }

    public List<Module> getModules() { return Collections.unmodifiableList(modules); }

    public Module getByName(String name) {
        return modules.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}

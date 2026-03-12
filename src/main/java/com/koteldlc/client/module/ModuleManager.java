package com.koteldlc.client.module;

import com.koteldlc.client.module.modules.combat.*;
import com.koteldlc.client.module.modules.movement.*;
import com.koteldlc.client.module.modules.visual.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public void registerDefaults() {
        register(new TriggerBot());
        register(new Hitboxes());
        register(new JumpCircles());
        register(new Trail());
        register(new ESP());
        register(new TargetHUD());
        register(new KillAura());
        register(new TeleportAura());
        register(new Speed());
        register(new NoFall());
        register(new Sprint());
        register(new Strafe());

        // ESP Settings
        register(new HurtTimeESP());
        register(new InvulnerableESP());
        register(new SneakingESP());
        register(new GlidingESP());
        register(new SleepingESP());
        register(new TargetESP());

        // HUD Settings
        register(new InGameTimeHUD());
        register(new RealTimeHUD());
        register(new SessionTimeHUD());
        register(new ServerTimeHUD());
    }

    public void register(Module module) { modules.add(module); }

    public List<Module> getModules() { return Collections.unmodifiableList(modules); }

    public Module getByName(String name) {
        return modules.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}

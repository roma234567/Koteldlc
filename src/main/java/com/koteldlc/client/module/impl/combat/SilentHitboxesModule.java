package com.koteldlc.client.module.impl.combat;

import com.koteldlc.client.config.modules.BypassProfileRepository;
import com.koteldlc.client.gui.settings.BooleanSetting;
import com.koteldlc.client.gui.settings.SliderSetting;
import com.koteldlc.client.module.Category;
import com.koteldlc.client.module.Module;

public class SilentHitboxesModule extends Module {
    private final SliderSetting hitboxSize;
    private final SliderSetting serverAimSpeed;
    private final BooleanSetting serverOnly;

    public SilentHitboxesModule(BypassProfileRepository repository) {
        super("Silent Hitboxes", "Серверный hitbox expand без дерганья камеры", -1, Category.COMBAT);
        hitboxSize = register(new SliderSetting("hitboxSize", 3.0, 1.0, 15.0, 0.5));
        serverAimSpeed = register(new SliderSetting("serverAimSpeed", 0.3, 0.05, 1.0, 0.05));
        serverOnly = register(new BooleanSetting("serverOnly", true));
        repository.registerDynamicSettings(this, "combat", "silent_hitboxes");
        repository.applyValueOverrides(this, "combat", "silent_hitboxes");
    }

    public float getHitboxSize() {
        return hitboxSize.getValue().floatValue();
    }

    public float getServerAimSpeed() {
        return serverAimSpeed.getValue().floatValue();
    }

    public boolean isServerOnly() {
        return serverOnly.getValue();
    }
}

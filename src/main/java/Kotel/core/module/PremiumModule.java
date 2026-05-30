package Kotel.core.module;

import Kotel.core.setting.BooleanSetting;
import Kotel.core.setting.ModeSetting;
import Kotel.core.setting.NumberSetting;
import java.util.List;

/**
 * Shared base for polished KotelDLC modules.
 * Modules expose settings, status text, and lifecycle hooks only; integration with
 * Minecraft/Fabric events can be added later in places where it is permitted.
 */
public abstract class PremiumModule extends Module {
    private final BooleanSetting notifications = add(new BooleanSetting("Notifications", true));
    private final ModeSetting profile = add(new ModeSetting("Profile", "Legit", List.of("Legit", "Balanced", "Premium")));
    private String status = "Ready";

    protected PremiumModule(String name, String description, ModuleCategory category) {
        super(name, description, category);
    }

    protected NumberSetting number(String name, double value, double min, double max) {
        return add(new NumberSetting(name, value, min, max));
    }

    protected BooleanSetting bool(String name, boolean value) {
        return add(new BooleanSetting(name, value));
    }

    protected ModeSetting mode(String name, String value, String... modes) {
        return add(new ModeSetting(name, value, List.of(modes)));
    }

    protected void status(String status) {
        this.status = status;
    }

    public String statusLine() {
        return name() + " • " + status + " • " + profile.value();
    }

    public BooleanSetting notifications() {
        return notifications;
    }

    public ModeSetting profile() {
        return profile;
    }
}

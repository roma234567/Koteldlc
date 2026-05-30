package Kotel.modules.movement;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class Speed extends PremiumModule {
    public Speed() {
        super("Speed", "Movement speed profile with HUD telemetry.", ModuleCategory.MOVEMENT);
        number("Multiplier", 1.0, 0.1, 3.0);
        mode("Mode", "Vanilla", "Vanilla", "Smooth", "Timer Preview");
        status("Configured");
    }
}

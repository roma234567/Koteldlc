package Kotel.modules.combat;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class AimAssistModule extends PremiumModule {
    public AimAssistModule() {
        super("AimAssistModule", "Smooth aim practice helper with configurable sensitivity preview.", ModuleCategory.COMBAT);
        number("Smoothness", 65.0, 1.0, 100.0);
        number("FOV", 45.0, 1.0, 180.0);
        mode("Curve", "Ease Out", "Linear", "Ease Out", "Bezier");
        status("Configured");
    }
}

package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class Particles extends PremiumModule {
    public Particles() {
        super("Particles", "Custom particle style manager.", ModuleCategory.VISUAL);
        mode("Pack", "Lucky Stars", "Lucky Stars", "Smoke", "Minimal");
        number("Density", 0.7, 0.0, 2.0);
        status("Configured");
    }
}

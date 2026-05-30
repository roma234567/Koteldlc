package Kotel.modules.movement;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class ElytraGlide extends PremiumModule {
    public ElytraGlide() {
        super("ElytraGlide", "Elytra glide angle calculator and descent preview.", ModuleCategory.MOVEMENT);
        number("Pitch", -5.0, -45.0, 45.0);
        bool("Trajectory", true);
        status("Configured");
    }
}

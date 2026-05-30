package Kotel.modules.movement;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class Strafe extends PremiumModule {
    public Strafe() {
        super("Strafe", "Strafe angle visualizer and movement trainer.", ModuleCategory.MOVEMENT);
        number("Air Control", 60.0, 0.0, 100.0);
        mode("Style", "Circular", "Circular", "Strict", "Free");
        status("Configured");
    }
}

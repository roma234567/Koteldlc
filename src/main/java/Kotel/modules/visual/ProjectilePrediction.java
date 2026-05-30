package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class ProjectilePrediction extends PremiumModule {
    public ProjectilePrediction() {
        super("ProjectilePrediction", "Projectile trajectory preview.", ModuleCategory.VISUAL);
        bool("Landing Dot", true);
        number("Steps", 48, 8, 128);
        status("Configured");
    }
}

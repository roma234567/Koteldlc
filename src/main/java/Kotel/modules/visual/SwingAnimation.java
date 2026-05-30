package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class SwingAnimation extends PremiumModule {
    public SwingAnimation() {
        super("SwingAnimation", "First-person swing animation preset.", ModuleCategory.VISUAL);
        mode("Preset", "Lucky", "Lucky", "Slide", "Classic");
        number("Speed", 1.0, 0.1, 3.0);
        status("Configured");
    }
}

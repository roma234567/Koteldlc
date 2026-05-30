package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class JumpCircle extends PremiumModule {
    public JumpCircle() {
        super("JumpCircle", "Animated ring spawned after jumps.", ModuleCategory.VISUAL);
        number("Lifetime", 650, 100, 3000);
        mode("Shape", "Circle", "Circle", "Star", "Wave");
        status("Configured");
    }
}

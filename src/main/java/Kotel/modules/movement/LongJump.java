package Kotel.modules.movement;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class LongJump extends PremiumModule {
    public LongJump() {
        super("LongJump", "Long-jump charge meter for parkour practice.", ModuleCategory.MOVEMENT);
        number("Charge", 1.4, 0.1, 5.0);
        bool("Show Arc", true);
        status("Configured");
    }
}

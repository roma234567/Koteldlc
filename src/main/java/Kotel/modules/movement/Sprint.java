package Kotel.modules.movement;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class Sprint extends PremiumModule {
    public Sprint() {
        super("Sprint", "Automatic sprint preference and stamina HUD flag.", ModuleCategory.MOVEMENT);
        bool("Omni Sprint", false);
        bool("Keep Sprint", true);
        status("Configured");
    }
}

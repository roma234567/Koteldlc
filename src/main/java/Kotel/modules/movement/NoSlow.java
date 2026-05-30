package Kotel.modules.movement;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class NoSlow extends PremiumModule {
    public NoSlow() {
        super("NoSlow", "Movement slowdown analyzer for item-use and block states.", ModuleCategory.MOVEMENT);
        bool("Food", true);
        bool("Bow", true);
        bool("Shield", true);
        status("Configured");
    }
}

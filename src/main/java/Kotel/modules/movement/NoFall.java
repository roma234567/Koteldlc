package Kotel.modules.movement;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class NoFall extends PremiumModule {
    public NoFall() {
        super("NoFall", "Fall distance warning and landing assist indicator.", ModuleCategory.MOVEMENT);
        number("Warn Distance", 3.0, 1.0, 20.0);
        bool("Landing Overlay", true);
        status("Configured");
    }
}

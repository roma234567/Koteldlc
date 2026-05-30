package Kotel.modules.misc;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class AutoRespawn extends PremiumModule {
    public AutoRespawn() {
        super("AutoRespawn", "Respawn screen notifier and replay-safe timer.", ModuleCategory.MISC);
        number("Delay", 1.0, 0.0, 10.0);
        bool("Show Death Stats", true);
        status("Configured");
    }
}

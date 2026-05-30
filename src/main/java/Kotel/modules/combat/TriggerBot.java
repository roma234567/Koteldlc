package Kotel.modules.combat;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class TriggerBot extends PremiumModule {
    public TriggerBot() {
        super("TriggerBot", "Crosshair timing trainer that highlights valid attack windows.", ModuleCategory.COMBAT);
        number("Reaction Delay", 120.0, 0.0, 500.0);
        bool("Require Manual Click", true);
        mode("Target Filter", "Players", "Players", "Mobs", "All");
        status("Configured");
    }
}

package Kotel.modules.misc;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class AutoDuels extends PremiumModule {
    public AutoDuels() {
        super("AutoDuels", "Duel invite organizer and queue reminder.", ModuleCategory.MISC);
        mode("Kit", "Classic", "Classic", "UHC", "Crystal");
        bool("Friends Only", true);
        status("Configured");
    }
}

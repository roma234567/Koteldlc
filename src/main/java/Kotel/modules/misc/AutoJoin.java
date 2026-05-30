package Kotel.modules.misc;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class AutoJoin extends PremiumModule {
    public AutoJoin() {
        super("AutoJoin", "Server/mode join shortcut list.", ModuleCategory.MISC);
        mode("Mode", "Hub", "Hub", "Duels", "Anarchy", "Custom");
        bool("Confirm", true);
        status("Configured");
    }
}

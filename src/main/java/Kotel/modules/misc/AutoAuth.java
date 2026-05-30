package Kotel.modules.misc;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class AutoAuth extends PremiumModule {
    public AutoAuth() {
        super("AutoAuth", "Local credential reminder and masked auth helper.", ModuleCategory.MISC);
        bool("Mask Password", true);
        mode("Action", "Notify", "Notify", "Copy Command", "Disabled");
        status("Configured");
    }
}

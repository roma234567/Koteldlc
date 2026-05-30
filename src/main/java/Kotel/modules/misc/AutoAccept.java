package Kotel.modules.misc;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class AutoAccept extends PremiumModule {
    public AutoAccept() {
        super("AutoAccept", "Teleport request notification and safe-confirm workflow.", ModuleCategory.MISC);
        bool("Require Confirm", true);
        number("Expire Seconds", 15, 1, 120);
        status("Configured");
    }
}

package Kotel.modules.misc;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class AutoResell extends PremiumModule {
    public AutoResell() {
        super("AutoResell", "Auction resell checklist and price preset manager.", ModuleCategory.MISC);
        number("Margin", 12.5, 0.0, 100.0);
        bool("Confirm Listing", true);
        status("Configured");
    }
}
